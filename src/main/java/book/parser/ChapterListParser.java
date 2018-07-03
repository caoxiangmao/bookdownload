package book.parser;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface ChapterListParser {

    List<Chapter> parse(Document book) throws IOException;

}
