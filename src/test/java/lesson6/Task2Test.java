package lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Task2Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[]{1,3}, new int[]{1,2,4,4,2,3,4,1,7}},
                {new int[]{2,4}, new int[]{1,2,0,0,2,3,0,1,7}},
                {new int[]{3,5}, new int[]{1,2,4,4,2,3,0,1,7}},
                {new int[]{0,2,3,0,1,7}, new int[]{1,2,4,0,2,3,0,1,7}},
        });
    }
    private Task2 test;
    private final int[] array;
    private final int[] testArray;
    public Task2Test(int[] array, int[] testArray) {
        this.array = array;
        this.testArray = testArray;
    }

    @Before
    public void init() {
        test = new Task2();
    }

    @Test
    public void testTask1() {
        Assert.assertTrue("Arrays ara not equals", Arrays.equals(array, test.doIt(testArray)));
    }
}