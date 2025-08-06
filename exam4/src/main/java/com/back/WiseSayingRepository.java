package com.back;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    public boolean emptyByWiseSaying() {
        return wiseSayings.isEmpty();
    }

    public int findLastId() {
        return wiseSayings.getLast().getId();
    }

    public void save(WiseSaying wiseSaying) {
        wiseSayings.add(wiseSaying);
    }

}
