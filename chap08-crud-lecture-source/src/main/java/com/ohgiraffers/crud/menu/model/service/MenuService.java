package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ⑦ Service 클래스 생성
@Service
public class MenuService {

    private final MenuMapper menuMapper; // ⑧ DAO 의존성 주입 추가

    @Autowired
    public MenuService (MenuMapper menuMapper){ // 생성자 주입으로 의존성 추가
        this.menuMapper = menuMapper;
    }

    public List<MenuDTO> findAllMenus() {

        return menuMapper.findAllmenus(); // DAO 계층의 menuMapper 한 번 더 호출
    }

    public List<CategoryDTO> findAllCategory() {
        return menuMapper.findAllCategory();
    }
}
