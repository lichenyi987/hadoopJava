package com.neuedu.test;

import org.apache.hadoop.fs.FileSystem;
import org.junit.Test;

import com.neuedu.dfs.FileSystemUtils;

public class TestDemo {
	
	@Test
	public void test() {
		
//		FileSystemUtils.getFileSystem();
//		FileSystemUtils.mkdir();
//		FileSystemUtils.deleteDir();
//		FileSystemUtils.queryALLFile();
		FileSystemUtils.copyLocalFileUploadToHDFS();
		
	}

}
