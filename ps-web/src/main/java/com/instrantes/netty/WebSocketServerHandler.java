package com.instrantes.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

/**
 * Created by Lime on 2017/11/30
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());
    private WebSocketServerHandshaker handshaker;

    /**2017年12月5日此处有毒，原本放在此处的握手不能升级为websocket，放在channelRead方法中即可
     * channelRead0 在 Netty 5中可能会被改名为messageReceived
     * 回调完成后Netty会帮我们完成释放
     * Date: 2017/12/4
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //传统http接入
//        if (msg instanceof FullHttpRequest) { //FullHttpRequest结合HttpRequest和FullHttpMessage
//            handleHttpRequest(ctx, (FullHttpRequest) msg);
//        }
//        //WebSocket接入
//        else if (msg instanceof WebSocketScheme) {
//            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
//        }
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //传统http接入
        if (msg instanceof FullHttpRequest) { //FullHttpRequest结合HttpRequest和FullHttpMessage
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
        //WebSocket接入
        else if (msg instanceof WebSocketFrame) {
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令，如果是关闭按链路的指令就关闭链路
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }
        // 判断是否ping消息， 如果是维持链路的ping消息就返回Pong消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            System.out.println("本例程仅支持文本消息，不支持二进制消息");
            throw new UnsupportedOperationException(
                    String.format("%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端收到：" + request);
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("%s received %s", ctx.channel(), request));
        }
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "：" + request);
        // 群发
//        Global.group.writeAndFlush(tws);
        // 返回【谁发的发给谁】
//        ctx.channel().writeAndFlush(tws);
        ctx.channel().write(tws);
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        //如果HTTP解码失败,返回异常。查看头中是否含有Upgrade字段
        if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
            System.out.println(request.headers().get("Upgrade"));
            sendHttpRespones(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
//        构造握手响应，本机测试
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:7397/websocket", null, false);
        handshaker = webSocketServerHandshakerFactory.newHandshaker(request);

        if (handshaker == null)
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        else
            handshaker.handshake(ctx.channel(), request);
    }

    //返回信息
    private static void sendHttpRespones(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
        //返回应答给客户端
        if (response.status().code() != 200) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            System.out.println("response-------------------:"+response.content());
            System.out.println("request-------------------:"+request);
            response.content().writeBytes(byteBuf);
            byteBuf.release();
            //setContentLength()弃用改为HttpUtil.setContentLength(HttpMessage, long)
            HttpUtil.setContentLength(request,request.content().readableBytes());
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        if (!isKeepAlive(request) || response.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        //        Global.group.remove(ctx.channel());
        System.out.println("Client:" + incoming.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
