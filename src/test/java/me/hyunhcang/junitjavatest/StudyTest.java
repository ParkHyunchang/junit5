package me.hyunhcang.junitjavatest;

import com.sun.jna.platform.unix.solaris.LibKstat;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) //junit-platform.properties 에서 설정해둠
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyTest {

    int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension =
            new FindSlowTestExtension(1000L);

    @Order(2)
    @FastTest
    //@DisplayName("스터디 만들기 fast")
    //@EnabledOnOs({OS.MAC, OS.LINUX, OS.WINDOWS})
    void create_new_study() {
        //Study actual = new Study(100);
        System.out.println(this);
        System.out.println(value++);
        Study actual = new Study(1);

        assertThat(actual.getLimit()).isGreaterThan(0);
/*
        String test_env = System.getenv("TEST_ENV"); // 환경변수 값 가져와서
        String test_path = System.getenv("PATH");
        System.out.println("test_env : "+test_env);
        System.out.println("test_path : "+test_path);

        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV"))); // 그 값이 lcoal 이면 밑에꺼 해

//        Study actual = new Study(10);
//        assertThat(actual.getLimit()).isGreaterThan(0);

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("LOCAL");
            Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

        assumingThat("test".equalsIgnoreCase(test_env), () -> {
            System.out.println("test");
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });
        */
/*

        //assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
*/
/*

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String messaage = exception.getMessage();
        assertEquals("limit은 0 보다 커야 합니다.", exception.getMessage());
*/
/*
        assertAll(
                () -> assertNotNull(study),
            //assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DREAFT여야 한다.");
            //assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DREAFT여야 한다.");
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 " + StudyStatus.DRAFT + "상태다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
        );
*/
    }

    @Order(1)
    @SlowTest
    //@Test
    @DisplayName("스터디 만들기 slow ")
    //@Disabled
    //@DisabledOnOs(OS.WINDOWS)
    void create1_new_study_again() throws InterruptedException {
        //System.out.println("create1");
        Thread.sleep(1005L);
        System.out.println(this);
        System.out.println("create1 " + value++);
    }

    @Order(3)
    @DisplayName("스터디 만들기")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

    @Order(4)
    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    //@ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    //@ValueSource(ints = {10, 20 ,30})
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    //@NullAndEmptySource
    //void parameterizedTest(String message) {
//    void parameterizedTest(Integer limit) {
//        System.out.println(limit);
//    }

//    void parameterizedTest(@ConvertWith(StudyConverter.class) Study study) {
//        System.out.println(study.getLimit());
//    }

//    void parmeterizedTest(Integer limit, String name) {
//        Study study = new Study(limit, name);
//        System.out.println(study);
//    }

//    void parmeterizedTest(ArgumentsAccessor argumentsAccessor) {
//        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
//        System.out.println(study);
//    }

    void parmeterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only conver to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @BeforeAll
    //static void beforeAll() {
    void beforeAll() { // @TestInstance(TestInstance.Lifecycle.PER_CLASS) 이거 있으면 static 빼도 됌
        System.out.println("before all");
    }

    @AfterAll
    //static void afterAll() {
    static void afterAll() { // @TestInstance(TestInstance.Lifecycle.PER_CLASS) 이거 있으면 static 빼도 됌
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}