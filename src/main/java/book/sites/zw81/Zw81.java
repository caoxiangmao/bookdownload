package book.sites.zw81;

import book.parser.BookParser;
import book.parser.ChapterParser;
import book.sites.Site;

public class Zw81 implements Site {

    public ChapterParser getChapterParser(String chapterUrl) {
        return new ChapterParserZw81();
    }

    public BookParser getBookParser(String bookUrl) {
        return new BookParserZw81();
    }

    public boolean checkOwner(String bookUrl) {
        if (bookUrl.indexOf("www.81zw.us") < 0) {
            return false;
        }
        return true;
    }
}
