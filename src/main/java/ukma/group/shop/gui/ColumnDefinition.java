package ukma.group.shop.gui;

public class ColumnDefinition
{
	private boolean editable;
	private String name;
	private int width;
	private Object[] data;

	public ColumnDefinition(boolean editable, String name, int width, Object[] data)
	{
		this.editable = editable;
		this.setName(name);
		this.setWidth(width);
		this.setData(data);
	}

	public ColumnDefinition(String name, int width, Object[] data)
	{
		this(true, name, width, data);
	}

	public ColumnDefinition(String name, int width)
	{
		this(true, name, width, null);
	}

	public ColumnDefinition(boolean editable, String name)
	{
		this(editable, name, 16, null);
	}

	public ColumnDefinition(String name)
	{
		this(true, name, 16, null);
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

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
}
