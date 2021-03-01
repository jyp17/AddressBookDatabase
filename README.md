# AddressBookDatabase

Program allows user to create an Address Book that is able to be saved onto and loaded from a MySQL database.
User will be able to create new contacts, delete contacts, update existing contacts, list all contacts, and search for a given contact by navigating a menu.

The menu will prompt the user for an integer input (from 1 to 6) based on the following options:
  1. Add a new contact
  2. Update an existing contact
  3. Delete a contact
  4. List all contacts
  5. Search for contact
  6. Quit

When user enters 1, they will be prompted for the new contact's information (name, address, phone number).

When user enters 2, they will be prompted to select from additional menu options asking if they would like to update an existing contact's 1) first name, 2) last name, 3) address, 4) phone number. Upon entering the appropriate/desired integer (from 1 to 4), the user will then be able to update a specific details about the contact (first name, last name, address, or phone number) by entering the contact's new details.

When user enters 3, they will be prompted for the first and last name of the contact they wish to delete. Upon entering the information, the contact will be deleted from the database if they exist.

When user enters 4, they will be presented with a list of all the contacts in the database. The contacts will be sorted aplhabetically in last name, first name order.

When user enters 5, they will be prompted to select from additional menu options asking if they would like to search for a specific contact by 1) first name, 2) last name, or 3) phone number. Upon entering the appropriate/desired integer (from 1 to 3), the user will then be able to enter the name or phone number of the contact they are looking for. The program will return all matching search results, including results that begin with the name or number the user has entered. (E.g., if the user enters "John" for the first name, then they will be given a list of all contacts whose first name is "John" as well as contacts whose first name begins with "John", such as "Johnson".)

When user enters 6, they will quit the menu and the program.
