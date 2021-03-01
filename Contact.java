
public class Contact {
	private String firstName;
	private String lastName;
	private String fullName;
	private String address;
	private String phone;
	
	public Contact() {
		this.firstName = "N/A";
		this.lastName = "N/A";
		this.address = "N/A";
		this.phone = "N/A";
	}
	
	public Contact(String firstName, String lastName, String address, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		
		if(isPhoneValid(phone)) {
			this.phone = phone;
		}
		else {
			this.phone = "N/A";
			System.out.println("The phone number can only contain numerical values. Update this contact's phone number later through the main menu.");
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if(isPhoneValid(phone)) {
			this.phone = phone;
		}
		else {
			this.phone = "N/A";
			System.out.println("The phone number can only contain numerical values.");
		}
	}
	
	public boolean isPhoneValid(String number) {
		try {
			long num = Long.parseLong(number);
			if (num < 0) { // in case of a negative number
				return false;
			}
		} catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "\n\tName: " + firstName + " " + lastName +  
				"\n\tAddress: " + address + "\n\tPhone Number: " + phone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Contact) {
			Contact otherContact = (Contact) obj;
			boolean sameFirstName, sameLastName, sameAddress, samePhone;
			
			sameFirstName = this.firstName.equalsIgnoreCase(otherContact.firstName);
			sameLastName = this.lastName.equalsIgnoreCase(otherContact.lastName);
			sameAddress = this.address.equals(otherContact.address);
			samePhone = this.phone.equals(otherContact.phone);
			
			return sameFirstName && sameLastName && sameAddress && samePhone;
		}
		
		return false;
	}
}
