package nicohi.imgfx.filters;

import nicohi.imgfx.Picture;
/**
 *
 * @author nicohi
 */
public class GaussBlur {
	//TODO		
	
	public static int[][] kernel2D(int l, int stdev) {
		//TODO
		return null;	
	}

	public static int[][] applyKernel1D(int[][] img, int[] k) {
		int[][] res = new int[img[0].length][img[0].length];
		for (int y = 0; y < res.length; y++) {
			for (int x = 0; x < res[0].length; x++) {
				//TODO
				int p ;
			}
		}
		return res;
	}

	public static int multiplyPixel(int p) {
		//TODO
		int r = p;
		return 0;

	}
	
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
