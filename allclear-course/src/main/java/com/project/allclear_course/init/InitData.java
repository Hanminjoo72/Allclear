package com.project.allclear_course.init;

import com.project.allclear_course.domain.entity.College;
import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.domain.entity.Professor;
import com.project.allclear_course.repository.CollegeRepository;
import com.project.allclear_course.repository.DepartmentRepository;
import com.project.allclear_course.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class InitData implements ApplicationRunner {

    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;
    private final CollegeRepository collegeRepository;

    @Autowired
    public InitData(ProfessorRepository professorRepository, DepartmentRepository departmentRepository, CollegeRepository collegeRepository) {
        this.professorRepository = professorRepository;
        this.departmentRepository = departmentRepository;
        this.collegeRepository = collegeRepository;
    }
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initColleges();
        initDepartments();
        initProfessors();
    }

    private void initDepartments() {
        // Department 데이터 초기화
        Object[][] departments = {
                {1L, "국어국문학전공", 1L},
                {2L, "국사학전공", 1L},
                {3L, "문예창작전공", 1L},
                {4L, "한국어문화전공", 1L},
                {5L, "영어전공", 1L},
                {6L, "일어일본학전공", 1L},
                {7L, "유러피언스터디즈전공", 1L},
                {8L, "중어중국학전공", 1L},
                {9L, "국어국문학과", 1L},
                {10L, "국사학과", 1L},
                {11L, "문예창작과", 1L},
                {12L, "영어과", 1L},
                {13L, "일본어과", 1L},
                {14L, "프랑스어과", 1L},
                {15L, "독일어과", 1L},
                {16L, "유러피언스터디즈학과", 1L},
                {17L, "중어중국학과", 1L},
                {18L, "경영학전공", 2L},
                {19L, "국제경영학전공", 2L},
                {20L, "경제학전공", 2L},
                {21L, "문헌정보학전공", 2L},
                {22L, "사회복지학전공", 2L},
                {23L, "아동학전공", 2L},
                {24L, "경영학과", 2L},
                {25L, "국제경영학과", 2L},
                {26L, "경제학과", 2L},
                {27L, "문헌정보학과", 2L},
                {28L, "사회복지학과", 2L},
                {29L, "아동학과", 2L},
                {30L, "식품영양학전공", 3L},
                {31L, "보건관리학전공", 3L},
                {32L, "응용화학전공", 3L},
                {33L, "화장품학전공", 3L},
                {34L, "체육학전공", 3L},
                {35L, "식품영양학과", 3L},
                {36L, "보건관리학과", 3L},
                {37L, "컴퓨터학전공", 3L},
                {38L, "정보통계학전공", 3L},
                {39L, "컴퓨터학과", 3L},
                {40L, "정보통계학과", 3L},
                {41L, "체육학과", 3L},
                {42L, "약학과", 4L},
                {43L, "회화전공", 5L},
                {44L, "디지털공예전공", 5L},
                {45L, "큐레이터학전공", 5L},
                {46L, "피아노전공", 5L},
                {47L, "관현악전공", 5L},
                {48L, "성악전공", 5L},
                {49L, "회화과", 5L},
                {50L, "디지털공예과", 5L},
                {51L, "큐레이터학과", 5L},
                {52L, "피아노과", 5L},
                {53L, "관현악과", 5L},
                {54L, "성악과", 5L},
                {55L, "패션디자인전공", 6L},
                {56L, "시각＆실내디자인전공", 6L},
                {57L, "미디어디자인전공", 6L},
                {58L, "패션디자인학과", 6L},
                {59L, "시각＆실내디자인학과", 6L},
                {60L, "미디어디자인학과", 6L},
                {61L, "패션디자인전공(야)", 7L},
                {62L, "패션디자인학과(야)", 7L},
                {63L, "방송연예전공", 8L},
                {64L, "실용음악전공", 8L},
                {65L, "무용전공", 8L},
                {66L, "모델전공", 8L},
                {67L, "방송연예과", 8L},
                {68L, "실용음악과", 8L},
                {69L, "무용과", 8L},
                {70L, "모델과", 8L},
                {71L, "방송연예전공(야)", 9L},
                {72L, "방송연예과(야)", 9L},
                {73L, "커뮤니케이션콘텐츠전공", 10L},
                {74L, "문화예술경영전공", 10L},
                {75L, "글로벌MICE전공", 10L},
                {76L, "HCI사이언스전공", 10L},
                {77L, "데이터사이언스전공", 10L},
                {78L, "세무회계학과(야)", 11L},
                {79L, "금융융합경영학과(야)", 11L},
                {80L, "교양교직학부", 12L},
                {81L, "교직과정", 12L},
                {82L, "패션마케팅연계전공", 13L},
                {83L, "글로벌다문화학연계전공", 13L},
                {84L, "평생교육연계전공", 13L},
                {85L, "특별과정학부", 13L},
                {86L, "OCU", 13L},
                {87L, "K-MOOC", 13L}
        };

        for (Object[] dept : departments) {
            Department department = new Department();
            department.setId((Long) dept[0]);
            department.setName((String) dept[1]);

            // College 엔티티 설정
            College college = collegeRepository.findById((Long) dept[2])
                    .orElseThrow(() -> new RuntimeException("College not found with id " + dept[2]));
            department.setCollege(college);

            departmentRepository.save(department);
        }
        System.out.println("Departments have been initialized.");
    }

    private void initColleges() {
        // College 데이터 초기화
        Map<Long, String> colleges = new HashMap<>();
        colleges.put(1L, "인문학부");
        colleges.put(2L, "경영학부");
        colleges.put(3L, "자연과학부");
        colleges.put(4L, "약학부");
        colleges.put(5L, "예술학부");
        colleges.put(6L, "디자인학부");
        colleges.put(7L, "야간학부");
        colleges.put(8L, "방송연예학부");
        colleges.put(9L, "야간방송연예학부");
        colleges.put(10L, "커뮤니케이션학부");
        colleges.put(11L, "세무회계학부");
        colleges.put(12L, "교양교직학부");
        colleges.put(13L, "특별과정학부");

        for (Map.Entry<Long, String> entry : colleges.entrySet()) {
            College college = new College();
            college.setId(entry.getKey());
            college.setName(entry.getValue());
            collegeRepository.save(college);
        }
    }
    private void initProfessors() {
        // Professor 데이터 초기화
        Object[][] professorsData = {
                {1L, "주월랑", 1L, "인문관301"},
                {2L, "여태천", 1L, "인문관302"},
                {3L, "홍순애", 1L, "인문관303"},
                {4L, "구본현", 1L, "인문관304"},
                {5L, "최종석", 2L, "인문관305"},
                {6L, "홍인국", 2L, "인문관306"},
                {7L, "문미정", 2L, "인문관307"},
                {8L, "이용우", 2L, "인문관308"},
                {9L, "남미혜", 2L, "인문관309"},
                {10L, "이수명", 3L, "인문관310"},
                {11L, "윤대녕", 3L, "인문관311"},
                {12L, "이예은", 3L, "인문관312"},
                {13L, "신지연", 3L, "인문관313"},
                {14L, "하철승", 3L, "인문관314"},
                {15L, "조용선", 4L, "인문관315"},
                {16L, "최용원", 4L, "인문관316"},
                {17L, "신동희", 5L, "인문관317"},
                {18L, "최문수", 5L, "인문관318"},
                {19L, "최윤영", 5L, "인문관319"},
                {20L, "노경주", 5L, "인문관320"},
                {21L, "엘레나 스콰이얼스", 5L, "인문관321"},
                {22L, "로버트 로렌스", 5L, "인문관322"},
                {23L, "권영국", 5L, "인문관323"},
                {24L, "김유영", 6L, "인문관324"},
                {25L, "김영민", 6L, "인문관325"},
                {26L, "사와다 노부에", 6L, "인문관326"},
                {27L, "윤복희", 6L, "인문관327"},
                {28L, "송희영", 7L, "인문관328"},
                {29L, "드메흐레흐 마크", 7L, "인문관329"},
                {30L, "지몬바겐쉬츠", 7L, "인문관330"},
                {31L, "김윤상", 7L, "인문관331"},
                {32L, "서숙희", 7L, "인문관332"},
                {33L, "천현경", 8L, "인문관333"},
                {34L, "자이리", 8L, "인문관334"},
                {35L, "서봉교", 8L, "인문관335"},
                {36L, "홍준형", 8L, "인문관336"},
                {37L, "루멍", 8L, "인문관337"},
                {38L, "이동률", 8L, "인문관338"},
                {39L, "김윤태", 8L, "인문관339"},
                {40L, "여태천", 9L, "인문관340"},
                {41L, "홍순애", 9L, "인문관341"},
                {42L, "구본현", 9L, "인문관342"},
                {43L, "최종석", 10L, "인문관343"},
                {44L, "남미혜", 10L, "인문관344"},
                {45L, "박경", 10L, "인문관345"},
                {46L, "이용우", 10L, "인문관346"},
                {47L, "홍인국", 10L, "인문관347"},
                {48L, "김혜진", 11L, "인문관348"},
                {49L, "이예은", 11L, "인문관349"},
                {50L, "신지연", 11L, "인문관350"},
                {51L, "하철승", 11L, "인문관351"},
                {52L, "최윤영", 12L, "인문관352"},
                {53L, "문지순", 12L, "인문관353"},
                {54L, "오경미", 12L, "인문관354"},
                {55L, "권영국", 12L, "인문관355"},
                {56L, "노경주", 12L, "인문관356"},
                {57L, "윤복희", 13L, "인문관357"},
                {58L, "가쓰라가와 토모코", 13L, "인문관358"},
                {59L, "김영민", 13L, "인문관359"},
                {60L, "드메흐레흐 마크", 14L, "인문관360"},
                {61L, "지몬바겐쉬츠", 15L, "인문관361"},
                {62L, "조주은", 16L, "인문관362"},
                {63L, "송희영", 16L, "인문관363"},
                {64L, "김윤상", 16L, "인문관364"},
                {65L, "자이리", 17L, "인문관365"},
                {66L, "김윤태", 17L, "인문관366"},
                {67L, "이동률", 17L, "인문관367"},
                {68L, "서봉교", 17L, "인문관368"},
                {69L, "김아람", 18L, "인문관369"},
                {70L, "허광복", 18L, "인문관370"},
                {71L, "최항석", 18L, "인문관371"},
                {72L, "권혜원", 18L, "인문관372"},
                {73L, "신이현", 18L, "인문관373"},
                {74L, "정연희", 18L, "인문관374"},
                {75L, "김남곤", 18L, "인문관375"},
                {76L, "원지성", 18L, "인문관376"},
                {77L, "조현영", 18L, "인문관377"},
                {78L, "송유철", 19L, "인문관378"},
                {79L, "송상연", 19L, "인문관379"},
                {80L, "김현종", 19L, "인문관380"},
                {81L, "김준연", 19L, "인문관381"},
                {82L, "조혜진", 19L, "인문관382"},
                {83L, "최순화", 19L, "인문관383"},
                {84L, "김진", 20L, "인문관384"},
                {85L, "이홍화", 20L, "인문관385"},
                {86L, "최건호", 20L, "인문관386"},
                {87L, "박주헌", 20L, "인문관387"},
                {88L, "이혜영", 21L, "인문관388"},
                {89L, "서지영", 21L, "인문관389"},
                {90L, "정다희", 21L, "인문관390"},
                {91L, "차현주", 21L, "인문관391"},
                {92L, "배경재", 21L, "인문관392"},
                {93L, "이희정", 22L, "인문관393"},
                {94L, "유지희", 22L, "인문관394"},
                {95L, "서동명", 22L, "인문관395"},
                {96L, "남기철", 22L, "인문관396"},
                {97L, "이주영", 23L, "인문관397"},
                {98L, "강지현", 23L, "인문관398"},
                {99L, "성미영", 23L, "인문관399"},
                {100L, "김주희", 23L, "인문관400"},
                {101L, "이사임", 23L, "인문관401"},
                {102L, "김남곤", 24L, "인문관402"},
                {103L, "허광복", 24L, "인문관403"},
                {104L, "홍가연", 24L, "인문관404"},
                {105L, "박응석", 24L, "인문관405"},
                {106L, "권혜원", 24L, "인문관406"},
                {107L, "이다원", 24L, "인문관407"},
                {108L, "오윤경", 24L, "인문관408"},
                {109L, "조현영", 24L, "인문관409"},
                {110L, "최순화", 25L, "인문관410"},
                {111L, "이태호", 25L, "인문관411"},
                {112L, "송유철", 25L, "인문관412"},
                {113L, "조혜진", 25L, "인문관413"},
                {114L, "송상연", 25L, "인문관414"},
                {115L, "박주헌", 26L, "인문관415"},
                {116L, "최건호", 26L, "인문관416"},
                {117L, "이홍화", 26L, "인문관417"},
                {118L, "김진", 26L, "인문관418"},
                {119L, "배경재", 27L, "인문관419"},
                {120L, "정다희", 27L, "인문관420"},
                {121L, "서지영", 27L, "인문관421"},
                {122L, "차현주", 27L, "인문관422"},
                {123L, "서동명", 28L, "인문관423"},
                {124L, "신혜섭", 28L, "인문관424"},
                {125L, "이희정", 28L, "인문관425"},
                {126L, "유지희", 28L, "인문관426"},
                {127L, "남기철", 28L, "인문관427"},
                {128L, "정대련", 29L, "인문관428"},
                {129L, "이사임", 29L, "인문관429"},
                {130L, "성미영", 29L, "인문관430"},
                {131L, "강지현", 29L, "인문관431"},
                {132L, "이현주", 29L, "인문관432"},
                {133L, "이주영", 29L, "인문관433"},
                {134L, "김석중", 30L, "인문관434"},
                {135L, "양윤정", 30L, "인문관435"},
                {136L, "곽지원", 30L, "인문관436"},
                {137L, "곽재근", 30L, "인문관437"},
                {138L, "김정수", 30L, "인문관438"},
                {139L, "안령미", 31L, "인문관439"},
                {140L, "정민수", 31L, "인문관440"},
                {141L, "박혜진", 31L, "인문관441"},
                {142L, "이용주", 31L, "인문관442"},
                {143L, "진병석", 32L, "인문관443"},
                {144L, "배준원", 32L, "인문관444"},
                {145L, "성지하", 32L, "인문관445"},
                {146L, "박세연", 32L, "인문관446"},
                {147L, "고동수", 32L, "인문관447"},
                {148L, "이설훈", 33L, "인문관448"},
                {149L, "심종원", 33L, "인문관449"},
                {150L, "이승화", 33L, "인문관450"},
                {151L, "이현정", 34L, "인문관451"},
                {152L, "최진호", 34L, "인문관452"},
                {153L, "박찬우", 34L, "인문관453"},
                {154L, "이용현", 34L, "인문관454"},
                {155L, "곽애영", 34L, "인문관455"},
                {156L, "최용원", 34L, "인문관456"},
                {157L, "김정수", 35L, "인문관457"},
                {158L, "곽지원", 35L, "인문관458"},
                {159L, "양윤정", 35L, "인문관459"},
                {160L, "김석중", 35L, "인문관460"},
                {161L, "이용주", 36L, "인문관461"},
                {162L, "박정은", 36L, "인문관462"},
                {163L, "박혜진", 36L, "인문관463"},
                {164L, "정민수", 36L, "인문관464"},
                {165L, "박수희", 37L, "인문관465"},
                {166L, "장재경", 37L, "인문관466"},
                {167L, "이은영", 37L, "인문관467"},
                {168L, "노현아", 37L, "인문관468"},
                {169L, "한혁", 37L, "인문관469"},
                {170L, "임성채", 37L, "인문관470"},
                {171L, "남현우", 37L, "인문관471"},
                {172L, "전정훈", 37L, "인문관472"},
                {173L, "조권익", 38L, "인문관473"},
                {174L, "김동건", 38L, "인문관474"},
                {175L, "김현희", 38L, "인문관475"},
                {176L, "전희주", 38L, "인문관476"},
                {177L, "이완연", 39L, "인문관477"},
                {178L, "박창섭", 39L, "인문관478"},
                {179L, "최윤석", 39L, "인문관479"},
                {180L, "한혁", 39L, "인문관480"},
                {181L, "임성채", 39L, "인문관481"},
                {182L, "이은영", 39L, "인문관482"},
                {183L, "전정훈", 39L, "인문관483"},
                {184L, "박미순", 39L, "인문관484"},
                {185L, "전희주", 40L, "인문관485"},
                {186L, "김현희", 40L, "인문관486"},
                {187L, "김동건", 40L, "인문관487"},
                {188L, "곽애영", 41L, "인문관488"},
                {189L, "이용현", 41L, "인문관489"},
                {190L, "최진호", 41L, "인문관490"},
                {191L, "임세진", 42L, "인문관491"},
                {192L, "최지원", 42L, "인문관492"},
                {193L, "박광식", 42L, "인문관493"},
                {194L, "유승래", 42L, "인문관494"},
                {195L, "이향미", 42L, "인문관495"},
                {196L, "이수지", 42L, "인문관496"},
                {197L, "김종윤", 42L, "인문관497"},
                {198L, "유기연", 42L, "인문관498"},
                {199L, "장지은", 42L, "인문관499"},
                {200L, "강수진", 42L, "인문관500"},
                {201L, "남소희", 42L, "인문관501"},
                {202L, "최민식", 42L, "인문관502"},
                {203L, "오원영", 42L, "인문관503"},
                {204L, "윤종구", 43L, "인문관504"},
                {205L, "송지은", 43L, "인문관505"},
                {206L, "이승철", 43L, "인문관506"},
                {207L, "조소희", 43L, "인문관507"},
                {208L, "강수미", 43L, "인문관508"},
                {209L, "구모경", 43L, "인문관509"},
                {210L, "정혜정", 43L, "인문관510"},
                {211L, "이준승", 44L, "인문관511"},
                {212L, "박정근", 44L, "인문관512"},
                {213L, "민경익", 44L, "인문관513"},
                {214L, "김영경", 44L, "인문관514"},
                {215L, "임선양", 44L, "인문관515"},
                {216L, "남유진", 44L, "인문관516"},
                {217L, "엄성도", 44L, "인문관517"},
                {218L, "오정은", 44L, "인문관518"},
                {219L, "강민정", 44L, "인문관519"},
                {220L, "이화진", 45L, "인문관520"},
                {221L, "김학량", 45L, "인문관521"},
                {222L, "임산", 45L, "인문관522"},
                {223L, "양지연", 45L, "인문관523"},
                {224L, "김보라", 45L, "인문관524"},
                {225L, "이민영", 46L, "인문관525"},
                {226L, "박미정", 46L, "인문관526"},
                {227L, "윤형숙", 46L, "인문관527"},
                {228L, "이수현", 46L, "인문관528"},
                {229L, "최윤희", 46L, "인문관529"},
                {230L, "박재현", 46L, "인문관530"},
                {231L, "유혜숙", 46L, "인문관531"},
                {232L, "김지훈", 47L, "인문관532"},
                {233L, "주익성", 47L, "인문관533"},
                {234L, "이임수", 47L, "인문관534"},
                {235L, "정이은", 47L, "인문관535"},
                {236L, "인유진", 47L, "인문관536"},
                {237L, "나주리", 47L, "인문관537"},
                {238L, "이규석", 48L, "인문관538"},
                {239L, "권영일", 48L, "인문관539"},
                {240L, "유혜숙", 48L, "인문관540"},
                {241L, "이은정", 48L, "인문관541"},
                {242L, "김남희", 48L, "인문관542"},
                {243L, "김문희", 48L, "인문관543"},
                {244L, "김은정", 48L, "인문관544"},
                {245L, "임란경", 48L, "인문관545"},
                {246L, "이고운", 49L, "인문관546"},
                {247L, "구모경", 49L, "인문관547"},
                {248L, "송지은", 49L, "인문관548"},
                {249L, "서용", 49L, "인문관549"},
                {250L, "정혜정", 49L, "인문관550"},
                {251L, "박성환", 49L, "인문관551"},
                {252L, "조소희", 49L, "인문관552"},
                {253L, "이승철", 49L, "인문관553"},
                {254L, "윤종구", 49L, "인문관554"},
                {255L, "엄성도", 50L, "인문관555"},
                {256L, "강민정", 50L, "인문관556"},
                {257L, "임선양", 50L, "인문관557"},
                {258L, "박정근", 50L, "인문관558"},
                {259L, "남유진", 50L, "인문관559"},
                {260L, "이준승", 50L, "인문관560"},
                {261L, "김영경", 50L, "인문관561"},
                {262L, "안소윤", 50L, "인문관562"},
                {263L, "양지연", 51L, "인문관563"},
                {264L, "임산", 51L, "인문관564"},
                {265L, "김학량", 51L, "인문관565"},
                {266L, "김보라", 51L, "인문관566"},
                {267L, "장지원", 52L, "인문관567"},
                {268L, "이민영", 52L, "인문관568"},
                {269L, "강동규", 52L, "인문관569"},
                {270L, "정서연", 52L, "인문관570"},
                {271L, "유혜숙", 52L, "인문관571"},
                {272L, "박미정", 52L, "인문관572"},
                {273L, "최세영", 52L, "인문관573"},
                {274L, "주익성", 53L, "인문관574"},
                {275L, "김지훈", 53L, "인문관575"},
                {276L, "김유미", 53L, "인문관576"},
                {277L, "이임수", 53L, "인문관577"},
                {278L, "김준환", 53L, "인문관578"},
                {279L, "나주리", 53L, "인문관579"},
                {280L, "김문희", 54L, "인문관580"},
                {281L, "권영일", 54L, "인문관581"},
                {282L, "이규석", 54L, "인문관582"},
                {283L, "김은정", 54L, "인문관583"},
                {284L, "서희정", 54L, "인문관584"},
                {285L, "이미정", 55L, "인문관585"},
                {286L, "이주미", 55L, "인문관586"},
                {287L, "정주은", 55L, "인문관587"},
                {288L, "김소라", 55L, "인문관588"},
                {289L, "윤을요", 55L, "인문관589"},
                {290L, "피종삼", 55L, "인문관590"},
                {291L, "정재우", 55L, "인문관591"},
                {292L, "심효준", 56L, "인문관592"},
                {293L, "김혁래", 56L, "인문관593"},
                {294L, "김영철", 56L, "인문관594"},
                {295L, "이혜연", 56L, "인문관595"},
                {296L, "유현정", 56L, "인문관596"},
                {297L, "이용신", 56L, "인문관597"},
                {298L, "김동빈", 56L, "인문관598"},
                {299L, "박찬호", 56L, "인문관599"},
                {300L, "김영리", 56L, "인문관600"},
                {301L, "손희주", 56L, "인문관601"},
                {302L, "승문정", 56L, "인문관602"},
                {303L, "원미진", 56L, "인문관603"},
                {304L, "황희주", 57L, "인문관604"},
                {305L, "이윤희", 57L, "인문관605"},
                {306L, "정승연", 57L, "인문관606"},
                {307L, "임성택", 57L, "인문관607"},
                {308L, "정혜승", 57L, "인문관608"},
                {309L, "권성하", 58L, "인문관609"},
                {310L, "김소라", 58L, "인문관610"},
                {311L, "윤을요", 58L, "인문관611"},
                {312L, "이민지", 58L, "인문관612"},
                {313L, "송혜련", 58L, "인문관613"},
                {314L, "박찬호", 59L, "인문관614"},
                {315L, "심효준", 59L, "인문관615"},
                {316L, "손희주", 59L, "인문관616"},
                {317L, "김영리", 59L, "인문관617"},
                {318L, "손진호", 59L, "인문관618"},
                {319L, "손희영", 59L, "인문관619"},
                {320L, "김동빈", 59L, "인문관620"},
                {321L, "이용신", 59L, "인문관621"},
                {322L, "유현정", 59L, "인문관622"},
                {323L, "원미진", 59L, "인문관623"},
                {324L, "이혜연", 59L, "인문관624"},
                {325L, "이윤희", 60L, "인문관625"},
                {326L, "정혜승", 60L, "인문관626"},
                {327L, "임성택", 60L, "인문관627"},
                {328L, "조안나", 60L, "인문관628"},
                {329L, "황희주", 60L, "인문관629"},
                {330L, "이주미", 61L, "인문관630"},
                {331L, "이미정", 61L, "인문관631"},
                {332L, "정주은", 61L, "인문관632"},
                {333L, "김소라", 61L, "인문관633"},
                {334L, "윤을요", 61L, "인문관634"},
                {335L, "문준재", 61L, "인문관635"},
                {336L, "정재우", 61L, "인문관636"},
                {337L, "권성하", 62L, "인문관637"},
                {338L, "김소라", 62L, "인문관638"},
                {339L, "이민지", 62L, "인문관639"},
                {340L, "송혜련", 62L, "인문관640"},
                {341L, "태보라", 63L, "인문관641"},
                {342L, "이민주", 63L, "인문관642"},
                {343L, "서주희", 63L, "인문관643"},
                {344L, "김춘경", 63L, "인문관644"},
                {345L, "이동규", 63L, "인문관645"},
                {346L, "유성준", 63L, "인문관646"},
                {347L, "김용태", 63L, "인문관647"},
                {348L, "가두현", 63L, "인문관648"},
                {349L, "김광민", 64L, "인문관649"},
                {350L, "이지원", 64L, "인문관650"},
                {351L, "방현승", 64L, "인문관651"},
                {352L, "박호준", 64L, "인문관652"},
                {353L, "황호규", 64L, "인문관653"},
                {354L, "임주찬", 64L, "인문관654"},
                {355L, "유경옥", 64L, "인문관655"},
                {356L, "마도원", 64L, "인문관656"},
                {357L, "배가영", 64L, "인문관657"},
                {358L, "이동원", 64L, "인문관658"},
                {359L, "이범석", 64L, "인문관659"},
                {360L, "한민석", 64L, "인문관660"},
                {361L, "윤혜선", 65L, "인문관661"},
                {362L, "최윤실", 65L, "인문관662"},
                {363L, "윤수미", 65L, "인문관663"},
                {364L, "서은지", 65L, "인문관664"},
                {365L, "신승원", 65L, "인문관665"},
                {366L, "나정선", 65L, "인문관666"},
                {367L, "박윤지", 65L, "인문관667"},
                {368L, "차지은", 65L, "인문관668"},
                {369L, "김정은", 65L, "인문관669"},
                {370L, "박효선", 65L, "인문관670"},
                {371L, "김예진", 65L, "인문관671"},
                {372L, "정선옥", 66L, "인문관672"},
                {373L, "박순희", 66L, "인문관673"},
                {374L, "홍성희", 66L, "인문관674"},
                {375L, "배상국", 67L, "인문관675"},
                {376L, "마수현", 67L, "인문관676"},
                {377L, "태보라", 67L, "인문관677"},
                {378L, "이민주", 67L, "인문관678"},
                {379L, "김진수", 68L, "인문관679"},
                {380L, "황호규", 68L, "인문관680"},
                {381L, "방현승", 68L, "인문관681"},
                {382L, "김은영", 68L, "인문관682"},
                {383L, "박호준", 68L, "인문관683"},
                {384L, "마도원", 68L, "인문관684"},
                {385L, "이용훈", 68L, "인문관685"},
                {386L, "김광민", 68L, "인문관686"},
                {387L, "한민석", 68L, "인문관687"},
                {388L, "이연수", 69L, "인문관688"},
                {389L, "최윤실", 69L, "인문관689"},
                {390L, "윤혜선", 69L, "인문관690"},
                {391L, "윤수미", 69L, "인문관691"},
                {392L, "신승원", 69L, "인문관692"},
                {393L, "차지은", 69L, "인문관693"},
                {394L, "박순희", 70L, "인문관694"},
                {395L, "정주연", 70L, "인문관695"},
                {396L, "정선옥", 70L, "인문관696"},
                {397L, "태보라", 71L, "인문관697"},
                {398L, "이민주", 71L, "인문관698"},
                {399L, "서주희", 71L, "인문관699"},
                {400L, "김춘경", 71L, "인문관700"},
                {401L, "이동규", 71L, "인문관701"},
                {402L, "가두현", 71L, "인문관702"},
                {403L, "유성준", 71L, "인문관703"},
                {404L, "김용태", 71L, "인문관704"},
                {405L, "배상국", 72L, "인문관705"},
                {406L, "마수현", 72L, "인문관706"},
                {407L, "태보라", 72L, "인문관707"},
                {408L, "이민주", 72L, "인문관708"},
                {409L, "서주희", 73L, "인문관709"},
                {410L, "최믿음", 73L, "인문관710"},
                {411L, "김수희", 73L, "인문관711"},
                {412L, "채정화", 73L, "인문관712"},
                {413L, "이수지", 73L, "인문관713"},
                {414L, "정승환", 73L, "인문관714"},
                {415L, "피종삼", 73L, "인문관715"},
                {416L, "홍원식", 73L, "인문관716"},
                {417L, "최희진", 74L, "인문관717"},
                {418L, "정유리", 74L, "인문관718"},
                {419L, "김주희", 74L, "인문관719"},
                {420L, "홍유진", 74L, "인문관720"},
                {421L, "한새해", 74L, "인문관721"},
                {422L, "문준재", 74L, "인문관722"},
                {423L, "조규철", 74L, "인문관723"},
                {424L, "이은정", 74L, "인문관724"},
                {425L, "오득영", 74L, "인문관725"},
                {426L, "배상국", 74L, "인문관726"},
                {427L, "허준", 75L, "인문관727"},
                {428L, "윤영혜", 75L, "인문관728"},
                {429L, "이은성", 75L, "인문관729"},
                {430L, "노경주", 75L, "인문관730"},
                {431L, "김원준", 76L, "인문관731"},
                {432L, "유일선", 76L, "인문관732"},
                {433L, "김용민", 76L, "인문관733"},
                {434L, "김인기", 76L, "인문관734"},
                {435L, "권범", 77L, "인문관735"},
                {436L, "문혜영", 77L, "인문관736"},
                {437L, "강영훈", 77L, "인문관737"},
                {438L, "전희주", 77L, "인문관738"},
                {439L, "문성현", 77L, "인문관739"},
                {440L, "김태완", 77L, "인문관740"},
                {441L, "오상훈", 78L, "인문관741"},
                {442L, "권상호", 78L, "인문관742"},
                {443L, "김수", 78L, "인문관743"},
                {444L, "강민조", 78L, "인문관744"},
                {445L, "정연희", 78L, "인문관745"},
                {446L, "이찬희", 78L, "인문관746"},
                {447L, "손정국", 79L, "인문관747"},
                {448L, "조혜진", 79L, "인문관748"},
                {449L, "정호성", 79L, "인문관749"},
                {450L, "이찬희", 79L, "인문관750"},
                {451L, "박원주", 79L, "인문관751"},
                {452L, "오상훈", 79L, "인문관752"},
                {453L, "최희원", 79L, "인문관753"},
                {454L, "정윤희", 80L, "인문관754"},
                {455L, "이재현", 80L, "인문관755"},
                {456L, "유은주", 80L, "인문관756"},
                {457L, "이희영", 80L, "인문관757"},
                {458L, "양선진", 80L, "인문관758"},
                {459L, "서성빈", 80L, "인문관759"},
                {460L, "이용우", 80L, "인문관760"},
                {461L, "남미혜", 80L, "인문관761"},
                {462L, "김화성", 80L, "인문관762"},
                {463L, "박경", 80L, "인문관763"},
                {464L, "여영서", 80L, "인문관764"},
                {465L, "김형중", 80L, "인문관765"},
                {466L, "정민수", 80L, "인문관766"},
                {467L, "안령미", 80L, "인문관767"},
                {468L, "정혜경", 80L, "인문관768"},
                {469L, "문공주", 80L, "인문관769"},
                {470L, "황경아", 80L, "인문관770"},
                {471L, "최윤석", 80L, "인문관771"},
                {472L, "김현희", 80L, "인문관772"},
                {473L, "전정훈", 80L, "인문관773"},
                {474L, "박옥주", 80L, "인문관774"},
                {475L, "김명심", 80L, "인문관775"},
                {476L, "이병화", 80L, "인문관776"},
                {477L, "박현신", 80L, "인문관777"},
                {478L, "이정옥", 80L, "인문관778"},
                {479L, "김영민", 80L, "인문관779"},
                {480L, "고현진", 80L, "인문관780"},
                {481L, "드메흐레흐 마크", 80L, "인문관781"},
                {482L, "김영숙", 80L, "인문관782"},
                {483L, "지몬바겐쉬츠", 80L, "인문관783"},
                {484L, "남현우", 80L, "인문관784"},
                {485L, "홍원식", 80L, "인문관785"},
                {486L, "김은정", 80L, "인문관786"},
                {487L, "김종대", 80L, "인문관787"},
                {488L, "김성진", 80L, "인문관788"},
                {489L, "박재훈", 80L, "인문관789"},
                {490L, "윤기중", 80L, "인문관790"},
                {491L, "장성일", 80L, "인문관791"},
                {492L, "이한주", 80L, "인문관792"},
                {493L, "박혜연", 80L, "인문관793"},
                {494L, "김창숙", 80L, "인문관794"},
                {495L, "신경원", 80L, "인문관795"},
                {496L, "김규록", 80L, "인문관796"},
                {497L, "서봉교", 80L, "인문관797"},
                {498L, "김유영", 80L, "인문관798"},
                {499L, "권영국", 80L, "인문관799"},
                {500L, "조주은", 80L, "인문관800"},
                {501L, "윤금선", 80L, "인문관801"},
                {502L, "소주영", 80L, "인문관802"},
                {503L, "오은경", 80L, "인문관803"},
                {504L, "나주리", 80L, "인문관804"},
                {505L, "곽애영", 80L, "인문관805"},
                {506L, "김효진", 80L, "인문관806"},
                {507L, "최진호", 80L, "인문관807"},
                {508L, "이현정", 80L, "인문관808"},
                {509L, "장소진", 80L, "인문관809"},
                {510L, "이해진", 80L, "인문관810"},
                {511L, "주월랑", 80L, "인문관811"},
                {512L, "최정선", 80L, "인문관812"},
                {513L, "양정혜", 80L, "인문관813"},
                {514L, "이용현", 80L, "인문관814"},
                {515L, "최보경", 80L, "인문관815"},
                {516L, "박성호", 80L, "인문관816"},
                {517L, "김영아", 80L, "인문관817"},
                {518L, "전순식", 80L, "인문관818"},
                {519L, "임산", 80L, "인문관819"},
                {520L, "강수미", 80L, "인문관820"},
                {521L, "폴 리들", 80L, "인문관821"},
                {522L, "재라마야 배츨러", 80L, "인문관822"},
                {523L, "엘레나 스콰이얼스", 80L, "인문관823"},
                {524L, "도널랜 마이클", 80L, "인문관824"},
                {525L, "로버트 로렌스", 80L, "인문관825"},
                {526L, "스티븐 몰튼", 80L, "인문관826"},
                {527L, "신동희", 80L, "인문관827"},
                {528L, "박수미", 80L, "인문관828"},
                {529L, "오세은", 80L, "인문관829"},
                {530L, "신원선", 80L, "인문관830"},
                {531L, "이지연", 80L, "인문관831"},
                {532L, "임수영", 80L, "인문관832"},
                {533L, "이주미", 80L, "인문관833"},
                {534L, "오경미", 80L, "인문관834"},
                {535L, "김인기", 80L, "인문관835"},
                {536L, "조은나라", 80L, "인문관836"},
                {537L, "유기연", 80L, "인문관837"},
                {538L, "여태천", 80L, "인문관838"},
                {539L, "장은정", 80L, "인문관839"},
                {540L, "정승환", 80L, "인문관840"},
                {541L, "이윤옥", 81L, "인문관841"},
                {542L, "신기현", 81L, "인문관842"},
                {543L, "장은정", 81L, "인문관843"},
                {544L, "리상섭", 81L, "인문관844"},
                {545L, "조용선", 81L, "인문관845"},
                {546L, "", 81L, "인문관846"},
                {547L, "최소라", 82L, "인문관847"},
                {548L, "원지성", 82L, "인문관848"},
                {549L, "문형진", 83L, "인문관849"},
                {550L, "정승환", 84L, "인문관850"},
                {551L, "리상섭", 84L, "인문관851"},
                {552L, "장은정", 84L, "인문관852"},
                {553L, "임성연", 84L, "인문관853"},
                {554L, "조용선", 84L, "인문관854"},
                {555L, "", 85L, "인문관855"},
                {556L, "문형진", 85L, "인문관856"},
                {557L, "구재호", 85L, "인문관857"},
                {558L, "", 86L, "인문관858"},
                {559L, "", 87L, "인문관859"}
        };

        for (Object[] professorData : professorsData) {
            Professor professor = new Professor();
            professor.setId((Long) professorData[0]);
            professor.setName((String) professorData[1]);
            professor.setLab((String) professorData[3]);

            // Department 엔티티를 ID로 조회하여 설정
            Department department = departmentRepository.findById((Long) professorData[2])
                    .orElseThrow(() -> new RuntimeException("Department not found with ID " + professorData[2]));
            professor.setDepartment(department);

            professorRepository.save(professor);
        }
        System.out.println("Professors have been initialized.");
    }



}
