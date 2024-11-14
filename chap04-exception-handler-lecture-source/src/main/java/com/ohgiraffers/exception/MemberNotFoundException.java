package com.ohgiraffers.exception;

// 사용자 정의 임의의 클래스 생성 (not found)
public class MemberNotFoundException extends Exception { // Exception 상속

    public MemberNotFoundException(String s) { // 필드 하나 초기화 할 생성자

                super(s); // 부모인 Exceptio 호출 하면서, s 전달
    }
}
