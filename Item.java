/**
 * This class represents an item in the game.
 */
public class Item {
    private String aDescription;
    private int aWorth;

    /**
     * Constructor for objects of class Item
     */
    public Item(final String pDescription, final int pWorth) {
        this.aDescription = pDescription;
        this.aWorth = pWorth;
    }
    
    /**
     * @return the description of the item
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * @return the worth of the item
     */
    public int getWorth() {
        return this.aWorth;
    }
}
