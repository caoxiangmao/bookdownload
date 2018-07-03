package book;

import book.task.Task;

public class Launch {

    public static void main(String[] args) {

        Task download = new Task("4_4338", "http://www.biquge.com.tw/4_4338/", "/Users/caoxiangmao/Downloads");
        download.execute();


    }


}
