package nicohi.imgfx;

import java.util.Arrays;

/**
 *
 * @author nicohi
 */
public class Kernel {

	public static int[][] applyKernel2D(int[][] img, int[][] k) {
		int[][] res = new int[img.length][img[0].length];
		//iterate thru image
		for (int y = 0; y < res.length; y++) {
			for (int x = 0; x < res[0].length; x++) {
				int a = img[y][x] & 0xFF000000;
				double accR = 0;
				double accG = 0;
				double accB = 0;
				double wSum = 0;
				//iterate thru 2D kernel
				for (int i = 0; i < k.length; i++) {
					int kI = y - (k.length / 2) + i;
					if (kI < 0 || kI >= img.length) continue;
					for (int j = 0; j < k[0].length; j++) {
						int kJ = x - (k[0].length / 2) + j;
						//if (kJ < 0 || kJ >= img[0].length) kJ = x;
						if (kJ < 0 || kJ >= img[0].length) continue;

						accR += (img[kI][kJ] & 0x00FF0000) * k[i][j];
						accG += (img[kI][kJ] & 0x0000FF00) * k[i][j];
						accB += (img[kI][kJ] & 0x000000FF) * k[i][j];
						wSum += k[i][j];
					}
				}
				//calculate pixel value
				int accRi = Math.min(((int) (accR / wSum)) & 0x00FF0000 , 0x00FF0000);
				int accGi = Math.min(((int) (accG / wSum)) & 0x0000FF00 , 0x0000FF00);
				int accBi = Math.min(((int) (accB / wSum)) & 0x000000FF , 0x000000FF);
				res[y][x] = (int) (a + accRi + accGi + accBi);
			}
		}
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
					if (xk < 0 || xk >= img[0].length) continue;

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
}
