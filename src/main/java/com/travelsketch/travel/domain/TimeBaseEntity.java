package com.travelsketch.travel.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

/**
 * 생성일, 최종 수정일 공통 관리 클래스
 *
 * @author 임우택
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@NoArgsConstructor(access = PROTECTED)
public class TimeBaseEntity {

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "datetime default now()")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "datetime default now()")
    private LocalDateTime lastModifiedDate;

    public void remove() {
        this.isDeleted = true;
    }
}

