package ukma.group.shop.main;

import ukma.group.shop.dao.*;
import ukma.group.shop.dao.impl.SuppliesItemDaoImpl;
import ukma.group.shop.entity.*;
import ukma.group.shop.entity.pojo.SuppliesItemKey;
import ukma.group.shop.gui.ItemWindow;
import ukma.group.shop.gui.MainWindow;

import java.sql.Timestamp;

public class Main {

	public static void main(String[] args) {
	//	daoExample();

		MainWindow main_window = MainWindow.getInstance();

		System.out.println("Thanks, mr.Skeletal");
	}

	//TODO move elsewhere
	private static void daoExample() {
		SupplierDao supplierDao = DaoManager.getInstance().getSupplierDao();

		Supplier supplier = new Supplier("Supplier 1");
		supplierDao.persist(supplier);
		System.out.println(supplier);

		supplier.setName("Sup 1");
		supplierDao.update(supplier);

		for(Supplier s: supplierDao.findAll()) {
			System.out.println(s);
		}

		supplierDao.delete(supplier.getId());

	/*	Supplier supplier1 = supplierDao.find(75l);

		SupplyDao supplyDao = DaoManager.getInstance().getSupplyDao();

		Supply supply = supplyDao.find(3l);


		supply.setDate(Timestamp.valueOf("1992-01-11 11:22:33"));
		supply.setSupplier(supplier1);

		*//*supplyDao.persist(supply);
		System.out.println(supply);*//*
*//*
		supply.setDate(Timestamp.valueOf("1658-12-23 13:33:24"));
		supplyDao.update(supply);

		for (Supply s: supplyDao.findAll()
			 ) {
			System.out.println(s);
		}
		supplyDao.delete(supply.getId());*//*
        Department department = new Department(1l,"name");
        ItemDao itemDao = DaoManager.getInstance().getItemDao();
        Item item = new Item(33l, "Black Dildo", 3l, 69l, 42l, department);



        SuppliesItemDao suppliesItemDao = DaoManager.getInstance().getSuppliesItemDao();

//        suppliesItemDao.delete(new SuppliesItemKey(3L, 33L));
        SuppliesItem suppliesItem = new SuppliesItem();

        suppliesItem.setAmount(10);
        suppliesItem.setPrice(3.21);
        suppliesItem.setSupply(supply);
        suppliesItem.setItem(item);

        System.out.println(suppliesItemDao.find(new SuppliesItemKey(1L, 34L)));*/


      /* suppliesItemDao.update(suppliesItem);

        for (SuppliesItem si: suppliesItemDao.findAll()
             ) {
            System.out.println(si);
        }*/
    }

}
