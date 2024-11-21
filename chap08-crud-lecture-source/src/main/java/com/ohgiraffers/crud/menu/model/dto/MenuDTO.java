package com.ohgiraffers.crud.menu.model.dto;
// ⑥ DB의 데이터를 자바 언어로 받아주기 위해 MenuDTO 클래스 생성

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuDTO {

    /* comment.
    *   DTO는 DataBase 컬럼과 일치하게 작성하거나
    *   화면상에서 request 하는 데이터를 작성한다. */

    // db 컬럼과 맞춰서 작성
    private int code;
    private String name;
    private int price;
    private int categoryCode;
    private String OrderableStatus;
}
