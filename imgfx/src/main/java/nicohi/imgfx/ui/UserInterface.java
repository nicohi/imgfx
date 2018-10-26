package nicohi.imgfx.ui;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

/**
 *
 * @author nicohi
 */
public class UserInterface extends Application {
	int width = 1200;
	int height = 1000;

	@Override
	public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
		
        Scene scene = new Scene(root, width, height);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
				
}
