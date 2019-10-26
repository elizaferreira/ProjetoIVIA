import static org.junit.Assert.assertTrue;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
public class ImageUtilTest {

	@Test
	public void testSaveFileSuccess() throws IOException {
		
		File file = new File("C:/Users/Waldemar Neto/eclipse-workspace/iviaTest/src/icon_127114.png");
		
		InputStream logotipo = new FileInputStream(file);
		
		ImageUtil.saveToDiskImage(logotipo, file.getName(), "C:/Users/Waldemar Neto/Desktop");
		
		assertTrue(true);
	}

}
