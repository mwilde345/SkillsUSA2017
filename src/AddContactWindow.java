import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


public class AddContactWindow extends JFrame {

	private JPanel contentPane;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField phoneNumberText;
	private JTextField emailText;
	private JTextField companyText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContactWindow frame = new AddContactWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddContactWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 298, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
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
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton addContactBtn = new JButton("Save Contact");
		addContactBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameText.getText();
				String lastName = lastNameText.getText();
				String phoneNumber = phoneNumberText.getText();
				String email = emailText.getText();
				String company = companyText.getText();
				ContactsMain c = new ContactsMain();
				c.addContact(firstName, lastName, phoneNumber, email, company);
				//MainWindow.initList();
				dispose();
			}
		});
		getContentPane().add(addContactBtn, BorderLayout.SOUTH);
	}

}
