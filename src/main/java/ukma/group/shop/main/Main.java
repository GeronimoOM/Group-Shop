package ukma.group.shop.main;

import ukma.group.shop.dao.DaoManager;
import ukma.group.shop.dao.SupplierDao;
import ukma.group.shop.entity.Supplier;

public class Main {

	public static void main(String[] args) {
		daoExample();

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


	}

}
