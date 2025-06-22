import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.Parameterized;


public class MyTest {


    @ParameterizedTest
    @ValueSource(strings= {"Bahadir","Mahadair","Kahadir"})
    public void test01(String names){
        System.out.println(names);
    }
}
