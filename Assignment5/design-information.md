1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples)
	- A "GroceryList" class is defined to help realize this requirement with the attribute "itemsList", which is an array of "Items", as well as a method to "addItem()" and "removeItem()". 
	- The quantity of an item can be changed by the "Item" class, which contains an "itemQuantity" attribute and a "changeQuantity" method. 
	-NOTE however, this assumes the user only wants whole numbered changes, ie 5 pounds of apples as opposed to 5 and a half pounds.

2. The application must contain a database (DB) of items and corresponding item types.
	- To realize this a "Database" class is defined with attributes "itemType" and "itemName", both of string type, sharing an aggregated relation with the "Item" class.

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal) and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
	- Implementation is not covered here, however the "Database" class has a method "checkForItem()" which will make queries to the DB with regards to the user's information
	- If there is a match, an "Item" object is added to the GroceryList, and the option to "changeQuantity()" will appear.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
	- This is also part of the implementation and is explained in the step above.

5. Lists must be saved automatically and immediately after they are modified.
	- This requirement is taken care of by the "saveList" method within the actual "GroceryList" class

6. Users must be able to check off items in a list (without deleting them).
	- This requirement is handled by the "isItemChecked" attribute in the "Item" class along with the "checkItem"

7. Users must also be able to clear all the check-off marks in a list at once.
	- This requirement is handled by the "checkItemsAll()" method in the "GroceryList" class

8. Check-off marks for a list are persistent and must also be saved immediately.
	- This requirement is handled by the private method "saveList()" which will continually autosave with changes

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).
	- Not considered because it does not directly affect the system design

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
	- Handled within the "Customer" class with respective methods, "renameList()", "deleteList()", "selectList()"
	- The seperation of the "Customer" class and "GroceryList" class allows for a 1 to many relationship, meaning the "Customer" can have many "GroceryLists", allowing for multiple lists

11. The User Interface (UI) must be intuitive and responsive
	- This is also not considered because it does not directly affect the system design
