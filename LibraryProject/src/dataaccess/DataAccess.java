package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Author;
import business.Book;
import business.BookCopy;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
	public void saveNewBook(Book book); 
	//public  List<String> readBooksIsdn();
}
