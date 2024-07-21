package com.project.allclear_course.init;

import com.project.allclear_course.domain.entity.College;
import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.domain.entity.Lecture;
import com.project.allclear_course.domain.entity.Professor;
import com.project.allclear_course.repository.CollegeRepository;
import com.project.allclear_course.repository.DepartmentRepository;
import com.project.allclear_course.repository.LectureRepository;
import com.project.allclear_course.repository.ProfessorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class DataInitializer {

    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;
    private final CollegeRepository collegeRepository;
    private final LectureRepository lectureRepository;

    @Autowired
    public DataInitializer(ProfessorRepository professorRepository, DepartmentRepository departmentRepository, CollegeRepository collegeRepository, LectureRepository lectureRepository) {
        this.professorRepository = professorRepository;
        this.departmentRepository = departmentRepository;
        this.collegeRepository = collegeRepository;
        this.lectureRepository = lectureRepository;
    }

    @PostConstruct
    public void init() {
        //colleages
        String collegesData = "인문대학;사회과학대학;자연정보과학대학;약학대학;예술대학;디자인대학;공연예술대학;문화지식융합대학;미래인재융합대학;교양대학;연계-융복합전공";
        Map<String, College> collegesMap = new HashMap<>();

        for (String collegeName : collegesData.split(";")) {
            College college = new College();
            college.setName(collegeName);
            collegeRepository.save(college);
            collegesMap.put(collegeName, college);
        }

        //departments
        String departmentsData = "국어국문학전공,인문대학;국사학전공,인문대학;문예창작전공,인문대학;영어전공,인문대학;일어일본학전공,인문대학;중어중국학전공,인문대학;유러피언스터디즈전공,인문대학;"
                + "경영학전공,사회과학대학;경제학전공,사회과학대학;국제경영학전공,사회과학대학;문헌정보학전공,사회과학대학;사회복지학전공,사회과학대학;아동학전공,사회과학대학;"
                + "식품영양학전공,자연정보과학대학;보건관리학전공,자연정보과학대학;응용화학학전공,자연정보과학대학;화장품학전공,자연정보과학대학;컴퓨터학전공,자연정보과학대학;정보통계학전공,자연정보과학대학;체육학전공,자연정보과학대학;"
                + "약학과,약학대학;"
                + "화학전공,예술대학;디지털공예전공,예술대학;큐레이터전공,예술대학;피아노전공,예술대학;관현악전공,예술대학;성악전공,예술대학;"
                + "패션디자인전공,디자인대학;시각&실내디자인전공,디자인대학;미디어디자인전공,디자인대학;패션디자인전공(야),디자인대학;"
                + "방송연예전공,공연예술대학;실용음악전공,공연예술대학;무용전공,공연예술대학;모델전공,공연예술대학;방송연예전공(야),공연예술대학;"
                + "커뮤니케이션콘텐츠전공,문화지식융합대학;문화예술경영전공,문화지식융합대학;글로벌MICE전공,문화지식융합대학;HCI사이언스전공,문화지식융합대학;데이터사이언스전공,문화지식융합대학;"
                + "세무회계학과(야),미래인재융합대학;금융융합경영학과(야),미래인재융합대학;"
                + "교양과정,교양대학;교직과정,교양대학;평생교육연계전공,교양대학;"
                + "패션마케팅연계전공,연계-융복합전공;글로벌다문화학연계전공,연계-융복합전공;빅데이터연계전공,연계-융복합전공;공공문화예술연계전공,연계-융복합전공;평생교육연계전공,연계-융복합전공;공공인재연계전공,연계-융복합전공;글로벌MICE전공,연계-융복합전공;";

        Map<String, Department> departmentsMap = new HashMap<>();

        for (String departmentData : departmentsData.split(";")) {
            String[] parts = departmentData.split(",");
            String departmentName = parts[0];
            String collegeName = parts[1];

            Department department = new Department();
            department.setName(departmentName);
            department.setCollege(collegesMap.get(collegeName));
            departmentRepository.save(department);
            departmentsMap.put(departmentName, department);
        }

        //professor
        String professorsData = "여태천,국어국문학전공;홍순애,국어국문학전공;오규환,국어국문학전공;" +
                "이용우,국사학전공;최존석,국사학전공;남미혜,국사학전공;" +
                "윤대녕,문예창작전공;이예은,문예창작전공;하철승,문예창작전공;"+
                "문지순,영어전공;오경미,영어전공;권영국,영어전공;최문수,영어전공;최윤영,영어전공;노경주,영어전공;신동희,영어전공;"+
                "시와다 노부예,일어일본학전공;윤복희,일어일본학전공;김영민,일어일본학전공;김유영,일어일본학전공;"+
                "이동률,중어중국학전공;김윤태,중어중국학전공;홍준형,중어중국학전공;서봉교,중어중국학전공;자이 리,중어중국학전공;"+
                "도수환,유러피언스터디즈전공;송희영,유러피언스터디즈전공;김윤상,유러피언스터디즈전공;Demeereleere Marc,유러피언스터디즈전공;Simon Wagenschütz,유러피언스터디즈전공;"
                +"이은철,경영학전공;원지성,경영학전공;최항석,경영학전공;김남곤,경영학전공;김우영,경영학전공;허광복,경영학전공;권혜원,경영학전공;오윤경,경영학전공;조현영,경영학전공;박응석,경영학전공;";

        Map<String, Professor> professorsMap = new HashMap<>();
        for (String professorData : professorsData.split(";")) {
            String[] parts = professorData.split(",");
            String professorName = parts[0];
            String departmentName = parts[1];

            Professor professor = new Professor();
            professor.setName(professorName);
            professor.setDepartment(departmentsMap.get(departmentName));
            professorRepository.save(professor);
            professorsMap.put(professorName, professor);
        }

        String lecturesData = "1,여태천,국어국문학전공,국문B0002,1,한국어의이해,1,3,수[05] 금[02],인A0303,수[05] 금[02],2024,1,강의계획서;"
                + "1,홍순애,국어국문학전공,국문B0003,1,현대문학의이론과실제,2,3,화[06] 목[06],인A0304,화[06] 목[06],2024,2,강의계획서;"
                + "1,오규환,국어국문학전공,국문K0001,1,고전문학의이해,3,3, 수[06] 목[04],인A0304,화[06] 목[06],2024,2,강의계획서;";

        for (String lectureData : lecturesData.split(";")) {
            String[] parts = lectureData.split(",");

            Long studentId = Long.parseLong(parts[0]);
            String professorName = parts[1];
            String departmentName =parts[2];
            String lectureCode = parts[3];
            String division = parts[4];
            String lectureName = parts[5];
            String grade = parts[6];
            int credit = Integer.parseInt(parts[7]);
            String lectureDay = parts[8];
            String lectureRoom = parts[9];
            String lectureTime = parts[10];
            int lectureYear = Integer.parseInt(parts[11]);
            int semester = Integer.parseInt(parts[12]);
            String syllabus = parts[13];

            Department department = departmentsMap.get(departmentName);
            Professor professor = professorsMap.get(professorName);

            Lecture lecture = new Lecture();
            lecture.setStudentId(studentId);
            lecture.setProfessor(professor);
            lecture.setDepartment(department);
            lecture.setLectureCode(lectureCode);
            lecture.setLectureName(lectureName);
            lecture.setDivision(division);
            lecture.setGrade(grade);
            lecture.setCredit(credit);
            lecture.setLectureRoom(lectureRoom);
            lecture.setLectureDay(lectureDay);
            lecture.setLectureYear(lectureYear);
            lecture.setLectureTime(lectureTime);
            lecture.setSemester(semester);
            lecture.setSyllabus(syllabus);

            lectureRepository.save(lecture);
        }
    }
}
