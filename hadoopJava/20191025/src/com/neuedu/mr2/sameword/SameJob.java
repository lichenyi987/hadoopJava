package com.neuedu.mr2.sameword;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

import com.neuedu.mr.wordcount.WordJob;
import com.neuedu.mr.wordcount.WordMap;
import com.neuedu.mr.wordcount.WordRrduce;

public class SameJob extends Configured implements Tool{

	@Override
	public int run(String[] arg0) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS","hdfs://192.168.17.161:9000");
		
//		通过配置对象获得一个实例
		Job instance = Job.getInstance(conf,"wordcount");
		
//								Class对象  Class   （JAVA中有个类叫Class类）
//								反射技术
//      设置任务类型
		instance.setJarByClass(SameJob.class);
		
//		设置Mapper类型
		instance.setMapperClass(SameMap.class);
		
//		设置Reduce类型
		instance.setReducerClass(SameReduce.class);
		
//		设置输出key的类型
		instance.setOutputKeyClass(Text.class);
		
//		设置输出value的类型
		instance.setOutputKeyClass(Text.class);
		
//		设置输入文件的路径
		
		Path inPath = new Path("/wordcount.txt");
		FileInputFormat.addInputPath(instance, inPath);
		
		
//		设置输出路径
		Path ouPath = new Path("/wordcount/out");
		FileOutputFormat.setOutputPath(instance, ouPath);
		
//		等待执行成功，如果成功为true，否则为false
	    boolean waitForCompletion= instance.waitForCompletion(true);
	    System.out.println("执行完成");
	    System.exit(waitForCompletion?0:1);
	    
		
		return 0;
	}
}
