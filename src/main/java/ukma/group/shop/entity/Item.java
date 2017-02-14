package ukma.group.shop.entity;

public class Item {

    private Long id;
    private String name;
    private Long price;
    private Long amount;
    private Long min_amount;
    private Long department_id;
    
    public Item(String name, Long price, Long amount, Long min_amount, Long department_id)
    {
    	this.setName(name);
    	this.setPrice(price);
    	this.setAmount(amount);
    	this.setMin_amount(min_amount);
    	this.setDepartmentId(department_id);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getMin_amount() {
		return min_amount;
	}

	public void setMin_amount(Long min_amount) {
		this.min_amount = min_amount;
	}

	public Long getDepartmentId() {
		return department_id;
	}

	public void setDepartmentId(Long department_id) {
		this.department_id = department_id;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return name.equals(item.name) && price == item.price && amount == item.amount && min_amount == item.min_amount && department_id == item.department_id;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + price.hashCode() + amount.hashCode() + min_amount.hashCode() + department_id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", min_amount='").append(min_amount).append('\'');
        sb.append(", department_id='").append(department_id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
