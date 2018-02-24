package book;

import java.io.IOException;

public class Bootstrap {

    public static void main(String[] args) throws IOException {

        Download download = new Download();
        download.setBookHref("https://www.txtjia.com/shu/55467/");
        download.setBookTitle("xlxsh");
        download.setDownloadFile("/Users/caoxiangmao/xlxsh.txt");
        download.execute();
    }


}
