import java.sql.*;
import java.util.*;

public class AddressBookDB {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	
	public void readDB() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "password");
			statement = connect.createStatement();
			
			Menu();
			
		} catch(Exception e) {
			System.out.println("Can't establish a connection to database.");
			throw e;
		} finally {
			close();
		}
	}
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
		if (!resultSet.isBeforeFirst()) {    
		    System.out.println("Nothing found in database."); 
		}
		else {
			while(resultSet.next()) {
				String first_name = resultSet.getString("first_name");
				String last_name = resultSet.getString("last_name");
				String address = resultSet.getString("address");
				String phone = resultSet.getString("phone");
				
				System.out.println("Name: " + first_name + " " + last_name +
						"\nAddress: " + address + 
						"\nPhone Number: " + phone);
			}
		}
	}
	
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// searches if a contact exists in a table based on their first and last name
	public boolean isFound(Contact toBeFound) {
		try {
			preparedStatement = connect.prepareStatement("select * from contacts where first_name = ? and last_name = ?");
			preparedStatement.setString(1, toBeFound.getFirstName());
			preparedStatement.setString(2, toBeFound.getLastName());
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.isBeforeFirst()) {
				return false;
			}
			
			return true;	
		} catch (Exception e) {
			System.out.println("Could not search database for the contact specified.");
			return false;
		}
	}
	
	// add a new contact to table
	public void add(Contact toBeAdded) {
		try{
			preparedStatement = connect.prepareStatement("insert into AddressBook.contacts (first_name, last_name, address, phone) values(?, ?, ?, ?)");
			
			preparedStatement.setString(1, toBeAdded.getFirstName());
			preparedStatement.setString(2, toBeAdded.getLastName());
			preparedStatement.setString(3, toBeAdded.getAddress());
			preparedStatement.setString(4, toBeAdded.getPhone());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Failed to add contact into the address book.");
		}
	}
	
	// delete a contact from table
	public void delete(Contact toBeDeleted) {
		try{
			preparedStatement = connect.prepareStatement("delete from AddressBook.contacts where first_name = ? and last_name = ?;");
			
			preparedStatement.setString(1, toBeDeleted.getFirstName());
			preparedStatement.setString(2, toBeDeleted.getLastName());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Failed to delete contact from the address book.");
		}
	}
	
	// update contact info in the column specified by the user
	public void update(Contact toBeUpdated, String newValue, String column) {
		try{
			preparedStatement = connect.prepareStatement("update contacts set " + column + " = ? where first_name = ? and last_name = ?;");
			
			preparedStatement.setString(1, newValue);
			preparedStatement.setString(2, toBeUpdated.getFirstName());
			preparedStatement.setString(3, toBeUpdated.getLastName());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Failed to update contact's information.");
		}
	}
	
	// list all contacts in alphabetical order by last name, first name
	public void list() {
		try {
			preparedStatement = connect.prepareStatement("select * from AddressBook.contacts order by last_name asc, first_name asc");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);	
		} catch (Exception e) {
			System.out.println("Failed to list all contacts in the address book.");
		} 
	}
	
	// search for contact(s) by user specified condition
	public void search(String searchCondition, String column) {
		try{
			preparedStatement = connect.prepareStatement("select * from contacts where " + column + " like ?;");
			preparedStatement.setString(1, searchCondition + "%");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);
		} catch (Exception e) {
			System.out.println("Failed to execute search.");
		}
	}
	
	// the menu that the user will use to make changes to the address book
	public void Menu() {
		int userSelection;
		boolean isActive = true;
		Scanner menuSelect = new Scanner(System.in);
		MenuHelper assist = new MenuHelper(); //MenuHelper asks for additional information from the user
		
		System.out.println("-----------ADDRESS BOOK-----------");
		
		while (isActive) {
			System.out.println("Select from the following options:" +
					"\n1. Add a new contact" +
					"\n2. Update an existing contact" +
					"\n3. Delete a contact" +
					"\n4. List all contacts" +
					"\n5. Search for contact" +
					"\n6. Quit");
			
			try {
				userSelection = menuSelect.nextInt();
				
				if (userSelection == 1) {
					add(assist.askWhenAdding());
				}
				else if (userSelection == 2) {
					Contact old = assist.askWhenUpdating();
					// check if the contact whom the user is looking for exists
					if (!isFound(old)) {
						System.out.println("The contact whom you're looking for could not be found.");
					}
					else {
						System.out.println("Would you like to update 1) first name, 2) last name, 3) address, 4) phone number? ");
						int updateOption = menuSelect.nextInt();
						if (updateOption == 1) {
							update(old, assist.askForUpdatedValue(updateOption), "first_name");
						}
						else if (updateOption == 2) {
							update(old, assist.askForUpdatedValue(updateOption), "last_name");
						}
						else if (updateOption == 3) {
							update(old, assist.askForUpdatedValue(updateOption), "address");
						}
						else if (updateOption == 4) {
							update(old, assist.askForUpdatedValue(updateOption), "phone");
						}
						else {
							throw new InvalidInputException(); // if input is not 1 - 4
						}
					}
				}
				else if (userSelection == 3) {
					Contact del = assist.askWhenDeleting();
					// check if the contact whom the user is looking for exists
					if (!isFound(del)) {
						System.out.println("The contact whom you're looking for could not be found.");
					}
					else {
						delete(del);
					}
				}
				else if (userSelection == 4) {
					list();
				}
				else if (userSelection == 5) {
					System.out.println("Would you like to search by 1) first name, 2) last name, or 3) phone number? ");
					int searchSelect = menuSelect.nextInt();
					if (searchSelect == 1) {
						search(assist.askWhenSearching(searchSelect), "first_name");
					}
					else if (searchSelect == 2) {
						search(assist.askWhenSearching(searchSelect), "last_name");
					}
					else if (searchSelect == 3) {
						search(assist.askWhenSearching(searchSelect), "phone");
					}
					else { 
						throw new InvalidInputException(); // if user does not input an integer from 1-3 
					}
				}
				else if (userSelection == 6) {
					isActive = false;
					System.out.println("Goodbye!");
				}
				else {
					throw new InvalidInputException(); // if input is an integer that is not 1 - 6
				}
			} catch(InputMismatchException e) {
				System.out.println("Invalid input. You must enter an integer value.");
				menuSelect.nextLine();				
			} catch(InvalidInputException ie) {
				System.out.println("Invalid input. You must select from the available menu options.");
			}
		}
	}
}
