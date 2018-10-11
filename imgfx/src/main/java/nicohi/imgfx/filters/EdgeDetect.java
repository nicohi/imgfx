package nicohi.imgfx.filters;

import nicohi.imgfx.Kernel;

/**
 *
 * @author nicohi
 */
public class EdgeDetect {
	//https://en.wikipedia.org/wiki/Kernel_(image_processing)
	public static int[][] kernelV1 = {{1,0,-1},
									  {0,0,0},
									  {-1,0,1}};

	public static int[][] kernelV2 = {{0,1,0},
									  {1,-4,1},
									  {0,1,0}};

	public static int[][] kernelV3 = {{-1,-1,-1},
									  {-1,8,-1},
									  {-1,-1,-1}};

	public static int[][] edgeDetect(int[][] img, int[][] k) {
		int[][] res = Kernel.applyKernel2D(img, k);
		return res;
	}
}
