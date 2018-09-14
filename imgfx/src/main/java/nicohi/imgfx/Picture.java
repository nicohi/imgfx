package nicohi.imgfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author nicohi
 */
public class Picture {
	
	/**
	 * Reads image from path into a new int array representig RGB
	 * @param path
	 * @return
	 */
	public static int[][] fromPath(URL path)	{
		try {
			BufferedImage img = ImageIO.read(path);
			return bufferedImageToPicture(img);
		} catch (IOException ex) {
			Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new int[0][0];
	}

	/**
	 * Converts BufferedImage into a new array of int representing RGB
	 * @param image
	 * @return
	 */
	public static int[][] bufferedImageToPicture(BufferedImage image) {
		//TODO 
		final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][] result = new int[height][width];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
				argb += ((int) pixels[pixel + 1] & 0xff); // blue
				argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
				   col = 0;
				   row++;
				}
			}
		} else {
			final int pixelLength = 3;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += -16777216; // 255 alpha
				argb += ((int) pixels[pixel] & 0xff); // blue
				argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
				   col = 0;
				   row++;
				}
			}
		}
		return result;
	}

}