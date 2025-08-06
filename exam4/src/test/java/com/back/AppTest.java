package com.back;

import java.io.ByteArrayOutputStream;

class AppTest {

    public static String run(String in) {

        ByteArrayOutputStream out = TestUtil.setOutToByteArray();
        App app = new App();
        app.sc = TestUtil.genScanner(in);
        app.run();
        TestUtil.clearSetOutToByteArray(out);

        return out.toString();
    }
}