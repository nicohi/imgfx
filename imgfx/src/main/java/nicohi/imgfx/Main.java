package nicohi.imgfx;

import nicohi.imgfx.Picture;
import java.io.File;
import java.net.URL;
import nicohi.imgfx.filters.PixelSort;

/**
 *
 * @author nicohi
 */
public class Main {

	public static void main(String [] args)	{
		try {
			URL f = new File(args[0]).toURI().toURL();
			int[][] pic = Picture.readFromPath(f);
			System.out.println(pic.length + " " + pic[0].length);
			PixelSort.tSort(Integer.parseInt(args[1]), pic);
			Picture.writeToPath(new File("out.jpg"), pic);
			
		} catch (Exception ex) {
			System.out.println("no file given or invalid file: " + ex);
		}
	}

}