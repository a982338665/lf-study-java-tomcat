package test;
  
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class HttpServer implements Runnable {  
    //服务器监听  package com.lei.second;


    private ServerSocket serverSocket;  
    //标志位，标识当前服务器是否正在运行  
    private boolean isRunning;  
    public HttpServer(int port) {  
        try {  
            serverSocket = new ServerSocket(port);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public void stop() {  
        this.isRunning = false;  
    }  
      
    public void start() {  
        this.isRunning = true;  
        new Thread(this).start();  
    }  
      
    @Override  
    public void run() {  
        while (isRunning) { //一直监听，知道收到停止的命令  
            Socket socket = null;  
            try {  
                socket = serverSocket.accept(); //如果没有请求，会在这里等待，有客户端请求的时候才会继续往下执行  
  
                BufferedReader bufferedReader = new BufferedReader(  
                        new InputStreamReader(socket.getInputStream()));    //获取输入流  
                StringBuilder stringBuilder = new StringBuilder();  
                String line = null;  
                while ((line = bufferedReader.readLine())!=null  
                        &&!line.equals("")) {  
                    stringBuilder.append(line).append("/n");  
                }  
                String record = stringBuilder.toString();  
                System.out.println("--------------------------------");  
                System.out.println(record);  
                PrintWriter printWriter = new PrintWriter(  
                        socket.getOutputStream(),true); //自动刷新缓存  
                doEcho(printWriter, record);    //将日志输出到浏览器  
                printWriter.close();  
                bufferedReader.close();  
                socket.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }     
        }  
    }  
      
    /* 
     * 讲收到的信回写给客户端 
     */  
    private void doEcho(PrintWriter printWriter, String record) {  
        printWriter.write("已接受数据");  
    }  
}  