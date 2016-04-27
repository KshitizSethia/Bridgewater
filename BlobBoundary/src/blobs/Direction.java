package blobs;

enum Direction {
  up(-1, 0), down(1, 0), left(0, -1), right(0, 1), none(0, 0);

  public final int drow;
  public final int dcol;

  private Direction(int dx, int dy) {
    this.drow = dx;
    this.dcol = dy;
  }
}
