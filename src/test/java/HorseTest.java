import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class HorseTest {

    @BeforeAll
    static void setup() {
        System.out.println("Начало тестирования Horse");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("Tестирование Horse завершино");
    }
    @BeforeEach
    void init(){
        System.out.println("Подготовка к тесту");
    }
    @AfterEach
    void afterEach() {
        System.out.println("Тест завершён");
    }


    @Test
    void nameCantBeNull(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @Test
    void nameCantBeBlank(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Horse("", 10));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " " ,"  "})
    void nameCantBeBlank(String name){
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Horse("", 10));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    void speedCantBeNegative(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Horse("Петр", -52));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    void distanceCantBeNegative(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Horse("Петр", 52,-52));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }


    @Test
    void getNameTest(){
        Horse horse = new Horse("Петр", 52,52);
        assertEquals("Петр", horse.getName());
    }

    @Test
    void getSpeedTest(){
        Horse horse = new Horse("Петр", 52,52);
        assertEquals(52, horse.getSpeed());
    }

    @Test
    void getDistanseTest(){
        Horse horse = new Horse("Петр", 52,52);
        Horse secondHorse = new Horse("Петр", 52);
        assertEquals(52, horse.getDistance());
        assertEquals(0, secondHorse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.7, 0.9})
    void moveTest(double mockRandomValue) {

        try (MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandomValue);
            Horse horse = new Horse("Петр", 16.0, 10.0);
            // Выполняем метод move
            horse.move();
            assertEquals(10.0 + 16.0 * mockRandomValue, horse.getDistance());
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.times(1));
        }
    }


}
