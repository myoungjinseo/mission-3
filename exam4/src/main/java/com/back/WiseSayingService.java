package com.back;

import com.back.dto.request.WiseSayingRequest;
import com.back.dto.request.WiseSayingUpdateResponse;
import com.back.dto.response.FindIdResponse;
import com.back.dto.response.WiseSayingResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    private final String PATH = "src/main/java/com/back/db/wiseSaying/";

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

    public void initializeFiles() {
        File file = new File(PATH);
        if (!file.exists() || !file.isDirectory()) return;

        File[] files = file.listFiles();
        if (files == null) return;

        for (File f : files) {
            if (f.isFile()) f.delete();
        }
    }

    public void createWiseSayingJson() throws IOException {
        List<WiseSaying> wiseSayingList = wiseSayingRepository.findAll();
        for(WiseSaying wiseSaying : wiseSayingList){
            StringBuilder sb = new StringBuilder();
            String file = PATH + wiseSaying.getId() + ".json";
            FileWriter fwJson = new FileWriter(file);
            sb.append("{\n");
            sb.append("  \"id\": ").append(wiseSaying.getId()).append(",\n");
            sb.append("  \"content\": \"").append(wiseSaying.getContent()).append("\",\n");
            sb.append("  \"author\": \"").append(wiseSaying.getAuthor()).append("\"\n");
            sb.append("}");
            fwJson.write(sb.toString());
            fwJson.close();
        }
        FileWriter fwTxt = new FileWriter(PATH + "lastId.txt");
        int lastId = wiseSayingRepository.findLastId();
        fwTxt.write(String.valueOf(lastId));
        fwTxt.close();
    }
}
