package book.sites.txtjia;

import book.parser.BookParser;
import book.parser.ChapterParser;
import book.sites.Site;

public class TxtJia implements Site {

    public ChapterParser getChapterParser(String chapterUrl) {
        return new ChapterParserTxtJia();
    }

    public BookParser getBookParser(String bookUrl) {
        return new BookParserTxtJia();
    }

    public boolean checkOwner(String bookUrl) {
        if (bookUrl.indexOf("www.txtjia.com") < 0) {
            return false;
        }
        return true;
    }
}
