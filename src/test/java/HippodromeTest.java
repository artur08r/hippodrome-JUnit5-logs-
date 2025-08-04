import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HippodromeTest {

    @BeforeEach
    void init(){
        System.out.println("Тест начался");
    }

    @AfterEach
    void end(){
        System.out.println("Тест закончен");
    }

    @Test
    void listOfHorsesCantBeNull(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    void listOfHorsesCantBeEmpty(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<Horse>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }


    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Asadfgsdf", 20));
        horses.add(new Horse("Asadffsdg", 2));
        horses.add(new Horse("Asasdg", 30));
        horses.add(new Horse("fsdfghsdg", 22));
        horses.add(new Horse("Assdfsdg", 21));
        horses.add(new Horse("Asfdfsdg", 22));
        horses.add(new Horse("Asagfsdg", 25));
        horses.add(new Horse("Asahgfsdfsdg", 25));
        horses.add(new Horse("Asafsdfsdg", 23));
        horses.add(new Horse("Asagfsfsdg", 50));
        horses.add(new Horse("Asafsfsdg", 40));
        horses.add(new Horse("Asdafsdsdg", 30));
        horses.add( new Horse("Asafsd", 10));
        horses.add(new Horse("Asgdadfsdg", 20));
        horses.add(new Horse("Asadfsdg", 30));
        horses.add(new Horse("Assrdfsdg", 44));
        horses.add(new Horse("Aafsdfsdg", 52));
        horses.add(new Horse("safdfsdg", 32));
        horses.add(new Horse("safsdfsdg", 26));
        horses.add(new Horse("Assdaffsdg", 37));
        horses.add(new Horse("Adfsafssdg", 38));
        horses.add(new Horse("fsdfsdg", 24));
        horses.add(new Horse("Asafssdg", 23));
        horses.add(new Horse("Asafsdg", 26));
        horses.add(new Horse("Asafg", 26));
        horses.add(new Horse("Afsdfsdg", 20));
        horses.add(new Horse("Asafsdg", 11));
        horses.add(new Horse("Asafsdg", 1));
        horses.add(new Horse("Asasfdfsdfsdg", 24));
        horses.add(new Horse("Asafsdg", 45));

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveTest(){
        // 1. Создаём список из 50 моков лошадей
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(Mockito.mock(Horse.class));
        }

        // 2. Создаём Hippodrome с моками
        Hippodrome hippodrome = new Hippodrome(mockHorses);

        // 3. Вызываем метод move()
        hippodrome.move();

        // 4. Проверяем вызовы move() у всех лошадей
        for (Horse horse : mockHorses) {
            Mockito.verify(horse).move();
        }
    }


    @Test
    void getWinnerTest(){
        // 1. Создаем тестовых лошадей с разными дистанциями
        Horse slowHorse = Mockito.mock(Horse.class);
        Mockito.when(slowHorse.getDistance()).thenReturn(10.0);

        Horse mediumHorse = Mockito.mock(Horse.class);
        Mockito.when(mediumHorse.getDistance()).thenReturn(15.0);

        Horse fastHorse = Mockito.mock(Horse.class);
        Mockito.when(fastHorse.getDistance()).thenReturn(20.0);

        // 2. Создаем Hippodrome с этими лошадьми
        List<Horse> horses = Arrays.asList(slowHorse, mediumHorse, fastHorse);
        Hippodrome hippodrome = new Hippodrome(horses);

        // 3. Вызываем метод getWinner()
        Horse winner = hippodrome.getWinner();

        // 4. Проверяем, что вернулась лошадь с максимальной дистанцией
        assertEquals(fastHorse, winner);
    }
}
