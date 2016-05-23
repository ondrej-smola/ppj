package javappj; /**
 * Created by The CAT on 25.4.2016.
 */
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class fileSys {
    public static  void  newImage(String url,int id){

        try {
            URL u=new URL(url);
            String scheme = u.getProtocol();
            String host = u.getHost();
            if("file".equalsIgnoreCase(scheme) && !(host != null && !"".equals(host))){

                FileInputStream is = null;
                FileOutputStream os = null;
                try {
                    is = new FileInputStream(new File(url));
                    os = new FileOutputStream("./images/"+id+url.split(".")[url.split(".").length-1]);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                }  finally {
                    is.close();
                    os.close();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("problem file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("problem file");
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
