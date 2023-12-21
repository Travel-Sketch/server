package com.travelsketch.travel.api.controller.board;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract class PostCustomValid {

    public static void checkFileNameLength(List<MultipartFile> files) {
        if (files != null) {
            for (MultipartFile file : files) {
                if (file.getOriginalFilename() == null) continue;
                if (file.getOriginalFilename().length() > 100) {
                    throw new IllegalArgumentException("업로드 파일명 길이가 허용된 최대 크기를 초과했습니다.");
                }
            }
        }
    }

}
