package nicohi.imgfx.filters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicohi
 */
public class EdgeDetectTest {
	
	public EdgeDetectTest() {
	}
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void edgeDetectTest1() {
		int[][] k1 = {{10526880,10395294,10526880},
					  {7500402,6974058,7500402},
					  {7500402,6381921,7500402}};
		int[][] k2 = {{0,0,0},
					  {0,0xFFFFFF,0xFFFFFF},
					  {0,0,0}};
		k2 = EdgeDetect.edgeDetect(k2, EdgeDetect.kernelV1);
		assertArrayEquals(k1, k2);
	}
}
