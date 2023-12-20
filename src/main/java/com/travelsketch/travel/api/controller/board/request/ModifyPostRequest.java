package com.travelsketch.travel.api.controller.board.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class ModifyPostRequest {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 50, message = "제목은 최대 50자입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    private List<MultipartFile> newFiles;

    private List<Long> deletedFileIds;

    @Builder
    private ModifyPostRequest(String title, String content, List<MultipartFile> newFiles, List<Long> deletedFileIds) {
        this.title = title;
        this.content = content;
        this.newFiles = newFiles;
        this.deletedFileIds = deletedFileIds;
    }

}
