package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
import com.travelsketch.travel.domain.board.AttachedFile;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.PostCategory;
import com.travelsketch.travel.domain.board.UploadFile;
import com.travelsketch.travel.domain.board.repository.AttachedFileRepository;
import com.travelsketch.travel.domain.board.repository.PostRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final FileStore fileStore;
    private final AttachedFileRepository attachedFileRepository;

    public CreatePostResponse createPost(String email, String title, String content, List<MultipartFile> files) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        Member member = findMember.get();

        Post post = Post.builder()
            .title(title)
            .content(content)
            .category(PostCategory.FREE)
            .member(member)
            .scrapCount(0)
            .commentCount(0)
            .build();

        Post savedPost = postRepository.save(post);

        try {
            List<UploadFile> uploadFiles = fileStore.storeFiles(files);
            for (UploadFile uploadFile : uploadFiles) {
                AttachedFile attachedFile = AttachedFile.builder()
                    .post(savedPost)
                    .uploadFile(uploadFile)
                    .build();
                attachedFileRepository.save(attachedFile);
            }
            return CreatePostResponse.of(savedPost, uploadFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
