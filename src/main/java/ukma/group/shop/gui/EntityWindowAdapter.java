package ukma.group.shop.gui;

import java.util.List;

public interface EntityWindowAdapter<T>
{
	T rowToEntity(Object[] row);
	Object[] entityToRow(T entity);

	void onAdd(T entity);
	void onRemove(T entity);
	void onUpdate(T entity);
	
	List<T> load();
}
