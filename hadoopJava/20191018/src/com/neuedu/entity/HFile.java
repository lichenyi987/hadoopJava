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




	//	��Ա���� �ļ�·��
	private Path path;
	
//	�ļ���С
	private long fileSize;
	
	
	

}
