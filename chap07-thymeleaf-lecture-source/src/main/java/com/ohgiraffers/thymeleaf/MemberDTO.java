package com.ohgiraffers.thymeleaf;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String name;
    private int age;
    private char gender;
    private String address;
}
