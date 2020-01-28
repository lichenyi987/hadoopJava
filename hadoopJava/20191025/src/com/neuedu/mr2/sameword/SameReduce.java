package com.neuedu.mr2.sameword;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SameReduce extends Reducer<Text, Text, Text, Text>{
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
//		计数器
		int count = 0;
//		创建stringBulider
		StringBuffer stringBuffer = new StringBuffer();
		for (Text text : values) {
//			追加相同的单词
			stringBuffer.append(" "+text.toString());
			count++;
		}
//		如果数量大于一，证明还有别的相同字母的单词
		if(count>1) {
			context.write(key, new Text(stringBuffer.toString()));
		}
	}

}
