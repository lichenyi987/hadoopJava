package com.neuedu.mr.wordcount;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

public class WordJob extends Configured implements Tool {

	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
//		�������� (������̨�ֲ�ʽ)
		Configuration configuration = new Configuration();
		
//		 ����marpreduce�������ĸ���Ⱥ��     ��������hdfs���ģ�
		configuration.set("fs.defaultFS","hdfs://192.168.17.161:9000");
		
//		ͨ�����ö�����һ��ʵ��
		Job instance = Job.getInstance(configuration,"wordcount");
		
//								Class����  Class   ��JAVA���и����Class�ࣩ
//								���似��
//      ������������
		instance.setJarByClass(WordJob.class);
		
//		����Mapper����
		instance.setMapperClass(WordMap.class);
		
//		����Reduce����
		instance.setReducerClass(WordRrduce.class);
		
		
		instance.setMapOutputKeyClass(Text.class);
		instance.setMapOutputValueClass(IntWritable.class);
		
//		�������key������
		instance.setOutputKeyClass(Text.class);
		
//		�������value������
		instance.setOutputKeyClass(IntWritable.class);
		
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
	
	public static void main(String[] args) {
		
		try {
			new WordJob().run(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
