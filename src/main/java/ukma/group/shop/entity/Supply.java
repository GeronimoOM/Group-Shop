package ukma.group.shop.entity;

import java.sql.Timestamp;

public class Supply {
    private Long id;
    private Timestamp date;
    private Supplier supplier;

    public Supply() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "id=" + id +
                ", date=" + date +
                ", supplier=" + supplier +
                '}';
    }
}
