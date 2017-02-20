package ukma.group.shop.gui;


import ukma.group.shop.dao.DaoManager;
import ukma.group.shop.dao.SupplyDao;
import ukma.group.shop.entity.Supply;

import java.util.List;

public class SuppliesWindow extends EntityWindow<Supply>{

    public SuppliesWindow(String title, List<ColumnDefinition> column_structure, EntityWindowAdapter<Supply> adapter) {
        super(title, column_structure, adapter);
    }
/*
    private static class SupplyWindowAdapter implements EntityWindowAdapter<Supply>{
        private SupplyDao dao = DaoManager.getInstance().getSupplyDao();


    }*/
}
