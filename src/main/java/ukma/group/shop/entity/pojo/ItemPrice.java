package ukma.group.shop.entity.pojo;

import ukma.group.shop.entity.Item;

public class ItemPrice {

    private Item item;

    private float price;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
