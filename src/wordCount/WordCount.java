package wordCount;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.TestMiniMRClientCluster.MyReducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;



public class WordCount {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		Configuration conf  = new Configuration();
		conf.set("numReduce", args[2]);
		Job myJob = new Job(conf, "WordCount");
		myJob.setJarByClass(WordCount.class);
		myJob.setMapperClass(MyMapper.class);
		myJob.setReducerClass(MyReducer.class);
		myJob.setNumReduceTasks(Integer.parseInt(conf.get("numReduce")));
		myJob.setMapOutputKeyClass(Text.class);
		myJob.setMapOutputValueClass(IntWritable.class);
		myJob.setOutputKeyClass(Text.class);
		myJob.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(myJob, new Path(args[0]));
		FileOutputFormat.setOutputPath(myJob, new Path(args[1]));
		int jobCode = (myJob.waitForCompletion(true) ? 0:1);
		System.exit(jobCode);
		
		
	}

}
