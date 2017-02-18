package ukma.group.shop.entity;

public class Item {

    private Long id;
    private String name;
    private Long price;
    private Long amount;
    private Long minAmount;
    private Long departmentId;
    
    public Item(String name, Long price, Long amount, Long minAmount, Long departmentId)
    {
    	this(null, name, price, amount, minAmount, departmentId);
    }

    public Item(Long id, String name, Long price, Long amount, Long minAmount, Long departmentId)
    {
    	this.setId(id);
    	this.setName(name);
    	this.setPrice(price);
    	this.setAmount(amount);
    	this.setMinAmount(minAmount);
    	this.setDepartmentId(departmentId);
    }
    
    public Item()
    {
    	
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

	public Long getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Long minAmount) {
		this.minAmount = minAmount;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return name.equals(item.name) && price == item.price && amount == item.amount && minAmount == item.minAmount && departmentId == item.departmentId;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + price.hashCode() + amount.hashCode() + minAmount.hashCode() + departmentId.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", minAmount='").append(minAmount).append('\'');
        sb.append(", departmentId='").append(departmentId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
