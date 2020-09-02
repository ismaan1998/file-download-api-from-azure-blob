package com.example.demo;

import com.example.demo.controllers.FileDownload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void fileDownloadTest() {
		FileDownload fileDownload = new FileDownload();
		assert(fileDownload.getFile()!=null);

	}

}
