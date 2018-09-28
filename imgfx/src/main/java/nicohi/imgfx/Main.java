package nicohi.imgfx;

import nicohi.imgfx.Picture;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import nicohi.imgfx.filters.GaussBlur;
import nicohi.imgfx.filters.PixelSort;

/**
 *
 * @author nicohi
 */
public class Main {

	public static void main(String [] args)	{
		try {
			if (args[0].equals("psort")) {
				URL f = new File(args[1]).toURI().toURL();
				int[][] pic = Picture.readFromPath(f);
				System.out.println(pic.length + " " + pic[0].length);
				PixelSort.tSort(Integer.parseInt(args[2]), pic);
				Picture.writeToFile(new File("out.png"), pic);
			}
			if (args[0].equals("gblur")) {
				URL f = new File(args[1]).toURI().toURL();
				int[][] pic = Picture.readFromPath(f);
				//System.out.println(Arrays.toString(pic));
				int[][] res = GaussBlur.applyKernel1D(pic, GaussBlur.kernel1D(Double.parseDouble(args[2])));
				//int[][] res = GaussBlur.applyKernel1D(Picture.rotateRight(pic), GaussBlur.kernel1D(Double.parseDouble(args[2])));
				//System.out.println(pic.length + " " + pic[0].length);
				//System.out.println(Arrays.toString(GaussBlur.kernel1D(Double.parseDouble(args[1]))));
				Picture.writeToFile(new File("out.png"), res);
			}
			
		} catch (Exception ex) {
			//System.out.println("no file given or invalid file: " + ex);
			System.out.println(ex);
			//int[][] img1 = {{2, 3, 1}, {4, 2, 1}};
			//int[][] img2 = {{1, 2, 3}, {1, 2, 4}};
			//PixelSort.tSort(0, img1);
			//System.out.println(img1[1][0] +" "+ img1[1][1]);
		}
	}

}