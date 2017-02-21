require 'faker'

$departments = ["For Boys", "For Girls", "Stuffed Animals"]
$departments_amount = $departments.size

$employees_amount = 0
class Employee
	def initialize
		$employees_amount += 1

		@id = $employees_amount
		@name = Faker::Name.name.gsub('\'', '')
		@salary = rand(1000..10000)
		@department_id = rand(1..$departments_amount)
	end
end
$employees = []
4.times { $employees.push(Employee.new) }

$suppliers_amount = 0
class Supplier
	def initialize
		$suppliers_amount += 1

		@id = $suppliers_amount
		@name = Faker::Company.name.gsub('\'', '')
	end
end
$suppliers = []
4.times { $suppliers.push(Supplier.new) }

$items_amount = 0
class Item
	def initialize
		$items_amount += 1

		@id = $items_amount
		@name = Faker::Pokemon.name.gsub('\'', '') + ([" toy", " figurine", " statue", " plushie", " picture"].sample)
		@price = rand(10..100)
		@amount = 0
		@min_amount = rand(1..20)
		@department_id = rand(1..$departments_amount)
	end

	def add(amount)
		@amount += amount
	end

	def remove(amount)
		@amount -= amount
	end
end
$items = []
8.times { $items.push(Item.new) }

$orders_amount = 0
class Order
	def initialize
		$orders_amount += 1

		@id = $orders_amount
		@date = Faker::Date.backward(356)
		@supplier_id = rand(1..$suppliers_amount)
		@employee_id = rand(1..$employees_amount)
		@supply_id = nil

		@items = []

		amount = rand(1..2)
		amount.times do
			new_item = { order_id: @id, item_id: rand(1..$items_amount), amount: rand(32..64) }
			found = false
			@items.each { |item| found = true if new_item.instance_variable_get(:@id) == item.instance_variable_get(:@id) }
			@items.push(new_item) if not found
		end
	end

	def supply_id(id)
		@supply_id = id
	end
end
$orders = []
8.times { $orders.push(Order.new) }

$supplies_amount = 0
class Supply
	def initialize
		$supplies_amount += 1

		@id = $supplies_amount
		@date = Faker::Date.backward(356)
		@supplier_id = rand(1..$suppliers_amount)

		@items = []

		amount = rand(1..2) 
		amount.times do
			order_i = rand(0...$orders.size)
			order = $orders[order_i]
			next if $orders[order_i].instance_variable_get(:@supply_id) != nil or order.instance_variable_get(:@supplier_id) != @supplier_id
			$orders[order_i].supply_id(@id)
			order.instance_variable_get(:@items).each do |order_item|
				new_item = { supply_id: @id, item_id: order_item[:item_id], amount: order_item[:amount], price: rand(10..100) }
				($items.find_all { |item| item.instance_variable_get(:@id) == new_item[:item_id] })[0].add(new_item[:amount])
				found = false
				@items.each { |item| found = true if new_item.instance_variable_get(:@id) == item.instance_variable_get(:@id) }
				@items.push(new_item) if not found
			end
		end
	end

	def empty?
		return @items.empty?
	end
end
$supplies = []
32.times { $supplies.push(Supply.new) }
$supplies.delete_if { |supply| supply.empty? }


$purchases_amount = 0
class Purchase
	def initialize
		$purchases_amount += 1

		@id = $purchases_amount
		@date = Faker::Date.backward(356)
		@employee_id = rand(1..$employees_amount)
		
		@items = []

		amount = rand(1..2)
		amount.times do
			item_i = rand(1..$items_amount)
			new_item = { purchase_id: @id, item_id: $items[item_i].instance_variable_get(:@id), amount: rand(0..3) }
			new_item[:amount] = $items[item_i].instance_variable_get(:@amount).to_i if new_item[:amount] > $items[item_i].instance_variable_get(:@amount).to_i
			next if new_item[:amount] == 0
			($items.find_all { |item| item.instance_variable_get(:@id) == new_item[:item_id] })[0].remove(new_item[:amount])
			found = false
			@items.each { |item| found = true if new_item.instance_variable_get(:@id) == item.instance_variable_get(:@id) }
			@items.push(new_item) if not found
		end
	end

	def empty?
		return @items.empty?
	end
end
$purchases = []
16.times { $purchases.push(Purchase.new) }
$purchases.delete_if { |purchase| purchase.empty? }

File.open('data.sql', 'w') do |file|
	$departments.size.times { |i| file.write("INSERT INTO `sh_departments` (`id`, `name`) VALUES (#{i + 1}, '#{$departments[i]}');\n") }
	file.write("\n")

	$employees.each { |el| file.write("INSERT INTO `sh_employees` (`id`, `name`, `salary`, `department_id`) VALUES (#{el.instance_variable_get(:@id)}, '#{el.instance_variable_get(:@name)}', #{el.instance_variable_get(:@salary)}, #{el.instance_variable_get(:@department_id)});\n") }
	file.write("\n")

	$suppliers.each { |el| file.write("INSERT INTO `sh_suppliers` (`id`, `name`) VALUES (#{el.instance_variable_get(:@id)}, '#{el.instance_variable_get(:@name)}');\n") }
	file.write("\n")

	$items.each { |el| file.write("INSERT INTO `sh_items` (`id`, `name`, `price`, `amount`, `min_amount`, `department_id`) VALUES (#{el.instance_variable_get(:@id)}, '#{el.instance_variable_get(:@name)}', #{el.instance_variable_get(:@price)}, #{el.instance_variable_get(:@amount)}, #{el.instance_variable_get(:@min_amount)}, #{el.instance_variable_get(:@department_id)});\n") }
	file.write("\n")

	$orders.each do |el|
		file.write("INSERT INTO `sh_orders` (`id`, `date`, `supplier_id`, `employee_id`, `supply_id`) VALUES (#{el.instance_variable_get(:@id)}, '#{el.instance_variable_get(:@date)}', #{el.instance_variable_get(:@supplier_id)}, #{el.instance_variable_get(:@employee_id)}, #{el.instance_variable_get(:@supply_id) == nil ? "NULL" : el.instance_variable_get(:@supply_id)});\n")
		el.instance_variable_get(:@items).each do |item|
			file.write("INSERT INTO `sh_orders_items` (`order_id`, `item_id`, `amount`) VALUES (#{item[:order_id]}, #{item[:item_id]}, #{item[:amount]});\n")
		end
	end
	file.write("\n")

	$supplies.each do |el|
		file.write("INSERT INTO `sh_supplies` (`id`, `date`, `supplier_id`) VALUES (#{el.instance_variable_get(:@id)}, '#{el.instance_variable_get(:@date)}', #{el.instance_variable_get(:@supplier_id)});\n")
		el.instance_variable_get(:@items).each do |item|
			file.write("INSERT INTO `sh_supplies_items` (`supply_id`, `item_id`, `amount`, `price`) VALUES (#{item[:supply_id]}, #{item[:item_id]}, #{item[:amount]}, #{item[:price]});\n")
		end
	end
	file.write("\n")

	$purchases.each do |el|
		file.write("INSERT INTO `sh_purchases` (`id`, `date`, `employee_id`) VALUES (#{el.instance_variable_get(:@id)}, '#{el.instance_variable_get(:@date)}', #{el.instance_variable_get(:@employee_id)});\n")
		el.instance_variable_get(:@items).each do |item|
			file.write("INSERT INTO `sh_purchases_items` (`purchase_id`, `item_id`, `amount`) VALUES (#{item[:purchase_id]}, #{item[:item_id]}, #{item[:amount]});\n")
		end
	end
	file.write("\n")

end
