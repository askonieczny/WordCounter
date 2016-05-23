package wordCount;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
	
public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	public void map(LongWritable key, Text value, Context context){
		IntWritable voutput = new IntWritable(1);
		String newvalue = value.toString();
		String[] sarray = newvalue.split(" ");
		for(int i = 0; i < sarray.length; i++){
			try {
				context.write(new Text(sarray[i]), voutput);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

