package nicohi.imgfx.filters;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import nicohi.imgfx.Picture;

/**
 *
 * @author nicohi
 */
public class PixelSort {
    //TODO add generic comparators to sort
	
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
	public static void pixelSelSortThreshold(int tr, int[][] img) {
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
					selSortRange(b, t+1, img[y]);
				}
			}
		}
	}

	/**
	 * Apply pixel sort to an image with a brightness threshold.
	 * @param tr
	 * @param img
	 */
	public static void pixelMergeSortThreshold(int tr, int[][] img) {
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
					mergeSortRange(b, t+1, img[y]);
				}
			}
		}
	}

	/**
	 * Sorts a range of indices in an array with selection sort.
	 * @param b
	 * @param t
	 * @param row
	 */
	public static void selSortRange(int b, int t, int[] row) {
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
		selSortRange(b + 1, t, row);
	}

	/**
	 * Sorts a range of indices in an array with merge sort.
	 * @param b
	 * @param t
	 * @param row
	 */
	public static void mergeSortRange(int b, int t, int[] row) {
		setRange(b, mergeSort(copyRange(b, t, row)), row);
	}

	/**
	 *
	 * @param l
	 * @return
	 */
	public static int[] mergeSort(int[] l) {
		if (l.length <= 1) return l;
		int len = l.length / 2;
		return merge(mergeSort(copyRange(0, len, l)),
					 mergeSort(copyRange(len, l.length, l)));
	}

	/**
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static int[] merge(int[] l1, int[] l2) {
		//if (l1.length == 0) return l2;
		//if (l2.length == 0) return l1;
		int len = l1.length + l2.length;
		int[] res = new int[len];
		int i1 = 0;
		int i2 = 0;
		int ir = 0;
		while (ir < res.length) {
			if (i1 == l1.length) {
				res[ir] = l2[i2];
				i2++;
				ir++;
				continue;
			} else if (i2 == l2.length) {
				res[ir] = l1[i1];
				i1++;
				ir++;
				continue;
			}
			if (l1[i1] < l2[i2]) {
				res[ir] = l1[i1];
				i1++;
			} else {
				res[ir] = l2[i2];
				i2++;
			}
			ir++;
		}
		return res;
	}

	/**
	 *
	 * @param b
	 * @param rowSeg
	 * @param row
	 */
	public static void setRange(int b, int[] rowSeg, int[] row) {
		for (int i = b; i < rowSeg.length + b; i++) row[i] = rowSeg[i - b];
	}

	/**
	 *
	 * @param b
	 * @param t
	 * @param row
	 * @return
	 */
	public static int[] copyRange(int b, int t, int[] row) {
		int[] res = new int[t - b];
		for (int i = b; i < t; i++) res[i - b] = row[i];
		return res;
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
