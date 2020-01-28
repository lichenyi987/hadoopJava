package com.neuedu.mr2.sameword;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SameMap extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
//		将每一个Text的单词转成为String类型
		String string = value.toString();
//		将String类型转成char数组
		char[] charArray = string.toCharArray();
		
//		Arrays 数组工具类
//		给每一个单词排序
		Arrays.sort(charArray);
//		再讲排好序的字符串数组转回字符串
		String string2 = new String(charArray);
//		将排好序的单词做key输出 未排好序的单词做value输出
		context.write(new Text(string2), value);
	}
}
