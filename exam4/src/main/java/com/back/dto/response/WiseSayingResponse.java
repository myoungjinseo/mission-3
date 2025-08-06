package com.back.dto.response;

import com.back.WiseSaying;

public record WiseSayingResponse(
        int id,
        String content,
        String author,
        int findId,
        String msg
) {
    public static WiseSayingResponse of(FindIdResponse response, WiseSaying wiseSaying) {
        if(wiseSaying == null) {
            return new WiseSayingResponse(0,"","", response.id(), response.msg());
        }
        return new WiseSayingResponse(wiseSaying.getId(),wiseSaying.getContent(),wiseSaying.getAuthor(),response.id(),response.msg());
    }
}
