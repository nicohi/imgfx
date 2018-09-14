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
	public void redSortTest1() {
		int[][] img1 = {{2,3,1}, {4,2,1}};
		int[][] img2 = {{1,2,3}, {1,2,4}};
		PixelSort.redSort(img1);
		assertEquals(img1, img2);
	}
	
}
