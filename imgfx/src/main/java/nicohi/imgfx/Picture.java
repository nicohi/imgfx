package nicohi.imgfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.File;
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
	 * Reads image from path into a new int array representing RGB
	 * @param path
	 * @return int[][] picture
	 */
	public static int[][] readFromPath(URL path) {
		try {
			BufferedImage img = ImageIO.read(path);
			return bufferedImageToPicture(img);
		} catch (IOException ex) {
			Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new int[0][0];
	}

	public static int[][] grayscale(int[][] img) {
		int[][] res = new int[img.length][img[0].length];
		for (int y = 0; y < res.length; y++) {
			for (int x = 0; x < res[0].length; x++) {
				int a = img[y][x] & 0xff000000;
				int b = (img[y][x] & 0xff);
				int g = (img[y][x] & 0xff00) >> 8;
				int r = (img[y][x] & 0xff0000) >> 16;
				int avg = (r + g + b) / 3;
				res[y][x] = a + (avg << 16) + (avg << 8) + avg;
			}
		}
		return res;
	}

	/**
	 * Write a picture to a file.
	 * @param file
	 * @param pic
	 * @return true if write success
	 */
	public static boolean writeToFile(File file, int[][] pic) {
		try {
			BufferedImage bufImg = pictureToBufferedImage(pic);
			return ImageIO.write(bufImg, "png", file);
		} catch (IOException ex) {
			System.out.println("OUTPUTFAIL");
			Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	/**
	 * Convert a picture to a BufferedImage.
	 * @param pic
	 * @return BufferedImage
	 */
	public static BufferedImage pictureToBufferedImage(int[][] pic) {
		final int width = pic[0].length;
		final int height = pic.length;
		int[] pixels = new int[height * width * 4];

		for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += 4) {
			int argb = pic[row][col];
			//argb -= -16777216; // 255 alpha
			int r = ((int) argb & 0xff); // red
			int g = (((int) argb & 0xff00) >> 8); // green
			int b = (((int) argb & 0xff0000) >> 16); // blue
			int a = (((int) argb & 0xff000000) >> 24); // blue
			pixels[pixel] = b;
			pixels[pixel + 1] = g;
			pixels[pixel + 2] = r;
			pixels[pixel + 3] = a;
			col++;
			if (col == width) {
			   col = 0;
			   row++;
			}
		}
		
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = Raster.createWritableRaster(result.getSampleModel(), null);
		raster.setPixels(0, 0, width, height, pixels);
		result.setData(raster);
		result.flush();
		return result;
	}

	/**
	 * Converts BufferedImage into a new array of int representing ARGB
	 * @param image
	 * @return int[][] picture
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

	/**
	 * Rotate image 90deg right.
	 * @param img
	 * @return rotated image
	 */
	public static int[][] rotateRight(int[][] img) {
		int[][] res = new int[img[0].length][img.length];
		for (int y = 0; y < res.length; y++) {
			for (int x = 0; x < res[0].length; x++) {
				res[y][x] = img[(-1 * (x - res[0].length) - 1)][y];
			}
		}
		return res;
	}

	/**
	 * 
	 * Rotate image 90deg left.
	 * @param img
	 * @return
	 */
	public static int[][] rotateLeft(int[][] img) {
		int[][] res = new int[img[0].length][img.length];
		for (int y = 0; y < res.length; y++) {
			for (int x = 0; x < res[0].length; x++) {
				//System.out.println(x);
				res[y][x] = img[x][-1 * (y - res.length) - 1];
			}
		}
		return res;
	}

	/**
	 * Copy a 2d array to a new 2d array. Returns the new array.
	 * @param img
	 * @return int[][] picture
	 */
	public static int[][] copyImg(int[][] img) {
		int[][] copy = new int[img.length][img[0].length];
		for (int y = 0; y < img.length; y++) {
			for (int x = 0; x < img[0].length; x++) {
				copy[y][x] = img[y][x];
			}
		}	
		return copy;
	}

	/**
	 * Copy the contents of one array to the other.
	 * @param in
	 * @param out
	 */
	public static void copyTo(int[][] in, int[][] out) {
		for (int y = 0; y < in.length; y++) {
			for (int x = 0; x < in[0].length; x++) {
				out[y][x] = in[y][x];
			}
		}	
	}

	public static int setR(int p, int r) {
		return (p & 0xFF00FFFF) + (r & 0x00FF0000);
	}

	public static int setG(int p, int g) {
		return (p & 0xFFFF00FF) + (g & 0x0000FF00);
	}

	public static int setB(int p, int b) {
		return (p & 0xFFFFFF00) + (b & 0x000000FF);
	}
}