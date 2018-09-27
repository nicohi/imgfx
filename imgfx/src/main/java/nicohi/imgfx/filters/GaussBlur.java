package nicohi.imgfx.filters;

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

	public static int[] kernel1D(double stdev) {
		//In practice only pixels up to six standard deviations away need to be included. https://en.wikipedia.org/wiki/Gaussian_blur
		int width = (int) Math.ceil(6.0 * stdev) + 1;
		int[] k = new int[width];
		
		double fac = 1.0 / Math.sqrt(2 * Math.PI * stdev * stdev);
		
		double expIC = -1.0 / (2 * stdev * stdev);

		for (int i = 1; i < width; i++) {
			double expC = Math.pow(i - width / 2, 2) * expIC;
			double eFac = Math.pow(Math.E, expC);
			k[i] = (int) (fac * eFac);
		}

		return k;
	}
}
