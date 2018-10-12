package nicohi.imgfx;

import nicohi.imgfx.Picture;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import nicohi.imgfx.benchmark.GaussBlurBenchmark;
import nicohi.imgfx.benchmark.PixelSortBenchmark;
import nicohi.imgfx.filters.EdgeDetect;
import nicohi.imgfx.filters.GaussBlur;
import nicohi.imgfx.filters.PixelSort;
import nicohi.imgfx.ui.TextUserInterface;

/**
 *
 * @author nicohi
 */
public class Main {

	public static void main(String [] args)	{
		TextUserInterface.exec(args);
	}
}