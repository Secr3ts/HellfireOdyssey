import java.util.ArrayList;

/**
 * Class TransporterRoom
 */
public class TransporterRoom extends Room {

    private RoomRandomizer aRoomRandomizer;

    public TransporterRoom(final String pRoomName, final String pImageName, final boolean pIsTrapDoor) {
        super(pRoomName, pImageName, pIsTrapDoor);
        this.aRoomRandomizer = new RoomRandomizer();
    }

    public void addRooms(final ArrayList<Room> pRooms) {
        this.aRoomRandomizer.addRooms(pRooms);
    }

    /**
     * Room
     * 
     * @param pDirection Direction to get the exit
     * @return the exit room (random)
     */
    public Room getRandomExit() {
        return this.aRoomRandomizer.getRandomRoom();
    }

}