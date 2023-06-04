package core.objects;

import java.util.ArrayList;
import java.util.Random;

// STATIC CLASS DEFINES DIMENSION AND PLACEMENT OPERATIONS
public class Ranges {
    private static Coord size;
    private static ArrayList<Coord> coordsList;
    private static final Random random = new Random();

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

    // check if value in Range size
    public static boolean inRange(Coord coord) {
        return coord.getX() >= 0 && coord.getX() < size.getX()
                && coord.getY() >= 0 && coord.getY() < size.getY();
    }

    public static Coord getRandomCoord () {
        return new Coord(
            random.nextInt(size.getX()),
            random.nextInt(size.getY())
        );
    }

    public static ArrayList<Coord> getCoordsAround(Coord coord){
         Coord around;
         ArrayList<Coord> listCoords = new ArrayList<Coord>();
         for (int x = coord.getX() - 1; x <= coord.getX() + 1; x++)
             for (int y = coord.getY() - 1; y <= coord.getY() + 1; y++)
                 if (inRange(around = new Coord(x,y)))
                    if(!around.equals(coord))
                        listCoords.add(around);
         return listCoords;
    }
}
