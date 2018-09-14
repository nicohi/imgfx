package nicohi.imgfx.filters;

import java.util.Arrays;

/**
 *
 * @author nicohi
 */
public class PixelSort {
	//TODO different types of sort

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
}
