package com.example.nettydemo.client.handler;

import com.example.nettydemo.model.RequestData;
import com.example.nettydemo.model.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    ChannelHandlerContext ctx;
    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {

        this.ctx = ctx;
        RequestData msg = new RequestData();
        //msg.setIntValue(123);
        msg.setStringValue(
                "all work and no play makes johnny a dull boy");
        ChannelFuture future = ctx.writeAndFlush(msg);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(((ResponseData) msg).getIntValue());
        ctx.close();
    }

    public void sendMessage(String msg) {
        RequestData data = new RequestData();
        data.setStringValue(msg);
        ChannelFuture future = ctx.writeAndFlush(data);
    }

}