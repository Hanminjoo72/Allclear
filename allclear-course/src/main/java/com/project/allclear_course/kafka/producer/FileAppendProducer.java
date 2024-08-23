package com.project.allclear_course.kafka.producer;


import com.project.allclear_course.kafka.event.EventHandler;
import com.project.allclear_course.kafka.event.FileEventHandler;
import com.project.allclear_course.kafka.event.FileEventSource;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

public class FileAppendProducer {
    public static final Logger logger = LoggerFactory.getLogger(FileAppendProducer.class.getName());

    public static void main(String[] args) {
        //토픽 이름
        String topicName = "file-topic";

        Properties props  = new Properties();

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "52.78.240.46:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);

        //KafkaProducer객체 생성 -> ProducerRecord생성 -> send() 비동기 방식 전송
        boolean sync = false;
        //파일 위치
        File file = new File("D:\\4th_grade\\Hanium\\Allclear\\allclear-course\\src\\main\\resources\\excelFile\\courses._v3.txt");
        EventHandler eventHandler = new FileEventHandler(kafkaProducer,topicName,sync);

        //10초
        //FileEventSource 객체는 주어진 파일을 일정 간격으로 모니터링하며 변경 사항을 감지함. 여기서는 10초(10000ms) 간격으로 설정되어있음
        FileEventSource fileEventSource = new FileEventSource(10000,file,eventHandler);
        //스레드로 만듦
        Thread fileEventSourceThread = new Thread(fileEventSource);

        fileEventSourceThread.start(); //모니터링

        try{
            fileEventSourceThread.join(); //fileEventSourceThread가 끝날때까지 main은 기다린다.
        }catch (InterruptedException e){
            logger.error(e.getMessage());
        }finally {
            kafkaProducer.close();
        }
    }
}
