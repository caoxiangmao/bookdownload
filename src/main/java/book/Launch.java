package book;

import book.download.DownloadTask;

import java.io.IOException;

public class Launch {

    public static void main(String[] args) {

        DownloadTask download = new DownloadTask("9074", "http://www.biquge8.com/9_9074/", "/Users/xxxx/Downloads");
        download.execute();


    }


}
