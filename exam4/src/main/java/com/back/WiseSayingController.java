package com.back;

import com.back.dto.request.WiseSayingRequest;
import com.back.dto.request.WiseSayingUpdateResponse;
import com.back.dto.response.WiseSayingResponse;

import java.io.IOException;
import java.util.List;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService = new WiseSayingService();

    public int createWiseSaying(WiseSayingRequest request){
        return wiseSayingService.createWiseSaying(request);
    }

    public List<WiseSaying> getWiseSayings() {
        return wiseSayingService.getWiseSayings();
    }

    public String deleteWiseSaying(String command) {
        return wiseSayingService.deleteWiseSaying(command);
    }

    public WiseSayingResponse getWiseSayingById(String command) {
        return wiseSayingService.getWiseSayingById(command);
    }

    public void updateWiseSaying(WiseSayingUpdateResponse request) {
        wiseSayingService.updateWiseSaying(request);
    }

    public void initializeFiles() {
        wiseSayingService.initializeFiles();
    }

    public void createWiseSayingJson() throws IOException {
        wiseSayingService.createWiseSayingJson();
    }

    public String createAllWiseSayingJson() throws IOException {
        return wiseSayingService.createAllWiseSayingJson();
    }
}
