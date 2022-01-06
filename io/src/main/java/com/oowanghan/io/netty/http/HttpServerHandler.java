package com.oowanghan.io.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter的子类
 * HttpObject 表示客户端和服务器端相互通讯的数据被封装成HttpObject类型
 *
 * @Author WangHan
 * @Create 2021/10/20 10:30 下午
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println("对应的channel=" + ctx.channel() + " pipeline=" + ctx
                .pipeline() + " 通过pipeline获取channel" + ctx.pipeline().channel());

        System.out.println("当前ctx的handler=" + ctx.handler());

        if (msg instanceof HttpRequest) {
            System.out.println(" msg类型 = " + msg.getClass());
            System.out.println("客户端地址 = " + ctx.channel().remoteAddress());


            HttpRequest request = (HttpRequest) msg;
            if ("/favicon.ico".equals(new URI(request.uri()).getPath())) {
                System.out.println("请求了 favicon.ico, filter");
                return;
            }

            // 构造response
            ByteBuf content = Unpooled.copiedBuffer("hello, this is server", CharsetUtil.UTF_8);
            DefaultFullHttpResponse httpResponse =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(httpResponse);
        }
    }
}
