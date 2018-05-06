package book.sites.xxbiquge;

import book.parser.BookParser;
import book.parser.ChapterParser;
import book.sites.Site;

public class XxBiQuGe implements Site {

    public ChapterParser getChapterParser(String chapterUrl) {
        return new ChapterParserXxBiQuGe();
    }

    public BookParser getBookParser(String bookUrl) {
        return new BookParserXxBiQuGe();
    }

    public boolean checkOwner(String bookUrl) {
        if (bookUrl.indexOf("www.xxbiquge.com") < 0) {
            return false;
        }
        return true;
    }
}
