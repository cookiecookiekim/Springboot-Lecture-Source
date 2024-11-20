package com.ohgiraffers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ② 컨트롤러 클래스
@Controller
@RequestMapping("/lecture/*") // lecture 하위 모두 매핑하겠다.
public class LectureController {

    @GetMapping("expression")
    public ModelAndView expression(ModelAndView mv) {

        // 한 명의 정보를 DTO 클래스를 이용하여 mv에 Key 와 Value로 값 집어 넣기
        mv.addObject("member", new MemberDTO("김규남", 20, '남', "서울시"));
        // member로만 출력을 하면 ToString으로 출력되기 때문에 사용자 입장에서 당황할 수 있음.
        // 오브젝트(member)가 가지고 있는 필드에 접근해서 가져올 수 있다.

        mv.addObject("hello", "hi~<h2>타임리프</h2>");

        mv.setViewName("lecture/expression"); // view에 대한 경로 지정 (앞에 templates와 뒤에 html 생략)

        return mv;
    }

    @GetMapping("conditional")
    public ModelAndView conditional(ModelAndView mv) {

        mv.addObject("num",1);
        mv.addObject("str","바나나");

        // th:each (여러개 담을 List 객체 생성)
        List<MemberDTO> memberList = new ArrayList<>();
        // 아래 추가한 list는 DB에서 SELECT 해왔다고 생각하기
        memberList.add(new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberList.add(new MemberDTO("시진핑", 5, '남', "베이징 사천구"));
        memberList.add(new MemberDTO("핑핑이", 7, '남', "강원도 춘천시"));
        memberList.add(new MemberDTO("티니핑", 2, '여', "강원도 원주시"));
        memberList.add(new MemberDTO("핑구", 3, '여', "경기도 광주시"));

        mv.addObject("memberList" , memberList); // 모델 객체에 담아주기

        mv.setViewName("lecture/conditional"); // 보여줄 화면 경로 지정

        return mv;
    }

    @GetMapping("etc")
    public ModelAndView etc(ModelAndView mv) {

        // 모든 필드를 초기화 하는 생성자로 값 초기화 진행
        SearchCriteria criteria = new SearchCriteria(1, 10, 3);

        // key, value 형식으로 저장 가능하지만, key 작성하지 않을 시
        // 인스턴스의 타입 =  클래스명이 곧 키 값이 된다.
        mv.addObject(criteria);

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberList.add(new MemberDTO("시진핑", 5, '남', "베이징 사천구"));
        memberList.add(new MemberDTO("핑핑이", 7, '남', "강원도 춘천시"));
        memberList.add(new MemberDTO("티니핑", 2, '여', "강원도 원주시"));
        memberList.add(new MemberDTO("핑구", 3, '여', "경기도 광주시"));

        mv.addObject("memberList" , memberList);

        // Map 형태로 전달해보기
        Map<String, MemberDTO> memberMap = new HashMap<>();
        memberMap.put("1", new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberMap.put("2", new MemberDTO("시진핑", 5, '남', "베이징 사천구"));
        memberMap.put("3", new MemberDTO("핑핑이", 7, '남', "강원도 춘천시"));
        memberMap.put("4", new MemberDTO("티니핑", 2, '여', "강원도 원주시"));
        memberMap.put("5", new MemberDTO("핑구", 3, '여', "경기도 광주시"));

        mv.addObject("memberMap", memberMap);

        mv.setViewName("lecture/etc");

        return mv;
    }

    // 24-11-20 (수) 1교시 조각
    @GetMapping ("fragment")
    public ModelAndView fragment (ModelAndView mv){

        // 테스트 용으로 데이터 넣기
        mv.addObject("test1", "value1");
        mv.addObject("test2", "value2");

        mv.setViewName("lecture/fragment"); // view 페이지 경로 지정

        return mv;
    }
}
