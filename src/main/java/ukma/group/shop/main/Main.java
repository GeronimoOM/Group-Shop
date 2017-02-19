package ukma.group.shop.main;

import ukma.group.shop.dao.DaoManager;
import ukma.group.shop.dao.SupplierDao;
import ukma.group.shop.entity.Supplier;
import ukma.group.shop.gui.ItemWindow;
import ukma.group.shop.gui.MainWindow;

public class Main {

	public static void main(String[] args) {
		daoExample();
		
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


	}

}
