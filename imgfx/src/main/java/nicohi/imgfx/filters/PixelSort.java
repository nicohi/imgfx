package nicohi.imgfx.filters;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

/**
 *
 * @author nicohi
 */
public class PixelSort {

	/**
	 * Sorts horizontal rows of image based on the red value of each pixel
	 * @param img
	 * @return
	 */
	public static void redSort(int[][] img) {
		//TODO implement own sort
		for (int y = 0; y < img.length; y++) {
			Arrays.sort(img[y]);
		}	
	}	

	public static int[][] copyImg(int[][] img) {
		int[][] copy = new int[img.length][img[0].length];
		for (int y = 0; y < img.length; y++) {
			for (int x = 0; x < img[0].length; x++) {
				copy[y][x] = img[y][x];
			}
		}	
		return copy;
	}

	public static void copyTo(int[][] in, int[][] out) {
		for (int y = 0; y < in.length; y++) {
			for (int x = 0; x < in[0].length; x++) {
				out[y][x] = in[y][x];
			}
		}	
	}

	public static void tSort(int tr, int[][] img) {
		int[][] mask = copyImg(img);
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
					sortRange(b, t, img[y]);
				}
			}
		}
	}

	public static void sortRange(int b, int t, int[] row) {
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

	public static void applyF(IntUnaryOperator f, int[][] img) {
		for (int y = 0; y < img.length; y++) {
			for (int x = 0; x < img[0].length; x++) {
				img[y][x] = f.applyAsInt(img[y][x]);
			}
		}
	}

	public static void fSort(IntUnaryOperator f, int[][] img) {
		applyF(f, img);
		for (int y = 0; y < img.length; y++) {
			Arrays.sort(img[y]);
		}
	}

	public static IntUnaryOperator binaryThreshold(int min) {
		return new IntUnaryOperator() {
			@Override
			public int applyAsInt(int op) {
				int br = op & 0xFFFFFF;
				br = ((br & 0xFF0000) >> 16) + ((br & 0xFF00) >> 8) + (br & 0xFF);
				if (br > min) return 0xFFFFFFFF;
				return 0xFF000000;
			}
		};
	}

	public static IntUnaryOperator threshold(int min) {
		return new IntUnaryOperator() {
			@Override
			public int applyAsInt(int op) {
				int br = op & 0xFFFFFF;
				br = ((br & 0xFF0000) >> 16) + ((br & 0xFF00) >> 8) + (br & 0xFF);
				if (br > min) return op;
				return 0;
			}
		};
	}
	
	public IntUnaryOperator innerRange(int min, int max) {
		return null;
	}
}
