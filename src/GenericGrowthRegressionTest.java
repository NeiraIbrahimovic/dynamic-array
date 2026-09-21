import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Regression checks added during portfolio maintenance. */
class GenericGrowthRegressionTest {
    @Test void retainsIntegersAcrossCapacityGrowth() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 20; i++) list.add(i);
        for (int i = 0; i < 20; i++) assertEquals(Integer.valueOf(i), list.get(i));
    }
    @Test void findsAndRemovesNullWithoutBreakingLaterSearches() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add(null); list.add("value");
        assertEquals(0, list.indexOf(null));
        assertEquals(1, list.indexOf("value"));
        assertTrue(list.remove((String)null));
        assertEquals("value", list.get(0));
        assertEquals(-1, list.indexOf(null));
    }
}
