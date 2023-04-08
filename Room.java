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
    private Item aItem;

    /**
     * Constructeur de la classe Room
     * @param pRoomName
     */
    public Room(final String pRoomName) {
        this.aDescription = pRoomName;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = null;
        this.aItem = null;
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

    public void setItem(final String pItemName, final int pWorth) {
        this.aItem = new Item(pItemName, pWorth);
    }
} // Room
