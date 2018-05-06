package book.sites;

import book.parser.BookParser;
import book.parser.ChapterParser;

public interface Site {

    ChapterParser getChapterParser(String chapterUrl);

    BookParser getBookParser(String bookUrl);

    boolean checkOwner(String bookUrl);
}
