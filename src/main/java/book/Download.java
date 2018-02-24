package book;

import book.txtjia.BookParserTxtJia;
import book.txtjia.ChapterParserTxtJia;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Download {

    private String downloadFile;

    private String bookHref;

    private String bookTitle;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(String downloadFile) {
        this.downloadFile = downloadFile;
    }

    public String getBookHref() {
        return bookHref;
    }

    public void setBookHref(String bookHref) {
        this.bookHref = bookHref;
    }

    public void execute() {
        try {
            BookParser bookParser = getBookParser();
            Book book = new Book();
            book.setTitle(bookTitle);
            book.setHref(bookHref);
            List<Chapter> chapterList = bookParser.parse(book);
            System.out.println("Read Chapter Finish,num=" + chapterList.size());
            PrintWriter writer = new PrintWriter(new FileWriter(downloadFile));
            ChapterParser chapterParser = getChapterParser();
            for (Chapter chapter : chapterList) {
                chapterParser.parse(chapter, writer);
            }
            writer.close();
            System.out.println("Download Finish");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected BookParser getBookParser() {
        if (bookHref.startsWith("https://www.txtjia.com")) {
            return new BookParserTxtJia();
        }
        return null;
    }

    protected ChapterParser getChapterParser() {
        if (bookHref.startsWith("https://www.txtjia.com")) {
            return new ChapterParserTxtJia();
        }
        return null;
    }

}