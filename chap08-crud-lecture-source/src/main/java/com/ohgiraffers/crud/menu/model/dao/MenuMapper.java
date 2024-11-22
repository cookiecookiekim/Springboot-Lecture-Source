package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// ⑧ DAO 계층의 MenuMapper 클래스 생성

/* comment. @Mapper
*   Mybatis의 전용 어노테이션으로 Repository의 더 구체적인 기능을 가진 어노테이션
*   → XML 파일을 구체적으로 읽을 수 있는 기능 */
@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllmenus(); // 쿼리문 매핑 ID
    // → 쿼리문 작성하러 ⑨ resources / mappers / menuMapper.xml

    List<CategoryDTO> findAllCategory();

    void registNewMapper(MenuDTO newMenu);

    List<MenuDTO> selectMenuCodeList(int selectCode); // 메뉴 코드로 조회하기

    List<MenuDTO> clickMenuSelect(int code);

    List<MenuAndCategoryDTO> findAllMenuAndCategory();
}
