package com.example.nettydemo.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.netty.tcp.TcpServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class IOServer {

    private static final Logger log = LoggerFactory.getLogger(IOServer.class.getName());

    File file = new File("grib.grb");
    FileOutputStream fos = new FileOutputStream(file, true);

    private Channel channel;

    public IOServer() throws FileNotFoundException {
    }

    public static void main(String[] args) throws Exception {

        //new IOServer().run();
    }


    public void start(int port) {
        var disposableServer = TcpServer.create()
                .port(port)
//                .doOnChannelInit((connectionObserver, channel1, socketAddress) -> {
//                    channel1.pipeline().addLast(new PresentationLayerHandler());
//                })
                .wiretap(true)
//                .childObserve((connection, newState) -> connection.inbound().receive().asString().subscribe(s -> log.info(s)))
//                .doOnConnection(connection -> connection.inbound().receive().asString(StandardCharsets.US_ASCII).subscribe(s -> log.info(s)))
//                .doOnConnection(connection -> connection.inbound().receive().asString().doOnNext(s -> log.info(s)))
                .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(4096 * 1000))
                .handle((nettyInbound, nettyOutbound) -> nettyInbound
                                .receive().handle((byteBuf, voidSynchronousSink) -> {
//                                    var length = byteBuf.readCharSequence(8, StandardCharsets.US_ASCII);
//                                    var id = byteBuf.readCharSequence(2, StandardCharsets.US_ASCII);
                                    try {
                                        byteBuf.readBytes(fos, byteBuf.readableBytes());
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }


//                            var length = byteBuf.readCharSequence(8, StandardCharsets.US_ASCII);
//                            var id = byteBuf.readCharSequence(2, StandardCharsets.US_ASCII);
//                            log.info(String.valueOf(length));
//
//                                    // if (buf.isReadable()) { // a buf should always be readable here
//                                    FileOutputStream fos = null;
//                                    try {
//                                        fos = new FileOutputStream("file.grb");
//                                        byteBuf.readBytes(fos, byteBuf.readableBytes());
//                                    } catch (IOException e) {
//                                        throw new RuntimeException(e);
//                                    }


//                            var data = byteBuf.readCharSequence(Integer.parseInt(String.valueOf(length)), StandardCharsets.US_ASCII);
//                            System.out.println(data);
                                }).doOnError(throwable -> throwable.printStackTrace()).then()
                );

        var connection = disposableServer.bindNow();
        connection.onDispose().block();


//        disposableServer.onDispose().block();

//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        public void initChannel(SocketChannel ch)
//                                throws Exception {
//                            ch.pipeline().addLast(new RequestDecoder(),
//                                    new ResponseDataEncoder(),
//                                    new ProcessingHandler());
//                        }
//                    }).option(ChannelOption.SO_BACKLOG, 128)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true);
//
//
//            ChannelFuture f = b.bind(port).sync();
//            channel = f.channel();
//            f.channel().closeFuture().sync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//        }
    }

}
