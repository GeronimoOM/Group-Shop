package ukma.group.shop.gui;


import ukma.group.shop.dao.DaoManager;
import ukma.group.shop.dao.SupplyDao;
import ukma.group.shop.entity.Item;
import ukma.group.shop.entity.Supplier;
import ukma.group.shop.entity.Supply;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class SupplyWindow extends EntityWindow<Supply> {

    public SupplyWindow(String title, List<ColumnDefinition> column_structure, EntityWindowAdapter<Supply> adapter) {
        super(title, column_structure, adapter);
    }

    private static class SupplyWindowAdapter implements EntityWindowAdapter<Supply> {
        private SupplyDao dao = DaoManager.getInstance().getSupplyDao();

        @Override
        public Supply rowToEntity(Object[] row) {
            Long id = (Long) row[0];
            /*Item item = (Item)row[1];
            Long price = (Long)row[2];
            Long amount = (Long)row[3];*/
            //TODO: find the bug and refractor this shit
            Timestamp date = null;

            if (row[1] instanceof Timestamp) {
                date = (Timestamp) row[1];
            } else if (row[1] instanceof String) {
                date = Timestamp.valueOf((String) row[1]);
            }
            Supplier supplier = (Supplier) row[2];
            return new Supply(id, date, supplier);
        }

        public Object[] entityToRow(Supply entity) {
            System.out.println(entity);
            return new Object[]{entity.getId(), entity.getDate(), entity.getSupplier()};
        }

        @Override
        public void onAdd(Supply entity) {
            dao.persist(entity);
//            System.out.println("onadd - " + entity);
        }

        @Override
        public void onRemove(Supply entity) {
            dao.delete(entity.getId());
        }

        @Override
        public void onUpdate(Supply entity) {
            dao.update(entity);
//            System.out.println("onupd" + entity);
        }

        @Override
        public List<Supply> load() {
            return dao.findAll();
        }
    }

    public SupplyWindow() {
        super(
                "Supplies",
                Arrays.asList(
                        new ColumnDefinition("id", false),
                        /*new ColumnDefinition("item", 200, DaoManager.getInstance().getItemDao().findAll().toArray()),
                        new ColumnDefinition("price"),
                        new ColumnDefinition("amount"),*/
                        new ColumnDefinition("date", 200),
                        new ColumnDefinition("supplier", 200, DaoManager.getInstance().getSupplierDao().findAll().toArray())
                ),
                new SupplyWindowAdapter());

    }
}
