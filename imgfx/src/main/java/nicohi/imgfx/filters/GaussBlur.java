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
		int[] k = kernel1D2(w);
		int [][] res = img;
		res = Kernel.applyKernel1D(img, k);
		res = Picture.rotateRight(res);
		res = Kernel.applyKernel1D(res, k);
		res = Picture.rotateLeft(res);
		return res;
	}

	/**
	 * Apply given 1D kernel to image
	 * @param img
	 * @param k
	 * @return new image
	 */
	public static int[][] applyKernel1D(int[][] img, int[] k) {
		int[][] res = new int[img.length][img[0].length];
		//System.out.println(Arrays.toString(k));
		for (int y = 0; y < res.length; y++) {
			for (int x = 0; x < res[0].length; x++) {
				int a = img[y][x] & 0xFF000000;
				double accR = 0;
				double accG = 0;
				double accB = 0;
				double wSum = 0;
				for (int i = 0; i < k.length; i++) {
					//System.out.println(Double.MAX_VALUE);
					//if (accR < 0) System.out.println(accR);
					int os = k.length / 2;
					int xk = x - os + i;
					if (xk < 0 || xk >= img[0].length) xk = x;

					accR += (img[y][xk] & 0x00FF0000) * k[i];
					accG += (img[y][xk] & 0x0000FF00) * k[i];
					accB += (img[y][xk] & 0x000000FF) * k[i];
					wSum += k[i];
				}
				int accRi = Math.min(((int) (accR / wSum)) & 0x00FF0000 , 0x00FF0000);
				int accGi = Math.min(((int) (accG / wSum)) & 0x0000FF00 , 0x0000FF00);
				int accBi = Math.min(((int) (accB / wSum)) & 0x000000FF , 0x000000FF);
				//System.out.println("in:  " + Integer.toHexString(img[y][x]));
				//System.out.println("out: " + Long.toHexString(accR / wSum));
				res[y][x] = (int) (a + accRi + accGi + accBi);
			}
		}
		return res;
	}

	/**
	 * 1D gaussian kernel using pascals triangle.
	 * @param w
	 * @return
	 */
	public static int[] kernel1Dfac(int w) {
		if (w <= 1) {
			int[] k = {1};
			return k;
		}
		if (w % 2 == 0) w += 1;
		int[] k = new int[w];
		int d = w - 1;
		BigInteger df = factorial(BigInteger.valueOf(d));
		for (int i = 0; i < w; i++) {
			k[i] = (df.divide((factorial(BigInteger.valueOf(i))
					.multiply(factorial(BigInteger.valueOf(d - i)))))).intValue();
		}
		//System.out.println(Arrays.toString(k));	
		return k;
	}

	/**
	 * Finds the factorial of i for BigIntegers.
	 * @param i
	 * @return
	 */
	public static BigInteger factorial(BigInteger i) {
		//System.out.println(i);
		if (i.equals(BigInteger.ZERO)) return BigInteger.ONE; 
		return i.multiply(factorial(i.subtract(BigInteger.ONE)));
	}
	
	/**
	 * 1D gaussian kernel using formula for gaussian distribution.
	 * @param stdev
	 * @return
	 */
	public static int[] kernel1D2(double stdev) {
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
