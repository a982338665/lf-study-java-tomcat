package pers.li.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * create by lishengbo 2018/5/23
 * tomcat -底层实现 http-Socket
 */
public class TomcatServer {
    //统计服务访问了多少次
    private static int count=0;

    public static void main(String[] args) {

        ServerSocket ss=null;
        Socket socket=null;
        //http底层就是socket
        try{
            ss=new ServerSocket(9999);
            System.out.println("服务初始化完成，等待客户端连接........");
            while (true){
                //阻塞，等待客户端连接
                socket = ss.accept();
                //解析其你去携带信息
                InputStream is = socket.getInputStream();
                HttpRequest request = new HttpRequest(is);
                //响应请求开始++++++++++++++++++++++++++++++++++
                OutputStream os = socket.getOutputStream();
                //通过解析后得到的uri来寻找对应的静态资源以返回
                HttpResponse response = new HttpResponse(os);
                String uri = request.getUri();
                //判断是否为静态资源
                boolean result = Utils.isStatic(uri);
                if(result){
                        response.writeStaticFile(uri.substring(1));
                }
//                String html="<html><head><title>hello</title></head><body>hello 皮卡丘</body></html>";
                //信息输出
//                os.write(html.getBytes());
//                os.flush();
//                os.close();
                //关闭客户端
                socket.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
