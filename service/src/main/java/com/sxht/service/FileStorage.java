package com.sxht.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by LJ on 2017/3/24.
 */
public interface FileStorage {
    String save(MultipartFile file, String dirPath) throws IOException;
}
