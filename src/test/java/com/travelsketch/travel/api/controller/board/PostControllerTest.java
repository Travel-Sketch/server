package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.ControllerTestSupport;
import com.travelsketch.travel.api.controller.board.request.CreatePostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/posts";

    @DisplayName("게시물을 등록할 수 있다")
    @Test
    void createPost() throws Exception {
        //given
        CreatePostRequest request = CreatePostRequest.builder()
            .title("게시물 제목")
            .content("게시물 내용")
            .build();

        MockMultipartFile multipartRequest = new MockMultipartFile(
            "request",
            "",
            MediaType.APPLICATION_JSON_VALUE,
            objectMapper.writeValueAsString(request).getBytes()
        );

        //when //then
        mockMvc.perform(
                multipart(BASE_URL)
                    .file(multipartRequest)
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value("201"));
    }

    @DisplayName("게시물 등록시 다수의 파일을 첨부할 수 있다.")
    @Test
    void createPostWithFiles() throws Exception {
        //given
        CreatePostRequest request = CreatePostRequest.builder()
            .title("게시물 제목")
            .content("게시물 내용")
            .build();

        MockMultipartFile multipartRequest = new MockMultipartFile(
            "request",
            "",
            MediaType.APPLICATION_JSON_VALUE,
            objectMapper.writeValueAsString(request).getBytes()
        );
        MockMultipartFile image1 = new MockMultipartFile(
            "files", //name
            "abc1.png", //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );
        MockMultipartFile image2 = new MockMultipartFile(
            "files", //name
            "abc2.png", //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );

        //when //then
        mockMvc.perform(
                multipart(BASE_URL)
                    .file(multipartRequest)
                    .file(image1)
                    .file(image2)
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value("201"));
    }

    @DisplayName("게시물 등록시 제목은 필수값이다.")
    @Test
    void createPostWithoutTitle() throws Exception {
        //given
        CreatePostRequest request = CreatePostRequest.builder()
            .title(" ")
            .content("게시물 내용")
            .build();

        //when //then
        mockMvc.perform(
                multipart(BASE_URL)
                    .file(new MockMultipartFile(
                        "request",
                        "",
                        MediaType.APPLICATION_JSON_VALUE,
                        objectMapper.writeValueAsString(request).getBytes()
                    ))
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("제목은 필수입니다."));
    }

    @DisplayName("게시물 등록시 제목은 최대 50자이다.")
    @Test
    void createPostOutOfMaxSizeTitle() throws Exception {
        //given
        CreatePostRequest request = CreatePostRequest.builder()
            .title(getText(51))
            .content("게시물 내용")
            .build();

        //when //then
        mockMvc.perform(
                multipart(BASE_URL)
                    .file(new MockMultipartFile(
                        "request",
                        "",
                        MediaType.APPLICATION_JSON_VALUE,
                        objectMapper.writeValueAsString(request).getBytes()
                    ))
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("제목은 최대 50자입니다."));
    }

    @DisplayName("게시물 등록시 내용은 필수값이다.")
    @Test
    void createPostWithoutContent() throws Exception {
        //given
        CreatePostRequest request = CreatePostRequest.builder()
            .title("게시물 제목")
            .content(" ")
            .build();

        //when //then
        mockMvc.perform(
                multipart(BASE_URL)
                    .file(new MockMultipartFile(
                        "request",
                        "",
                        MediaType.APPLICATION_JSON_VALUE,
                        objectMapper.writeValueAsString(request).getBytes()
                    ))
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("내용은 필수입니다."));
    }

    private String getText(int size) {
        return "a".repeat(Math.max(0, size));
    }

}