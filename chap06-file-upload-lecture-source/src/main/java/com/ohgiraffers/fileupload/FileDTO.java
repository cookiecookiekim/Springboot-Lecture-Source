package com.ohgiraffers.fileupload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FileDTO {

    private String originFileName;
    /* comment.
    *   여러명의 사람이 동일한 이름을 가진 파일을 등록할 수 있다.
    *   따라서 원본 파일 이름도 저장하되 각각 고유한 이름을 따로 부여해서
    *   파일명이 겹치는 상황을 피해야 한다. */
    private String UUIDFilename; // UUID 파일의 고유 아이디 식별
    private String filePath;
    private String description;

}
