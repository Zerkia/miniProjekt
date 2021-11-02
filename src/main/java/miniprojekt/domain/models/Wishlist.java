package miniprojekt.domain.models;

public class Wishlist {

    private String itemName;
    private int itemQuantity;


    public Wishlist(){}

    public Wishlist(String itemName, int itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
