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

    public WiseSaying findById(int id) {
        String author = wiseSayings.get(id).getAuthor();
        String content = wiseSayings.get(id).getContent();
        int WiseSayingId = wiseSayings.get(id).getId();
        return new WiseSaying(WiseSayingId, content, author);
    }

    public void update(int id, WiseSaying wiseSaying) {
        wiseSayings.set(id, wiseSaying);
    }
}
