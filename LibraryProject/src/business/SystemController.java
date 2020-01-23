package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

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

	//Adding new members
	public void addMember(LibraryMember m)
	{
		DataAccess data = new DataAccessFacade();
		data.saveNewMember(m);
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		//Members
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}


	//Returns details of all lib members
	public ObservableList allMembers() {
		DataAccess da = new DataAccessFacade();
//		List<LibraryMember> retval = new ArrayList<>();
		HashMap<String, LibraryMember> map = da.readMemberMap();
		ObservableList<LibraryMember> l= FXCollections.observableArrayList();
		for(Map.Entry<String, LibraryMember> entry: map.entrySet()) {
			l.add(entry.getValue());
		}
		return l;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	public Auth getcurrentAuth()
	{
		return currentAuth;
	}
	
	
}
