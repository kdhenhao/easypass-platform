/**
 * Project Name:lmExpress-web
 * File Name:Test.java
 * Package Name:cn.bluemobi.autocode
 * Date:2016年11月29日下午1:46:10
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import cn.bluemobi.platform.core.utils.PropertiesUtils;
import freemarker.template.TemplateException;

/**
 * Description: mapper、service自动生成小工具。 <br/>
 * 使用方法，修改main方法中的表名，运行main方法。 注意，每次生成都会覆盖上次生成内容。 拓展。可以自行修改mybatis generator
 * plugin，可以自己增加freemarker模版 Date: 2016年11月20日 下午1:46:10 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class AutocodeUtils {
    public static void main(String[] args) {
        String tableName = "order_main2";// 数据库表名，
        String entityPackage = "order";// 可以为空，但entity直接生成在entity包下
        String projectPath = System.getProperty("user.dir");// 项目路径
        String packagePath = AutocodeUtils.class.getPackage().getName();// 项目包结构
        try {
            // 生成实体,xml，mapper接口
            generateMapper(projectPath, packagePath, tableName, entityPackage);
            // 生成service
            generateService(projectPath, packagePath, tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateService(String projectPath, String packagePath, String tableName)
            throws IOException, TemplateException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
        cfg.setDirectoryForTemplateLoading(new File(projectPath + "/src/test/resources/autocode"));
        cfg.setObjectWrapper(new freemarker.template.DefaultObjectWrapper());
        // 绑定数据
        Map<String, String> root = new HashMap<String, String>();
        root.put("package", packagePath);
        root.put("interface", rename(tableName) + "Service");
        root.put("class", rename(tableName) + "ServiceImpl");
        root.put("MapperInterface", rename(tableName) + "Mapper");
        root.put("MapperInterface2", lowercaseFirstChar(rename(tableName)) + "Mapper");
        // 生成service接口
        freemarker.template.Template service = cfg.getTemplate("service.ftl");
        Writer out = new OutputStreamWriter(new FileOutputStream(projectPath + "/src/main/java/"
                + packageToPath(packagePath) + "/service/" + root.get("interface") + ".java"));
        service.process(root, out);
        // 生成service接口实现类
        freemarker.template.Template serviceImpl = cfg.getTemplate("serviceImpl.ftl");
        Writer out2 = new OutputStreamWriter(new FileOutputStream(projectPath + "/src/main/java/"
                + packageToPath(packagePath) + "/service/impl/" + root.get("class") + ".java"));
        serviceImpl.process(root, out2);
        out.flush();
    }

    /**
     * 生成xml mapper entity
     * 
     * @param entityPackage 实体类所在包
     */
    private static void generateMapper(String projectPath, String packagePath, String tableName, String entityPackage)
            throws SQLException, IOException, InterruptedException, XMLParserException, InvalidConfigurationException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream configFile = AutocodeUtils.class.getResourceAsStream("/autocode/MybatisGeneratorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        //
        Context context = config.getContext("context");
        PluginConfiguration myPlugin = new PluginConfiguration();
        myPlugin.setConfigurationType("cn.bluemobi.platform.MyGeneratorPlugin");
        context.addPluginConfiguration(myPlugin);
        // 配置jdbc
        JDBCConnectionConfiguration jdbc = context.getJdbcConnectionConfiguration();
        jdbc.setDriverClass(PropertiesUtils.getProperty("jdbc.driver", "/conf/jdbc.properties"));
        jdbc.setConnectionURL(PropertiesUtils.getProperty("jdbc.url", "/conf/jdbc.properties"));
        jdbc.setUserId(PropertiesUtils.getProperty("jdbc.user", "/conf/jdbc.properties"));
        jdbc.setPassword(PropertiesUtils.getProperty("jdbc.password", "/conf/jdbc.properties"));
        // 配置项目路径，包名
        JavaModelGeneratorConfiguration entity = context.getJavaModelGeneratorConfiguration();
        entity.setTargetProject(projectPath + "/src/main/java");
        entity.setTargetPackage(packagePath + ".entity");
        // 配置mapper.xml生成路径
        SqlMapGeneratorConfiguration xml = context.getSqlMapGeneratorConfiguration();
        xml.setTargetProject(projectPath + "/src/main/resources");
        xml.setTargetPackage(packagePath + ".mapper");
        // 配置mapper接口生成路径
        JavaClientGeneratorConfiguration mapper = context.getJavaClientGeneratorConfiguration();
        mapper.setTargetProject(projectPath + "/src/main/java");
        mapper.setTargetPackage(packagePath + ".mapper");
        // 添加表
        List<TableConfiguration> tables = context.getTableConfigurations();
        tables.clear();
        TableConfiguration table = new TableConfiguration(context);
        table.setTableName(tableName);
        if (StringUtils.isNotEmpty(entityPackage)) {
            table.setDomainObjectName(entityPackage + "." + rename(tableName));
        } else {
            table.setDomainObjectName(rename(tableName));
        }
        table.setSelectByExampleStatementEnabled(false);
        table.setCountByExampleStatementEnabled(false);
        table.setUpdateByExampleStatementEnabled(false);
        table.setDeleteByExampleStatementEnabled(false);
        tables.add(table);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    /**
     * 去掉下划线，首字母大写
     */
    private static String rename(String tableName) {
        String[] infos = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String info : infos) {
            sb.append(upcaseFirstChar(info));
        }
        return sb.toString();
    }

    /**
     * 首字母大写
     */
    private static String upcaseFirstChar(String info) {
        return info.substring(0, 1).toUpperCase() + info.substring(1, info.length());
    }

    /**
     * 首字母小写
     */
    private static String lowercaseFirstChar(String info) {
        return info.substring(0, 1).toLowerCase() + info.substring(1, info.length());
    }

    /**
     * 包路径变成文件路径
     */
    private static String packageToPath(String packagePath) {
        String s = new String(packagePath);
        s = s.replaceAll("\\.", "/");
        return s;
    }

}
