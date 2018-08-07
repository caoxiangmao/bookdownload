package book.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChapterListParserImpl3 implements ChapterListParser {


    public List<Chapter> parse(Document doc) throws IOException {

        Elements newsHeadlines = doc.select("div[class=article-list] a");

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
