package concordance.splitter;

import java.util.Iterator;

import com.ibm.icu.text.BreakIterator;

class ICUProjectIterator implements Iterator<String> {

  String        content;
  BreakIterator iterator;
  int           startIndex;
  int           endIndex;

  public ICUProjectIterator(BreakIterator iter, String content) {
    this.iterator = iter;
    iterator.setText(content);
    this.content = content;
    startIndex = iter.first();
    endIndex = iter.next();
  }

  @Override
  public boolean hasNext() {
    return endIndex != BreakIterator.DONE;
  }

  @Override
  public String next() {
    String result = content.substring(startIndex, endIndex);
    startIndex = endIndex;
    endIndex = iterator.next();
    return result;
  }
}
