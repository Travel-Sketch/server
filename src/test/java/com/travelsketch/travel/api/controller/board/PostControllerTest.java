package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.ControllerTestSupport;
import com.travelsketch.travel.api.controller.board.request.CreatePostRequest;
import com.travelsketch.travel.api.controller.board.request.ModifyPostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/posts";

    @DisplayName("제목, 내용을 입력 받아서 게시물을 등록한다.")
    @Test
    void createPost() throws Exception {
        //given
        CreatePostRequest request = CreatePostRequest.builder()
            .title("게시물 제목")
            .content("게시물 내용")
            .build();

        //when //then
        mockMvc.perform(
                multipart(BASE_URL)
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
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
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .file(image1)
                    .file(image2)
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value("201"));
    }

    @DisplayName("입력 받은 제목이 빈 문자열이면 예외가 발생한다.")
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
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("제목은 필수입니다."));
    }

    @DisplayName("입력 받은 제목의 길이가 50자를 초과하면 예외가 발생한다.")
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
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("제목은 최대 50자입니다."));
    }

    @DisplayName("입력 받은 내용이 빈 문자열이면 예외가 발생한다.")
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
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("내용은 필수입니다."));
    }

    @DisplayName("첨부 파일의 업로드 파일명 길이가 100자를 초과하면 예외가 발생한다.")
    @Test
    void uploadWithExceededUploadFileNameLength() throws Exception {
        //given
        CreatePostRequest request = CreatePostRequest.builder()
            .title("게시물 제목")
            .content("게시물 내용")
            .build();

        MockMultipartFile image1 = new MockMultipartFile(
            "files", //name
            getText(101), //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );

        //when //then
        mockMvc.perform(
                multipart(BASE_URL)
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .file(image1)
                    .with(csrf())
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("업로드 파일명 길이가 허용된 최대 크기를 초과했습니다."));
    }

    @DisplayName("제목, 내용, 첨부파일을 입력 받아서 게시물을 수정한다.")
    @Test
    void modifyPostWithFiles() throws Exception {
        //given
        ModifyPostRequest request = ModifyPostRequest.builder()
            .title("게시물 제목")
            .content("게시물 내용")
            .build();

        MockMultipartFile image1 = new MockMultipartFile(
            "newFiles", //name
            "abc1.png", //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );

        //when //then
        mockMvc.perform(
                multipart(BASE_URL + "/{postId}", 1)
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .file(image1)
                    .with(csrf())
                    .with(req -> {
                        req.setMethod("PATCH");
                        return req;
                    })
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("OK"));
    }

    @DisplayName("게시물 수정 시 입력 받은 제목이 빈 문자열이면 예외가 발생한다.")
    @Test
    void modifyPostWithoutTitle() throws Exception {
        //given
        ModifyPostRequest request = ModifyPostRequest.builder()
            .title(" ")
            .content("게시물 내용")
            .build();

        //when //then
        mockMvc.perform(
                multipart(BASE_URL + "/{postId}", 1)
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .with(csrf())
                    .with(req -> {
                        req.setMethod("PATCH");
                        return req;
                    })
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("제목은 필수입니다."));
    }

    @DisplayName("게시물 수정 시 입력 받은 제목의 길이가 50자를 초과하면 예외가 발생한다.")
    @Test
    void modifyPostOutOfMaxSizeTitle() throws Exception {
        //given
        ModifyPostRequest request = ModifyPostRequest.builder()
            .title(getText(51))
            .content("게시물 내용")
            .build();

        //when //then
        mockMvc.perform(
                multipart(BASE_URL + "/{postId}", 1)
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .with(csrf())
                    .with(req -> {
                        req.setMethod("PATCH");
                        return req;
                    })
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("제목은 최대 50자입니다."));
    }

    @DisplayName("게시물 수정 시 입력 받은 내용이 빈 문자열이면 예외가 발생한다.")
    @Test
    void modifyPostWithoutContent() throws Exception {
        //given
        ModifyPostRequest request = ModifyPostRequest.builder()
            .title("게시물 제목")
            .content(" ")
            .build();

        //when //then
        mockMvc.perform(
                multipart(BASE_URL + "/{postId}", 1)
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .with(csrf())
                    .with(req -> {
                        req.setMethod("PATCH");
                        return req;
                    })
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("내용은 필수입니다."));
    }


    @DisplayName("게시물 수정 시 첨부 파일의 업로드 파일명 길이가 100자를 초과하면 예외가 발생한다.")
    @Test
    void modifyWithExceededUploadFileNameLength() throws Exception {
        //given
        ModifyPostRequest request = ModifyPostRequest.builder()
            .title("게시물 제목")
            .content("게시물 내용")
            .build();

        MockMultipartFile image1 = new MockMultipartFile(
            "newFiles", //name
            getText(101), //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );

        //when //then
        mockMvc.perform(
                multipart(BASE_URL + "/{postId}", 1)
                    .part(new MockPart("title", request.getTitle().getBytes()))
                    .part(new MockPart("content", request.getContent().getBytes()))
                    .file(image1)
                    .with(csrf())
                    .with(req -> {
                        req.setMethod("PATCH");
                        return req;
                    })
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("업로드 파일명 길이가 허용된 최대 크기를 초과했습니다."));
    }

    private String getText(int size) {
        return "a".repeat(Math.max(0, size));
    }

}