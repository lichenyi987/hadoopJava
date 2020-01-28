package com.neuedu.mr2.sameword;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SameReduce extends Reducer<Text, Text, Text, Text>{
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
//		������
		int count = 0;
//		����stringBulider
		StringBuffer stringBuffer = new StringBuffer();
		for (Text text : values) {
//			׷����ͬ�ĵ���
			stringBuffer.append(" "+text.toString());
			count++;
		}
//		�����������һ��֤�����б����ͬ��ĸ�ĵ���
		if(count>1) {
			context.write(key, new Text(stringBuffer.toString()));
		}
	}

}
