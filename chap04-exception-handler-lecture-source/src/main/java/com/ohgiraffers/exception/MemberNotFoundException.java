package com.ohgiraffers.exception;

public class MemberNotFoundException extends Exception { // Exception 상속

    public MemberNotFoundException(String s) { // 필드 하나 초기화 할 생성자

                super(s); // 부모인 Exceptio 호출 하면서, s 전달
    }
}
