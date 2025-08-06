package com.back.dto.request;

import com.back.WiseSaying;

public record WiseSayingUpdateResponse(
     WiseSaying wiseSaying,
     int findId
) {
    public static WiseSayingUpdateResponse of(WiseSaying wiseSaying, int id) {
        if(wiseSaying==null) {

        }
        return new WiseSayingUpdateResponse(wiseSaying,id);
    }
}
