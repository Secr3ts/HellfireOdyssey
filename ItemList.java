import java.util.HashMap;
import java.util.Set;

/**
 * Classe ItemList - une liste d'items
 * @author Aloïs Fournier
 */
public class ItemList {
    private HashMap<String, Item> aItemList;
   
    /**
     * Constructeur de la classe ItemList
     */
    public ItemList() {
        this.aItemList = new HashMap<String, Item>();
    }

    /**
     * Ajoute un item à la liste
     * @param pItem
     */
    public void addItem(final Item pItem) {
        this.aItemList.put(pItem.getName(), pItem);
    }

    /**
     * Retourne un item de la liste
     * @param pItemName
     * @return Item
     */
    public Item getItem(final String pItemName) {
        return this.aItemList.get(pItemName);
    }

    /**
     * Retire un item de la liste
     * @param pItemName
     */
    public void removeItem(final String pItemName) {
        if (this.aItemList.containsKey(pItemName)) {
            this.aItemList.remove(pItemName);
        }
    }

    /**
     * Vérifie si un item est dans la liste
     * @param pItemName
     * @return
     */
    public boolean hasItem(final String pItemName) {
        if (this.aItemList.containsKey(pItemName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retourne la liste des items en String
     * @return String
     */
    public String getItemString() {
        String items = "Items: ";
        Set<String> keys = this.aItemList.keySet();

        for(String itemKey : keys) {
            items += itemKey + " ";
        }

        int weight = 0;
        for (Item item : this.aItemList.values()) {
            weight += item.getWeight();
        }

        return items + "\nTotal Weight: " + weight;
    }
}