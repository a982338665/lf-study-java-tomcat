package pers.li.server;

import java.io.IOException;
import java.io.InputStream;

/**
 * create by lishengbo 2018/5/23
 * 解析客户端请求
 */
//========GET /hello HTTP/1.1
//        Host: localhost:9999
//        Connection: keep-alive
//        Cache-Control: max-age=0
//        User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36
//        Upgrade-Insecure-Requests: 1
//        Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*   /*;q=0.8
//Accept-Encoding: gzip, deflate, br
//Accept-Language: zh-CN,zh;q=0.8,en;q=0.6

public class HttpRequest {

    //所有请求都开始于uri
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    //解析请求信息
    public HttpRequest(InputStream is) throws IOException{
        //定义一个存放信息缓存的地方
        byte[] buff = new byte[1024];
        int len = is.read(buff);
        if(len>0){
            //将读取出来的字节信息转换为可读
            String msg = new String(buff, 0, len);
            System.out.println("========"+msg+"==========");
            //解析请求消息开始--------------
            uri=msg.substring(msg.indexOf("/"),msg.indexOf("HTTP/1.1")-1);
            System.out.println("uri:---->"+uri);
        }else {
            System.out.println("bad request!");
        }
    }

}
