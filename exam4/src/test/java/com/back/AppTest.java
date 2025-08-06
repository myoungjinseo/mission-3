package com.back;

import java.io.ByteArrayOutputStream;

class AppTest {

    public static String run(String in) {

        // app.run 실행되는 동안 콘솔 출력을 막아준다.
        ByteArrayOutputStream out = TestUtil.setOutToByteArray();
        App app = new App();
        app.sc = TestUtil.genScanner(in);
        app.run();
        // app.run 실행이 중단되어 콘솔 출력으로 변경해준다.
        TestUtil.clearSetOutToByteArray(out);

        return out.toString();
    }
}