import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

/**
 * Created by sampanna.kahu on 10/01/16.
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Coordinate> getNeighbours(int image_width, int image_height) {
        List<Coordinate> candidateNeighbours = Lists.newLinkedList();
        candidateNeighbours.add(new Coordinate(x - 1, y));
        candidateNeighbours.add(new Coordinate(x + 1, y));
        candidateNeighbours.add(new Coordinate(x, y - 1));
        candidateNeighbours.add(new Coordinate(x, y + 1));

        List<Coordinate> output = Lists.newLinkedList();
        for (Coordinate coordinate: candidateNeighbours) {
            if (!isOutOfBounds(coordinate,image_width,image_height)) {
                output.add(coordinate);
            }
        }
        return output;
    }

    private boolean isOutOfBounds(Coordinate coordinate, int width, int height) {
        if (coordinate.getX()<0 || coordinate.getY()<0 || coordinate.getX()>width || coordinate.getY()>height) {
            return true;
        }
        return false;
    }
}
