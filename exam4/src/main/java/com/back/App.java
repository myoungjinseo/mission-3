package com.back;

import com.back.dto.request.WiseSayingRequest;

import java.util.List;
import java.util.Scanner;

public class App {

    Scanner sc = new Scanner(System.in);
    private final WiseSayingController wiseSayingController = new WiseSayingController();
    public void run() {
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
                WiseSayingRequest request = new WiseSayingRequest(content, author);

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
            } else {
                System.out.println("명령어가 존재하지 않습니다.");
            }
        }
    }
}
