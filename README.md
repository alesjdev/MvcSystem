# MVC System (In progress)
This is a stock/inventory management system with an integrated Point of Sale (POS). It uses Java Servlets/Filters backed up by a PostgreSQL database.

It allows you to view/edit/delete suppliers, product stock and categories, clients, employees.

After making a sale in the POS, it saves the order and applied discount, and subtracts the sold stock in the corresponding product(s) database.

Decided to go with a "Validation Code" system where managers give a code for new employees to be able to register an account, and different code will mean different account type.

Account types are:
Manager rank - Access to the complete system, able to see/edit stock, clients and all.
Employee rank - Access limited to POS and view recent orders.