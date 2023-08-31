package com.nemo.server.domain.category;

import com.nemo.server.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;
    private String colorCode;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Category(Long id, String name, String colorCode, Member member) {
        this.id = id;
        this.name = name;
        this.colorCode = colorCode;
        this.member = member;
    }

    public void edit(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }
}
