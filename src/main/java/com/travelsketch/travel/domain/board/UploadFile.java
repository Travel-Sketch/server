package com.travelsketch.travel.domain.board;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadFile {

    @Column(nullable = false, updatable = false)
    @Size(max = 100, message = "파일명은 최대 100자입니다.")
    private String uploadFileName;

    @Column(nullable = false, updatable = false)
    private String storeFileName;

    @Builder
    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}