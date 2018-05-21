package book.download;

import book.parser.Book;
import book.parser.BookParser;
import book.parser.Chapter;
import book.parser.ChapterParser;
import book.sites.Site;
import book.sites.SiteList;
import book.sites.biquguan.BookParserBiQuGuan;
import book.sites.biquguan.ChapterParserBiQuGuan;
import book.sites.txtjia.BookParserTxtJia;
import book.sites.txtjia.ChapterParserTxtJia;
import book.sites.xsla.BookParserXsLa;
import book.sites.xsla.ChapterParserXsLa;
import book.sites.xxbiquge.BookParserXxBiQuGe;
import book.sites.xxbiquge.ChapterParserXxBiQuGe;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class DownloadTask {

    private String dir;

    private String bookHref;

    private String bookTitle;

    public DownloadTask(String bookTitle, String bookHref, String dir) {
        this.bookHref = bookHref;
        this.bookTitle = bookTitle;
        this.dir = dir;
    }

    public void execute() {
        try {
            Book book = new Book();
            book.setTitle(bookTitle);
            book.setHref(bookHref);

            Site site = SiteList.getInstance().getSite(bookHref);
            if (site == null) {
                throw new IllegalStateException(bookHref);
            }
            List<DownloadSubTask> subTaskList = new ArrayList<DownloadSubTask>();
            List<Chapter> chapterList = filter(site.getBookParser(bookHref).parse(book));

            System.out.println("ChapterList,num=" + chapterList.size());
            int pages = 3;
            int pageSize = chapterList.size() / pages;
            Thread[] threads = new Thread[pages];
            for (int i = 0; i < pages; i++) {
                List<Chapter> subList = chapterList.subList(pageSize * i, Math.min((i + 1) * pageSize, chapterList.size()));
                DownloadSubTask subTask = new DownloadSubTask(site, subList, new PrintWriter((new FileWriter(dir + "/" + bookTitle + "_" + i + ".txt"))));
                subTaskList.add(subTask);
                threads[i] = new Thread(subTask);
                threads[i].start();
            }

            Thread monitorThread = new Thread(new MonitorTask(subTaskList));
            monitorThread.setDaemon(true);
            monitorThread.start();

            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("DownloadTask Finish");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<Chapter> filter(List<Chapter> chapters) {
        for (int i = 0; i < chapters.size(); i++) {
            String title = chapters.get(i).getTitle().trim();
            if (title.startsWith("001") || title.startsWith("第一章") || title.startsWith("第1章") || title.startsWith("序章")) {
                return chapters.subList(i, chapters.size());
            }
        }
        return null;
    }

}
