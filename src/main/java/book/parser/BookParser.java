package book.parser;

import java.io.IOException;
import java.util.List;

public interface BookParser {

    List<Chapter> parse(Book book) throws IOException;

}
