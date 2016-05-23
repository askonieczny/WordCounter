package wordCount;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;



public  class OurReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	public void reduce(Text key, Iterable<IntWritable> values, Context context){
		Iterator<IntWritable> iterator = values.iterator();
		int sum = 0;
		while(iterator.hasNext()){
			sum += Integer.parseInt(iterator.next().toString());
			
		}
		try {
			context.write(key, new IntWritable(sum));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
