package com.example.nettydemo.client.handler;

import com.example.nettydemo.model.SendObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;

public class WMOHeaderHandler extends MessageToByteEncoder<SendObject> {

//    @Override
//    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//
//        ctx.channel().write("0058063200");
//        super.write(ctx, msg, promise);
//    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, SendObject sendObject, ByteBuf byteBuf) throws Exception {

        byteBuf.writeCharSequence(sendObject.getHeader(), StandardCharsets.US_ASCII);
//        byteBuf.writeBytes(sendObject.getPath().toFile(), )
        byteBuf.writeBytes(sendObject.getData());
    }
}
