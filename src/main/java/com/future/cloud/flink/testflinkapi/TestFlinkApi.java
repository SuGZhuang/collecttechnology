package com.future.cloud.flink.testflinkapi;

import com.google.common.collect.Lists;
import org.apache.flink.api.common.io.FileInputFormat;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.IOException;
import java.util.*;

public class TestFlinkApi {

    public static void main(String[] args) throws Exception {

        LocalStreamEnvironment localEnvironment = StreamExecutionEnvironment.createLocalEnvironment();
        StreamExecutionEnvironment remoteEnvironment = StreamExecutionEnvironment.createRemoteEnvironment("", 1, "");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        /**
         * 基于集合
         */
        List<String> list = Lists.newArrayList();
        list.add("庄");
        list.add("广");
        list.add("园");

        // 从Java的Collection创建数据流 集合中所有的数据类型必须相同
        env.fromCollection(list);

        // 从一个迭代器中创建数据流 class指定了迭代器返回元素的类型
        Iterator<String> iterator = list.iterator();
        env.fromCollection(iterator, String.class);

        // 从给定的对象序列中创建数据流 所有对象类型必须相同
        env.fromElements();

        // 从一个迭代器中创建并行数据流  class指定了迭代器返回元素的类型
//        env.fromParallelCollection();

        /**
         * 基于文件
         */
        // 逐行读取文本文件，即符合规范的文件，并将其作为字符串返回
        env.readTextFile("");
        // 根据指定的文件输入格式读取（一次）文件
        env.readFile(new FileInputFormat<String>() {
            @Override
            public boolean reachedEnd() throws IOException {
                return false;
            }

            @Override
            public String nextRecord(String s) throws IOException {
                return null;
            }
        },  "");

        /**
         * 基于socket套接字
         */
        // 从套接字读取
        env.socketTextStream("", 1);


        // 在执行环境上使用来设置缓冲区填充的最大等待时间
        env.setBufferTimeout(5L);
        // 添加数据源
//        env.addSource();
        // 执行
        env.execute();

    }

}
