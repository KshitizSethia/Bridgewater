package blobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class BlobBoundaryRunner {

  public static void main(String[] args) throws FileNotFoundException {
    if (args.length < 1) {
      System.out.format("Blob Boundary Calculator%n%n"
          + "Arguments: <input_file_path>%n%nInput file format:%n"
          + "- one row in a line%n"
          + "- numbers in a line without any seperation%n");
      System.out.format("%nExample:%n000%n010%n");
      System.exit(0);
    }

    int[][] grid = readGrid(args[0]);
    BlobBoundary obj = new BlobBoundary();
    int[] results = obj.calculateBlobBoundaries(grid);

    System.out.format("Cell Reads: %d%n", obj.getAccessCount());
    System.out.format("Top: %d%n", results[Boundary.top.idx]);
    System.out.format("Left: %d%n", results[Boundary.left.idx]);
    System.out.format("Bottom: %d%n", results[Boundary.bottom.idx]);
    System.out.format("Right: %d%n", results[Boundary.right.idx]);
  }

  private static int[][] readGrid(String filePath)
      throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));
    List<String> lines = new ArrayList<String>();
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }
    scanner.close();

    if (lines.size() == 0) {
      System.out.println("ERROR: blank input, exiting");
      System.exit(-1);
    }

    int numCols = lines.get(0).split("").length;
    int[][] result = new int[lines.size()][numCols];

    for (int row = 0; row < result.length; row++) {
      String[] nums = lines.get(row).split("");
      for (int col = 0; col < result[0].length; col++) {
        if (nums[col].equals("1")) {
          result[row][col] = 1;
        } else {
          result[row][col] = 0;
        }
      }
    }

    return result;
  }

}
