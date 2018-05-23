package pers.li.server;

/**
 * create by lishengbo 2018/5/23
 */
public  class Utils {

    private static  String [] suffixs={"html","css","jpg","js","jpeg","png","txt","JPG"};

    /**
     * 判断是否为静态资源
     * @return
     */
    public static boolean isStatic(String uri) throws Exception {
        if(uri==null || uri.equals("")){
            throw new Exception("fuck--->访问uri为null");
        }
        //静态资源集合
        for (int i = 0; i <suffixs.length ; i++) {
            if(uri.endsWith("."+suffixs[i])){
                return  true;
            }
        }
        return false;
    }

    public static String getContentType(String uri){
        String type=uri.substring(uri.lastIndexOf(".")+1);
        if(type.equalsIgnoreCase("jpg")||type.equalsIgnoreCase("png")){
            return "Content-Type:image/jpeg";
        }else {
            return "Content-Type:text/html;charset=UTF-8";
        }
    }

    public static void main(String[] args) {
        getContentType("sds.jpg");
    }
}
