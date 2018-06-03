package book.sites.biqugex;

import book.parser.Book;
import book.parser.BookParser;
import book.parser.Chapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookParserBiQuGeX implements BookParser {


    public List<Chapter> parse(Book book) throws IOException {

        String href = book.getHref();
        Document doc = Jsoup.connect(href).get();
        Elements newsHeadlines = doc.select("div[class=listmain] a");

        List<Chapter> chapterList = new ArrayList<Chapter>();
        for (Element element : newsHeadlines) {
            String chapterUrl = element.absUrl("href");
            String chapterTitle = element.text();
            if (chapterUrl == null || chapterTitle == null || chapterTitle.equals("") || chapterUrl.equals("")) {
                continue;
            }
            Chapter chapter = new Chapter();
            chapter.setTitle(chapterTitle);
            chapter.setHref(chapterUrl);
            chapterList.add(chapter);
        }
        return chapterList;
    }

}
