/**
 * Project Name:lmExpress-web
 * File Name:MyGeneratorPlugin.java
 * Package Name:cn.bluemobi.web
 * Date:2016年11月29日下午4:22:29
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * Description: <br/>
 * Date: 2016年11月29日 下午4:22:29 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class MyGeneratorPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 更新的时候不更新create_by create_tm两个字段
     */
    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        List<Element> children = element.getElements();
        Element child = children.get(1);
        XmlElement set = (XmlElement) child;
        List<Element> ifs = set.getElements();
        Iterator<Element> ite = ifs.iterator();
        boolean hasModifyTm = false;
        while (ite.hasNext()) {
            XmlElement ifz = (XmlElement) ite.next();
            if (ifz.getFormattedContent(0).contains("create_by") || ifz.getFormattedContent(0).contains("create_tm")) {
                // 是createby或者createTm
                ite.remove();
            } else if (ifz.getFormattedContent(0).contains("modify_tm")) {
                ite.remove();
                hasModifyTm = true;
            }
        }
        if (hasModifyTm) {
            XmlElement ifz = new XmlElement("if");
            ifz.addAttribute(new Attribute("test", "1 = 1"));
            ifz.addElement(new TextElement("modify_tm = now(),"));
            ifs.add(ifz);
        }
        return true;
    }

    /**
     * 更新的时候不更新create_by create_tm两个字段
     */
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        ArrayList<Element> children = (ArrayList<Element>) element.getElements();
        Iterator<Element> ite = children.iterator();
        while (ite.hasNext()) {
            TextElement el = (TextElement) ite.next();
            if (el.getContent().contains("create_by")) {
                ite.remove();
            } else if (el.getContent().contains("create_tm")) {
                ite.remove();
            }
        }
        // 替换modifyTm
        for (int i = 1; i < children.size(); i++) {
            TextElement el = (TextElement) children.get(i);
            if (el.getContent().contains("modify_tm")) {
                children.set(i, new TextElement(el.getContent().replace("#{modifyTm,jdbcType=TIMESTAMP}", "now()")));
            }
        }
        // 检查逗号
        for (int i = 1; i < children.size() - 1; i++) {
            TextElement el = (TextElement) children.get(i);
            if (i != children.size() - 2) {
                if (!el.getContent().endsWith(",")) {
                    // 补上，
                    children.set(i, new TextElement(el.getContent() + ","));
                }
            } else {
                if (el.getContent().endsWith(",")) {
                    // 补上，
                    String str = el.getContent();
                    children.set(i, new TextElement(str.substring(0, str.length() - 1)));
                }
            }
        }
        return true;
    }

    /**
     * 插入的时候create_tm为now(),modify_tm
     */
    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        ArrayList<Element> children = (ArrayList<Element>) element.getElements();
        // 替换modifyTm
        for (int i = 1; i < children.size(); i++) {
            TextElement el = (TextElement) children.get(i);
            if (el.getContent().contains("modifyTm")) {
                children.set(i, new TextElement(el.getContent().replace("#{modifyTm,jdbcType=TIMESTAMP}", "now()")));
            }
            if (el.getContent().contains("createTm")) {
                children.set(i, new TextElement(el.getContent().replace("#{createTm,jdbcType=TIMESTAMP}", "now()")));
            }
        }
        return true;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        // 不生成insert selective
        return false;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        // 不生成insert selective
        return false;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        // 不生成insert selective
        return false;
    }

    /**
     * 生成完mapper接口，增加一个分页方法
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        Method m = new Method("selectForPage");
        m.setVisibility(JavaVisibility.PUBLIC);
        m.setReturnType(new FullyQualifiedJavaType("List<Map<String,Object>>"));
        m.addParameter(new Parameter(new FullyQualifiedJavaType("Map<String,Object>"), "params"));
        interfaze.addMethod(m);
        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Map"));
        return true;
    }

    /**
     * xml生成之后添加分页查询方法
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement selectForPage = new XmlElement("select");
        selectForPage.addAttribute(new Attribute("id", "selectForPage"));
        selectForPage.addAttribute(new Attribute("resultType", "Map"));
        String tableName = introspectedTable.getTableConfiguration().getTableName();
        String bf = getBrief(tableName);// 表名转别名

        StringBuilder sb = new StringBuilder("select \n");
        List<IntrospectedColumn> colums = introspectedTable.getAllColumns();
        for (IntrospectedColumn colum : colums) {
            if (isDateColumn(colum)) { // date
                sb.append("     date_format(" + bf + "." + colum.getActualColumnName() + ",'%Y-%m-%d %H:%i:%s') as "
                        + colum.getJavaProperty() + ",\n");
            } else {
                sb.append("     " + bf + "." + colum.getActualColumnName() + " as " + colum.getJavaProperty() + ",\n");
            }
        }
        sb.setLength(sb.length() - 2);// 截掉最后一个逗号
        sb.append("\n");
        sb.append("   from ").append(tableName).append(" ").append(bf).append("\n");
        sb.append("   where 1=1 \n");
        sb.append("   order by ").append(bf).append(".id");
        selectForPage.addElement(new TextElement(sb.toString()));
        document.getRootElement().addElement(selectForPage);
        return true;
    }

    private String getBrief(String name) {
        String[] infos = name.split("_");
        String str = "";
        for (String s : infos) {
            str = str + s.charAt(0);
        }
        return str;
    }

    private boolean isDateColumn(IntrospectedColumn colum) {
        boolean b1 = colum.getFullyQualifiedJavaType().equals(FullyQualifiedJavaType.getDateInstance())
                && "DATE".equalsIgnoreCase(colum.getJdbcTypeName()); //$NON-NLS-1$
        boolean b2 = colum.getFullyQualifiedJavaType().equals(FullyQualifiedJavaType.getDateInstance())
                && "TIME".equalsIgnoreCase(colum.getJdbcTypeName()); //$NON-NLS-1$
        boolean b3 = colum.getFullyQualifiedJavaType().equals(FullyQualifiedJavaType.getDateInstance())
                && "TIMESTAMP".equalsIgnoreCase(colum.getJdbcTypeName()); //$NON-NLS-1$
        return b1 || b2 || b3;
    }
}
