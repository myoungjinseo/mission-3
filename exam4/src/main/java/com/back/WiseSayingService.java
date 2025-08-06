package com.back;

import com.back.dto.request.WiseSayingRequest;
import com.back.dto.request.WiseSayingUpdateResponse;
import com.back.dto.response.FindIdResponse;
import com.back.dto.response.WiseSayingResponse;

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
        String msg = "%d번 명언이 삭제되었습니다.".formatted(deleteId);
        FindIdResponse response = findIdById(deleteId, msg);
        if(response.id() == -1)
            return response.msg();
        wiseSayingRepository.deleteById(response.id());
        return response.msg();
    }

    public int getId(String command) {
        String[] arr = command.split("=");
        return Integer.parseInt(arr[1]);
    }

    public FindIdResponse findIdById(int id, String msg) {
        int findId = wiseSayingRepository.findIdById(id);
        if (findId == -1)
            return FindIdResponse.of(findId,"%d번 명언은 존재하지 않습니다.".formatted(id));
        return FindIdResponse.of(findId,msg);
    }

    public WiseSayingResponse getWiseSayingById(String command) {
        int updateId = getId(command);
        String msg = "%d번 명언이 수정되었습니다.".formatted(updateId);
        FindIdResponse response = findIdById(updateId, msg);
        if(response.id() == -1)
            return WiseSayingResponse.of(response,null);
        WiseSaying wiseSaying = wiseSayingRepository.findById(response.id());
        return WiseSayingResponse.of(response,wiseSaying);
    }

    public void updateWiseSaying(WiseSayingUpdateResponse request) {
        wiseSayingRepository.update(request.findId(),request.wiseSaying());
    }
}
