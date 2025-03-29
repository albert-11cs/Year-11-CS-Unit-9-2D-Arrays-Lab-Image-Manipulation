package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    //CHALLENGE 0: Display Image

    public static void main(String[] args) {
        String imagePath = "cyberpunk2077.jpg";

        // Original
        new APImage(imagePath).draw();

        // Transformed versions
        grayScale(imagePath);
        blackAndWhite(imagePath);
        edgeDetection(imagePath, 20);
        reflectImage(imagePath);
        rotateImage(imagePath);
    }

     //CHALLENGE ONE: Grayscale
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        APImage grayImage = image.clone();

        for (int y = 0; y < grayImage.getHeight(); y++) {
            for (int x = 0; x < grayImage.getWidth(); x++) {
                Pixel p = grayImage.getPixel(x, y);
                int avg = getAverageColour(p);
                p.setRed(avg);
                p.setGreen(avg);
                p.setBlue(avg);
            }
        }

        grayImage.draw();
    }

    /**
     * Helper method to calculate average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    }

    /**
     * CHALLENGE TWO: Black and White
     */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        APImage bwImage = image.clone();

        for (int y=0;y<bwImage.getHeight();y++) {
            for (int x=0;x<bwImage.getWidth();x++) {
                Pixel p = bwImage.getPixel(x,y);
                int avg = getAverageColour(p);
                int newValue;
                if (avg<128) {
                    newValue = 0;
                } else {
                    newValue = 255;
                }
                p.setRed(newValue);
                p.setGreen(newValue);
                p.setBlue(newValue);
            }
        }

        bwImage.draw();
    }

    /**
     * CHALLENGE Three: Edge Detection
     */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        APImage edgeImage = new APImage(image.getWidth(), image.getHeight());

        // make the whole image white
        for (int y=0;y<edgeImage.getHeight();y++) {
            for (int x=0;x<edgeImage.getWidth();x++) {
                Pixel p = edgeImage.getPixel(x,y);
                p.setRed(255);
                p.setGreen(255);
                p.setBlue(255);
            }
        }

        //detect edges and draw them as black dots
        for (int y=0;y<image.getHeight()-1;y++) {
            for (int x=0;x<image.getWidth()-1;x++) {
                Pixel current = image.getPixel(x,y);
                Pixel right = image.getPixel(x+1,y);
                Pixel below = image.getPixel(x,y+1);

                int currentAvg = getAverageColour(current);
                int rightAvg = getAverageColour(right);
                int belowAvg = getAverageColour(below);

                if (Math.abs(currentAvg - rightAvg) > threshold || Math.abs(currentAvg - belowAvg) > threshold) {
                    edgeImage.getPixel(x, y).setRed(0);
                    edgeImage.getPixel(x, y).setGreen(0);
                    edgeImage.getPixel(x, y).setBlue(0);
                }
            }
        }

        edgeImage.draw();
    }

    /**
     * CHALLENGE Four: Reflect Image
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        APImage reflectedImage = new APImage(image.getWidth(), image.getHeight());

        for (int y=0;y<image.getHeight();y++) {
            for (int x=0;x<image.getWidth();x++) {
                Pixel p = image.getPixel(x,y);
                reflectedImage.setPixel(image.getWidth()-1-x,y,p.clone());
            }
        }

        reflectedImage.draw();
    }

    /**
     * CHALLENGE Five: Rotate Image
     */
    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        APImage rotatedImage = new APImage(image.getHeight(), image.getWidth());

        for (int y=0;y<image.getHeight();y++) {
            for (int x=0;x<image.getWidth();x++) {
                Pixel p = image.getPixel(x,y);
                rotatedImage.setPixel(image.getHeight()-1-y,x,p.clone());
            }
        }
        rotatedImage.draw();
    }
}