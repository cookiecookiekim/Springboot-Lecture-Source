package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /* comment. @Transactional
    *   스프링 프레임워크에서 제공하는 트렌젝션 관리 어노테이션으로
    *   데이터베이스의 상태를 변화시키는 작업(DML)을 하나의 단위로 묶는 것.
    *   따라서 데이터베이스 조작에 관련된 일이 일어날 때 메서드의 실행이
    *   정상적으로 완료되면 commit, 예외가 발생하면 rollback을 수행하여
    *   데이터의 일관성을 유지하는 데 사용
    *   → 내부적으로 AOP기능 사용*/
    @Transactional
    public void regirstMenu(MenuDTO newMenu) {
        menuMapper.registNewMapper(newMenu);
    }
}
