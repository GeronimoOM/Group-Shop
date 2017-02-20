package ukma.group.shop.dao;

import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Purchase;

import java.util.List;

public interface PurchaseDao extends Dao<Purchase, Long> {

    List<Purchase> findByDepartment(Department department);

}
