package test;
  
public class Luancher {  
    public static void main(String[] args) {  
        HttpServer httpServer = new HttpServer(7777);  
        httpServer.start();  
    }  
}  