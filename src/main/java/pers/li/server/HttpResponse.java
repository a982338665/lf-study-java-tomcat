package pers.li.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * create by lishengbo 2018/5/23
 */
public class HttpResponse {

    private OutputStream os=null;
    public HttpResponse(OutputStream os) throws IOException{
        this.os=os;
    }

    /**
     * 响应方法-响应静态文件，比如指定的html
     * @param path
     * @throws IOException
     */
    public void  writeStaticFile(String path) throws IOException{
        FileInputStream fis = new FileInputStream(path);
        byte[] buff = new byte[1024];
        int len=0;
        //回应的状态行
        String statusLine = "HTTP/1.1 200 OK\r\n";
        byte[] statusLineBytes = statusLine.getBytes();
        os.write(statusLineBytes);
        //回应的头部
        String responseHeader = String.format(Utils.getContentType(path) );
        byte[] responseHeaderBytes = responseHeader.getBytes();
        os.write(responseHeaderBytes);
        //头部与内容的分隔行
        os.write(new byte[]{13,10});
        while ((len = fis.read(buff))!=-1){
            os.write(buff,0,len);
        };
        fis.close();
        os.flush();
        os.close();
    }

    public static void main(String[] args) {
        System.out.println(new File("html/").getAbsolutePath());
    }
}
