package com.example.nettydemo;

import com.example.nettydemo.server.IOServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@EnableAsync
class NettyDemoApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Autowired
//    TCPClient tcpClient;

    @Test
    void testIOServer() {
        try {
            IOServer server = new IOServer();
//            server.start(8888);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> server.start(8888));

            TCPClient tcpClient = new TCPClient();
            tcpClient.start(8888);

//            connection.onDispose().block();

//            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
