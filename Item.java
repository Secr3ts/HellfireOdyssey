/**
 * This class represents an item in the game.
 */
public class Item {
    private String aName;
    private String aDescription;
    private int aWorth;

    /**
     * Constructor for objects of class Item
     */
    public Item(final String pName, final String pDescription, final int pWorth) {
        this.aName = pName;
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
     * @return the name of the item
     */
    public String getName() {
        return this.aName;
    }
    /**
     * @return the worth of the item
     */
    public int getWorth() {
        return this.aWorth;
    }
}
