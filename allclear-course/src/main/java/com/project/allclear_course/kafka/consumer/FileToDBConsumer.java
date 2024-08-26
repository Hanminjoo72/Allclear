package com.project.allclear_course.kafka.consumer;

import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.domain.entity.Professor;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileToDBConsumer<K extends Serializable, V extends Serializable> {
    public static final Logger logger = LoggerFactory.getLogger(FileToDBConsumer.class.getName());
    protected KafkaConsumer<K, V> kafkaConsumer;
    protected List<String> topics;

    private LecturesDBHandler lecturesDBHandler;

    public FileToDBConsumer(Properties consumerProps, List<String> topics, LecturesDBHandler lecturesDBHandler) {
        this.kafkaConsumer = new KafkaConsumer<>(consumerProps);
        this.topics = topics;
        this.lecturesDBHandler = lecturesDBHandler; //DB처리 업무처리용객체
    }

    public void initConsumer() {
        this.kafkaConsumer.subscribe(this.topics);
        shutdownHookToRuntime(this.kafkaConsumer);
    }

    private void shutdownHookToRuntime(KafkaConsumer<K, V> kafkaConsumer) {
        Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Main program starts to exit by calling wakeup");
            kafkaConsumer.wakeup();
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    private void processRecord(ConsumerRecord<K, V> record) {
        Lecture lecture = makeLecture(record);
        if (lecture != null) {
            this.lecturesDBHandler.insertOrUpdateLecture(lecture);
        } else {
            logger.error("Skipping record due to invalid lecture: " + record.value());
        }
    }


    private Lecture makeLecture(ConsumerRecord<K, V> record) {
        String messageValue = (String) record.value();
        // 키를 문자열로 변환 (필요한 경우)
        String key = record.key() != null ? record.key().toString() : "";

        logger.info("Received record with key: " + key + " ########## messageValue: " + messageValue);

//        byte[] messageBytes = messageValue.getBytes(StandardCharsets.UTF_8);
//        // 바이트 배열을 출력하여 인코딩 문제 확인
//        logger.info("Raw message bytes: " + Arrays.toString(messageBytes));


        try {
            // 메시지를 올바른 인코딩으로 재인코딩 시도
//            messageValue = new String(messageValue.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//            logger.info("###### Decoded messageValue: " + messageValue);
//            String[] tokens = messageValue.split(",");
//
//            for (int i = 0; i < tokens.length; i++) {
//                logger.info("Token " + i + ": " + tokens[i].trim());
//            }

            final String regex = "(?<=^|,)(\"(?:[^\"]|\"\")*\"|[^,]*)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(messageValue);

            List<String> tokens = new ArrayList<>();
            while (matcher.find()) {
                tokens.add(matcher.group().replaceAll("^\"|\"$", "").replace("\"\"", "\""));
            }

            // 로그로 토큰 확인
            for (int i = 0; i < tokens.size(); i++) {
                logger.info("Token " + i + ": " + tokens.get(i).trim());
            }

            // Parsing fields, assuming ID is the first token
            Long lectureId = tryParseLong(key.trim());
            Long departmentId = tryParseLong(tokens.get(0).trim());
            String lectureCode = tokens.get(1).trim();
            String lectureName = tokens.get(2).trim();
            String division = tokens.get(3).trim();
            Long professorId = tryParseLong(tokens.get(5).trim());
            int credit = tryParseInt(tokens.get(6).trim());
            int allowedNumberOfStudents = tryParseInt(tokens.get(7).trim());
            int currentNumberOfStudents = tryParseInt(tokens.get(8).trim());
            String grade = tokens.get(9).trim();
            String lectureDay = tokens.get(10).trim();
            String lectureTime = tokens.get(11).trim();
            int lectureYear = tryParseInt(tokens.get(12).trim());
            int semester = tryParseInt(tokens.get(13).trim());
            String syllabus = tokens.get(14).trim();

            if (lectureId == null || departmentId == null || professorId == null) {
                logger.error("Invalid data: " + messageValue);
                return null;
            }
            // Create entities using IDs
            Professor professor = new Professor(professorId);
            Department department = new Department(departmentId);

            return Lecture.builder()
                    .id(lectureId) // Include ID here
                    .professor(professor)
                    .department(department)
                    .lectureCode(lectureCode)
                    .division(division)
                    .lectureName(lectureName)
                    .grade(grade)
                    .credit(credit)
                    .allowedNumberOfStudents(allowedNumberOfStudents)
                    .currentNumberOfStudents(currentNumberOfStudents)
                    .lectureDay(lectureDay)
                    .lectureTime(lectureTime)
                    .lectureYear(lectureYear)
                    .semester(semester)
                    .syllabus(syllabus)
                    .delStatus(false)
                    .build();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            logger.error("Error parsing message: " + messageValue, e);
            return null; // Or handle the error accordingly
        }
    }
    private Long tryParseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            logger.error("Failed to parse Long from value: " + value, e);
            return null;
        }
    }

    private static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.error("Failed to parse Integer from value: {}", value, e);
            return null;
        }
    }

    private void processRecords(ConsumerRecords<K, V> records) {
        List<Lecture> lectures = makeLectures(records);
        if (lectures != null && !lectures.isEmpty()) {
            try {
                for (Lecture lecture : lectures) {
                    this.lecturesDBHandler.insertOrUpdateLecture(lecture);
                }
            } catch (Exception e) {
                logger.error("Failed to insert or update lectures in the database", e);
            }
        } else {
            logger.warn("No valid lectures to insert or update, or lectures list is empty");
        }
    }

    private List<Lecture> makeLectures(ConsumerRecords<K, V> records) {
        List<Lecture> lectures = new ArrayList<>();
        for (ConsumerRecord<K, V> record : records) {
            Lecture lecture = makeLecture(record);
            if (lecture != null) {
                lectures.add(lecture);
            } else {
                logger.error("Failed to create Lecture from record: " + record.value());
            }
        }
        return lectures;
    }

    public void pollConsumes(long durationMillis, String commitMode) {
        if (commitMode.equals("sync")) {
            pollCommitSync(durationMillis);
        } else {
            pollCommitAsync(durationMillis);
        }
    }

    private void pollCommitAsync(long durationMillis) {
        try {
            while (true) {
                ConsumerRecords<K, V> consumerRecords = this.kafkaConsumer.poll(Duration.ofMillis(durationMillis));
                logger.info("consumerRecords count: " + consumerRecords.count());
                if (consumerRecords.count() > 0) {
                    try {
                        processRecords(consumerRecords);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
                this.kafkaConsumer.commitAsync((offsets, exception) -> {
                    if (exception != null) {
                        logger.error("Offsets {} not completed, error: {}", offsets, exception.getMessage());
                    }
                });
            }
        } catch (WakeupException e) {
            logger.error("Wakeup exception has been called", e);
        } catch (Exception e) {
            logger.error("Exception during polling: " + e.getMessage(), e);
        } finally {
            logger.info("##### Commit sync before closing");
            try {
                kafkaConsumer.commitSync();
            } catch (Exception e) {
                logger.error("Exception during commit sync: " + e.getMessage(), e);
            }
            logger.info("Finally consumer is closing");
            close();
        }
    }

    protected void pollCommitSync(long durationMillis) {
        try {
            while (true) {
                ConsumerRecords<K, V> consumerRecords = this.kafkaConsumer.poll(Duration.ofMillis(durationMillis));
                processRecords(consumerRecords);
                if (consumerRecords.count() > 0) {
                    this.kafkaConsumer.commitSync();
                    logger.info("Commit sync has been called");
                }
            }
        } catch (WakeupException e) {
            logger.error("Wakeup exception has been called");
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            logger.info("##### Commit sync before closing");
            kafkaConsumer.commitSync();
            logger.info("Finally consumer is closing");
            close();
        }
    }

    protected void close() {
        this.kafkaConsumer.close();
        this.lecturesDBHandler.close();
    }

    public static void main(String[] args) {
        String topicName = "file-topic";

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "52.78.240.46:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "file-group");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

//        String url = "jdbc:postgresql://allclear-db.cjg6o2myyjuv.ap-northeast-2.rds.amazonaws.com/allclear?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false";
        String url = "jdbc:postgresql://allclear-db.cjg6o2myyjuv.ap-northeast-2.rds.amazonaws.com:5432/allclear";
        String user = "allclear";
        String password = "allclear321!!";
        LecturesDBHandler lecturesDBHandler = new LecturesDBHandler(url, user, password);

        FileToDBConsumer<String, String> fileToDBConsumer = new FileToDBConsumer<>(props, List.of(topicName), lecturesDBHandler);
        fileToDBConsumer.initConsumer();
        String commitMode = "async";

        logger.info("Starting polling...");
        fileToDBConsumer.pollConsumes(3000, commitMode);
        logger.info("Polling complete. Closing consumer.");
        fileToDBConsumer.close();
    }
}
