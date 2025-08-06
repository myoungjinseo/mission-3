package com.back;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public List<WiseSaying> findAllByOrderByIdDesc() {
        return wiseSayings.stream()
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .collect(Collectors.toList());
    }

    public int findIdById(int getId) {
        return IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == getId)
                .findFirst()
                .orElse(-1);
    }

    public void deleteById(int id) {
        wiseSayings.remove(id);
    }
}
