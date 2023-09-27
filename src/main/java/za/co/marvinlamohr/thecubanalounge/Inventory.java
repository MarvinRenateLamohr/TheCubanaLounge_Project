package za.co.marvinlamohr.thecubanalounge;

public class Inventory {

    private int inventoryItemId;
    private String inventoryItemName;
    private Double inventoryItemPrice;
    private String inventoryItemCategory;


    public Inventory(int inventoryItemId, String inventoryItemName, Double inventoryItemPrice, String inventoryItemCategory) {
        this.inventoryItemId = inventoryItemId;
        this.inventoryItemName = inventoryItemName;
        this.inventoryItemPrice = inventoryItemPrice;
        this.inventoryItemCategory = inventoryItemCategory;
    }


    public int getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(int inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getInventoryItemName() {
        return inventoryItemName;
    }

    public void setInventoryItemName(String inventoryItemName) {
        this.inventoryItemName = inventoryItemName;
    }

    public Double getInventoryItemPrice() {
        return inventoryItemPrice;
    }

    public void setInventoryItemPrice(Double inventoryItemPrice) {
        this.inventoryItemPrice = inventoryItemPrice;
    }

    public String getInventoryItemCategory() {
        return inventoryItemCategory;
    }

    public void setInventoryItemCategory(String inventoryItemCategory) {
        this.inventoryItemCategory = inventoryItemCategory;
    }
}
