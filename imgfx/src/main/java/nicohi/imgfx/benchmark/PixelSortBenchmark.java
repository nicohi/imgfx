package nicohi.imgfx.benchmark;

import nicohi.imgfx.Picture;
import nicohi.imgfx.filters.PixelSort;

/**
 *
 * @author nicohi
 */
public class PixelSortBenchmark {
	
	public static void mergeVsSelSort(int[][] img) {
		int[][] img1 = Picture.copyImg(img);
		int[][] img2 = Picture.copyImg(img);
		System.out.println("\nImage size is " + img[0].length + "x" + img.length);
		System.out.println("\nTesting mergesort");
		long t1 = System.currentTimeMillis();
		PixelSort.pixelMergeSortThreshold(0, img1);
		t1 = System.currentTimeMillis() - t1;
		System.out.println("\n	time taken: " + t1 + "ms");
		System.out.println("\nTesting selectionsort");
		long t2 = System.currentTimeMillis();
		PixelSort.pixelSelSortThreshold(0, img2);
		t2 = System.currentTimeMillis() - t2;
		System.out.println("\n	time taken: " + t2 + "ms\n");
	}	
}
