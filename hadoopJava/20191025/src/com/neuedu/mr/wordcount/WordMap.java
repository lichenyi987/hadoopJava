package com.neuedu.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//泛型 jdk5以后的 规定类型    JVM最后运行的是CLass字节码文件，编译时候检查，运行的时候泛型就没有了 

//第一个KEYIN是怎么切出来的方法，用字节偏移量 
public class WordMap  extends Mapper<LongWritable, Text, Text, IntWritable>{
	
//	如果你想实现框架的某个功能，继承Mapper类
	@Override
//						偏移量                 获取到的值                                                                     结合上下文
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
//						分割
//						正则表达式    校验格式用		
		String[] split = value.toString().split(",");
		
//		增强for循环
//		迭代器
		
		for (String string : split) {
			context.write(new Text(string), new IntWritable(1));
		}
		
	}

}
