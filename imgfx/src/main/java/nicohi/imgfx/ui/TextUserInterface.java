package nicohi.imgfx.ui;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import nicohi.imgfx.util.Picture;
import nicohi.imgfx.benchmark.GaussBlurBenchmark;
import nicohi.imgfx.benchmark.PixelSortBenchmark;
import nicohi.imgfx.filters.EdgeDetect;
import nicohi.imgfx.filters.GaussBlur;
import nicohi.imgfx.filters.PixelSort;

/**
 *
 * @author nicohi
 */
public class TextUserInterface {

	public static String helpString = 
			"imgfx - image effect program\n"
			+ "\n"
			+ "writes to out.png"
			+ "list of arguments:\n"
			+ "\trrot [source image] - rotate image right by 90 degrees\n"
			+ "\tlrot [source image] - rotate image left by 90 degrees\n"
			+ "\tpsort [source image] [threshold value (0-765)] - pixel sort effect with threshold\n"
			+ "\tgblur2d [source image] [stdev value(0.0-50.0)] - gaussian blur with 2D kernel (slower)\n"
			+ "\tgblur1d [source image] [stdev value(0.0-50.0)] - gaussian blur with 1D kernel (faster)\n"
			+ "\tedge [source image] - edge detection\n"
			+ "\tbench [source image] - run benchmarking\n"
			+ "";
	
	public static void exec(String[] args) {
		try {
			if (args.length == 0) {
				System.out.println(helpString);
				return;
			}
			if (args[0].equals("psort")) {
				URL f = new File(args[1]).toURI().toURL();
				int[][] pic = Picture.readFromPath(f);
				//System.out.println(pic.length + " " + pic[0].length);
				PixelSort.pixelMergeSortThreshold(Integer.parseInt(args[2]), pic);
				Picture.writeToFile(new File("out.png"), pic);
			}
			if (args[0].equals("gblur2d")) {
				URL f = new File(args[1]).toURI().toURL();
				int[][] pic = Picture.readFromPath(f);
				int[][] kernel = GaussBlur.kernel2D(Double.parseDouble(args[2]));
				for (int i = 0; i < kernel.length; i++) 
					System.out.println(Arrays.toString(kernel[i]));
				int[][] res = GaussBlur.gaussianBlur2D(pic, Double.parseDouble(args[2]));
				Picture.writeToFile(new File("out.png"), res);
			}
			if (args[0].equals("gblur1d")) {
				URL f = new File(args[1]).toURI().toURL();
				int[][] pic = Picture.readFromPath(f);
				int[][] res = GaussBlur.gaussianBlur1D(pic, Integer.parseInt(args[2]));
				Picture.writeToFile(new File("out.png"), res);
			}
			if (args[0].equals("lrot")) {
				URL f = new File(args[1]).toURI().toURL();
				int[][] pic = Picture.readFromPath(f);
				int[][] res = Picture.rotateLeft(pic);
				Picture.writeToFile(new File("out.png"), res);
			}
			if (args[0].equals("rrot")) {
				URL f = new File(args[1]).toURI().toURL();
				int[][] pic = Picture.readFromPath(f);
				int[][] res = Picture.rotateRight(pic);
				Picture.writeToFile(new File("out.png"), res);
			}
			if (args[0].equals("bench")) {
				URL f = new File(args[1]).toURI().toURL();
				int[][] img = Picture.readFromPath(f);
				PixelSortBenchmark.mergeVsSelSort(img);
				GaussBlurBenchmark.benchmark2(img);
			}
			if (args[0].equals("edge")) {
				URL f = new File(args[1]).toURI().toURL();
				//int[][] img = Picture.grayscale(GaussBlur.gaussianBlur1D(Picture.readFromPath(f), 2.0));
				int[][] img = Picture.grayscale(Picture.readFromPath(f));
				int[][] res = EdgeDetect.edgeDetect(img, EdgeDetect.kernelV2);
				//res = GaussBlur.gaussianBlur1D(res, 0.8);
				//res = EdgeDetect.edgeDetect(img, EdgeDetect.sobelY);
				Picture.writeToFile(new File("out.png"), res);
			}
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}	

}
