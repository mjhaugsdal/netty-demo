package com.example.nettydemo;

import com.example.nettydemo.client.decoder.ResponseDataDecoder;
import com.example.nettydemo.client.encoder.RequestDataEncoder;
import com.example.nettydemo.client.handler.ClientHandler;
import com.example.nettydemo.model.RequestData;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TCPClient {

    ClientHandler clientHandler = new ClientHandler();

    public void init() {
        String host = "localhost";
        int port = 8888;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                public void initChannel(SocketChannel ch)
                        throws Exception {
                    ch.pipeline().addLast(new RequestDataEncoder(),
                            new ResponseDataDecoder(), clientHandler);
                }
            });

            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.err.println("Failed to connect.");
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
