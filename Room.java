import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Aloïs Fournier
 * @version 2023.15.02
 * 
 */
public class Room 
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private HashMap<String, Item> aItem;

    /**
     * Constructeur de la classe Room
     * @param pRoomName
     */
    public Room(final String pRoomName, final String pImageName) {
        this.aDescription = pRoomName;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImageName;
        this.aItem = new HashMap<String, Item>();
    }
    
    /**
     * remove an item from the room
    */
    public void removeItem(final String pItemName) {
        this.aItem.remove(pItemName);
    }

    /**
     * Retourne la description de la salle
     * @return String
     */
    public String getDescription() {
        return this.aDescription;
    }
    
    /**
     * Définit les sorties de la salle dans la direction indiquée
     * @param pDirection
     * @param pRoom
     */
    public void setExit(final String pDirection, final Room pRoom) {
        this.aExits.put(pDirection, pRoom);
    }
    /**
     * Retourne la salle dans la direction indiquée
     * @param pDirection
     * @return Room
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }
    /**
     * Retourne les sorties d'une salle en une String
     * @return String
     */
    public String getExitString() {
        
        String exit = "Exits: ";
        Set<String> keys = this.aExits.keySet();

        for(String exitKey : keys) {
            exit += exitKey + " ";
        }
        return exit;
    }
    /**
     * Retourne la description complète de la salle
     * @return Description complète de la salle
     */   
    public String getLongDescription() {
        return "You are " + this.aDescription + ".\n" + this.getExitString();
    }

    public String getImageName() {
        return this.aImageName;
    }

    
    public void addItem(final String pItemName, final Item pItem) {
        this.aItem.put(pItemName, pItem);
    }

    public void addNewItem(final String pItemName, final String pDesc, final int pWorth) {
        this.aItem.put(pItemName, new Item(pItemName, pDesc , pWorth));
    }

    public boolean hasItem(final String pItemName) {
        if (this.aItem.containsKey(pItemName)) {
            return true;
        } else {
            return false;
        }
    }
    
    public Item getItem(final String pItemName) {
        return this.aItem.get(pItemName);
    }

    public String getItemsString() {
        String items = "Items: ";
        Set<String> keys = this.aItem.keySet();

        for(String itemKey : keys) {
            items += itemKey + " ";
        }
        return items;
    }
} // Room
