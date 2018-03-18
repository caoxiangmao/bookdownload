package book;

import java.io.IOException;

public class Bootstrap {

    public static void main(String[] args) throws IOException {

        Download download = new Download();
        download.setBookHref("https://www.xxbiquge.com/27_27755/");
        download.setBookTitle("gshdzhun");
        download.setDownloadFile("/Users/caoxiangmao/gshdzhun.txt");
        download.execute();
    }


}
