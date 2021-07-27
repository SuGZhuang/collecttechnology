package com.future.cloud.flink.testFlinkOther;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.stereotype.Component;

@Component
public class OneExample {

    public void openTestFlink() throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Person> personDataStream = env.fromElements(new Person("庄广园1", 16),
                new Person("庄广园2", 26),
                new Person("庄广园3", 27),
                new Person("庄广园4", 10),
                new Person("庄广园5", 5)
        );

        // 参数 FilterFunction<Person>
        DataStream<Person> filter = personDataStream.filter(new FilterFunction<Person>() {
            @Override
            public boolean filter(Person person) throws Exception {
                return person.getAge() > 22;
            }
        });

        filter.print();

        env.execute();

    }

}
