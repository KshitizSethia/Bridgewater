package concordance.splitter;

import java.util.Iterator;
import java.util.Locale;

import com.ibm.icu.text.BreakIterator;

final class ICUProjectWordIterator extends StringIterator {

  ICUProjectIterator iterator;
  String             word;

  public ICUProjectWordIterator(String sentence) {
    super(sentence);
    iterator = new ICUProjectIterator(
        BreakIterator.getWordInstance(Locale.ENGLISH), sentence);
    word = getNextValidWord();
  }

  private String getNextValidWord() {
    while (iterator.hasNext()) {
      String word = iterator.next().toLowerCase();
      if (word.matches("[a-z0-9.]+") && !word.equals(".")) {
        return word;
      }
    }

    return null;
  }

  @Override
  public boolean hasNext() {
    return word != null;
  }

  @Override
  public String next() {
    String result = word;
    word = getNextValidWord();
    return result;
  }

  @Override
  public Iterator<String> iterator() {
    return this;
  }

}
