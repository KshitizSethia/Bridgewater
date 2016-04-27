package concordance.splitter;

import java.util.Iterator;

final class WhitespaceSplittingWordIterator extends StringIterator {

  String[] words;
  int      index;

  public WhitespaceSplittingWordIterator(String sentence) {
    super(sentence);
    words = sentence.split("\\s+");
    index = 0;
  }

  @Override
  public boolean hasNext() {
    return index < words.length;
  }

  @Override
  public String next() {
    String word = words[index];
    index++;
    return word.toLowerCase();
  }

  @Override
  public Iterator<String> iterator() {
    return this;
  }

}
