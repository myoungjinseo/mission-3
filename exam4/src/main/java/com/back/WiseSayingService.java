package com.back;

import com.back.dto.request.WiseSayingRequest;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public int createWiseSaying(WiseSayingRequest request) {
        int lastId = 0;
        if(!wiseSayingRepository.emptyByWiseSaying())
            lastId = wiseSayingRepository.findLastId();
        WiseSaying wiseSaying = new WiseSaying(lastId + 1, request.content(), request.author());
        wiseSayingRepository.save(wiseSaying);
        return wiseSaying.getId();
    }

    public List<WiseSaying> getWiseSayings() {
        return wiseSayingRepository.findAllByOrderByIdDesc();
    }

    public String deleteWiseSaying(String command) {
        int deleteId = getId(command);
        int findId = wiseSayingRepository.findIdById(deleteId);
        if(findId == -1)
            return "%d번 명언은 존재하지 않습니다.".formatted(deleteId);
        wiseSayingRepository.deleteById(findId);
        return "%d번 명언이 삭제되었습니다.".formatted(deleteId);
    }

    public int getId(String command) {
        String[] arr = command.split("=");
        return Integer.parseInt(arr[1]);
    }
}
