package Coursework;

import java.util.LinkedList;

import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

class Customer{
    // variables
    private String firstName;
    private String lastName;

    // functions-using encapsulation(get,set)
    public void setFirstName(String FName){
        this.firstName = FName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String LName){
        this.lastName = LName;
    }

    public String getLastName(){
        return lastName;
    }
}

class Account extends Customer{
    // variables
    private int balance;
    private int accountNumber;

    // METHODS
    // Account-constructor
    public Account(String FName, String LName, int accNum, int accBal){
        setFirstName(FName);
        setLastName(LName);
        this.accountNumber = accNum;
        this.balance = accBal;
    }

    // functions
    public int getBalance(){
        return balance;
    }

    public int getAccountNum(){
        return accountNumber;
    }

    public void deposit(int amount){
        if (amount > 0){
            this.balance += amount;
            System.out.println("Deposited Rs." + amount);
        }else{
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(int amount){
        if (amount > 0 && amount <= this.balance){
            this.balance -= amount;
            System.out.println("Withdrew Rs." + amount);
        }else{
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
}

class Transaction{
    public void transfer(Account acc1, Account acc2, int amount){
        if (acc1.getBalance() >= amount){
            acc1.withdraw(amount);
            acc2.deposit(amount);
            System.out.println("Transferred Rs." + amount + " from " + acc1.getAccountNum() + " to " + acc2.getAccountNum() + "." );
        }else {
        	System.out.println("Insufficient funds in account1 for transfer.");
        }
    }
}

class ReadAccounts{
	//variables
	public BufferedReader reader;
	public String url;
	
	//constructor
	public ReadAccounts(String URL){
		url = URL;
		try {
			reader = new BufferedReader(new FileReader(url));
//			System.out.println("The data is read.");
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void writeAccounts(LinkedList<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(url))) {
            for (Account account : accounts) {
                String line = account.getFirstName() + "," + account.getLastName() + "," + account.getAccountNum() + "," + account.getBalance();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//functions
	public LinkedList<String> getFirstNames(){
		LinkedList<String> firstNames = new LinkedList<>();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
//				System.out.println("Reading line: " + line);
				String[] parts = line.split(",");
//				System.out.println("Adding first name: " + parts[0]);
				firstNames.add(parts[0]);
			}
			//close the current reader
			reader.close();
			//create a new reader to start reading from the beginning of the file 
			reader = new BufferedReader(new FileReader(url));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return firstNames;
	}
	
	public LinkedList<String> getLastNames(){
		LinkedList<String> lastNames = new LinkedList<>();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				lastNames.add(parts[1]);
			}
			reader.close();
			reader = new BufferedReader(new FileReader(url));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return lastNames;
	}
	
	public LinkedList<Integer> getAccounts(){
		LinkedList<Integer> accountNumbers = new LinkedList<>();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				accountNumbers.add(Integer.parseInt(parts[2]));
			}
			reader.close();
			reader = new BufferedReader(new FileReader(url));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return accountNumbers;
	}
	
	public LinkedList<Integer> getBalances(){
		LinkedList<Integer> balances = new LinkedList<>();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				balances.add(Integer.parseInt(parts[3]));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return balances;
	}
}

public class final_coursework {
	public static void main(String[] args){
        String file = "D:\\OOP\\src\\Coursework\\Accounts.csv";
        ReadAccounts readAccounts = new ReadAccounts(file);
        
        LinkedList<String> firstNames = readAccounts.getFirstNames();
        LinkedList<String> lastNames = readAccounts.getLastNames();
        LinkedList<Integer> accountList = readAccounts.getAccounts();
        LinkedList<Integer> balanceList = readAccounts.getBalances();
        
        LinkedList<Account> accounts = new LinkedList<>();
        for (int i = 0; i < firstNames.size(); i++) {
        	accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountList.get(i), balanceList.get(i)));
        	GUI gui = new GUI(accounts);
			
			gui.setSize(900, 800);
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gui.setVisible(true);
        	
        }
	}
}