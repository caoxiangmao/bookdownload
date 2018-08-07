package book;

import book.task.Task;

public class Launch {

    public static void main(String[] args) {

        Task download = new Task("jueshizhuzai", "http://www.biqule.com/book_34747/", "/Users/caoxiangmao/Downloads");
        download.execute();


    }


}
