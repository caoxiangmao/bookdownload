package book.sites.xsla;

import book.parser.BookParser;
import book.parser.ChapterParser;
import book.sites.Site;

public class XsLa implements Site {

    public ChapterParser getChapterParser(String chapterUrl) {
        return new ChapterParserXsLa();
    }

    public BookParser getBookParser(String bookUrl) {
        return new BookParserXsLa();
    }

    public boolean checkOwner(String bookUrl) {
        if (bookUrl.indexOf("www.xs.la") < 0) {
            return false;
        }
        return true;
    }
}
