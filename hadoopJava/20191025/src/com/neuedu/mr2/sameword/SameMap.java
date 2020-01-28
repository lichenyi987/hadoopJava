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
//		��ÿһ��Text�ĵ���ת��ΪString����
		String string = value.toString();
//		��String����ת��char����
		char[] charArray = string.toCharArray();
		
//		Arrays ���鹤����
//		��ÿһ����������
		Arrays.sort(charArray);
//		�ٽ��ź�����ַ�������ת���ַ���
		String string2 = new String(charArray);
//		���ź���ĵ�����key��� δ�ź���ĵ�����value���
		context.write(new Text(string2), value);
	}
}
