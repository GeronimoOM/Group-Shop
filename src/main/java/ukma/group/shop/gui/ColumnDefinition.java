package ukma.group.shop.gui;

public class ColumnDefinition 
{
	private String name;
	private int width;

	public ColumnDefinition(String name, int width)
	{
		this.setName(name);
		this.setWidth(width);
	}


	public ColumnDefinition(String name)
	{
		this(name, 16);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
