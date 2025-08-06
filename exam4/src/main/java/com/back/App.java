package com.back;

import com.back.dto.request.WiseSayingRequest;
import com.back.dto.request.WiseSayingUpdateResponse;
import com.back.dto.response.WiseSayingResponse;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {

    Scanner sc = new Scanner(System.in);
    private final WiseSayingController wiseSayingController = new WiseSayingController();
    public void run() throws IOException {
        String command = "";
        System.out.println("== 명언 앱 ==");
        while (!command.equals("종료")){
            System.out.print("명령) ");
            command = sc.nextLine();
            if(command.equals("등록")){
                System.out.print("명언 : ");
                String content = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
                WiseSayingRequest request = WiseSayingRequest.of(content, author);

                int id = wiseSayingController.createWiseSaying(request);

                System.out.printf("%d번 명언이 등록되었습니다.\n", id);
            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                List<WiseSaying> response = wiseSayingController.getWiseSayings();
                for (WiseSaying wiseSaying : response) {
                    System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
                }
            } else if (command.contains("삭제?id=")) {
                String response = wiseSayingController.deleteWiseSaying(command);
                System.out.println(response);
            } else if (command.contains("수정?id=")) {
                WiseSayingResponse response = wiseSayingController.getWiseSayingById(command);
                if(response.findId() != -1){
                    System.out.println("명언(기존) : " + response.content());
                    System.out.print("명언 : ");
                    String updateContent = sc.nextLine();
                    System.out.println("작가(기존) : " + response.author());
                    System.out.print("작가 : ");
                    String updateAuthor = sc.nextLine();
                    WiseSaying updatewiseSaying = new WiseSaying(response.id(), updateContent,updateAuthor);
                    WiseSayingUpdateResponse request = WiseSayingUpdateResponse.of(updatewiseSaying, response.findId());
                    wiseSayingController.updateWiseSaying(request);
                } else {
                    System.out.println(response.msg());
                }
            } else {
                System.out.println("명령어가 존재하지 않습니다.");
            }
        }
        System.out.println("프로그램 다시 시작\n");
        System.out.println("명령) 목록");
        wiseSayingController.getWiseSayings();
        wiseSayingController.createWiseSayingJson();
    }
}
