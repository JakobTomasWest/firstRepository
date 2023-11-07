package assign01;


import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel" brightnesses
 * 255 is "white" 127 is "gray" 0 is "black" with intermediate values in between
 * Author: Ben Jones and ***Jakob Tomas West***
 */
public class GrayscaleImage {
    private double[][] imageData; // the actual image data


    /**
     * Initialize an image from a 2D array of doubles
     * This constructor creates a copy of the input array
     * @param data initial pixel values
     * @throws IllegalArgumentException if the input array is empty or "jagged" meaning not all rows are the same length
     */
    public GrayscaleImage(double[][] data){
        if(data.length == 0 || data[0].length == 0){
            throw new IllegalArgumentException("Image is empty");
        }

        imageData = new double[data.length][data[0].length];
        for(var row = 0; row < imageData.length; row++){
            if(data[row].length != imageData[row].length){
                throw new IllegalArgumentException("All rows must have the same length");
            }
            for(var col = 0; col < imageData[row].length; col++){
                imageData[row][col] = data[row][col];
            }
        }
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale
     * Uses the AWT Graphics2D class to do the conversion, so it may add
     * an item to your dock/menu bar as if you're loading a GUI program
     * @param url where to download the image
     * @throws IOException if the image can't be downloaded for some reason
     */
    public GrayscaleImage(URL url) throws IOException {
        var inputImage = ImageIO.read(url);
        //convert input image to grayscale
        //based on (https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models)
        var grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d= grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        //raster is basically a width x height x 1 3-dimensional array
        var grayRaster = grayImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                //getSample parameters are x (our column) and y (our row), so they're "backwards"
                imageData[row][col] = grayRaster.getSampleDouble(col, row, 0); //throws arrayIndexOutofBounds
            }
        }
    }

    public void savePNG(File filename) throws IOException {
        var outputImage = new BufferedImage(imageData[0].length, imageData.length, BufferedImage.TYPE_BYTE_GRAY);
        var raster = outputImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                raster.setSample(col, row, 0, imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    ///Methods to be filled in by students below

    /**
     * Get the pixel brightness value at the specified coordinates
     * (0,0) is the top left corner of the image, (width -1, height -1) is the bottom right corner
     * @param x horizontal position, increases left to right
     * @param y vertical position, **increases top to bottom**
     * @return the brightness value at the specified coordinates
     * @throws IllegalArgumentException if x, y are not within the image width/height
     */
    public double getPixel(int x, int y) throws IllegalArgumentException{

        //I am given a Grayscale image that is denoted by a 2D array ImageData
        //for each row and column I need to be able to geta value from 0-255 or the value at their combined coordinates\
        //check if x and y are within the bounds of the imageData array
        if(x <0 || x >= imageData[0].length || y < 0 || y > imageData.length){
            throw new IllegalArgumentException("The pixel is not within the image width/height parameters");
        } else {
            //you get the [x][y] but you need to switch to get the [row value] [column value]
            return imageData[y][x];
        }

    }

    /**
     * Two images are equal if they have the same size and each corresponding pixel
     * in the two images is exactly equal
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof GrayscaleImage)){
            return false;
        }

        GrayscaleImage otherImage = (GrayscaleImage)other;
        ///check to see if the y bounds are same
        if(this.imageData.length != otherImage.imageData.length){
            return false;
        }
        // Check if each row of the image data array has the same x -> length
        // Seeing if x content bounds are the same for each row
        for (int y = 0; y < this.imageData.length; y++) {
            if (this.imageData[y].length != otherImage.imageData[y].length) {
                return false;
            }
        }

        //Since the imageData.length and the imageData[y].length are the same check each value in each
        //matrix
        for(int y = 0; y < this.imageData.length; y++){
            for(int x = 0; x <this.imageData[y].length; x++){
                if (this.imageData[y][x] != otherImage.imageData[y][x]){
                    return false;
                }
            }
        }
        return true;


    }


    /**
     * Computes the average of all values in image data
     * @return the average of the imageData array
     */
    public double averageBrightness(){
        //for every row in the length of our image data check
        double totalPixels =0;
        double totalSum=0;
        for(int y = 0; y <imageData.length; y++){
            // check the value at each x position in the imageData.length and sum the values
            for(int x = 0; x < imageData[y].length; x++){
                double number =imageData[y][x];
                totalSum = totalSum + number;
                System.out.println(number);
            }
            //add number of pixels / values at each Y for each row
            totalPixels = totalPixels + imageData[y].length;
            System.out.println("total number of pixels: " +totalPixels);
        }

        double average = totalSum/totalPixels;

        return average;
    }

    /**
     * Return a new GrayScale image where the average new average brightness is 127
     * To do this, uniformly scale each pixel (ie, multiply each imageData entry by the same value)
     * Due to rounding, the new average brightness will not be 127 exactly, but should be very close
     * The original image should not be modified
     * @return a GrayScale image with pixel data uniformly rescaled so that its averageBrightness() is 127
     */
    public GrayscaleImage normalized(){
        //copy the data from the original so that the test is accurate
        GrayscaleImage grayscaleImage = new GrayscaleImage(this.imageData);
//        int totalPixels =0;
//        double totalSum=0;
//        for(int y = 0; y <grayscaleImage.imageData.length; y++){
//            // check the value at each x position in the imageData.length and add 127
//            for(int x = 0; x < grayscaleImage.imageData[y].length; x++){
//                double number =grayscaleImage.imageData[y][x];
//                totalSum = totalSum + number;
//                //apparently you can just add a pixel each time you read a value instead of reading the length after you
//                //read the whole row
//                totalPixels = totalPixels + totalPixels;
//            }
//        }
//        // Calculate the current average brightness
//        double average = totalSum / totalPixels;
//        System.out.println("this is the current average " + average);
        double avg = grayscaleImage.averageBrightness();
        System.out.println("greyscale average: " +avg);

        // Get the ratio so that you can just multiply each [y][x] by the same factor to get 127 average
        double multiplier = 127 / avg;
        System.out.println("127/" +avg + " =" +multiplier);

        for( int y = 0; y < grayscaleImage.imageData.length; y++){
            //go through each row
            for(int x =0; x <grayscaleImage.imageData[y].length; x++){
                // for every x value in the row length take value and multiply it by the factor
                grayscaleImage.imageData[y][x] = (grayscaleImage.imageData[y][x] * multiplier);
            }
        }
        return grayscaleImage;
    }


    /**
     * Returns a new grayscale image that has been "mirrored" across the y-axis
     * In other words, each row of the image should be reversed
     * The original image should be unchanged
     * @return a new GrayscaleImage that is a mirrored version of the this
     */
    public GrayscaleImage mirrored(){
        //make a copy of the original imageData to reverse it
        GrayscaleImage grayscaleImageReversed = new GrayscaleImage(this.imageData);
        //The rows are going to be the same because we are only flipping across the middle of x column
        for(int y=0; y < imageData.length; y++){
            for(int x=0; x < imageData[y].length; x++){
                //take the whole length of the row and get to the opposite corresponding position
                int mirroredIndex = imageData[y].length - 1 -x;
                //get our current x value in that row and place it in the mirrored position/index for our reversed array
                grayscaleImageReversed.imageData[y][mirroredIndex] = this.imageData[y][x];
                //grayscaleImageReversed.imageData[y][x] = this.imageData[y][mirroredIndex];
            }
        }

        return grayscaleImageReversed ;
    }

    /**
     * Returns a new GrayscaleImage of size width x height, containing the part of `this`
     * from startRow -> startRow + height, startCol -> startCol + width
     * The original image should be unmodified
     * @param startRow
     * @param startCol
     * @param width
     * @param height
     * @return A new GrayscaleImage containing the sub-image in the specified rectangle
     * @throws IllegalArgumentException if the specified rectangle goes outside the bounds of the original image
     */
    public GrayscaleImage cropped(int startRow, int startCol, int width, int height){
        //throw if specified rectangle goes outside the bounds of the original image
        // the crop cannot start before the image does and it also can't be larger than the row/column parameters
        if (startRow < 0 || startCol < 0 || startRow + height > this.imageData.length || startCol + width > this.imageData[0].length) {
            throw new IllegalArgumentException("The specified rectangle goes outside the bounds of the image");
        }

        // GrayscaleImage grayscaleImageCropped = new GrayscaleImage( new double[height][width]);
        // Create a new 2D array for the cropping image data
        double[][] imageDataCropped = new double[height][width];
        //GrayscaleImage grayscaleImageCropped = new GrayscaleImage( imageDataCropped);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //for each position in the row, assign the image data value with the starting position in mind
                imageDataCropped[y][x] = this.imageData[startRow + y][startCol + x];
            }
        }
        return new GrayscaleImage(imageDataCropped);
    }

    /**
     * Returns a new "centered" square image (new width == new height)
     * For example, if the width is 20 pixels greater than the height,
     * this should return a height x height image, with 10 pixels removed from the left and right
     * edges of the image
     * If the number of pixels to be removed is odd, remove 1 fewer pixel from the left or top part
     * (note this convention should be SIMPLER/EASIER to implement than the alternative)
     * The original image should not be changed
     * @return a new, square, GrayscaleImage
     */
    public GrayscaleImage squarified(){
        //{{1,2,3},{4,5,6}} to {{1,2},{4,5}}
        int myHeight = this.imageData.length;
        int myWidth = this.imageData[0].length;
        int difference = 0;
        GrayscaleImage squareGrayScale;
        if(myHeight>myWidth){
            //difference is the closest value to ((the difference between the height & width) /2)
            // if your original h=800 and w=400, then the diff will be 400/2
            //we divide it by two because the new image is centered equally within that old set of parameters
            squareGrayScale = new GrayscaleImage(new double[myWidth][myWidth]);
            difference = Math.floorDiv(myHeight - myWidth,2);
            //from the top left of the first y-value to the last y value for our new square
            int startingYvalue=0;
            for(int y = difference; y < difference + myWidth; y++){
                for( int x =0; x < myWidth; x++){
                    //on the first loop: we need to put our value
                    // into our new grayScaleImage but at the 0th row in it, not at the corresponding
                    //row from our original image
                    squareGrayScale.imageData[startingYvalue][x]= imageData[y][x];
                }
                //after each loop we have to iterate to the new y position
                // we need to iterate the starting yvalue after each row
                startingYvalue = startingYvalue + startingYvalue;
            }


        } else {
            squareGrayScale = new GrayscaleImage(new double[myHeight][myHeight]);
            difference = Math.floorDiv(myWidth - myHeight,2);
            int startXvalue = 0;
            for(int y = 0; y < myHeight; y++){
                for (int x = difference; x < difference + myHeight; x++){
                    squareGrayScale.imageData[y][startXvalue]= imageData[y][x];
                    //increase the starting xValue of our new square image for every iteration of y
                    startXvalue ++;
                }
                startXvalue =0;
            }

        }

        return squareGrayScale;
    }


}
