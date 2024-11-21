package com.ohgiraffers.crud.menu.model.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("category") // 마이바티스 제공 알리아스 설정
public class CategoryDTO {

    private int code;
    private String name;
    private Integer refCategoryCode;

}
