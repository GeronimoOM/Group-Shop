package ukma.group.shop.dao;

import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;

import java.util.List;

public interface SupplierDao extends Dao<Supplier, Long> {

    List<Supplier> findByItem(Item item);

}
