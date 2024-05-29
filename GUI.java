package Coursework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private Transaction transferObject;
	private StringBuilder sbAllData;
	private LinkedList<Account> globalAccounts;
	private JLabel showAllData;
	private JButton showAllButton;
	private JButton depositButton;
	private JButton withdrawButton;
	private JButton transferButton;
	private JTextField accDeposit;
	private JTextField accWithdraw;
	private JTextField acc1Transfer;
	private JTextField acc2Transfer;
	private JTextField depositInput;
	private JTextField withdrawInput;
	private JTextField transferAmount;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;

	public GUI(LinkedList<Account> accounts) {
		super("Banking System");
		getContentPane().setBackground(new Color(255, 255, 255));
		
		globalAccounts = accounts;
		sbAllData = new StringBuilder();
		transferObject = new Transaction();
		
		//build the string for all account data
		sbAllData.setLength(0); // Clear the existing data
        sbAllData.append("<html><style>");
        sbAllData.append("table{ border: 1px solid black; padding:20px;}");
        sbAllData.append("table, td, th {border-collapse: collapse}");
        sbAllData.append("</style>");
        sbAllData.append("<tr><th style = 'padding:20px;'>First Name</th><th style = 'padding:20px;'>Last Name</th><th style = 'padding:20px;'>Account</th><th style = 'padding:20px;'>Balance</th>");
        for (Account acc : globalAccounts) {
        	sbAllData.append("<tr>");
            sbAllData.append("<td style = 'padding:20px;'>").append(acc.getFirstName()).append("</td>");
            sbAllData.append("<td style = 'padding:20px;'>").append(acc.getLastName()).append("</td>");
            sbAllData.append("<td style = 'padding:20px;'>").append(acc.getAccountNum()).append("</td>");
            sbAllData.append("<td style = 'padding:20px;'>").append(acc.getBalance()).append("</td>");
            sbAllData.append("</tr>");
        }
        sbAllData.append("</table></html>");
		getContentPane().setLayout(null);
		
		//initialize and add component
		showAllData = new JLabel();
		showAllData.setBounds(314, 294, 599, 435);
		showAllData.setIcon(null);
		showAllData.setBackground(new Color(255, 255, 255));
		showAllData.setFont(new Font("Tahoma", Font.PLAIN, 10));
		getContentPane().add(showAllData);
		
		showAllButton = new JButton("Display account data");
		showAllButton.setBounds(92, 392, 179, 25);
		getContentPane().add(showAllButton);
	
		depositButton = new JButton("Deposit");
		depositButton.setBounds(82, 230, 100, 25);
		getContentPane().add(depositButton);
		
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(356, 230, 100, 25);
		getContentPane().add(withdrawButton);
		
		transferButton = new JButton("Transfer");
		transferButton.setBounds(657, 230, 100, 25);
		getContentPane().add(transferButton);
		
		accDeposit = new JTextField();
		accDeposit.setBounds(138, 107, 100, 25);
		getContentPane().add(accDeposit);
		
		accWithdraw = new JTextField();
		accWithdraw.setBounds(411, 107, 100, 25);
		getContentPane().add(accWithdraw);
		
		acc1Transfer = new JTextField();
		acc1Transfer.setBounds(715, 107, 100, 25);
		getContentPane().add(acc1Transfer);
		
		acc2Transfer = new JTextField();
		acc2Transfer.setBounds(715, 142, 100, 25);
		getContentPane().add(acc2Transfer);
		
		depositInput = new JTextField();
		depositInput.setBounds(138, 142, 100, 25);
		getContentPane().add(depositInput);
		
		withdrawInput = new JTextField();
		withdrawInput.setBounds(411, 142, 100, 25);
		getContentPane().add(withdrawInput);
		
		transferAmount = new JTextField();
		transferAmount.setBounds(715, 178, 100, 25);
		getContentPane().add(transferAmount);
		
		JLabel lblNewLabel = new JLabel("Deposit");
		lblNewLabel.setBounds(109, 75, 100, 13);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Withdraw");
		lblNewLabel_1.setBounds(379, 75, 77, 13);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Transfer");
		lblNewLabel_2.setBounds(687, 75, 93, 13);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Account no.:");
		lblNewLabel_3.setBounds(27, 113, 77, 13);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Amount: ");
		lblNewLabel_4.setBounds(27, 148, 77, 13);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Account no.:");
		lblNewLabel_5.setBounds(314, 113, 77, 13);
		getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Amount:");
		lblNewLabel_6.setBounds(314, 148, 77, 13);
		getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("From account: ");
		lblNewLabel_7.setBounds(592, 113, 93, 13);
		getContentPane().add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("To account: ");
		lblNewLabel_8.setBounds(592, 148, 93, 13);
		getContentPane().add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Amount: ");
		lblNewLabel_9.setBounds(592, 184, 93, 13);
		getContentPane().add(lblNewLabel_9);
		
		//create the event handler and add to buttons
		HandlerClass handler = new HandlerClass();
		showAllButton.addActionListener(handler);
		depositButton.addActionListener(handler);
		withdrawButton.addActionListener(handler);
		transferButton.addActionListener(handler);
	}

	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String url = "Accounts.csv";
			if (e.getSource() == showAllButton) {
				showAllData.setText(sbAllData.toString());
			}
			else if (e.getSource() == depositButton) {
				int accountNumber = Integer.parseInt(accDeposit.getText());
				int amount = Integer.parseInt(depositInput.getText());
				for (Account acc : globalAccounts) {
					if (acc.getAccountNum() == accountNumber) {
						acc.deposit(amount);
						sbAllData.setLength(0); // Clear the existing data
				        sbAllData.append("<html><style>");
				        sbAllData.append("table{ border: 1px solid black; padding:20px;}");
				        sbAllData.append("table, td, th {border-collapse: collapse}");
				        sbAllData.append("</style>");
				        sbAllData.append("<tr><th style = 'padding:20px;'>First Name</th><th style = 'padding:20px;'>Last Name</th><th style = 'padding:20px;'>Account</th><th style = 'padding:20px;'>Balance</th>");
				        for (Account acc1 : globalAccounts) {
				        	sbAllData.append("<tr>");
				            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getFirstName()).append("</td>");
				            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getLastName()).append("</td>");
				            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getAccountNum()).append("</td>");
				            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getBalance()).append("</td>");
				            sbAllData.append("</tr>");
				        }
				        sbAllData.append("</table></html>");
				        
				        try (BufferedWriter writer = new BufferedWriter(new FileWriter(url))) {
				            for (Account account : globalAccounts) {
				                String line = account.getFirstName() + "," + account.getLastName() + "," + account.getAccountNum() + "," + account.getBalance();
				                writer.write(line);
				                writer.newLine();
				            }
				        } catch (IOException e1) {
				            e1.printStackTrace();
				        }
						break;
					}
				}
			}
			else if (e.getSource() == withdrawButton) {
				int accountNumber = Integer.parseInt(accWithdraw.getText());
				int amount = Integer.parseInt(withdrawInput.getText());
				for (Account acc : globalAccounts) {
					if (acc.getAccountNum() == accountNumber) {
						acc.withdraw(amount);
						sbAllData.setLength(0); // Clear the existing data
				        sbAllData.append("<html><style>");
				        sbAllData.append("table{ border: 1px solid black; padding:20px;}");
				        sbAllData.append("table, td, th {border-collapse: collapse}");
				        sbAllData.append("</style>");
				        sbAllData.append("<tr><th style = 'padding:20px;'>First Name</th><th style = 'padding:20px;'>Last Name</th><th style = 'padding:20px;'>Account</th><th style = 'padding:20px;'>Balance</th>");
				        for (Account acc1 : globalAccounts) {
				        	sbAllData.append("<tr>");
				            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getFirstName()).append("</td>");
				            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getLastName()).append("</td>");
				            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getAccountNum()).append("</td>");
				            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getBalance()).append("</td>");
				            sbAllData.append("</tr>");
				        }
				        sbAllData.append("</table></html>");
				        
				        try (BufferedWriter writer = new BufferedWriter(new FileWriter(url))) {
				            for (Account account : globalAccounts) {
				                String line = account.getFirstName() + "," + account.getLastName() + "," + account.getAccountNum() + "," + account.getBalance();
				                writer.write(line);
				                writer.newLine();
				            }
				        } catch (IOException e1) {
				            e1.printStackTrace();
				        }
						break;
					}
				}
			}
			else if (e.getSource() == transferButton) {
				int sourceAccountNumber = Integer.parseInt(acc1Transfer.getText());
				int targetAccountNumber = Integer.parseInt(acc2Transfer.getText());
				int amount = Integer.parseInt(transferAmount.getText());
				Account sourceAccount = null;
				Account targetAccount = null;
				for (Account acc : globalAccounts) {
					if (acc.getAccountNum() == sourceAccountNumber) {
						sourceAccount = acc;
					}
					else if (acc.getAccountNum() == targetAccountNumber) {
						targetAccount = acc;
					}
					if (sourceAccount != null && targetAccount != null) {
						if (sourceAccount.getBalance() >= amount) {
							transferObject.transfer(sourceAccount,  targetAccount, amount);
							sbAllData.setLength(0); // Clear the existing data
					        sbAllData.append("<html><style>");
					        sbAllData.append("table{ border: 1px solid black; padding:20px;}");
					        sbAllData.append("table, td, th {border-collapse: collapse}");
					        sbAllData.append("</style>");
					        sbAllData.append("<tr><th style = 'padding:20px;'>First Name</th><th style = 'padding:20px;'>Last Name</th><th style = 'padding:20px;'>Account</th><th style = 'padding:20px;'>Balance</th>");
					        for (Account acc1 : globalAccounts) {
					        	sbAllData.append("<tr>");
					            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getFirstName()).append("</td>");
					            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getLastName()).append("</td>");
					            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getAccountNum()).append("</td>");
					            sbAllData.append("<td style = 'padding:20px;'>").append(acc1.getBalance()).append("</td>");
					            sbAllData.append("</tr>");
					        }
					        sbAllData.append("</table></html>");
					        
					        try (BufferedWriter writer = new BufferedWriter(new FileWriter(url))) {
					            for (Account account : globalAccounts) {
					                String line = account.getFirstName() + "," + account.getLastName() + "," + account.getAccountNum() + "," + account.getBalance();
					                writer.write(line);
					                writer.newLine();
					            }
					        } catch (IOException e1) {
					            e1.printStackTrace();
					        }
						}
						else {
							System.out.println("Insufficient funds in source account for transfer.");
						}
						break;
					}
				} 
				if (sourceAccount == null || targetAccount == null){
					System.out.println("One or both accounts not found.");
				}
			}
			showAllData.setText(sbAllData.toString());
		}
	}
}