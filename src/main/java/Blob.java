import com.google.common.collect.Lists;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by sampanna.kahu on 10/01/16.
 */
public class Blob {
    private final static Logger LOGGER = Logger.getLogger(Blob.class.getName());
    List<Coordinate> coordinateList = Lists.newLinkedList();

    public void addCoordinate(Coordinate coordinate){
        coordinateList.add(coordinate);
    }

    public void removeCoordinate(Coordinate coordinate) {
        coordinateList.remove(coordinate);
    }

    public boolean contains(Coordinate coordinate) {
        if (coordinateList.contains(coordinate)) {
            return true;
        } else {
            return false;
        }
    }

    public BufferedImage createImage() {
        Color white = new Color(255,255,255);
        Color black = new Color(0,0,0);
        int[] dimens = getImageSize();
        BufferedImage output = new BufferedImage(dimens[0]+1, dimens[1]+1, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = output.createGraphics();
        graphics.setPaint(white);
        graphics.fillRect(0,0,output.getWidth(),output.getHeight());
        for (Coordinate coordinate: this.coordinateList) {
            output.setRGB(coordinate.getX() - dimens[2], coordinate.getY() - dimens[3], black.getRGB());
        }
        return output;
    }

    public int[] getImageSize() {
        if (this.coordinateList.isEmpty())
            return null;

        int[] dimens = {-1, -1, -1, -1};
        int minx, miny, maxx, maxy;
        List<Integer> xList = Lists.newLinkedList();
        List<Integer> yList = Lists.newLinkedList();
        for (Coordinate coordinate: this.coordinateList) {
            xList.add(coordinate.getX());
            yList.add(coordinate.getY());
        }

        minx = Collections.min(xList);
        maxx = Collections.max(xList);
        miny = Collections.min(yList);
        maxy = Collections.max(yList);

        dimens[0] = maxx-minx;
        dimens[1] = maxy-miny;
        dimens[2] = minx;
        dimens[3] = miny;

        return dimens;
    }

}
