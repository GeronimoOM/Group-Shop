package ukma.group.shop.entity;

import java.sql.Date;

public class Order {

    private Long id;

    private Date date;

    private Supplier supplier;

    private Supply supply;

    private Employee employee;

	public Order() {
		super();
	}

	public Order(Long id, Date date, Supplier supplier, Supply supply, Employee employee) {
		super();
		this.id = id;
		this.date = date;
		this.supplier = supplier;
		this.supply = supply;
		this.employee = employee;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
