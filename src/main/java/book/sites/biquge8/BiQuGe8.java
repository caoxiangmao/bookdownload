package book.sites.biquge8;

import book.parser.BookParser;
import book.parser.ChapterParser;
import book.sites.Site;

public class BiQuGe8 implements Site {

    public ChapterParser getChapterParser(String chapterUrl) {
        return new ChapterParserBiQuGe8();
    }

    public BookParser getBookParser(String bookUrl) {
        return new BookParserBiQuGe8();
    }

    public boolean checkOwner(String bookUrl) {
        if (bookUrl.indexOf("www.biquge8.com") < 0) {
            return false;
        }
        return true;
    }

}
