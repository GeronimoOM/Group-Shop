package ukma.group.shop.entity;


public class SuppliesItem {
    private Supply supply;
    private Item item;
    private int amount;
    private double price;

    public SuppliesItem() {}

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SuppliesItem{" +
                "supply=" + supply +
                ", item=" + item +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
