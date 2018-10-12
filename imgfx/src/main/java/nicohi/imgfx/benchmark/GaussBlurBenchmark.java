package nicohi.imgfx.benchmark;

import nicohi.imgfx.Kernel;
import nicohi.imgfx.Picture;
import nicohi.imgfx.filters.GaussBlur;

/**
 *
 * @author nicohi
 */
public class GaussBlurBenchmark {

	/**
	 *
	 * @param img
	 */
	public static void benchmark1(int[][] img) {
		int[] k1D = {1, 3, 6, 3, 1};
		int[][] k2D = {{1, 1, 1, 1, 1},
					   {1, 2, 3, 2, 1},
					   {1, 3, 6, 3, 1},
					   {1, 2, 3, 2, 1},
					   {1, 1, 1, 1, 1}};
		int[][] img1 = Picture.copyImg(img);
		int[][] img2 = Picture.copyImg(img);
		System.out.println("\nImage size is " + img[0].length + "x" + img.length);
		System.out.println("\nTesting 1D Gaussian blur");
		long t1 = System.currentTimeMillis();
		Kernel.applyKernel1D(img1, k1D);
		t1 = System.currentTimeMillis() - t1;
		System.out.println("\n	time taken: " + t1 + "ms");
		System.out.println("\nTesting 2D Gaussian blur");
		long t2 = System.currentTimeMillis();
		Kernel.applyKernel2D(img2, k2D);
		t2 = System.currentTimeMillis() - t2;
		System.out.println("\n	time taken: " + t2 + "ms\n");
	}
	
	/**
	 *
	 * @param img
	 */
	public static void benchmark2(int[][] img) {
		int len = 5;
		int[][] img1 = Picture.copyImg(img);
		int[][] img2 = Picture.copyImg(img);
		System.out.println("\nImage size is " + img[0].length + "x" + img.length);
		System.out.println("\nTesting 1D Gaussian blur");
		System.out.println("Kernel size is: " + GaussBlur.kernel1D2(len).length);
		long t1 = System.currentTimeMillis();
		GaussBlur.gaussianBlur1D(img1, len);
		t1 = System.currentTimeMillis() - t1;
		System.out.println("\n	time taken: " + t1 + "ms");
		System.out.println("\nTesting 2D Gaussian blur");
		System.out.println("Kernel size is: " + GaussBlur.kernel2D(len).length + "x" + GaussBlur.kernel2D(len).length);
		long t2 = System.currentTimeMillis();
		GaussBlur.gaussianBlur2D(img1, len);
		t2 = System.currentTimeMillis() - t2;
		System.out.println("\n	time taken: " + t2 + "ms\n");
	}
}
