/**
 * Project Name:banma
 * File Name:FileUploadController.java
 * Package Name:cn.bluemobi.banma.controller.backend
 * Date:2015年12月25日下午4:15:16
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.controller.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.utils.DateUtils;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.utils.PlatformConstants;
import cn.bluemobi.platform.utils.JsonUtils;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

/**
 * 
 * Description: 文件上传<br/>
 * date: 2017年5月23日 下午8:30:59 <br/>
 *
 * @author oscarwang
 * @version
 */
@Controller
@RequestMapping("/admin/upload")
public class FileUploadController extends PlatformBaseController {

    @RequestMapping("/uploadImg")
    @ResponseBody
    public MapDto uploadImg(@RequestParam("dir") String dir, @RequestParam("file") MultipartFile file,
            HttpServletRequest request) throws IllegalStateException, IOException {
        MapDto data = new MapDto();
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename().toLowerCase();
            String type = fileName.substring(fileName.lastIndexOf("."));
            /*
             * if (!imgs.contains(type)) { data.errorMsg("不支持该格式的图片"); return
             * data; } if (file.getSize() > PlatformConstants.IMAGE_SIZE_LIMIT)
             * { data.errorMsg("图片太大不能上传，最多只能" +
             * PlatformConstants.IMAGE_SIZE_LIMIT / 1024 + "k"); return data; }
             */
            String imgPath = dir + File.separator + UUID.randomUUID() + type;

            StringBuilder newFileName = new StringBuilder(PlatformConstants.IMG_DISK_PATH);
            newFileName.append(File.separator).append(imgPath);

            File newFile = new File(newFileName.toString());
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            file.transferTo(newFile);

            // 如果上传的是视频
            if (type.equals(".3gp") || type.equals(".mp4") || type.equals(".avi")) {
                DecimalFormat df = new DecimalFormat("#.00");
                long fileS = file.getSize();
                String sizeStr = "";
                if (fileS < 1024) {
                    sizeStr = df.format((double) fileS) + "B";
                } else if (fileS < 1048576) {
                    sizeStr = df.format((double) fileS / 1024) + "KB";
                } else if (fileS < 1073741824) {
                    sizeStr = df.format((double) fileS / 1048576) + "MB";
                } else {
                    sizeStr = df.format((double) fileS / 1073741824) + "GB";
                }
                Encoder encoder = new Encoder();
                String lengthStr = "";
                MultimediaInfo m = null;
                try {
                    m = encoder.getInfo(newFile);
                    long ls = m.getDuration();// 毫秒单位
                    lengthStr = ls / 60000 + ":" + (ls % 60000) / 1000;
                } catch (EncoderException e) {
                    e.printStackTrace();
                }
                data.putInData("size", sizeStr);
                data.putInData("length", lengthStr);
            }

            data.putInData("src", PlatformConstants.IMG_CONTEXT_PATH + "/" + imgPath.replace('\\', '/'));
            data.putInData("img", imgPath.replace('\\', '/'));
        }
        return data;
    }

    private static Set<String> imgs = new HashSet<String>();

    static {
        imgs.addAll(Arrays.asList(PlatformConstants.IMAGE_FORMAT_ARRAY));
    }

    @RequestMapping("/uploadAndroid")
    @ResponseBody
    public MapDto uploadAndroid(@RequestParam("dir") String dir, @RequestParam("file") MultipartFile file,
            HttpServletRequest request) throws IllegalStateException, IOException {
        MapDto data = new MapDto();
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename().toLowerCase();
            String type = fileName.substring(fileName.lastIndexOf("."));
            if (!StringUtils.equals(type, ".apk")) {
                data.errorMsg("支持支apk格式的安卓程序");
                return data;
            }
            String imgPath = dir + File.separator + UUID.randomUUID() + type;
            StringBuilder newFileName = new StringBuilder(PlatformConstants.IMG_DISK_PATH);
            newFileName.append(File.separator).append(imgPath);

            File newFile = new File(newFileName.toString());
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            file.transferTo(newFile);
            data.putInData("src", PlatformConstants.IMG_CONTEXT_PATH + "/" + imgPath.replace('\\', '/'));
            data.putInData("img", imgPath.replace('\\', '/'));
        }
        return data;
    }

    /**
     * 富文本编辑器上传图片
     */
    @RequestMapping("/uploadManager")
    public void uploadManager(HttpServletRequest request, HttpServletResponse response)
            throws FileUploadException, IOException {
        // 文件保存目录路径
        // String savePath =
        // request.getServletContext().getRealPath("/resources/upload");
        String savePath = PlatformConstants.IMG_DISK_PATH;
        // 文件保存目录URL
        // String saveUrl = request.getScheme() + "://" +
        // request.getServerName() + ":" + request.getServerPort()
        // + request.getContextPath() + "/resources/upload";
        String saveUrl = PlatformConstants.IMG_CONTEXT_PATH;
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        // 最大文件大小
        long maxSize = 1000000;
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println(getError("请选择文件。"));
            return;
        }
        // 检查目录
        File uploadDir = new File(savePath);
        if (!uploadDir.isDirectory()) {
            uploadDir.mkdirs();
        }
        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            out.println(getError("上传目录没有写权限。"));
            return;
        }
        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            out.println(getError("目录名不正确。"));
            return;
        }
        // 创建文件夹
        savePath = savePath + "/" + dirName;
        saveUrl = saveUrl + "/" + dirName;
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        String ymd = DateUtils.format(new Date(), "yyyyMMdd");
        savePath = savePath + "/" + ymd;
        saveUrl = saveUrl + "/" + ymd;
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (!StringUtils.equals(myFileName.trim(), "")) {
                        // 检查文件大小
                        if (file.getSize() > maxSize) {
                            out.println(getError("上传文件大小超过限制。"));
                            return;
                        }
                        // 检查扩展名
                        String fileExt = myFileName.substring(myFileName.lastIndexOf(".") + 1).toLowerCase();
                        if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                            out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                            return;
                        }

                        String newFileName = DateUtils.format(new Date(), "yyyyMMddHHmmss") + "_"
                                + new Random().nextInt(1000) + "." + fileExt;
                        try {
                            File uploadedFile = new File(savePath, newFileName);
                            file.transferTo(uploadedFile);
                        } catch (Exception e) {
                            out.println(getError("上传文件失败。"));
                            return;
                        }
                        Map<String, Object> obj = new HashMap<String, Object>();
                        obj.put("error", 0);
                        obj.put("url", saveUrl + "/" + newFileName);
                        out.println(JsonUtils.toJson(obj));
                    }
                }
            }
        }
    }

    /**
     * 富文本编辑器 管理文件
     */
    @RequestMapping("/fileManager")
    public void fileManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 文件保存目录路径
        // String rootPath =
        // request.getServletContext().getRealPath("/resources/upload/");
        String rootPath = PlatformConstants.IMG_DISK_PATH + "/";
        // 文件保存目录URL
        // String rootUrl = request.getScheme() + "://" +
        // request.getServerName() + ":" + request.getServerPort()
        // + request.getContextPath() + "/resources/upload/";
        String rootUrl = PlatformConstants.IMG_CONTEXT_PATH + "/";
        // 图片扩展名
        String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String dirName = request.getParameter("dir");
        if (dirName != null) {
            if (!Arrays.<String> asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
                out.println("目录不合法");
                return;
            }
            rootPath += dirName + "/";
            rootUrl += dirName + "/";
            File saveDirFile = new File(rootPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
        }
        // 根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }
        // 排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
        // 不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            out.println("Access is not allowed.");
            return;
        }
        // 最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            out.println("Parameter is not valid.");
            return;
        }
        // 目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if (!currentPathFile.isDirectory()) {
            out.println("Directory does not exist.");
            return;
        }
        // 遍历目录取的文件信息
        List<Hashtable<String, Object>> fileList = new ArrayList<Hashtable<String, Object>>();
        if (currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String> asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }
        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);
        out.println(JsonUtils.toJson(result));
    }

    private String getError(String message) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("error", 1);
        obj.put("message", message);
        return JsonUtils.toJson(obj);
    }

    private static class NameComparator implements Comparator<Hashtable<String, Object>> {
        public int compare(Hashtable<String, Object> hashA, Hashtable<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
            }
        }
    }

    private static class SizeComparator implements Comparator<Hashtable<String, Object>> {
        public int compare(Hashtable<String, Object> hashA, Hashtable<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
                    return 1;
                } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    private static class TypeComparator implements Comparator<Hashtable<String, Object>> {
        public int compare(Hashtable<String, Object> hashA, Hashtable<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
            }
        }
    }
}
