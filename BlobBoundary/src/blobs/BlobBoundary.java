package blobs;

public class BlobBoundary {

  private long accessCount;

  /**
  *	Calculates the four boundaries of a single blob given in the 2d array
  *
  * Limits: arrays with max size of Integer.MAX_VALUE in each dimension
  * @return array with the boundaries, use {@link Boundary} enum to index
  * 
  */
  public int[] calculateBlobBoundaries(int[][] grid) {
    accessCount = 0;
    int[] result = new int[] { -1, -1, -1, -1 };

    int[] rc = getPointOfStartOfBlob(grid);

    long accessCountBackup = getAccessCount();
    if (rc[0] == -1 || getNextDirection(grid, Direction.right, rc[0],
        rc[1]) == Direction.left) {
      return result;
    }
    accessCount = accessCountBackup;

    result[Boundary.top.idx] = rc[0];
    result[Boundary.bottom.idx] = rc[0];
    result[Boundary.left.idx] = rc[1];
    result[Boundary.right.idx] = rc[1];

    locateBlobBoundaries(grid, rc[0], rc[1], result);
    return result;
  }

  private void locateBlobBoundaries(final int[][] grid, final int startRow,
      final int startCol, final int[] result) {
    Direction currentDirection = Direction.right;
    int row = startRow;
    int col = startCol;

    do {
      currentDirection = getNextDirection(grid, currentDirection, row, col);
      row += currentDirection.drow;
      col += currentDirection.dcol;
      updateBlobLimits(result, row, col);
    } while (!(row == startRow && col == startCol));
  }

  private Direction getNextDirection(final int[][] grid,
      final Direction previousDirection, final int row, final int col) {
    switch (previousDirection) {
      case right: {
        if (isFilled(grid, row + Direction.up.drow, col + Direction.up.dcol)) {
          return Direction.up;
        } else if (isFilled(grid, row + Direction.right.drow,
            col + Direction.right.dcol)) {
          return Direction.right;
        } else if (isFilled(grid, row + Direction.down.drow,
            col + Direction.down.dcol)) {
          return Direction.down;
        } else {
          return Direction.left;
        }
      }
      case left: {
        if (isFilled(grid, row + Direction.down.drow,
            col + Direction.down.dcol)) {
          return Direction.down;
        } else if (isFilled(grid, row + Direction.left.drow,
            col + Direction.left.dcol)) {
          return Direction.left;
        } else if (isFilled(grid, row + Direction.up.drow,
            col + Direction.up.dcol)) {
          return Direction.up;
        } else {
          return Direction.right;
        }
      }
      case up: {
        if (isFilled(grid, row + Direction.left.drow,
            col + Direction.left.dcol)) {
          return Direction.left;
        } else if (isFilled(grid, row + Direction.up.drow,
            col + Direction.up.dcol)) {
          return Direction.up;
        } else if (isFilled(grid, row + Direction.right.drow,
            col + Direction.right.dcol)) {
          return Direction.right;
        } else {
          return Direction.down;
        }
      }
      case down: {
        if (isFilled(grid, row + Direction.right.drow,
            col + Direction.right.dcol)) {
          return Direction.right;
        } else if (isFilled(grid, row + Direction.down.drow,
            col + Direction.down.dcol)) {
          return Direction.down;
        } else if (isFilled(grid, row + Direction.left.drow,
            col + Direction.left.dcol)) {
          return Direction.left;
        } else {
          return Direction.up;
        }
      }
      default:
        throw new IllegalArgumentException("previousDirection");
    }
  }

  private void updateBlobLimits(int[] result, int row, int col) {
    result[Boundary.top.idx] = Math.min(result[Boundary.top.idx], row);
    result[Boundary.bottom.idx] = Math.max(result[Boundary.bottom.idx], row);
    result[Boundary.left.idx] = Math.min(result[Boundary.left.idx], col);
    result[Boundary.right.idx] = Math.max(result[Boundary.right.idx], col);
  }

  private int[] getPointOfStartOfBlob(int[][] grid) {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (isFilled(grid, row, col)) {
          return new int[] { row, col };
        }
      }
    }
    return new int[] { -1, -1 };
  }

  private boolean isFilled(int[][] grid, int row, int col) {
    if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
      return false;
    }
    accessCount++;
    return grid[row][col] == 1;
  }

  public long getAccessCount() {
    return accessCount;
  }
}
