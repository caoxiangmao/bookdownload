package book.sites.biqugex;

import book.parser.BookParser;
import book.parser.ChapterParser;
import book.sites.Site;
import book.sites.biquge8.BookParserBiQuGe8;

public class BiQuGeX implements Site {

    public ChapterParser getChapterParser(String chapterUrl) {
        return new ChapterParserBiQuGeX();
    }

    public BookParser getBookParser(String bookUrl) {
        return new BookParserBiQuGeX();
    }

    public boolean checkOwner(String bookUrl) {
        if (bookUrl.indexOf("www.biqugex.com") < 0) {
            return false;
        }
        return true;
    }

}
