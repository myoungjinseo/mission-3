package com.back;

import com.back.dto.request.WiseSayingRequest;

import java.util.List;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService = new WiseSayingService();

    public int createWiseSaying(WiseSayingRequest request){
        return wiseSayingService.createWiseSaying(request);
    }

    public List<WiseSaying> getWiseSayings() {
        return wiseSayingService.getWiseSayings();
    }
}
