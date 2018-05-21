package book.sites.biquge;

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

public class BookParserBiQuGe implements BookParser {


    public List<Chapter> parse(Book book) throws IOException {

        String href = book.getHref();
        Document doc = Jsoup.connect(href).get();
        Elements newsHeadlines = doc.select("#list a");
        List<Chapter> chapterList = new ArrayList<Chapter>();
        for (Element element : newsHeadlines) {
            String href1 = element.attr("href");
            if (href1 == null || !href1.startsWith("/")) {
                continue;
            }
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
