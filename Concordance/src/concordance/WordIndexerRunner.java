package concordance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public final class WordIndexerRunner {

  public static void main(String[] args) throws FileNotFoundException {
    if (args.length < 1) {
      System.out.format("Concordance%n%n" + "Arguments: <input_file_path>%n");
      System.exit(0);
    }
    String data = new Scanner(new File(args[0])).useDelimiter("\\Z").next();

    HashMap<String, List<Integer>> index = WordIndexer.buildWordIndex(data);
    System.out.println(WordIndexer.prettyPrintIndex(index));
  }

}
