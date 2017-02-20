package ukma.group.shop.dao;

public class SuppliesItemKey {

    private Long supplyId;
    private Long itemId;

    public SuppliesItemKey(Long supplyId, Long itemId) {
        this.supplyId = supplyId;
        this.itemId = itemId;
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
