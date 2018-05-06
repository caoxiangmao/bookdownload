package book.download;

import book.parser.Chapter;
import book.parser.ChapterParser;
import book.sites.Site;

import java.io.PrintWriter;
import java.util.List;

public class DownloadSubTask implements Runnable {

    private List<Chapter> chapterList;

    private PrintWriter out;

    private Site site;

    public DownloadSubTask(Site site, List<Chapter> chapterList, PrintWriter out) {
        this.chapterList = chapterList;
        this.out = out;
        this.site = site;
    }

    public void run() {
        try {
            for (Chapter chapter : chapterList) {
                ChapterParser chapterParser = site.getChapterParser(chapter.getHref());
                chapterParser.parse(chapter, out);
            }
            out.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
