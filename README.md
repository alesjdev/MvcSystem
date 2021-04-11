# MVC System (In progress)
This is a stock/inventory management system with an integrated Point of Sale (POS). It uses Java Servlets/Filters backed up by a PostgreSQL database.

It allows you to view/edit/delete suppliers, product stock and categories, clients, employees.

After making a sale in the POS, it saves the order and applied discount, and subtracts the sold stock in the corresponding product(s) database.

## Planned additions
Still deciding how to handle the Sign In system, since it doesn't make sense for anyone to freely create accounts in a private company stock handling system. Decided to go with a "Validation Code" system where managers give a code for new employees, and different code will mean different account type.

Account types will be:
Manager rank - Access to the complete system, able to see/edit stock, clients and all.
Employee rank - Access limited to POS and view recent orders.
