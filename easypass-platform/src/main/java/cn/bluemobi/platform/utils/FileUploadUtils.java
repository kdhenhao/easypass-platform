package cn.bluemobi.platform.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.utils.PlatformConstants;

public class FileUploadUtils {
    public static MapDto uploadImg(String dir, MultipartFile file) throws IllegalStateException, IOException {
        MapDto data = new MapDto();
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename().toLowerCase();
            String type = fileName.substring(fileName.lastIndexOf("."));
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
}
