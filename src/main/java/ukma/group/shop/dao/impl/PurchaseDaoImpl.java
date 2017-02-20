package ukma.group.shop.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import ukma.group.shop.dao.PurchaseDao;
import ukma.group.shop.entity.Department;
import ukma.group.shop.entity.Purchase;

import java.util.List;

public class PurchaseDaoImpl implements PurchaseDao{

    private static final String SQL_SELECT_PURCHASES = "SELECT p.id AS p_id, date, employee_id, p.name as p_name "+
            "FROM sh_purchases p INNER JOIN sh_employees e ON p.employee_id=e.id";
    private static final String SQL_INSERT_ORDER = "INSERT INTO sh_purchases (date, employee_id) VALUES (?, ?)";
    private QueryRunner queryRunner;

    public PurchaseDaoImpl(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public Purchase find(Long key) {
        return null;
    }

    @Override
    public List<Purchase> findAll() {
        return null;
    }

    @Override
    public void persist(Purchase entity) {

    }

    @Override
    public void update(Purchase entity) {

    }

    @Override
    public void delete(Long key) {

    }

    @Override
    public List<Purchase> findByDepartment(Department department) {
        return null;
    }
}
