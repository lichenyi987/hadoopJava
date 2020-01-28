package com.neuedu.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordRrduce extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	
//	�ϲ�����
	
//	key��ͬ ��ϲ�һ��reduce����
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> vaIues,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		for (IntWritable intWritable:vaIues) {
			sum += intWritable.get();
		}
		context.write(key, new IntWritable(sum));
	}

}
