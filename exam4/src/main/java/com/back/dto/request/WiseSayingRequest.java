package com.back.dto.request;

public record WiseSayingRequest(
        String content,
        String author
) {
    public static WiseSayingRequest of(String content, String author) {
        return new WiseSayingRequest(content, author);
    }
}
