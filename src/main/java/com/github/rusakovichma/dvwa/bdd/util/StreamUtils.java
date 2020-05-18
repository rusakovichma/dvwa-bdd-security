package com.github.rusakovichma.dvwa.bdd.util;

import java.io.ByteArrayInputStream;

public class StreamUtils {

    private StreamUtils(){
    }

    public static String toString(ByteArrayInputStream is) {
        int size = is.available();
        char[] theChars = new char[size];
        byte[] bytes    = new byte[size];

        is.read(bytes, 0, size);
        for (int i = 0; i < size;)
            theChars[i] = (char)(bytes[i++]&0xff);

        return new String(theChars);
    }

}
