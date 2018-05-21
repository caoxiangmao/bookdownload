package book.download;

import book.parser.Chapter;
import book.parser.ChapterParser;
import book.sites.Site;

import java.io.PrintWriter;
import java.util.List;

public class MonitorTask implements Runnable {

    private List<DownloadSubTask> taskList;

    private int total;

    private long start;

    public MonitorTask(List<DownloadSubTask> taskList) {
        this.taskList = taskList;
        for (DownloadSubTask subTask : taskList) {
            total += subTask.getChapterNum();
        }
    }


    public void run() {
        try {
            start = System.currentTimeMillis();
            while (true) {
                int doneSum = 0;
                for (DownloadSubTask subTask : taskList) {
                    doneSum += subTask.getChapterDoneNum();
                }
                long percent = Math.round(((double) doneSum * 100) / total);
                long elapsed = System.currentTimeMillis() - start;
                System.out.println("[" + percent + "%], [" + elapsed/1000+"秒]");
                Thread.sleep(3000);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
