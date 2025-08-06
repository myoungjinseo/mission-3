package com.back.dto.response;

public record FindIdResponse(
        int id,
        String msg
) {
    public static FindIdResponse of(int id, String msg) {
        return new FindIdResponse(id, msg);
    }
}
