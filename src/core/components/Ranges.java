package core.components;

import java.util.ArrayList;

// STATIC CLASS DEFINES DIMENSION OPERATIONS
public class Ranges {
    private static Coord size;
    private static ArrayList<Coord> coordsList;

    public static void setSize(Coord _size){
        // Field generation
        Ranges.size = _size;
        Ranges.coordsList = new ArrayList<Coord>();
        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                coordsList.add(new Coord(x, y));
            }

        }
    }
    public static Coord getSize() {
        return size;
    }
    public static ArrayList<Coord> getCoordsList() {
        return coordsList;
    }
}
