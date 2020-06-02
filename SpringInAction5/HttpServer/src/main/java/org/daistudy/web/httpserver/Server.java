package org.daistudy.web.httpserver;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080); // 端口监听
        System.out.println("server is running...");
        for (;;){
            final Socket socket = serverSocket.accept();
            System.out.println("connected from " + socket.getRemoteSocketAddress());
            Thread thread = new Handler(socket);
            thread.start();
        }
    }
}

class Handler extends Thread{
    Socket socket;

    public Handler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try (InputStream input = this.socket.getInputStream()){
            try(OutputStream output = this.socket.getOutputStream()){
                handle(input, output);
            }
        }catch (Exception e){
            try{
                this.socket.close();
            }catch (IOException ioe){

            }
            System.out.println("client disconnected.");
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        System.out.println("Process new http request...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        // TODO： 处理请求
        boolean requestOk = false;
        String first = reader.readLine();
        System.out.println(first);
        if (first.startsWith("GET / HTTP/1.")) {
            requestOk = true;
        }
        for (;;) {
            String header = reader.readLine();
            if (header.isEmpty()) { // 读取到空行时, HTTP Header读取完毕
                break;
            }
            System.out.println(header);
        }
        System.out.println(requestOk ? "Response OK" : "Response Error");
        if (!requestOk) {
            // 发送错误响应:
            writer.write("404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            // 发送成功响应:
            String data = "<html><body><h1>Hello, world!</h1></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n"); // 空行标识Header和Body的分隔
            writer.write(data);
            writer.flush();
        }
    }
}
