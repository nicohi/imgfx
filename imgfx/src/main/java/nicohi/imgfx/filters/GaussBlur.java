package nicohi.imgfx.filters;

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
		for (int y = 0; y < res.length; y++) {
			for (int x = 0; x < res[0].length; x++) {
				int p = 0xFF000000;
				//TODO
				for (int i = 0; i < k.length; i++) {
					int os = k.length / 2;
					int xk = x - os + i;
					if (xk < 0 || xk >= img[0].length) 
						//if kernel is on edge of img
						p = addPixels(p, multiplyPixel(img[y][x], 1 / k[i]));
					else
						p = addPixels(p, multiplyPixel(img[y][xk], 1 / k[i]));
				}
				res[y][x] = p;
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
		int r1 = (p1 & 0x00FF0000) >> 16;
		int g1 = (p1 & 0x0000FF00) >> 8;
		int b1 = p1 & 0x000000FF;

		int r2 = (p2 & 0x00FF0000) >> 16;
		int g2 = (p2 & 0x0000FF00) >> 8;
		int b2 = p2 & 0x000000FF;

		return a + (Math.min((int) (r1 + r2), 0xFF) << 16)
				+ (Math.min((int) (g1 + g2), 0xFF) << 8)
				+ (Math.min((int) (b1 + b2), 0xFF));
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
	 * @param stdev
	 * @return
	 */
	public static int[] kernel1D(double stdev) {
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
			//TODO fix arbitrary factor 50
			double res = Math.floor(fac * eFac * 50) + 1;
			k[i] = (int) res;
		}

		return k;
	}
}
