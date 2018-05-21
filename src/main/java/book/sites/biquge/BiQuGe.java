package book.sites.biquge;

import book.parser.BookParser;
import book.parser.ChapterParser;
import book.sites.Site;
import book.sites.xxbiquge.BookParserXxBiQuGe;
import book.sites.xxbiquge.ChapterParserXxBiQuGe;

public class BiQuGe implements Site {

    public ChapterParser getChapterParser(String chapterUrl) {
        return new ChapterParserBiQuGe();
    }

    public BookParser getBookParser(String bookUrl) {
        return new BookParserBiQuGe();
    }

    public boolean checkOwner(String bookUrl) {
        if (bookUrl.indexOf("www.biquge.com") < 0) {
            return false;
        }
        return true;
    }
}
