import com.google.common.collect.Lists;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by sampanna.kahu on 10/01/16.
 */
public class BlobExtraction {
    private final static Logger LOGGER = Logger.getLogger(BlobExtraction.class.getName());
    private static final int threshold = 30;
    private static final Color white = new Color(255,255,255);

    public static void main(String[] args) {
        String filePath = "/Users/sampanna.kahu/Desktop/TestImages/";
        String inputFileName = "blackBlobs.bmp";

        try {

            //Reading the image to be processed
            File file = new File(filePath + inputFileName);
            BufferedImage input_image = ImageIO.read(file);
            LOGGER.info("File reading complete.");

            //finding the width and height of the image
            int width = input_image.getWidth();
            int height = input_image.getHeight();
            LOGGER.info("Input image dimensions: Width="+ width + ", Height=" + height);


            for (int i=0; i<width; i++) {
                input_image.setRGB(i,0,white.getRGB());
                input_image.setRGB(i,height-1,white.getRGB());
            }
            for (int i=0; i<height; i++) {
                input_image.setRGB(0,i,white.getRGB());
                input_image.setRGB(width-1,i,white.getRGB());
            }

            LOGGER.info("Finding blobs...");
            List<Blob> blobList = Lists.newLinkedList();
            for (int x=0; x<width ; x++) {
                for (int y=0; y<height; y++) {
                    if (isBlack(input_image.getRGB(x,y),threshold) && !isCoordinateAccountedFor(blobList, new Coordinate(x,y))) {
                        blobList.add(floodFill(new Coordinate(x,y), input_image, true));
                    }
                }
            }
            LOGGER.info("Number of blobs found: " + blobList.size());

            for (int i=0; i<blobList.size(); i++) {
                BufferedImage outputImage = blobList.get(i).createImage();
                File outputFile = new File(filePath + inputFileName + "_outputBlob"+i+".bmp");
                try {
                    ImageIO.write(outputImage, "bmp", outputFile);
                    LOGGER.info("Created file for blob number " + i + ". Location: " + filePath + inputFileName + "_outputBlob"+i+".bmp");
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.severe("Failed to write file for blob number :"+ i + ". Location: " + filePath + inputFileName + "_outputBlob"+i+".bmp");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.severe("Failed to read input image from location: " + filePath + inputFileName);
        }

    }

    private static Blob floodFill(Coordinate coordinate, BufferedImage input_image, boolean replacementAllowed) {
        BufferedImage image = input_image;
        int width = input_image.getWidth();
        int height = input_image.getHeight();
        Blob outputBlob = new Blob();
        LinkedList<Coordinate> queue = Lists.newLinkedList();
        queue.add(coordinate);
        while (!queue.isEmpty()) {
            Coordinate node = queue.removeLast();
            if (isBlack(image.getRGB(node.getX(),node.getY()),threshold)) {
                outputBlob.addCoordinate(node);
                image.setRGB(node.getX(), node.getY(), white.getRGB());
                for (Coordinate neighbour: node.getNeighbours(width, height)) {
                    if (isBlack(image.getRGB(neighbour.getX(), neighbour.getY()), threshold)) {
                        queue.addLast(neighbour);
                    }
                }
            }
        }

        if (replacementAllowed) {
            input_image = image;
        }

        return outputBlob;
    }

    private static boolean isCoordinateAccountedFor(List<Blob> blobList, Coordinate coordinate) {
        for (Blob blob : blobList) {
            if (blob.contains(coordinate)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBlack(int RGB, int threshold) {
        Color color = new Color(RGB);
        if (color.getRed()<threshold && color.getGreen()<threshold && color.getBlue()<threshold) {
            return true;
        } else {
            return false;
        }
    }

}
