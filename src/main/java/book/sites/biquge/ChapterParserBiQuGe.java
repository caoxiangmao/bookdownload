package book.sites.biquge;

import book.parser.Chapter;
import book.parser.ChapterParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ChapterParserBiQuGe extends ChapterParser {

    @Override
    public void parse(Chapter chapter, PrintWriter writer) throws IOException {
        System.out.println(chapter.getTitle());
        writer.println(chapter.getTitle());
        String href = chapter.getHref();
        Document doc = connect(href);
        Elements bookName = doc.select("div[class=bookname] h1");
        String name = bookName.text();
        Elements newsHeadlines = doc.select("#content");
        if (newsHeadlines.size() == 0) {
            return;
        }
        Element text = newsHeadlines.get(0);
        List<Node> nodeList = text.childNodes();
        for (Node node : nodeList) {
            if (node instanceof TextNode) {
                String str = ((TextNode) node).text();
                if (str == null || str.trim().equals("")) {
                    continue;
                }
                writer.println(str.trim());
            }
        }
    }
}
