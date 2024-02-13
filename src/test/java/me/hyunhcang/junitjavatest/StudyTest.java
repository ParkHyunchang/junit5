package me.hyunhcang.junitjavatest;

import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.Message;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StudyTest {

    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")
    void create_new_study() {

        //assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

//        IllegalArgumentException exception =
//                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
//        String messaage = exception.getMessage();
//        assertEquals("limit은 0 보다 커야 합니다.", exception.getMessage());



//        assertAll(
//                () -> assertNotNull(study),
//            //assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DREAFT여야 한다.");
//            //assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DREAFT여야 한다.");
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 " + StudyStatus.DRAFT + "상태다."),
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
//        );
    }

    @Test
    @DisplayName("스터디 만들기 ( ˃ ⩌˂) ")
    void create1_new_study_again() {
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
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