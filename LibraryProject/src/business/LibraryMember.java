package business;

import java.io.Serializable;
import java.time.LocalDate;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord checkRecord ;

	
	public CheckoutRecord getCheckRecord()
	{
		return this.checkRecord;
	}
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add,CheckoutRecord checkout) {
		super(fname,lname, tel, add);
		this.memberId = memberId;	
		this.checkRecord = checkout;
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
