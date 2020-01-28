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
		
//		ͨ�����ö�����һ��ʵ��
		Job instance = Job.getInstance(conf,"wordcount");
		
//								Class����  Class   ��JAVA���и����Class�ࣩ
//								���似��
//      ������������
		instance.setJarByClass(SameJob.class);
		
//		����Mapper����
		instance.setMapperClass(SameMap.class);
		
//		����Reduce����
		instance.setReducerClass(SameReduce.class);
		
//		�������key������
		instance.setOutputKeyClass(Text.class);
		
//		�������value������
		instance.setOutputKeyClass(Text.class);
		
//		���������ļ���·��
		
		Path inPath = new Path("/wordcount.txt");
		FileInputFormat.addInputPath(instance, inPath);
		
		
//		�������·��
		Path ouPath = new Path("/wordcount/out");
		FileOutputFormat.setOutputPath(instance, ouPath);
		
//		�ȴ�ִ�гɹ�������ɹ�Ϊtrue������Ϊfalse
	    boolean waitForCompletion= instance.waitForCompletion(true);
	    System.out.println("ִ�����");
	    System.exit(waitForCompletion?0:1);
	    
		
		return 0;
	}
}
