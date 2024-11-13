package com.ohgiraffers.handlermethod;

/* DTO 기본 조건
* 기본 생성자 , 모든 필드 초기화 생성자
*   Getter , Setter , ToString */

/* comment.
*   form 태그의 name 속성과 필드 이름(변수명) 맞춰줘야 함!!!!!! */

// ★👀3. @ModelAttribute를 이용한 파라미터 전달 받기 - DTO 👀★
public class MenuDTO {

    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;

    public MenuDTO(){ // 기본 생성자
    }

    public MenuDTO(String name, int price, int categoryCode, String orderableStatus) {
        this.name = name;
        this.price = price;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
