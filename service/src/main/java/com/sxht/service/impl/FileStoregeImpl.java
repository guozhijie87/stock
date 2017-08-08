package com.sxht.service.impl;

import com.sxht.service.FileStorage;
import com.sxht.util.IDUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lmm on 2016/12/22.
 */
@Service
public class FileStoregeImpl implements FileStorage {
    @Override
    public String save(MultipartFile file, String dirPath) throws IOException {
        File dir = new File(dirPath);

        if (!(dir.exists() && dir.isDirectory())) {
            dir.mkdirs();
        }

        String name = file.getOriginalFilename();
        String[] list = name.split("\\.");
        String extension = list[list.length - 1];

        String fileName = String.valueOf(IDUtils.getInstance().getId())
                + "."
                + extension;
        String path = dirPath
                + File.separator
                + fileName;

        FileOutputStream stream = new FileOutputStream(path);

        stream.write(file.getBytes());
        stream.close();

        return fileName;
    }
}
