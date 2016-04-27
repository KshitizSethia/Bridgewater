package concordance.splitter;

import java.util.Iterator;

public abstract class StringIterator implements Iterator<String>, Iterable<String>{

  String content;

  public StringIterator(String content) {
    this.content = content;
  }
}
