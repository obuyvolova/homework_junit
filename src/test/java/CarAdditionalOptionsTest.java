import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static rocks.cleancode.hamcrest.throwable.MessageMatcher.message;

public class CarAdditionalOptionsTest {

    CarAdditionalOptions sut;

    @BeforeAll
    public static void started() {
        System.out.println("Tests CarAdditionalOptions Started");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests CarAdditionalOptions completed");
    }

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new CarAdditionalOptions();
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
        sut = null;
    }

    @ParameterizedTest
    @MethodSource("sourse")
    public void testAddWinterTires(int numberOfTires, int costPerTire, int expected) throws CarZeroTiresExceptions {
        int result = sut.addWinterTires(numberOfTires, costPerTire);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> sourse() {
        return Stream.of(Arguments.of(4, 12564, 50256), Arguments.of(5, 19654, 98270), Arguments.of(5, 13760, 68800));
    }

    @ParameterizedTest
    @MethodSource("sourseHamcrest")
    //Hamcrest
    public void testAddWinterTiresHamcrest(int numberOfTires, int costPerTire, int expected) throws CarZeroTiresExceptions {
        int result = sut.addWinterTires(numberOfTires, costPerTire);
        assertThat(result, equalTo(expected));
    }

    private static Stream<Arguments> sourseHamcrest() {
        return Stream.of(Arguments.of(4, 12564, 50256), Arguments.of(5, 19654, 98270), Arguments.of(5, 13760, 68800));
    }

    @Test
    public void testNumberOfTiresIsZero() {
        int numberOfTires = 0, costPerTire = 15000;
        var expected = CarZeroTiresExceptions.class;
        assertThrowsExactly(expected, () -> sut.addWinterTires(numberOfTires, costPerTire));
    }

    @Test
    public void testFirstPutTheCarOnRegister() {
        String expected = sut.putTheCarOnRegister("A101AA", "750");
        assertFalse(expected.isEmpty());
    }

    @Test
    //Hamcrest
    public void testFirstPutTheCarOnRegisterHamcrest() {
        String car = sut.putTheCarOnRegister("A101AA", "750");
        assertThat(car, not(isEmptyString()));
    }

    @Test
    public void testSecondPutTheCarOnRegister() {
        String expected = sut.putTheCarOnRegister("A101AA", "750");
        assertEquals(expected, "A101AA 750");
    }

    @Test
    //Hamcrest
    public void testSecondPutTheCarOnRegisterHamcrest() {
        String expected = sut.putTheCarOnRegister("A101AA", "750");
        assertThat(expected, equalTo("A101AA 750"));
    }

    @Test
    //Hamcrest
    public void testNumberOfTiresIsZeroHamcrestWithFeature() throws CarZeroTiresExceptions {
        Throwable throwable = new CarZeroTiresExceptions("¬веденное количество шин равн€етс€ 0!");
        assertThat(throwable, message(is(equalTo("¬веденное количество шин равн€етс€ 0!"))));
    }

    @Test
    //Hamcrest
    public void testSecondPutTheCarOnRegisterHamcrestWithFeature() {
        String expected = sut.putTheCarOnRegister("A101AA", "750");
        assertThat(expected, allOf(startsWith("A1"), containsString("AA"), endsWith("50")));
    }
}
