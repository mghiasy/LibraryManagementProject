package business;

import java.util.List;

import dataaccess.Auth;
import javafx.collections.ObservableList;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public ObservableList allMembers();
	public Auth getcurrentAuth();
	public List<String> allBookIds();
	public void addMember(LibraryMember m);

	
}
