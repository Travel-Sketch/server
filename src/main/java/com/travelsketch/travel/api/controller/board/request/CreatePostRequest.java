package com.travelsketch.travel.api.controller.board.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class CreatePostRequest {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 50, message = "제목은 최대 50자입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    private List<MultipartFile> files;

    @Builder
    private CreatePostRequest(String title, String content, List<MultipartFile> files) {
        this.title = title;
        this.content = content;
        this.files = files;
    }

}
