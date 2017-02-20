package ukma.group.shop.dao;

import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;
import ukma.group.shop.entity.pojo.ItemPrice;

import java.util.List;

public interface ItemDao extends Dao<Item, Long> {

    List<ItemPrice>  findBySupplierWithLastPrice(Supplier supplier);

    List<Item> findByDepartment(Department department);

    List<Item> findByDepartmentDepleting(Department department);

}
