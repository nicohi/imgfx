import nicohi.imgfx.Picture;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicohi
 */
public class PictureTest {
	
	public PictureTest() {
	}

	@Before
	public void setUp() {
	}

	@Test
	public void copyImgTest1() {
		int[][] img2 = {{1,2,3}, {1,2,4}};
		int[][] img1 = Picture.copyImg(img2);
		assertArrayEquals(img2, img1);
	}

	@Test
	public void copyImgToTest1() {
		int[][] img2 = {{1,2,3}, {1,2,4}};
		int[][] img1 = {{0,3,3}, {2,2,3}};
		Picture.copyTo(img2, img1);
		assertArrayEquals(img2, img1);
	}

	
}
