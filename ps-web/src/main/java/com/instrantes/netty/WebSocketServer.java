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

/**
 * Created by Lime on 2017/11/28
 */
public class WebSocketServer {
    public void start(int port) throws Exception {
        //创建了两个(线程池和selector，reactor模型)一个main loop一个child loop通过ServerBoootstrap的group方法组合起来
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
            ChannelFuture cf=b.bind(port).sync(); //绑定端口，netty中所有IO操作都是异步的，它会立即返回，但不能保证完成操作
            System.out.println("webSocket start at port:"+port);
            cf.channel().closeFuture().sync(); //  应用程序会一直等待，直到channel关闭
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 7397;
        }
        new WebSocketServer().start(port);

    }
}
