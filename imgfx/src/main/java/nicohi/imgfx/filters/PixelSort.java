package nicohi.imgfx.filters;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import nicohi.imgfx.Picture;

/**
 *
 * @author nicohi
 */
public class PixelSort {

	/**
	 * Sorts horizontal rows of image based on the red value of each pixel
	 * @param img
	 */
	public static void redSort(int[][] img) {
		//TODO implement own sort
		for (int y = 0; y < img.length; y++) {
			Arrays.sort(img[y]);
		}	
	}	

	/**
	 * Apply pixel sort to an image with a brightness threshold.
	 * @param tr
	 * @param img
	 */
	public static void tSort(int tr, int[][] img) {
		int[][] mask = Picture.copyImg(img);
		applyF(binaryThreshold(tr), mask);
		
		for (int y = 0; y < mask.length; y++) {
			int b = 0;
			int t = img[y].length;
			boolean s = true;
			for (int x = 0; x < mask[0].length; x++) {
				int rgb = mask[y][x] & 0xFFFFFF;
				//System.out.println(Integer.toHexString(rgb));
				if (rgb != 0 && !s) {
					b = x;
					s = true;
				}
				if ((rgb == 0 || x == mask[0].length - 1) && s) {
					t = x;
					s = false;
					sortRange(b, t+1, img[y]);
				}
			}
		}
	}

	/**
	 * Sorts a range of indices in an array.
	 * @param b
	 * @param t
	 * @param row
	 */
	public static void sortRange(int b, int t, int[] row) {
		//TODO better sort
		//System.out.println("sorting range "+b+"-"+t);
		if (b >= t) return;
		int from = b;
		int min = row[b];
		for (int i = b; i < t; i++) {
			if (row[i] < min) {
				from = i;
				min = row[i];
			}	
		}
		row[from] = row[b];
		row[b] = min;
		sortRange(b + 1, t, row);
	}

	/**
	 * Apply a unary operator to all pixel values.
	 * @param f
	 * @param img
	 */
	public static void applyF(IntUnaryOperator f, int[][] img) {
		for (int y = 0; y < img.length; y++) {
			for (int x = 0; x < img[0].length; x++) {
				img[y][x] = f.applyAsInt(img[y][x]);
			}
		}
	}

	/**
	 * Apply a unary operator and sort.
	 * @param f
	 * @param img
	 */
	public static void fSort(IntUnaryOperator f, int[][] img) {
		applyF(f, img);
		for (int y = 0; y < img.length; y++) {
			Arrays.sort(img[y]);
		}
	}

	/**
	 * Returns a unary operator which turns any pixel under the threshold value in brightness to black and any above to white.
	 * @param min
	 * @return
	 */
	public static IntUnaryOperator binaryThreshold(int min) {
		return new IntUnaryOperator() {
			@Override
			public int applyAsInt(int op) {
				int br = op & 0xFFFFFF;
				br = ((br & 0xFF0000) >> 16) + ((br & 0xFF00) >> 8) + (br & 0xFF);
				if (br >= min) return 0xFFFFFFFF;
				return 0xFF000000;
			}
		};
	}

	/**
	 * Pixels under threshold value are made black.
	 * @param min
	 * @return int[][] ARGB picture
	 */
	public static IntUnaryOperator threshold(int min) {
		return new IntUnaryOperator() {
			@Override
			public int applyAsInt(int op) {
				int br = op & 0xFFFFFF;
				br = ((br & 0xFF0000) >> 16) + ((br & 0xFF00) >> 8) + (br & 0xFF);
				if (br > min) return op;
				return 0xFF000000;
			}
		};
	}
	
	/**
	 * Returns a unary operator which turns a range of brightness values to white.
	 * @param min
	 * @param max
	 * @return
	 */
	public IntUnaryOperator innerRange(int min, int max) {
		//TODO
		return null;
	}
}
