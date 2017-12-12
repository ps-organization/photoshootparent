package com.instrantes.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Lime on 2017/12/4
 */
public class NettyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.err.println("nettyListener Startup!");
        new Thread(){
            public void run(){
                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                try {
                    ServerBootstrap b = new ServerBootstrap();
                    b.group(bossGroup, workerGroup)
                            .channel(NioServerSocketChannel.class)
                            .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    //abstractBoostStrap中的Handler是个工厂类，它为每个新接入的客户端都创建一个新的Handler
                                    ChannelPipeline pipeline = socketChannel.pipeline();
                                    pipeline.addLast("http-codec", new HttpServerCodec());//将请求和应答消息编码或解码成http消息
                                    pipeline.addLast("aggregator",  new HttpObjectAggregator(65536));  //  上传限制3M
                                    pipeline.addLast("http-chunked",new ChunkedWriteHandler());//向客户端发送html5信息，支持通信
                                    pipeline.addLast("handler",new WebSocketServerHandler());
                                }
                            });
                    ChannelFuture cf=b.bind(7397).sync(); //绑定端口，netty中所有IO操作都是异步的，因此需要它监听，它会立即返回，但不能保证完成操作
                    System.out.println("webSocket start at port:"+7397);
                    cf.channel().closeFuture().sync(); //  应用程序会一直等待，直到channel关闭
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        }.start();
        System.err.println("nettyListener end!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
