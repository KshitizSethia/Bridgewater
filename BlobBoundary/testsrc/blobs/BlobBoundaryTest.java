package blobs;

import org.junit.Assert;
import org.junit.Test;
import blobs.GridBuilder;

public class BlobBoundaryTest {

  static final BlobBoundary testObj = new BlobBoundary();

  @Test
  public void test_singlePoint() {
    int[] result = testObj.calculateBlobBoundaries(new int[][] {
        new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 0, 0 } });
    Assert.assertEquals(result[Boundary.top.idx], -1);
    Assert.assertEquals(result[Boundary.bottom.idx], -1);
    Assert.assertEquals(result[Boundary.left.idx], -1);
    Assert.assertEquals(result[Boundary.right.idx], -1);
  }

  @Test
  public void test_twoPoints_horizontal() {
    int[] result = testObj.calculateBlobBoundaries(new int[][] {
        new int[] { 0, 0, 0 }, new int[] { 0, 1, 1 }, new int[] { 0, 0, 0 } });
    Assert.assertEquals(result[Boundary.top.idx], 1);
    Assert.assertEquals(result[Boundary.bottom.idx], 1);
    Assert.assertEquals(result[Boundary.left.idx], 1);
    Assert.assertEquals(result[Boundary.right.idx], 2);
  }

  @Test
  public void test_twoPoints_vertical() {
    int[] result = testObj.calculateBlobBoundaries(new int[][] {
        new int[] { 0, 0, 0 }, new int[] { 0, 1, 0 }, new int[] { 0, 1, 0 } });
    Assert.assertEquals(result[Boundary.top.idx], 1);
    Assert.assertEquals(result[Boundary.bottom.idx], 2);
    Assert.assertEquals(result[Boundary.left.idx], 1);
    Assert.assertEquals(result[Boundary.right.idx], 1);
  }

  @Test
  public void test_square() {
    int[][] grid = new GridBuilder(4, 4).fillBox(1, 1, 2, 2).build();
    int[] result = testObj.calculateBlobBoundaries(grid);

    Assert.assertEquals(result[Boundary.top.idx], 1);
    Assert.assertEquals(result[Boundary.left.idx], 1);
    Assert.assertEquals(result[Boundary.bottom.idx], 2);
    Assert.assertEquals(result[Boundary.right.idx], 2);
  }

  @Test
  public void test_J() {
    int[][] grid = new GridBuilder(10, 6).fillBox(1, 2, 1, 3)
        .fillBox(1, 3, 4, 1).fillBox(4, 1, 1, 3).fillBox(3, 1, 1, 1).build();
    int[] result = testObj.calculateBlobBoundaries(grid);

    Assert.assertEquals(result[Boundary.top.idx], 1);
    Assert.assertEquals(result[Boundary.left.idx], 1);
    Assert.assertEquals(result[Boundary.bottom.idx], 4);
    Assert.assertEquals(result[Boundary.right.idx], 4);
  }

  @Test
  public void test_exampleScenario() {
    int[][] grid = new GridBuilder(10, 10).fillBox(1, 2, 4, 3)
        .fillBox(2, 4, 6, 3).clearBox(3, 3, 1, 3).build();
    int[] result = testObj.calculateBlobBoundaries(grid);

    Assert.assertEquals(1, result[Boundary.top.idx]);
    Assert.assertEquals(2, result[Boundary.left.idx]);
    Assert.assertEquals(7, result[Boundary.bottom.idx]);
    Assert.assertEquals(6, result[Boundary.right.idx]);
    Assert.assertEquals(44, testObj.getAccessCount());
  }

  @Test
  public void test_exampleScenario_checkAccessCount() {
    int[][] grid = new GridBuilder(10, 10).fillBox(1, 2, 4, 3)
        .fillBox(2, 4, 6, 3).clearBox(3, 3, 1, 3).build();
    testObj.calculateBlobBoundaries(grid);

    Assert.assertEquals(44, testObj.getAccessCount());
  }

  @Test
  public void test_largestGrid_all_ones() {
    // TODO
  }
}
