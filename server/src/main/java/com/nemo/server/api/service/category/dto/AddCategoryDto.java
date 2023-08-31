package com.nemo.server.api.service.category.dto;

import com.nemo.server.domain.category.Category;
import com.nemo.server.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class AddCategoryDto {

    private String name;
    private String colorCode;

    @Builder
    private AddCategoryDto(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public Category toEntity(Member member) {
        return Category.builder()
                .name(this.name)
                .colorCode(this.colorCode)
                .member(member)
                .build();
    }
}
