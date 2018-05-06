package book.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class ChapterParser {

    private static final int RETRY_TIMES = 3;

    /**
     * parse
     *
     * @param chapter
     * @param writer
     * @throws IOException
     */
    public abstract void parse(Chapter chapter, PrintWriter writer) throws IOException;

    protected Document connect(String href) throws IOException {
        int count = 0;
        while (count < RETRY_TIMES) {
            try {
                Document doc = Jsoup.connect(href).get();
                count++;
                return doc;
            } catch (Throwable e) {
                e.printStackTrace();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        throw new IOException("connect failure," + href);

    }

}
