package com.travelsketch.travel.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attached_file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Embedded
    private UploadFile uploadFile;

    @Builder
    private AttachedFile(Long id, Post post, UploadFile uploadFile) {
        this.id = id;
        this.post = post;
        this.uploadFile = uploadFile;
    }
}