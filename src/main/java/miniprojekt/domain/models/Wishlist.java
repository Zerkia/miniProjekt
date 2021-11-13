package miniprojekt.domain.models;

public class Wishlist {

    private String itemName;
    private int itemQuantity;
    private String itemLink;
    private int wishlistID;

    public Wishlist(String itemName, int itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public Wishlist(String itemName, int itemQuantity, String itemLink, int wishlistID) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemLink = itemLink;
        this.wishlistID = wishlistID;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getItemQuantity() { return itemQuantity; }

    public void setItemQuantity(int itemQuantity) { this.itemQuantity = itemQuantity; }

    public String getItemLink() { return itemLink; }

    public void setItemLink(String itemLink) { this.itemLink = itemLink; }

    @Override
    public String toString() {
        return "Wishlist{" +
                "itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
