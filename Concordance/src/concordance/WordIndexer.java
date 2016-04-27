package concordance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import concordance.splitter.SplitterFactory;
import concordance.splitter.StringIterator;

public final class WordIndexer {

  private WordIndexer() {
    // block instantiation, this is meant to be a library
  }

  /**
   * Calculate the concordance of a set of sentences
   * @param sentences Set of sentences whose concordance needs to be generated
   * @return Map from the word to the sentences it occurs in
   */
  public static final HashMap<String, List<Integer>>
      buildWordIndex(String sentences) {

    HashMap<String, List<Integer>> index = new HashMap<>();

    StringIterator sentenceIterator =
        SplitterFactory.createSentenceSplitter(sentences);

    int sentenceIndex = 0;

    for (String sentence : sentenceIterator) {
      sentenceIndex++;

      // get words from sentences
      StringIterator wordIterator =
          SplitterFactory.createWordSplitter(sentence);
      for (String word : wordIterator) {
        // add to index
        if (!index.containsKey(word)) {
          index.put(word, new ArrayList<Integer>());
        }
        index.get(word).add(sentenceIndex);
      }

    }

    return index;
  }

  /**
   * Pretty prints the concordance for a set of sentences 
   * @param index the concordance of the sentences
   * @return the pretty printed string
   */
  public static final String
      prettyPrintIndex(HashMap<String, List<Integer>> index) {
    StringBuilder sbr = new StringBuilder();
    List<String> sortedWords = new ArrayList<String>(index.keySet());
    Collections.sort(sortedWords);

    for (String word : sortedWords) {
      List<Integer> occurrences = index.get(word);
      sbr.append(String.format("%s\t{%d:%s}%n", word, occurrences.size(),
          StringUtils.join(occurrences, ",")));
    }
    return sbr.toString();
  }
}
