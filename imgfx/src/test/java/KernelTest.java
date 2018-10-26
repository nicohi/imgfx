
import nicohi.imgfx.util.Kernel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicohi
 */
public class KernelTest {
	
	public KernelTest() {
	}
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void apply2DKernelTest1() {
		int[][] img = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] k = {{0,0,0},{0,1,0},{0,0,0}};
		assertArrayEquals(img, Kernel.applyKernel2D(img, k));
	}

	@Test
	public void apply1DKernelTest1() {
		int[][] img = {{1,2,3},{4,5,6},{7,8,9}};
		int[] k = {0,1,0};
		assertArrayEquals(img, Kernel.applyKernel1D(img, k));
	}
}
