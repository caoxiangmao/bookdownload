package book.sites.biquguan;

import book.parser.BookParser;
import book.parser.ChapterParser;
import book.sites.Site;

public class BiQuGuan implements Site {

    public ChapterParser getChapterParser(String chapterUrl) {
        return new ChapterParserBiQuGuan();
    }

    public BookParser getBookParser(String bookUrl) {
        return new BookParserBiQuGuan();
    }

    public boolean checkOwner(String bookUrl) {
        if (bookUrl.indexOf("www.biquguan.com") < 0) {
            return false;
        }
        return true;
    }
}
