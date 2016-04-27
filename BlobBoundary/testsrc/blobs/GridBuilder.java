package blobs;

public class GridBuilder {

  int[][] grid;

  public GridBuilder(int numRows, int numCols) {
    if (numRows == 0 || numCols == 0) {
      throw new IllegalArgumentException(
          "one of the dimensions cannot be zero");
    }
    grid = new int[numRows][numCols];
  }

  public GridBuilder fillBox(int startRow, int startCol, int numRows,
      int numCols) {
    return fillBox(startRow, startCol, numRows, numCols, true);
  }

  public GridBuilder clearBox(int startRow, int startCol, int numRows,
      int numCols) {
    return fillBox(startRow, startCol, numRows, numCols, false);
  }

  private GridBuilder fillBox(int startRow, int startCol, int numRows,
      int numCols, boolean fillUp) {
    if (startRow < 0 || startRow + numRows >= grid.length || startCol < 0
        || startCol + numCols >= grid[0].length) {
      throw new IllegalArgumentException("box boundaries outside scope");
    }

    for (int row = startRow; row < startRow + numRows; row++) {
      for (int col = startCol; col < startCol + numCols; col++) {
        grid[row][col] = fillUp ? 1 : 0;
      }
    }

    return this;
  }

  public int[][] build() {
    int[][] result = new int[grid.length][grid[0].length];
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        result[row][col] = grid[row][col];
      }
    }
    return result;
  }
}
