package concordance.splitter;

public class SplitterFactory {

  private SplitterFactory() {
    // block instantiation, factory
  }

  public static StringIterator createWordSplitter(String sentence) {
    return new ICUProjectWordIterator(sentence);
  }

  public static StringIterator createSentenceSplitter(String sentences) {
    return new ICUProjectSentenceIterator(sentences);
  }
}
