package com.neuedu.dfs;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;;

public class FileSystemUtils {
	
	
	
//	close fileSystem关闭流的工具类
	
	public static void closeFileSystem(FileSystem fs) {
		if(null != fs ) {
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	
//	创建目录
	public static void mkdir() {
		FileSystem fileSystem = getFileSystem();
		Path path = new Path("hdfs://192.168.17.161:9000/dfs/aa");
		try {
			boolean mkdirs = fileSystem.mkdirs(path);
			System.out.println(mkdirs);
			fileSystem.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			closeFileSystem(fileSystem);
		}
		
		
	}
	
	
	
//	删除目录
	public static void deleteDir() {
		FileSystem fileSystem = getFileSystem();
		Path path = new Path("hdfs://192.168.17.161:9000/dfs");
		try {
			boolean delete = fileSystem.delete(path, true);
			System.out.println(delete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeFileSystem(fileSystem);
		}
		
	}
	

	
//	query this dir files 查询当前目录所有的文件
	public static void queryALLFile() {
		FileSystem fileSystem = getFileSystem();
		Path path = new Path("hdfs://192.168.17.161:9000/");
		
		try {
			FileStatus[] liFileStatus = fileSystem.listStatus(path);
			for(FileStatus fs:liFileStatus) {
				System.out.println(fs.getPath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeFileSystem(fileSystem);
		}
	}
	
	
//	file upload 文件上传
	public static void copyLocalFileUploadToHDFS() {
		FileSystem fileSystem = getFileSystem();
		
		Path distPath = new Path("/");
		
		Path srcpath = new Path("D:\\code\\hadoopJava\\junit.jar");
		try {
			fileSystem.copyFromLocalFile(srcpath, distPath);
			System.out.println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeFileSystem(fileSystem);
		}
	}
	
////	java FileSystem
	public static FileSystem getFileSystem() {
		Configuration configuration = new Configuration();
	
		configuration.set("fs.defaultFS","hdfs://192.168.17.161:9000");
		try {
			FileSystem fileSystem=FileSystem.get(configuration);
//			System.out.println(fileSystem);
			return fileSystem;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
