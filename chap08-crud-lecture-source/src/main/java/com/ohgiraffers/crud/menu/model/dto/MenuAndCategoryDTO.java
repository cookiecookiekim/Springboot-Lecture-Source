package com.ohgiraffers.crud.menu.model.dto;

import lombok.*;

// 24-11-22 (금) Join 조회를 위한 DTO
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuAndCategoryDTO { // 메뉴를 기준으로 카테고리 조회 (1:M 이므로)

    // 메뉴 기준으로 필드 작성
    private int code;
    private String name;
    private int price;
    private String orderableStatus;
    private CategoryDTO categoryDTO; // 상위인 CategoryDTO를 가져올 수 있도록 설정
}
