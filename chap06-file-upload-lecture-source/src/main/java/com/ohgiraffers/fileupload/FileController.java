package com.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    /* comment. yml 파일에 작성한 value를 읽어 들이기 위한 준비 */
    private ResourceLoader resourceLoader; // ResourceLoader 의존성 추가

    @PostMapping("single-file")
    public String singleFileTest (@RequestParam MultipartFile singleFile,
                                  // 인코딩 방식와 동일하게 설정
                                  String description, Model model) throws IOException {
                        // 매개변수 동일하게 잘 맞춰주면 RequestParam 생략 가능

        /* comment. @RequestParam 어노테이션
        *   요청한 데이터를 받아올 수 있게 하는 어노테이션
        *   매개변수명이 일치한다면 생략 가능. */
        System.out.println("singleFile = " + singleFile);
        System.out.println("description = " + description);

        /* index. 1. 파일을 저장할 위치 설정 */
        Resource resource =
                resourceLoader.getResource("classpath:static/img/single");

        String filePath = null;
        // 위에서 지정한 파일 저장 위치에 파일이 존재하지 않는다면 생성하겠다~
        if (!resource.exists()) {
            String root = "src/main/resources/static/img/single";
            File file = new File(root);

            file.mkdirs();
            // mkdirs : make directory의 약자
            // root에 지정한 경로대로 디렉토리를 만들어 준다.

            // 만든 폴더의 경로를 filePath 변수에 담아주기
            filePath = file.getAbsolutePath();
        } else { // 디렉토리가 만들어진 적이 있다면~
            filePath = resourceLoader.getResource("classpath:static/img/single")
                    .getFile().getAbsolutePath();
        }

        /* index. 2. 파일 데이터 받아오고, 저장할 경로 가져왔다면,
        *           데이터 변경처리 시작 */

        // 원본 파일명 담기
        String originFileName = singleFile.getOriginalFilename(); // 멀티파일 제공
        System.out.println("originFileName = " + originFileName);

        // 확장자 제거 ( ex : spring.jpg 에서 .jpg 잘라내기)
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);

        // 파일에 고유한 이름 부여 (고유 번호를 부여해주는 UUID 클래스)
        String savedName = UUID.randomUUID().toString().replace("-", "")
                        + ext;
        System.out.println("savedName = " + savedName);
        // 랜덤 ID 생성, String으로 변경

        /* index. 3. 고유한 파일 식별번호 및 파일 저장경로 생성 완료.
        *            이제 파일을 저장 경로에 저장. */
        // 전달 받은 파일 객체를 변환
        singleFile.transferTo(new File(filePath + "/" + savedName));

        model.addAttribute("message", "파일 업로드 생성");
        model.addAttribute("img", "static/img/single/" + savedName);

        return "result";
    }

    @PostMapping("multi-file") // 여러 파일을 가져오기 때문에 List<>로 설정
    public String multiFile (@RequestParam List<MultipartFile> multifile
                            ,String description
                            ,Model model) throws IOException {

        /* index. 1. 파일을 저장할 위치 설정 */
        Resource resource =
                resourceLoader.getResource("classpath:static/img/multi");

        String filePath = null;
        // 위에서 지정한 파일 저장 위치에 파일이 존재하지 않는다면 생성하겠다~
        if (!resource.exists()) {
            String root = "src/main/resources/static/img/multi";
            File file = new File(root);

            file.mkdirs();
            // mkdirs : make directory의 약자
            // root에 지정한 경로대로 디렉토리를 만들어 준다.

            // 만든 폴더의 경로를 filePath 변수에 담아주기
            filePath = file.getAbsolutePath();
        } else { // 디렉토리가 만들어진 적이 있다면~
            filePath = resourceLoader.getResource("classpath:static/img/multi")
                    .getFile().getAbsolutePath();
        }

        /* index. 2. 멀티 파일 변경 처리 */
        List<FileDTO> files = new ArrayList<>();
        List<String> savedFiles = new ArrayList<>();

        for (MultipartFile file : multifile){
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "")
                    + ext;

            files.add(new FileDTO(originFileName, savedName, filePath, description)); // 추가
            file.transferTo(new File(filePath + "/" + savedName)); // 변환
            savedFiles.add("static/img/multi/" + savedName); // 주소 작성
        }

        model.addAttribute("message", "파일 업로드 성공!");
        model.addAttribute("imgs", savedFiles);

        return "result";
    }

}
