package ukma.group.shop.entity;

public class Department {
	
	private Long id;
	private String name;

	public Department() {}
	
	public Department(Long id, String name) {
		this.setId(id);
		this.setName(name);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department dep = (Department) o;

        return name.equals(dep.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        //final StringBuilder sb = new StringBuilder("Item{");
        //sb.append("id=").append(id);
        //sb.append(", name='").append(name).append('\'');
        //sb.append('}');
        return name;//sb.toString();
    }
}
