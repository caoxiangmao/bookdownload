package book;

import java.io.IOException;

public class Bootstrap {

    public static void main(String[] args) throws IOException {

        Download download = new Download();
        download.setBookHref("https://www.biquguan.com/bqg23112/");
        download.setBookTitle("jiuyangjueshen");
        download.setDownloadFile("/Users/caoxiangmao/jiuyangjueshen.txt");
        download.execute();
    }


}
