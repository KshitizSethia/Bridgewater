package concordance;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class WordIndexerTest {

  private void verifyIndexForWord(HashMap<String, List<Integer>> index,
      String word, int... expectedIndices) {
    Integer[] actualIndices = new Integer[index.get(word).size()];
    index.get(word).toArray(actualIndices);

    Assert.assertArrayEquals(expectedIndices,
        ArrayUtils.toPrimitive(actualIndices));
  }

  @Test
  public void test_prettyPrintIndex() {
    String newLine = System.getProperty("line.separator");
    String sentences = "This is a small sentence.";
    
    HashMap<String, List<Integer>> index = WordIndexer.buildWordIndex(sentences);
    String actual = WordIndexer.prettyPrintIndex(index);
    
    StringBuilder expected = new StringBuilder();
    
    expected.append("a\t{1:1}");
    expected.append(newLine);
    expected.append("is\t{1:1}");
    expected.append(newLine);
    expected.append("sentence\t{1:1}");
    expected.append(newLine);
    expected.append("small\t{1:1}");
    expected.append(newLine);
    expected.append("this\t{1:1}");
    expected.append(newLine);
    
    Assert.assertEquals(expected.toString(), actual);
  }

  @Test
  public void test_sentenceWithoutEndingStop() {

  }

  @Test
  public void test_twoSentencesWithExclamationAndQuestion() {
    String sentences = "Hey! Can you split these sentences?";
    HashMap<String, List<Integer>> index =
        WordIndexer.buildWordIndex(sentences);

    verifyIndexForWord(index, "hey", 1);
    verifyIndexForWord(index, "can", 2);
    verifyIndexForWord(index, "you", 2);
    verifyIndexForWord(index, "split", 2);
    verifyIndexForWord(index, "these", 2);
    verifyIndexForWord(index, "sentences", 2);
  }

  @Test
  public void test_oneSentenceWithShortAbbreviation() {
    HashMap<String, List<Integer>> index =
        WordIndexer.buildWordIndex("Can you keep USA as a single word?");

    verifyIndexForWord(index, "can", 1);
    verifyIndexForWord(index, "you", 1);
    verifyIndexForWord(index, "keep", 1);
    verifyIndexForWord(index, "usa", 1);
    verifyIndexForWord(index, "as", 1);
    verifyIndexForWord(index, "a", 1);
    verifyIndexForWord(index, "single", 1);
    verifyIndexForWord(index, "word", 1);
  }

  @Test
  public void test_twoSentencesWithAbbreviationAndPunctuations() {
    String sentences =
        "Given an arbitrary text document written in English, write a program that will generate a concordance, i.e. an alphabetical list of all word occurrences, labeled with word frequencies. Bonus: label each word with the sentence numbers in which each occurrence appeared.";
    HashMap<String, List<Integer>> index =
        WordIndexer.buildWordIndex(sentences);

    verifyIndexForWord(index, "a", 1, 1);
    verifyIndexForWord(index, "all", 1);
    verifyIndexForWord(index, "alphabetical", 1);
    verifyIndexForWord(index, "an", 1, 1);
    verifyIndexForWord(index, "appeared", 2);
    verifyIndexForWord(index, "arbitrary", 1);
    verifyIndexForWord(index, "bonus", 2);
    verifyIndexForWord(index, "concordance", 1);
    verifyIndexForWord(index, "document", 1);
    verifyIndexForWord(index, "each", 2, 2);
    verifyIndexForWord(index, "english", 1);
    verifyIndexForWord(index, "frequencies", 1);
    verifyIndexForWord(index, "generate", 1);
    verifyIndexForWord(index, "given", 1);
    verifyIndexForWord(index, "in", 1, 2);
    verifyIndexForWord(index, "label", 2);
    verifyIndexForWord(index, "labeled", 1);
    verifyIndexForWord(index, "list", 1);
    verifyIndexForWord(index, "numbers", 2);
    verifyIndexForWord(index, "occurrence", 2);
    verifyIndexForWord(index, "occurrences", 1);
    verifyIndexForWord(index, "of", 1);
    verifyIndexForWord(index, "program", 1);
    verifyIndexForWord(index, "sentence", 2);
    verifyIndexForWord(index, "text", 1);
    verifyIndexForWord(index, "that", 1);
    verifyIndexForWord(index, "the", 2);
    verifyIndexForWord(index, "which", 2);
    verifyIndexForWord(index, "will", 1);
    verifyIndexForWord(index, "with", 1, 2);
    verifyIndexForWord(index, "word", 1, 1, 2);
    verifyIndexForWord(index, "write", 1);
    verifyIndexForWord(index, "written", 1);
    verifyIndexForWord(index, "i.e.", 1);
  }

}
