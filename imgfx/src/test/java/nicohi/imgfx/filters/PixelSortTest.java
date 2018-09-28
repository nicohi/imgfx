package nicohi.imgfx.filters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicohi
 */
public class PixelSortTest {
	
	public PixelSortTest() {
	}
	
	@Before
	public void setUp() {
	}

	@Test
	public void tSortTest1() {
		int[][] img1 = {{2,3,1}, {4,2,1}};
		int[][] img2 = {{1,2,3}, {1,2,4}};
		PixelSort.tSort(0, img1);
		assertArrayEquals(img2, img1);
	}
	
	@Test
	public void redSortTest1() {
		int[][] img1 = {{2,3,1}, {4,2,1}};
		int[][] img2 = {{1,2,3}, {1,2,4}};
		PixelSort.redSort(img1);
		assertArrayEquals(img2, img1);
	}

	@Test
	public void sortRangeTest1() {
		int[] img1 = {2,3,1,1};
		int[] img2 = {1,2,3,1};
		PixelSort.sortRange(0, 3, img1);
		assertArrayEquals(img2, img1);
	}

	@Test
	public void binaryThresholdTest1() {
		int[][] img1 = {{1,3,0}, {1,0,2}};
		int[][] img2 = {{0xFF000000,0xFFFFFFFF,0xFF000000}, {0xFF000000,0xFF000000,0xFFFFFFFF}};
		PixelSort.applyF(PixelSort.binaryThreshold(2), img1);
		assertArrayEquals(img2[0], img1[0]);
		assertArrayEquals(img2[1], img1[1]);
	}
	
}
