package blobs;

public enum Boundary {
  top(0), bottom(1), left(2), right(3);

  public final int idx;

  private Boundary(int index) {
    idx = index;
  }
}
