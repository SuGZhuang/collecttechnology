package com.future.cloud.flink.testflink;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;
import org.apache.flink.walkthrough.common.sink.AlertSink;
import org.apache.flink.walkthrough.common.source.TransactionSource;

/**
 * 欺诈检测
 */
public class FraudDetectionJob {

    public static void main(String[] args) throws Exception {

        // 用于设置执行环境  可以定义任务的属性  创建数据源  以及最终启动任务的执行
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Transaction> transactionDataStream = env.addSource(new TransactionSource()).name("transactions");

        DataStream<Alert> alertDataStream =  transactionDataStream.keyBy(Transaction::getAccountId).process(new FraudDetector()).name("fraud-detector");

        // 将DataStream写出到外部系统
        alertDataStream.addSink(new AlertSink()).name("send-alerts");

        // 参数是任务名  可以开始执行任务
        env.execute("Fraud Detection");

    }

}
