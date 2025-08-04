import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Disabled
public class MainTest {
    @BeforeAll
    static void tineOutTest(){
        System.out.println("TimeOut проверка");
    }

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void mainTest() throws Exception{
        Main.main(null);
    }

}
