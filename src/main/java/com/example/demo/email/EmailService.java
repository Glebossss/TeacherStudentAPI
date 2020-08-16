package com.example.demo.email;

@FunctionalInterface
public interface EmailService {

    void sendMessageForRegistr(String email, String name);
}
