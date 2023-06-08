package com.example.nettydemo;

import com.example.nettydemo.client.handler.WMOHeaderHandler;
import com.example.nettydemo.model.SendObject;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TCPClient {
//    ClientHandler clientHandler = new ClientHandler();

    public void start(int port) throws IOException, URISyntaxException {
        var is = TCPClient.class.getClassLoader().getResourceAsStream("grib2.grb");
        assert is != null;
        byte[] data = is.readAllBytes();
        var resource = TCPClient.class.getResource("/grib2.grb");
        var path = Paths.get(resource.toURI());

        var size = Files.size(path);

        SendObject sendObject = new SendObject(data, "00"+size+"00");
        Connection connection =
                TcpClient.create()
//                        .host("example.com"
                        .host("localhost")
                        .port(8888)
                        .wiretap(true)
                        .doOnChannelInit((connectionObserver, channel, remoteAddress) -> {
                            channel.pipeline().addLast(new WMOHeaderHandler());
                        })
//                        .doOnConnected(connection1 -> connection1.channel().write("00" + size + "00"))
//                        .doOnChannelInit((connectionObserver, channel, remoteAddress) -> {
//                            channel.write("00" + size + "00");
//                        })
//                        .doOnConnected(connection1 -> connection1.outbound().sendString(Mono.just("00" + size + "00")))
                        .connectNow();


//        connection.outbound()
//                .sendString(Mono.just("00" + size + "00"))
//                .then()
//                .subscribe();


        connection.outbound()

//                .withConnection(connection1 -> connection1.channel().pipeline().addLast(new WMOHeaderHandler()))
//                .sendString(Mono.just("hello 2"))
//                .withConnection(connection1 -> connection1.channel().write("00" + size + "00"))
//                .sendString(Mono.just("00" + size + "00"))
//                .sendFile(path)

                .sendObject(sendObject)
//                .sendFile(path)
                .then()
                .subscribe();
//                .subscribe(null, null, connection::dispose);

        connection.onDispose()
                .block();

    }

//        String host = "localhost";
//        int port = 8888;
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(workerGroup);
//            b.channel(NioSocketChannel.class);
//            b.option(ChannelOption.SO_KEEPALIVE, true);
//            b.handler(new ChannelInitializer<SocketChannel>() {
//
//                @Override
//                public void initChannel(SocketChannel ch)
//                        throws Exception {
//                    ch.pipeline().addLast(new RequestDataEncoder(),
//                            new ResponseDataDecoder(), clientHandler);
//                }
//            });
//
//            ChannelFuture f = b.connect(host, port).sync();
//            f.channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            workerGroup.shutdownGracefully();
//        }
}

