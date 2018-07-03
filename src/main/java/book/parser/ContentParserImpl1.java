package book.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ContentParserImpl1 extends ContentParser {

    @Override
    public void parse(Chapter chapter, PrintWriter writer) throws IOException {
        System.out.println(chapter.getTitle());
        writer.println(chapter.getTitle());
        String href = chapter.getHref();
        Document doc = connect(href);
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

    public boolean canRead(Chapter chapter) throws IOException {
        Document doc = connect(chapter.getHref());
        Elements newsHeadlines = doc.select("#content");
        if (newsHeadlines.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
