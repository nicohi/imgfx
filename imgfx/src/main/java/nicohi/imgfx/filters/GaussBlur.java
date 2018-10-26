package nicohi.imgfx.filters;

import java.math.BigInteger;
import java.util.Arrays;
import nicohi.imgfx.util.Kernel;
import nicohi.imgfx.util.Picture;
/**
 *
 * @author nicohi
 */
public class GaussBlur {
	//TODO		

	/**
	 * 2D gaussian kernel based on given standard deviation.
	 * @param stdev
	 * @return gaussian kernel
	 */
	public static int[][] kernel2D(double stdev) {
		int width = (int) Math.ceil(6.0 * stdev);
		if (width % 2 == 0) width++;
		int[][] k = new int[width][width];
		double fac = 1.0 / Math.sqrt(2 * Math.PI * stdev * stdev);
		double expIC = -1.0 / (2 * stdev * stdev);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				double expC = (Math.pow(i - width / 2, 2) + Math.pow(j - width / 2, 2)) * expIC;
				double eFac = Math.pow(Math.E, expC);
				double res = Math.floor(fac * eFac * width * 10);
				k[i][j] = (int) res;
			}
		}
		//System.out.println(Arrays.toString(k));	
		return k;
	}

	/**
	 * 2D gaussian blur with kernel generated from given width value.
	 * @param img
	 * @param w
	 * @return blurred image
	 */
	public static int[][] gaussianBlur2D(int[][] img, double w) {
		int[][] k = kernel2D(w);
		int [][] res = img;
		res = Kernel.applyKernel2D(img, k);
		return res;
	}

	/**
	 * 1D gaussian blur in x and y with kernel generated from given width value.
	 * @param img
	 * @param w
	 * @return blurred image
	 */
	public static int[][] gaussianBlur1D(int[][] img, double w) {
		int[] k = kernel1D(w);
		int [][] res = img;
		res = Kernel.applyKernel1D(img, k);
		res = Picture.rotateRight(res);
		res = Kernel.applyKernel1D(res, k);
		res = Picture.rotateLeft(res);
		return res;
	}
	
	/**
	 * 1D gaussian kernel using formula for gaussian distribution.
	 * @param stdev
	 * @return
	 */
	public static int[] kernel1D(double stdev) {
		//In practice only pixels up to six standard deviations away need to be included. https://en.wikipedia.org/wiki/Gaussian_blur
		int width = (int) Math.ceil(6.0 * stdev);
		if (width % 2 == 0) width++;
		int[] k = new int[width];
		double fac = 1.0 / Math.sqrt(2 * Math.PI * stdev * stdev);
		double expIC = -1.0 / (2 * stdev * stdev);
		for (int i = 0; i < width; i++) {
			double expC = Math.pow(i - width / 2, 2) * expIC;
			double eFac = Math.pow(Math.E, expC);
			// TODO normalization
			double res = Math.floor(fac * eFac * width * 10);
			k[i] = (int) res;
		}
		return k;
	}
}
