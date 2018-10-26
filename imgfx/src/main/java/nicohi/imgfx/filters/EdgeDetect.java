package nicohi.imgfx.filters;

import nicohi.imgfx.Kernel;

/**
 *
 * @author nicohi
 */
public class EdgeDetect {
	//https://en.wikipedia.org/wiki/Kernel_(image_processing)

	/**
	 *
	 */
	public static int[][] kernelV1 = {{1,0,-1},
									  {0,0,0},
									  {-1,0,1}};

	/**
	 *
	 */
	public static int[][] kernelV2 = {{0,1,0},
									  {1,-4,1},
									  {0,1,0}};

	/**
	 *
	 */
	public static int[][] kernelV3 = {{-1,-1,-1},
									  {-1,8,-1},
									  {-1,-1,-1}};

	/**
	 *
	 */
	public static int[][] sobelY = {{-1,2,-1},
									{0,0,0},
									{-1,2,-1}};

	/**
	 * Apply given kernel to image and then apply a blur for an edge detect effect.
	 * @param img
	 * @param k
	 * @return
	 */
	public static int[][] edgeDetect(int[][] img, int[][] k) {
		int[][] res = Kernel.applyKernel2D(img, k);
		res = GaussBlur.gaussianBlur1D(res, 1);
		return res;
	}
}
