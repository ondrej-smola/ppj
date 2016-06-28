package javappj.servis;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by The CAT on 25.4.2016.
 */
public class fileSys {


   /* public static fileSys get() throws IOException {
        return new fileSys();
    }*/

    @Value("${path}")
    private String imgpath;

    public fileSys() throws IOException {
        Path path = Paths.get(imgpath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    private Path getImagePath(String filename) {
        assert (filename != null);
        return Paths.get(imgpath).resolve(filename + ".jpg");
    }

    public void newImage(String filename, InputStream giftData) throws IOException {
        assert (giftData != null);
        Path target = getImagePath(filename);
        Files.copy(giftData, target, StandardCopyOption.REPLACE_EXISTING);
    }
    /*
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

    }*/
}
