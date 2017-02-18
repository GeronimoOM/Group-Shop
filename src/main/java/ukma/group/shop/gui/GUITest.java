package ukma.group.shop.gui;

import ukma.group.shop.entity.Item;

public class GUITest 
{

	public static void main(String[] args) 
	{
		MainWindow main_window = new MainWindow();

		ItemWindow item_window = new ItemWindow();

		item_window.add_entity(new Object[]{1, "some name", 1 , 2, 200, 10});
		item_window.add_entity(new Object[]{2, "some name2", 10 , 2, 20, 10});
		item_window.add_entity(new Object[]{3, "itemmm", 7 , 2, 20, 10});
		item_window.add_entity(new Object[]{4, "some name4", 2 , 20, 20, 10});
		item_window.add_entity(new Object[]{5, "some name56", 1 , 2, 20, 10});
		item_window.add_entity(new Object[]{6, "1111111111", 2 , 2, 20, 10});
		
		main_window.item_window = item_window;
		
	}

}
