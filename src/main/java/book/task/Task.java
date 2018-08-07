package book.task;

import book.parser.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private String dir;

    private String bookHref;

    private String bookTitle;

    List<ChapterListParser> chapterListParserList = new ArrayList<ChapterListParser>();

    List<ContentParser> contentParserList = new ArrayList<ContentParser>();

    public Task(String bookTitle, String bookHref, String dir) {
        this.bookHref = bookHref;
        this.bookTitle = bookTitle;
        this.dir = dir;
        chapterListParserList.add(new ChapterListParserImpl1());
        chapterListParserList.add(new ChapterListParserImpl2());
        chapterListParserList.add(new ChapterListParserImpl3());
        contentParserList.add(new ContentParserImpl1());
        contentParserList.add(new ContentParserImpl2());
    }

    private List<Chapter> parse(Document doc) throws IOException {
        for (ChapterListParser parser : chapterListParserList) {
            List<Chapter> chapterList = parser.parse(doc);
            if (chapterList.size() > 50) {
                return chapterList;
            }
        }
        throw new RuntimeException("ChapterListParser Not Found");
    }

    private ContentParser selectParser(List<Chapter> chapterList) throws IOException {
        for (ContentParser parser : contentParserList) {
            if (parser.canRead(chapterList.get(0))) {
                return parser;
            }
        }
        throw new RuntimeException("ContentParser Not Found");
    }

    public void execute() {
        try {
            String href = bookHref;
            Document doc = Jsoup.connect(href).get();
            List<Chapter> chapterList = parse(doc);
            chapterList = filter(chapterList);
            ContentParser parser = selectParser(chapterList);

            List<SubTask> subTaskList = new ArrayList<SubTask>();

            System.out.println("ChapterList,num=" + chapterList.size());
            int pages = 3;
            int pageSize = chapterList.size() / pages;
            Thread[] threads = new Thread[pages];
            for (int i = 0; i < pages; i++) {
                List<Chapter> subList = chapterList.subList(pageSize * i, Math.min((i + 1) * pageSize, chapterList.size()));
                SubTask subTask = new SubTask(parser, subList, new PrintWriter((new FileWriter(dir + "/" + bookTitle + "_" + i + ".txt"))));
                subTaskList.add(subTask);
                threads[i] = new Thread(subTask);
                threads[i].start();
            }

            Thread monitorThread = new Thread(new Monitor(subTaskList));
            monitorThread.setDaemon(true);
            monitorThread.start();

            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("Task Finish");
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
