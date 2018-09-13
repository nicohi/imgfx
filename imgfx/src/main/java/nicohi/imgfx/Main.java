package nicohi.imgfx;

import nicohi.imgfx.Picture;
import java.io.File;
import java.net.URL;

/**
 *
 * @author nicohi
 */
public class Main {

	public static void main(String [] args)	{
		try {
			Picture proc = new Picture(0, 0);
			URL f = new File(args[0]).toURI().toURL();
			int[][] pic = proc.fromPath(f);
			System.out.println(pic.length + " " + pic[0].length);
			
		} catch (Exception ex) {
			System.out.println("no file given or invalid file");
		}
	}

}