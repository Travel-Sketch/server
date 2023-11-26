package com.travelsketch.travel.domain;

import com.travelsketch.travel.domain.member.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@MappedSuperclass
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BaseEntity extends TimeBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    private Member createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_modified_by", nullable = false)
    private Member lastModifiedBy;

    protected BaseEntity(Member createdBy) {
        this.createdBy = createdBy;
    }
}
