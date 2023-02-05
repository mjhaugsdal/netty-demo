package com.example.nettydemo;

import com.example.nettydemo.server.IOServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NettyDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testIOServer() {
        try {
            System.out.println("hello");
            new IOServer(8888).run();
            System.out.println("world!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
