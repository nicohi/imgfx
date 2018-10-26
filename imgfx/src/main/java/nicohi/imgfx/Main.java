package nicohi.imgfx;

import nicohi.imgfx.util.Picture;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import nicohi.imgfx.benchmark.GaussBlurBenchmark;
import nicohi.imgfx.benchmark.PixelSortBenchmark;
import nicohi.imgfx.filters.EdgeDetect;
import nicohi.imgfx.filters.GaussBlur;
import nicohi.imgfx.filters.PixelSort;
import nicohi.imgfx.ui.TextUserInterface;
import nicohi.imgfx.ui.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author nicohi
 */
public class Main extends Application {

	public static void main(String [] args) {
		if (args.length > 0 && args[0].equals("ui")) {
			//UserInterface.launch(args);
			Application.launch(UserInterface.class, args);
		} else {
			TextUserInterface.exec(args);
			System.exit(0);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}