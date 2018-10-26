package nicohi.imgfx.filters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicohi
 */
public class GaussBlurTest {
	
	public GaussBlurTest() {
	}
	
	@Before
	public void setUp() {
	}

	@Test
	public void kernel1DTest() {
		int[] k1 = {0,3,16,27,16,3,0};
		int[] k2 = GaussBlur.kernel1D(1);
		assertArrayEquals(k1, k2);
	}

	@Test
	public void kernel2DTest() {
		int[][] k1 = {{0,0,0,0,0,0,0},
					  {0,0,2,3,2,0,0},
					  {0,2,10,16,10,2,0},
					  {0,3,16,27,16,3,0},
					  {0,2,10,16,10,2,0},
					  {0,0,2,3,2,0,0},
					  {0,0,0,0,0,0,0},
		};
		int[][] k2 = GaussBlur.kernel2D(1);
		assertArrayEquals(k1, k2);
	}
	
}
