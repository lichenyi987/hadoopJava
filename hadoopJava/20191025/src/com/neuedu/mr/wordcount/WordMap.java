package com.neuedu.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//���� jdk5�Ժ�� �涨����    JVM������е���CLass�ֽ����ļ�������ʱ���飬���е�ʱ���;�û���� 

//��һ��KEYIN����ô�г����ķ��������ֽ�ƫ���� 
public class WordMap  extends Mapper<LongWritable, Text, Text, IntWritable>{
	
//	�������ʵ�ֿ�ܵ�ĳ�����ܣ��̳�Mapper��
	@Override
//						ƫ����                 ��ȡ����ֵ                                                                     ���������
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
//						�ָ�
//						������ʽ    У���ʽ��		
		String[] split = value.toString().split(",");
		
//		��ǿforѭ��
//		������
		
		for (String string : split) {
			context.write(new Text(string), new IntWritable(1));
		}
		
	}

}
