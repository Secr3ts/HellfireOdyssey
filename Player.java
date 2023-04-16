import java.util.Stack;

public class Player {
    private String aName;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private ItemList aInventory;
    
    public Player(final String pName, final Room pCurrentRoom) {
        this.aCurrentRoom = pCurrentRoom;
        this.aName = pName;
        this.aPreviousRooms = new Stack<Room>();
        this.aInventory = new ItemList();
    }

    // Commands

    public void goRoom(final String pNextRoom) {
        this.aPreviousRooms.push(this.aCurrentRoom);
        Room vNextRoom = this.aCurrentRoom.getExit(pNextRoom);
        this.aCurrentRoom = vNextRoom;
    }

    public void back() {
        this.aCurrentRoom = this.aPreviousRooms.pop();
    }    

    public void take(final String pItemName) {
        this.aInventory.addItem(this.aCurrentRoom.getItem(pItemName));
        this.aCurrentRoom.removeItem(pItemName);
    }

    public void drop(final String pItemName) {
        this.aCurrentRoom.addItem(this.aInventory.getItem(pItemName).getName(), this.aInventory.getItem(pItemName));;
        this.aInventory.removeItem(pItemName);
    }

    // Getters
    public String getName() {
        return this.aName;
    }

    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    public Stack<Room> getPreviousRooms() {
        return this.aPreviousRooms;
    }
    
    public ItemList getInventory() {
        return this.aInventory;
    }

    public boolean hasItem(final String pItemName) {
        if (this.aInventory.getItem(pItemName).getName().equals(pItemName)) {
            return true;
        } else {
            return false;
        }
    }
}
