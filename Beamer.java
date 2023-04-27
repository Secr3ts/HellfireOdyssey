/**
 * A Beamer is an item that can be used to teleport the player to a previously
 * saved room.
 */
public class Beamer extends Item {
    private Room aSavedRoom;
    private boolean aIsCharged = false;

    /**
     * Constructor for objects of class Beamer
     * 
     * @param pName        the name of the Beamer
     * @param pDescription the description of the Beamer
     * @param pWorth       the worth of the Beamer
     * @param pWeight      the weight of the Beamer
     */
    public Beamer(final String pName, final String pDescription, final int pWorth, final int pWeight) {
        super(pName, pDescription, pWorth, pWeight);
        this.aIsCharged = false;
        this.aSavedRoom = null;
    }

    /**
     * @return the saved room
     */
    public Room getSavedRoom() {
        return this.aSavedRoom;
    }

    /**
     * @return true if the Beamer is charged
     */
    public boolean isCharged() {
        return this.aIsCharged;
    }

    /**
     * Charges the Beamer
     * 
     * @param pRoom the room to save
     */
    public void charge(final Room pRoom) {
        this.aSavedRoom = pRoom;
        this.aIsCharged = true;
    }

    /**
     * Fires the Beamer
     * 
     * @param pPlayer the player that will be used to push the room to the stack /
     *                remove the beamer when firing
     */
    public void fire(final Player pPlayer) {
        if (this.aIsCharged) {
            pPlayer.setCurrentRoom(this.aSavedRoom);
            this.aIsCharged = false;
            pPlayer.removeItem(this.getName());
        }
    }
}
