package com.ohgiraffers.crud.menu.controller;

import ch.qos.logback.core.model.Model;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// ⑤ menu의 관한 요청을 핸들링할 메서드 생성
@Controller
@RequestMapping("/menu/*")
public class MenuController {

    // ⑧ MenuService의 메서드를 사용하기 위해 관계를 형성해야 함.
    private final MenuService menuService; // 생성자 주입으로 형성

    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("list")
    public String findMenuList (Model model){ // String 설정

        // 전체 메뉴 조회는 MenuDTO 타입이 여러개이기 때문에 List
        List<MenuDTO> menuList = menuService.findAllMenus(); // ⑦ MenuService 클래스 생성
        // ⑫ 전체 조회 결과를 menuList에 담음

        // DB 조회 값이 잘 들어있는지 확인용
        for (MenuDTO menu : menuList) {
            System.out.println("menu = " + menu);
        }

        return "menu/list"; // view page 작성
    }
}
