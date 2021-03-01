import java.util.Scanner;

public class MenuHelper {
	private Scanner input;
	
	public MenuHelper() {
		input = new Scanner(System.in).useDelimiter("\n");
	}
	
	// ask user for the information of the contact they wish to add
	public Contact askWhenAdding() {
		Contact person = new Contact();
		
		System.out.println("Enter contact's first name: ");
		person.setFirstName(input.next());
		
		System.out.println("Enter contact's last name: ");
		person.setLastName(input.next());
		
		System.out.println("Enter contact's address: ");
		person.setAddress(input.next());
		
		System.out.println("Enter contact's phone number (numbers only, no dashes): ");	
		person.setPhone(input.next());
		
		return person;
	}
	
	// ask user for the information of the contact they wish to update
	public Contact askWhenUpdating() {
		Contact person = new Contact();
		
		System.out.println("Enter first name of the contact you wish to update: ");
		person.setFirstName(input.next());
		
		System.out.println("Enter last name of the contact you wish to update: ");
		person.setLastName(input.next());
		
		return person;
	}
	
	// ask user for the new information of a contact they are updating
	public String askForUpdatedValue(int menuSelection) {
		String newVal;
		
		if (menuSelection == 1) {
			System.out.println("Enter contact's new first name: ");
			newVal = input.next();
		}
		else if (menuSelection == 2) {
			System.out.println("Enter contact's new last name: ");
			newVal = input.next();
		}
		else if (menuSelection == 3) {
			System.out.println("Enter contact's new address: ");
			newVal = input.next();
		}
		else {
			System.out.println("Enter contact's new phone number: ");
			newVal = input.next();
		}
		
		return newVal;
	}
	
	// ask user for information of contact they want deleted
	public Contact askWhenDeleting() {
		Contact person = new Contact();
		
		System.out.println("Enter first name of the contact you wish to delete: ");
		person.setFirstName(input.next());
		
		System.out.println("Enter last name of the contact you wish to delete: ");
		person.setLastName(input.next());
		
		return person;
	}
	
	// ask user for information of a contact they wish to search
	public String askWhenSearching(int menuSelection) {
		String searchCondition;
		
		if (menuSelection == 1) {
			System.out.println("Enter contact's first name: ");
			searchCondition = input.next();
		}
		else if (menuSelection == 2) {
			System.out.println("Enter contact's last name: ");
			searchCondition = input.next();
		}
		else {
			System.out.println("Enter contact's phone number: ");
			searchCondition = input.next();
		}
		
		return searchCondition;
	}
}
