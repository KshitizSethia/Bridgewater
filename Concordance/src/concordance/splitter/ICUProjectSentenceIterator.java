package concordance.splitter;

import java.util.Iterator;
import java.util.Locale;

import com.ibm.icu.text.BreakIterator;

/**
 * SentenceIterator using icu_project's sentence break iterator
 * 
 * Limits: only supports input string till length Integer.MAX_VALUE
 */
final class ICUProjectSentenceIterator extends StringIterator {

  ICUProjectIterator iterator;
  int                startIndex;
  int                endIndex;

  public ICUProjectSentenceIterator(String sentences) {
    super(sentences);
    this.iterator = new ICUProjectIterator(
        BreakIterator.getSentenceInstance(Locale.ENGLISH), sentences);
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public String next() {
    String result = iterator.next();

    // remove the last "." and return
    result = result.trim();
    result = result.substring(0, result.length() - 1);
    return result;
  }

  @Override
  public Iterator<String> iterator() {
    return this;
  }

}
