package com.example.mynetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println("客户端消息" + msg);

        //send to client
        //ctx.writeAndFlush("Received your message !\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("onConnected");

        ctx.writeAndFlush("onConnected");

        super.channelActive(ctx);


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("onDisconnected");

        super.channelInactive(ctx);
    }
}