package com.rakib;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) throws TimeoutException, StreamingQueryException {

        System.setProperty("hadoop.home.dir", "c:/hadoop");
        Logger.getLogger("org.apache").setLevel(Level.WARNING);

        SparkSession sparkSession = SparkSession.builder()
                .appName("SparkSQL")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> rowDataset = sparkSession
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093")
                .option("subscribe", "studentinfo")
                .option("startingOffsets", "earliest")
                .load();
        rowDataset.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");


        //rowDataset.createOrReplaceTempView("student_info");
        //Dataset<Row> dataset = sparkSession.sql("SELECT value FROM student_info");


        StreamingQuery query = rowDataset
                .writeStream()
                .format("console")
                .outputMode(OutputMode.Append())
                .start();
        query.awaitTermination();


    }
}
