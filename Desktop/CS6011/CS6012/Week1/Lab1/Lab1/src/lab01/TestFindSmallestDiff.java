package lab01;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFindSmallestDiff {

    private int[] arr1,arr2,arr3,arr4,arr5;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //happens once per test run.
    }
    @Before
    public void setUp() throws Exception {
        // happens/called before every test method.
        arr1 = new int[0];
        arr2 = new int[] { 3, 3, 2 };
        arr3 = new int[] { 52, 4, -8, 0, -17 };
        arr4 = new int[] {-1 ,-7, - 9 , -3, -2};
        arr5 = new int[] {0, -234567891, 987654910, 345678929, -1000000910};
    }

    @After
    public void tearDown() throws Exception {
        // happens/called right after every Test.
//        arr1 = null;
//        arr2 = null;
//        arr3 = null;

    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // happens once per class, after all test methods have been run.
    }


    private void someHelperMethodUsedInManyTests() {
    }
    @Test
    public void emptyArray() {
        assertEquals(-1, DiffUtil.findSmallestDiff(arr1));

    }

    //all array elements come back equal when u only return 0 for the array
    @Test
    public void allArrayElementsEqual() {
        assertEquals(0, DiffUtil.findSmallestDiff(arr2));
    }
    @Test
    public void smallRandomArrayElements() {
        assertEquals(4, DiffUtil.findSmallestDiff(arr3));
    }



    @Test
    public void allNumbersAreNegative(){
        assertEquals(1, DiffUtil.findSmallestDiff(arr4));
    }

    @Test
    public  void largeRandomArrayElements(){
        assertEquals(234567891, DiffUtil.findSmallestDiff(arr5));
    }



}

