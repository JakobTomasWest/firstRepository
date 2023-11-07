package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;

    @BeforeEach
    void setUp() {
        // x position is inside the brackets, y is compartmentalizing the row #
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
    }

    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);

    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);
    }

    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        assertEquals(smallSquare.mirrored(), expected);
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
    }

    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
    }

    @Test
    void testGetPixelThrowsOnNegativeX(){
        assertThrows(IllegalArgumentException.class, () -> { smallSquare.getPixel(-1,0);});
    }


    @Test
    void testNewStuff(){
        var testA = new GrayscaleImage(new double[][]{{2,4},{3,5},{1,1},{17,20}});
        var testB = new GrayscaleImage(new double[][]{{9,1},{2,8},{67,2},{41,83}});
        var testN = new GrayscaleImage(new double[][]{{-2,6},{3,4}});
        assertEquals(testA.getPixel(1,3),20);
        assertEquals(testA.equals(testB),false);
        assertFalse(testA.equals(testB));
        assertEquals(testB.averageBrightness(),26.625);
        var testC = testB.normalized();
        assertEquals(testC.averageBrightness(),127);
        var testNN = new GrayscaleImage(new double[][]{{6,-2},{4,3}});
        assertEquals(testNN.mirrored(),testN);
        var testE = new GrayscaleImage(new double[][]{{6,2},{4,3}});
        var testF = testE.cropped(1,1,1,1);
        assertEquals(testF, new GrayscaleImage(new double[][]{{3}}));
        var testS = new GrayscaleImage(new double[][]{{2,1,2},{3,2,3}});
        var testSS =testS.squarified();
        var TestSExpected = new GrayscaleImage(new double[][]{{2,1},{3,2}});
        assertEquals(testSS,TestSExpected);
    }
}
