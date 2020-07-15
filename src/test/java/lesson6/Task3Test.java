package lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Task3Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {true, new int[]{1,1,1,1,1}},
                {true, new int[]{4,4,4,4,4}},
                {true, new int[]{2,3}},
                {true, new int[]{1,1,4,4,4,4,1,1}},
        });
    }

    private Task3 test;
    private boolean bool;
    private int[] array;

    public Task3Test(boolean bool, int[] array) {
        this.bool = bool;
        this.array = array;
    }

    @Before
    public void init() {
        test = new Task3();
    }

    @Test
    public void testTask2() {
        Assert.assertEquals(bool, test.doIt(array));
    }
}