package nicohi.imgfx.filters;

import java.util.Arrays;
import nicohi.imgfx.Picture;
/**
 *
 * @author nicohi
 */
public class GaussBlur {
	//TODO		

	/**
	 *
	 * @param l
	 * @param stdev
	 * @return
	 */
	
	public static int[][] kernel2D(int l, int stdev) {
		//TODO
		return null;	
	}

	/**
	 * Apply given 1D kernel to image
	 * @param img
	 * @param k
	 * @return new image
	 */
	public static int[][] applyKernel1D(int[][] img, int[] k) {
		int[][] res = new int[img.length][img[0].length];
		System.out.println(Arrays.toString(k));
		for (int y = 0; y < res.length; y++) {
			for (int x = 0; x < res[0].length; x++) {
				int a = img[y][x] & 0xFF000000;
				int accR = 0;
				int accG = 0;
				int accB = 0;
				int wSum = 0;
				for (int i = 0; i < k.length; i++) {
					int os = k.length / 2;
					int xk = x - os + i;
					if (xk < 0 || xk >= img[0].length) xk = x;

					accR += (img[y][xk] & 0x00FF0000) * k[i];
					accG += (img[y][xk] & 0x0000FF00) * k[i];
					accB += (img[y][xk] & 0x000000FF) * k[i];
					wSum += k[i];
				}
				accR = Math.min(accR / wSum, 0x00FF0000);
				accG = Math.min(accG / wSum, 0x0000FF00);
				accB = Math.min(accB / wSum, 0x000000FF);
				//System.out.println("in: " + Integer.toHexString(img[y][x]));
				//System.out.println("out: " + Integer.toHexString(a + accR + accG + accB));
				res[y][x] = addPixels(a, accR + accG + accB);
			}
		}
		return res;
	}

	/**
	 * Add the rgb values of 2 pixels (max 255 per channel)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static int addPixels(int p1, int p2) {
		int a = Math.min(((p1 & 0xFF000000) + (p2 & 0xFF000000)) / 2, 0xFF000000);
		int r1 = (p1 & 0x00FF0000);
		int g1 = (p1 & 0x0000FF00);
		int b1 = p1 & 0x000000FF;

		int r2 = (p2 & 0x00FF0000);
		int g2 = (p2 & 0x0000FF00);
		int b2 = p2 & 0x000000FF;

		return a + (Math.min((int) (r1 + r2), 0x00FF0000))
				 + (Math.min((int) (g1 + g2), 0x0000FF00))
				 + (Math.min((int) (b1 + b2), 0x000000FF));
	}

	/**
	 * Multiply rgb values by double
	 * @param p1
	 * @param fac
	 * @return
	 */
	public static int multiplyPixel(int p1, double fac) {
		int a = p1 & 0xFF000000;
		int r = (p1 & 0x00FF0000) >> 16;
		int g = (p1 & 0x0000FF00) >> 8;
		int b = p1 & 0x000000FF;

		int p2 = a + (Math.min((int) (r * fac), 0xFF) << 16)
				+ (Math.min((int) (g * fac), 0xFF) << 8)
				+ (Math.min((int) (b * fac), 0xFF));

		return p2;

	}

	/**
	 * New 1D gaussian kernel
	 * @param w
	 * @return
	 */
	public static int[] kernel1D(int w) {
		if (w <= 1) {
			int[] k = {1};
			return k;
		}
		if (w % 2 == 0) w += 1;
		int[] k = new int[w];

		int d = w - 1;
		int df = factorial(d);
		for (int i = 0; i < w; i++) {
			k[i] = df / (factorial(i) * factorial(d - i));
		}
		//System.out.println(Arrays.toString(k));	
		return k;
	}

	public static int factorial(int i) {
		if (i <= 1) return 1; 
		return i * factorial(i - 1);
	}
	
	/**
	 * New 1D gaussian kernel
	 * @param stdev
	 * @return
	 */
	public static int[] kernel1D2(double stdev) {
		//TODO implement proper discrete version
		//In practice only pixels up to six standard deviations away need to be included. https://en.wikipedia.org/wiki/Gaussian_blur
		int width = (int) Math.ceil(6.0 * stdev);
		if (width % 2 == 0) width++;
		int[] k = new int[width];
		
		double fac = 1.0 / Math.sqrt(2 * Math.PI * stdev * stdev);
		
		double expIC = -1.0 / (2 * stdev * stdev);

		for (int i = 0; i < width; i++) {
			double expC = Math.pow(i - width / 2, 2) * expIC;
			double eFac = Math.pow(Math.E, expC);
			// TODO 50
			double res = Math.floor(fac * eFac * 50);
			k[i] = (int) res;
		}

		return k;
	}
}
