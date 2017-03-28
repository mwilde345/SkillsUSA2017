import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class AddContact extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField phoneNumberText;
	private JTextField emailText;
	private JTextField companyText;
	public String firstName;
	public String lastName;
	public String phoneNumber;
	public String email;
	public String company;
	private Timer t;

	/**
	 * Create the dialog.
	 */
	public AddContact(JFrame parentFrame, ContactsMain c, DefaultTableModel tableModel) {
		super(parentFrame,"Add a Contact",true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 310, 238);
		//contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		
		JLabel lblFirstName = new JLabel("First Name");
		
		JLabel lblLastName = new JLabel("Last Name");
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		
		JLabel lblEmail = new JLabel("Email");
		
		JLabel lblCompany = new JLabel("Company");
		
		firstNameText = new JTextField();
		firstNameText.setColumns(10);
		
		lastNameText = new JTextField();
		lastNameText.setColumns(10);
		
		phoneNumberText = new JTextField();
		phoneNumberText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setColumns(10);
		
		companyText = new JTextField();
		companyText.setColumns(10);
		
		JLabel lblSuccess = new JLabel("SUCCESS");
		lblSuccess.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSuccess.setForeground(new Color(0, 128, 0));
		lblSuccess.setVisible(false);
		
		
		t = new Timer();		
		class SuccessTask extends TimerTask{
			@Override
			public void run() {
				lblSuccess.setVisible(false);					
			}
		}
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSuccess)
						.addComponent(lblCompany)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPhoneNumber)
								.addComponent(lblEmail)
								.addComponent(lblLastName)
								.addComponent(lblFirstName))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lastNameText, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(phoneNumberText, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(emailText, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(companyText, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(firstNameText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(firstNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(lastNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhoneNumber)
						.addComponent(phoneNumberText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(emailText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCompany)
						.addComponent(companyText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSuccess)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton addContactBtn = new JButton("Save Contact");
		addContactBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstName = firstNameText.getText();
				lastName = lastNameText.getText();
				phoneNumber = phoneNumberText.getText();
				email = emailText.getText();
				company = companyText.getText();
				c.addContact(firstName, lastName, phoneNumber, email, company);
				lblSuccess.setVisible(true);				
				t.schedule(new SuccessTask(), 1200);
				ArrayList<BusinessContact> currentContacts = c.getContacts();
				tableModel.setRowCount(0);
				for(BusinessContact b : currentContacts){
					tableModel.addRow(new Object[] {currentContacts.indexOf(b)+1,b.getLastName(),b.getFirstName(),b.getPhoneNumber(),
							b.getEmailAddress(), b.getCompany()});
				}
			}
		});
		getContentPane().add(addContactBtn, BorderLayout.SOUTH);
	}
}
