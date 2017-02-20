package ukma.group.shop.entity;

public class Item {

    private Long id;
    private String name;
    private Long price;
    private Long amount;
    private Long minAmount;
    private Department department;
    
    public Item(String name, Long price, Long amount, Long minAmount, Department department)
    {
    	this(null, name, price, amount, minAmount, department);
    }

    public Item(Long id, String name, Long price, Long amount, Long minAmount, Department department)
    {
    	this.setId(id);
    	this.setName(name);
    	this.setPrice(price);
    	this.setAmount(amount);
    	this.setMinAmount(minAmount);
    	this.setDepartment(department);
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
