package com.example.sample.board.data;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Board {

    @Data
    public static class Response{
        private int boId;
        private String title;
        private String contents;
        private String writer;
        private LocalDateTime lastModified;

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE) // 기본생성자로 객체 생성을방지 하기 위해 설정
    @AllArgsConstructor
    @Builder
    public static class Request {

        private String title;
        private String content;
        private String writer;
    }
}
