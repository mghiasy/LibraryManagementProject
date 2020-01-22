/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 984894
 */
public class CheckoutRecord implements Serializable{
    
    private List<CheckoutEntry> checkoutEntries ; //ChecoutRecord consists of many of entries 
    
    public CheckoutRecord(){
    	
    	checkoutEntries = new ArrayList<CheckoutEntry>();
      }

    
    public List<CheckoutEntry> getCheckoutEntries() {
    	
      	return this.checkoutEntries;
    }
    
    
    public void addEntry(BookCopy bookCopy ,String checkoutDate,String dueDate)
    {
    	CheckoutEntry newEntry = new CheckoutEntry();
    	newEntry.setCheckoutRecord(this);
    	
    	newEntry.setBookCopy(bookCopy);
    	newEntry.setCheckoutDate(checkoutDate);
    	newEntry.setDueDate(dueDate);
    	
    	checkoutEntries.add(newEntry);
    	System.out.println(newEntry.getCheckoutDate() + "  " + newEntry.getDueDate());
    }
    
   
}
