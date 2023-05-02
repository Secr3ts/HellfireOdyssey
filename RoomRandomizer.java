import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RoomRandomizer Class
 */
public class RoomRandomizer {
    private List<Room> aRooms;
    private Random aRandom;

    /**
     * Constructor of RoomRandomizer class
     * 
     * @param pRooms The ArrayList containing all GameEngine's Rooms
     */
    public RoomRandomizer(final ArrayList<Room> pRooms) {
        this.aRooms = pRooms;
        this.aRandom = new Random();
    }

    /**
     * Add a Room to the ArrayList just in case
     * 
     * @param pRoom Room to add
     */
    public void addRoom(final Room pRoom) {
        this.aRooms.add(pRoom);
    }

    /**
     * Get a random room
     * 
     * @return the random Room generated
     */
    public Room getRandomRoom() {
        int aRandom = this.aRandom.nextInt(this.aRooms.size());
        return this.aRooms.get(aRandom);
    }
}
