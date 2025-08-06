package com.back;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;

import static com.back.AppTest.run;
import static org.assertj.core.api.Assertions.assertThat;

class WiseSayingControllerTest {

    @BeforeEach
    void beforeEach() {
        AppTest.clear();
    }

    @AfterEach
    void afterEach() {
        AppTest.build();
    }

    @Test
    @DisplayName("등록")
    void createWiseSayingTest() throws IOException {

        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록")
    void getWiseSayingTest() throws IOException {

        final String out = run(
                """
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                종료
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("삭제")
    void deleteWiseSayingTest() throws IOException {
        final String out = run(
                """
                      등록
                      현재를 사랑하라.
                      작자미상
                      등록
                      과거에 집착하지 마라.
                      작자미상
                      목록
                      삭제?id=1
                      삭제?id=1
                      종료
                      """);

        assertThat(out)
                .contains("1번 명언이 삭제되었습니다.")
                .contains("1번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("수정")
    void updateWiseSayingTest() throws IOException {
        final String out = run(
                """
                      등록
                      현재를 사랑하라.
                      작자미상
                      등록
                      과거에 집착하지 마라.
                      작자미상
                      목록
                      삭제?id=1
                      삭제?id=1
                      수정?id=3
                      수정?id=2
                      현재와 자신을 사랑하라.
                      홍길동
                      목록
                      종료
                      """);

        assertThat(out)
                .contains("3번 명언은 존재하지 않습니다.")
                .contains("명언(기존) : 과거에 집착하지 마라.")
                .contains("명언 :")
                .contains("작가(기존) : 작자미상")
                .contains("작가 :")
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 홍길동 / 현재와 자신을 사랑하라.");
    }

    @Test
    @DisplayName("빌드")
    void createAllWiseSayingJson() throws IOException {

        File file = new File("src/main/java/com/back/db/wiseSaying/data.json");

        final String out = run(
                """
                      등록
                      현재를 사랑하라.
                      작자미상
                      등록
                      과거에 집착하지 마라.
                      작자미상
                      목록
                      삭제?id=1
                      삭제?id=1
                      수정?id=3
                      수정?id=2
                      현재와 자신을 사랑하라.
                      홍길동
                      목록
                      빌드
                      종료
                      """);

        assertThat(out)
                .contains("data.json 파일의 내용이 갱신되었습니다.");

        assertThat(file)
                .exists()
                .isFile();

    }
}