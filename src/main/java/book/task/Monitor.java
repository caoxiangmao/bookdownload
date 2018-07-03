package book.task;

import java.util.List;

public class Monitor implements Runnable {

    private List<SubTask> taskList;

    private int total;

    private long start;

    public Monitor(List<SubTask> taskList) {
        this.taskList = taskList;
        for (SubTask subTask : taskList) {
            total += subTask.getChapterNum();
        }
    }


    public void run() {
        try {
            start = System.currentTimeMillis();
            while (true) {
                int doneSum = 0;
                for (SubTask subTask : taskList) {
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
