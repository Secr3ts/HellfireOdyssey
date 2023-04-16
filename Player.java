import java.util.Random;
import java.util.Stack;

/**
 * Classe Player - un joueur du jeu d'aventure Zuul.
 * @author Aloïs Fournier
 */
public class Player {
    private String aName;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private ItemList aInventory;
    private int aMaxWeight;
    private int aCurrentWeight;

    /**
     * Constructeur de la classe Player
     * @param pName
     * @param pCurrentRoom
     */
    public Player(final String pName, final Room pCurrentRoom) {
        this.aCurrentRoom = pCurrentRoom;
        this.aName = pName;
        this.aPreviousRooms = new Stack<Room>();
        this.aInventory = new ItemList();
        this.aMaxWeight = new Random().nextInt(20 + 10) + 10;
        this.aCurrentWeight = 0;
    }

    // Commands

    /**
     * Déplace le joueur dans la salle indiquée
     * @param pNextRoom
     */
    public void goRoom(final String pNextRoom) {
        this.aPreviousRooms.push(this.aCurrentRoom);
        Room vNextRoom = this.aCurrentRoom.getExit(pNextRoom);
        this.aCurrentRoom = vNextRoom;
    }

    /**
     * Retourne le joueur dans la salle précédente
     */
    public void back() {
        this.aCurrentRoom = this.aPreviousRooms.pop();
    }    

    /**
     * Ajoute un item à l'inventaire du joueur
     * @param pItemName
     */
    public void take(final String pItemName) {
        this.aInventory.addItem(this.aCurrentRoom.getItem(pItemName));
        this.aCurrentRoom.removeItem(pItemName);
        this.aCurrentWeight += this.aInventory.getItem(pItemName).getWeight();
    }

    /**
     * Retire un item de l'inventaire du joueur
     * @param pItemName
     */
    public void drop(final String pItemName) {
        this.aCurrentRoom.addItem(this.aInventory.getItem(pItemName).getName(), this.aInventory.getItem(pItemName));;
        this.aInventory.removeItem(pItemName);
        this.aCurrentWeight -= this.aInventory.getItem(pItemName).getWeight();
    }

    // Getters
    /**
     * Retourne le nom du joueur
     * @return
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Retourne la salle actuelle du joueur
     * @return
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    /**
     * Retourne la pile des salles précédentes
     * @return
     */
    public Stack<Room> getPreviousRooms() {
        return this.aPreviousRooms;
    }
    
    /**
     * Retourne l'inventaire du joueur
     * @return
     */
    public ItemList getInventory() {
        return this.aInventory;
    }

    /**
     * Retourne true si l'item est dans l'inventaire du joueur
     * @param pItemName
     * @return
     */
    public boolean hasItem(final String pItemName) {
        if (this.aInventory.getItem(pItemName).getName().equals(pItemName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retourne le poids maximal que le joueur peut porter
     */
    public int getMaxWeight() {
        return this.aMaxWeight;
    }

    /**
     * Retourne le poids actuel de l'inventaire du joueur
     */
    public int getCurrentWeight() {
        return this.getCurrentWeight();
    }

    /**
     * Retourne true si le joueur peut porter l'item
     * @param pItemWeight
     * @return
     */
    public boolean canCarry(final int pItemWeight) {
        if (this.getCurrentWeight() + pItemWeight <= this.aMaxWeight) {
            return true;
        } else {
            return false;
        }
    }
}
