import java.util.HashMap;
import java.util.Set;

public class ItemList {
    private HashMap<String, Item> aItemList;
    
    public ItemList() {
        this.aItemList = new HashMap<String, Item>();
    }

    public void addItem(final Item pItem) {
        this.aItemList.put(pItem.getName(), pItem);
    }

    public Item getItem(final String pItemName) {
        return this.aItemList.get(pItemName);
    }

    public void removeItem(final String pItemName) {
        if (this.aItemList.containsKey(pItemName)) {
            this.aItemList.remove(pItemName);
        }
    }

    public boolean hasItem(final String pItemName) {
        if (this.aItemList.containsKey(pItemName)) {
            return true;
        } else {
            return false;
        }
    }

    public String getItemString() {
        String items = "Items: ";
        Set<String> keys = this.aItemList.keySet();

        for(String itemKey : keys) {
            items += itemKey + " ";
        }
        return items;
    }
}