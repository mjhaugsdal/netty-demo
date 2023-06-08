package com.example.nettydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class NettyDemoApplication {

    public static void main(String[] args) throws IOException, URISyntaxException {
        SpringApplication.run(NettyDemoApplication.class, args);

//        System.out.println("hello world, I have just started up");
        TCPClient tcpClient = new TCPClient();
        tcpClient.start(8888);
//
//        tcpClient.sendMessage("hello");

    }


}
