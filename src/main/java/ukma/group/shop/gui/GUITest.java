package ukma.group.shop.gui;

import ukma.group.shop.entity.Item;

public class GUITest 
{

	public static void main(String[] args) 
	{
		MainWindow main_window = new MainWindow();

		ItemWindow item_window = new ItemWindow();
		
		item_window.add_item(new Item("asdasd", new Long(1), new Long(1), new Long(20), new Long(1)));
		item_window.add_item(new Item("aaaaaa", new Long(1), new Long(1), new Long(1), new Long(1)));
		item_window.add_item(new Item("111", new Long(1), new Long(1), new Long(2), new Long(1)));
		item_window.add_item(new Item("22222222", new Long(7), new Long(3), new Long(1), new Long(1)));
		item_window.add_item(new Item("asdasd", new Long(1), new Long(1), new Long(20), new Long(1)));
		item_window.add_item(new Item("aaaaaa", new Long(1), new Long(1), new Long(1), new Long(1)));
		item_window.add_item(new Item("111", new Long(1), new Long(1), new Long(2), new Long(1)));
		item_window.add_item(new Item("22222222", new Long(7), new Long(3), new Long(1), new Long(1)));
		item_window.add_item(new Item("asdasd", new Long(1), new Long(1), new Long(20), new Long(1)));
		item_window.add_item(new Item("aaaaaa", new Long(1), new Long(1), new Long(1), new Long(1)));
		item_window.add_item(new Item("111", new Long(1), new Long(1), new Long(2), new Long(1)));
		item_window.add_item(new Item("22222222", new Long(7), new Long(3), new Long(1), new Long(1)));
		item_window.add_item(new Item("asdasd", new Long(1), new Long(1), new Long(20), new Long(1)));
		item_window.add_item(new Item("aaaaaa", new Long(1), new Long(1), new Long(1), new Long(1)));
		item_window.add_item(new Item("111", new Long(1), new Long(1), new Long(2), new Long(1)));
		item_window.add_item(new Item("22222222", new Long(7), new Long(3), new Long(1), new Long(1)));
		item_window.add_item(new Item("asdasd", new Long(1), new Long(1), new Long(20), new Long(1)));
		item_window.add_item(new Item("aaaaaa", new Long(1), new Long(1), new Long(1), new Long(1)));
		item_window.add_item(new Item("111", new Long(1), new Long(1), new Long(2), new Long(1)));
		item_window.add_item(new Item("22222222", new Long(7), new Long(3), new Long(1), new Long(1)));
		item_window.add_item(new Item("asdasd", new Long(1), new Long(1), new Long(20), new Long(1)));
		item_window.add_item(new Item("aaaaaa", new Long(1), new Long(1), new Long(1), new Long(1)));
		item_window.add_item(new Item("111", new Long(1), new Long(1), new Long(2), new Long(1)));
		item_window.add_item(new Item("22222222", new Long(7), new Long(3), new Long(1), new Long(1)));
		item_window.add_item(new Item("asdasd", new Long(1), new Long(1), new Long(20), new Long(1)));
		item_window.add_item(new Item("aaaaaa", new Long(1), new Long(1), new Long(1), new Long(1)));
		item_window.add_item(new Item("111", new Long(1), new Long(1), new Long(2), new Long(1)));
		item_window.add_item(new Item("22222222", new Long(7), new Long(3), new Long(1), new Long(1)));
		
		main_window.item_window = item_window;
		
	}

}
