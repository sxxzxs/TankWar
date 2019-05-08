package test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {

	@Test
	public void test() {
	try {
		//引入图片
		BufferedImage image = ImageIO.read(new File("C:/Users/Administrator/Desktop/IMG_3197.JPG"));
		assertNotNull(image);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
		
	}

}
