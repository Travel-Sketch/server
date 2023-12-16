package com.travelsketch.travel.api.controller.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class ModifyPostRequest {

    private String title;
    private String content;

    @Builder
    private ModifyPostRequest(String title, String content, List<MultipartFile> files, List<Long> deletedFileIds) {
        this.title = title;
        this.content = content;
    }

}
