package book;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public interface ChapterParser {
    void parse(Chapter chapter, PrintWriter writer) throws IOException;
}
