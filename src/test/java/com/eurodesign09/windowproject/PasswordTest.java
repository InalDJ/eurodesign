package com.eurodesign09.windowproject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String editorPassword = "12345";
        String adminTamaraPassword = "123456";
        System.out.println(encoder.encode(adminTamaraPassword));
        System.out.println(encoder.encode(editorPassword));
    }

}
