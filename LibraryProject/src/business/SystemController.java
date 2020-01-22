package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

//	public void loginAdmin()
//	{
//		currentAuth = Auth.ADMIN;
//	}
//
//	public void loginLibrarian()
//	{
//		currentAuth = Auth.LIBRARIAN;
//	}
//
//	public void loginBoth()
//	{
//		currentAuth = Auth.BOTH;
//	}

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
	}

	//farahat
	public void addMember(LibraryMember m)
	{
		DataAccess data = new DataAccessFacade();
		data.saveNewMember(m);
	}
	public Auth getcurrentAuth()
	{
		return currentAuth;
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		//Members
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	public List<LibraryMember> allMembers() {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> retval = new ArrayList<>();
		//Members
		retval.addAll(da.readMemberMap().values());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	
}
