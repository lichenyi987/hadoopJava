package com.neuedu.entity;

import org.apache.hadoop.fs.Path;

public class HFile {
	
	public HFile() {
		
	}
	
	
	
	public HFile(Path path, long fileSize) {
		super();
		this.path = path;
		this.fileSize = fileSize;
	}

	


	public Path getPath() {
		return path;
	}



	public void setPath(Path path) {
		this.path = path;
	}



	public long getFileSize() {
		return fileSize;
	}



	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	

	@Override
	public String toString() {
		return "HFile [path=" + path + ", fileSize=" + fileSize + "]";
	}




	//	成员变量 文件路径
	private Path path;
	
//	文件大小
	private long fileSize;
	
	
	

}
