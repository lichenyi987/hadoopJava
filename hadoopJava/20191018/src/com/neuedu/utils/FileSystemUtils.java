package com.neuedu.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.OutputBuffer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import com.neuedu.entity.*;

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
	public static List<HFile> queryALLFile() {
		FileSystem fileSystem = getFileSystem();
		Path path = new Path("hdfs://192.168.17.161:9000/neusoft3");
		ArrayList<HFile> arrayList = new ArrayList<>(); 
		try {
			FileStatus[] liFileStatus = fileSystem.listStatus(path);
			for(FileStatus fs:liFileStatus) {
				Path path2 = fs.getPath();
				long len = fs.getLen();
				HFile hFile = new HFile(path2,len);
				arrayList.add(hFile);
			}
			return arrayList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeFileSystem(fileSystem);
		}
		return null;
	}
	
	
//	file upload 文件上传
	public static void copyLocalFileUploadToHDFS() {
		FileSystem fileSystem = getFileSystem();
		
		Path distPath = new Path("/");
		Path srcpath = new Path("D://code//hadoopJava//junit.jar");
		try {
			fileSystem.copyFromLocalFile(srcpath, distPath);
			System.out.println("上传success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeFileSystem(fileSystem);
		}
	}
	
//	file download 文件下载
	public static void fileDownLoad(String webPath) {
		FileSystem fileSystem = getFileSystem();
		
		Path distPath = new Path("D://file//");
		Path srcpath = new Path(webPath);
		try {
			fileSystem.copyToLocalFile(srcpath, distPath);
			System.out.println("下载成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeFileSystem(fileSystem);
		}
		
	}
	
	
//	获取节点信息
	public static void getDataInfo() {
		FileSystem fileSystem = getFileSystem();
		DistributedFileSystem df = (DistributedFileSystem)fileSystem;
		try {
		 	DatanodeInfo[] datanodeInfos = df.getDataNodeStats();
		 	for(DatanodeInfo datanodeInfo:datanodeInfos) {
		 		System.out.println(datanodeInfo.getName());
		 		System.out.println(datanodeInfo.getDfsUsed());
		 	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	cat读取文件内容
	public static void readHDFSFile() {
		FileSystem fs = getFileSystem();
		FSDataInputStream open = null;
		try {
			open = fs.open(new Path("/hello.sh"));
			byte[] buffer = new byte[1024];
			while(open.read(buffer)>0) {
				System.out.println(new String(buffer));
			}
		} catch (IllegalArgumentException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(null!=open) {
				try {
					open.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("hello");
		}
	}
	
	
//	写文件
	public static void writeHDFSFile(String context) {
		FileSystem fileSystem = getFileSystem();
		FSDataOutputStream create = null;
		
		try {
			create = fileSystem.create(new Path("/20191018/first.txt"));
			create.write(context.getBytes());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(null!=create) {
				try {
					create.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
//	手动实现文件上传
	public void uploadFileByPerson(InputStream is,String fileName) {
		FileSystem fileSystem = getFileSystem();
		FSDataOutputStream create = null;
		
		try {
			create = fileSystem.create(new Path("/neusoft3/"+fileName));
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = is.read(buffer,0,buffer.length))>0) {
				create.write(buffer,0,len);
			}
			System.out.println("上传成功");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	手动实现下载文件
	public static void downLoadFileByPerson() {
		FileSystem fileSystem = getFileSystem();
		FSDataInputStream open = null;
		OutputStream os = null;
		try {
			open = fileSystem.open(new Path("/neusoft3/1.txt"));
			os = new FileOutputStream(new File("D:\\file\\1.txt"));
			int len = 0;
			byte [] buffer = new byte[1024];
			while((len=open.read(buffer))>0) {
				os.write(buffer,0,len);
			}
		} catch (IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("下载成功");
			if(null!=os) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null != open) {
				try {
					open.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
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
