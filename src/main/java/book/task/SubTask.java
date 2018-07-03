package book.task;

import book.parser.Chapter;
import book.parser.ContentParser;
import book.sites.Site;

import java.io.PrintWriter;
import java.util.List;

public class SubTask implements Runnable {

    private List<Chapter> chapterList;

    private PrintWriter out;

    private ContentParser parser;

    private int chapterNum;

    private volatile int chapterDoneNum;

    public SubTask(ContentParser parser, List<Chapter> chapterList, PrintWriter out) {
        this.chapterList = chapterList;
        this.out = out;
        this.parser = parser;
        this.chapterNum = chapterList.size();
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public int getChapterDoneNum() {
        return chapterDoneNum;
    }

    public void run() {
        try {
            for (Chapter chapter : chapterList) {
                parser.parse(chapter, out);
                chapterDoneNum++;
            }
            out.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
