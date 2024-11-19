package com.ohgiraffers.thymeleaf;

// 24-11-19 (화) 5교시 (검색 조건 정보 관련 클래스 생성)

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchCriteria {

    private int startPage; // 시작 페이지 번호
    private int endPage; // 마지막 페이지 번호
    private int currentPage; //  현재 페이지 번호

}
