package com.neuedu.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.hadoop.fs.FileSystem;
import org.junit.Test;

import com.neuedu.utils.FileSystemUtils;

public class TestDemo {
	
	@Test
	public void test() {
		InputStream fiInputStream;
		try {
			fiInputStream = new FileInputStream(new File("D:\\file\\1.txt"));
			new FileSystemUtils().uploadFileByPerson(fiInputStream, "1.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		FileSystemUtils.downLoadFileByPerson();
		
		
	}

}
