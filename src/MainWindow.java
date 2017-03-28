import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.ListSelectionModel;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MainWindow {

	private JFrame frame;
	DefaultTableModel tableModel;
	ContactsMain c;
	private JTable table;
	private TableColumnModel tcm;
	private Map hidden;
	private String[] detailColumns;
	JButton showItemBtn;
	JButton showAllBtn;
	JButton delBtn;
	JToggleButton showDetailBtn;
	BusinessContact currentContact;
	JScrollPane scrollPane;
	JPanel oneContact;
	JLabel lblFirstVal;

	JLabel lblLastVal;

	JLabel lblPhoneVal;

	JLabel lblEmailVal;

	JLabel lblCompanyVal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public MainWindow() throws IOException {
		c = new ContactsMain();
		hidden = new HashMap();
		detailColumns = new String[] {"Phone Number","Email","Company"};
		initialize();
	}
	class OpenL extends JPanel {
		JPanel panel = this;
		public OpenL(){
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			fc.setDialogTitle("Open File");
			fc.setAcceptAllFileFilterUsed(false);

			FileFilter f = new FileFilter(){
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					if (pathname.getName().toLowerCase().endsWith("dat"))
					{
						return true;
					}
					return false;
				}

				@Override
				public String getDescription() {
					// TODO Auto-generated method stub
					return null;
				}
			};
			fc.addChoosableFileFilter(f);
			// Demonstrate "Open" dialog:
			int rVal = fc.showOpenDialog(panel);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				FileManager.write(fc.getCurrentDirectory().toString()+"/"+c.getFilename(),c.getContacts());
				String filename = fc.getSelectedFile().getName().toString();
				c.setFilename(filename);
				c.setWorkingDir(fc.getCurrentDirectory().toString());
				c.setContacts(FileManager.read(fc.getCurrentDirectory().toString(),filename));
				initTable();
			}
		}
	}

	class SaveL extends JPanel {
		public SaveL() {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			fc.setDialogTitle("Save File");
			// Demonstrate "Save" dialog:
			int rVal = fc.showSaveDialog(new JPanel());
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String filename = fc.getSelectedFile().getName();
				c.setFilename(filename);
				FileManager.write(fc.getCurrentDirectory().toString()+"/"+filename,c.getContacts());

			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 611, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		WindowAdapter end = new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				FileManager.write(c.getWorkingDir() + "/"+c.getFilename(), c.getContacts());
			}
		};
		frame.addWindowListener(end);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenL o = new OpenL();
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveL s = new SaveL();
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.write(c.getWorkingDir() + "/"+c.getFilename(), c.getContacts());
				System.out.println("exited");
				System.exit(1);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnNewMenu = new JMenu("Options");
		menuBar.add(mnNewMenu);

		JMenuItem mntmSetWorkingDirectory = new JMenuItem("Set Working Directory");
		mnNewMenu.add(mntmSetWorkingDirectory);
		mntmSetWorkingDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetDirectory sd = new SetDirectory(c);
			}
		});
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);

		showItemBtn = new JButton("View Contact Info");
		showItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedClient = table.getSelectedRow();
				currentContact = c.getContacts().get(selectedClient);
				updateOneContact(currentContact);
				scrollPane.setViewportView(oneContact);
				showAllBtn.setVisible(true);
				showItemBtn.setVisible(false);
				showDetailBtn.setVisible(false);
				delBtn.setVisible(true);

			}
		});
		showItemBtn.setBackground(Color.ORANGE);
		toolBar.add(showItemBtn);
		showItemBtn.setVisible(false);

		showAllBtn = new JButton("View All Contacts");
		showAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllBtn.setVisible(false);
				showItemBtn.setVisible(false);
				showDetailBtn.setVisible(true);
				scrollPane.setViewportView(table);
				table.clearSelection();
				delBtn.setVisible(false);
			}
		});
		showAllBtn.setBackground(Color.ORANGE);
		toolBar.add(showAllBtn);
		showAllBtn.setVisible(false);

		JButton addBtn = new JButton("Add Contact");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddContact ac = new AddContact(frame, c, tableModel);
				ac.setVisible(true);
				delBtn.setVisible(false);
			}
		});

		showDetailBtn = new JToggleButton("Toggle Details");
		showDetailBtn.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					show();
				} else if(e.getStateChange()==ItemEvent.DESELECTED){
					hide();
				}

			}
		});

		showDetailBtn.setBackground(Color.ORANGE);
		toolBar.add(showDetailBtn);			
		addBtn.setBackground(Color.GREEN);
		toolBar.add(addBtn);

		delBtn = new JButton("Delete Contact");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this Contact?","Warning", dialogButton);
				if(dialogResult==JOptionPane.YES_OPTION){
					currentContact = c.getContacts().get(table.getSelectedRow());
					c.getContacts().remove(table.getSelectedRow());
					initTable();
					showItemBtn.setVisible(false);
					delBtn.setVisible(false);
					scrollPane.setViewportView(table);
					showAllBtn.setVisible(false);
					showDetailBtn.setVisible(true);
				}
			}
		});
		delBtn.setBackground(Color.RED);
		toolBar.add(delBtn);
		delBtn.setVisible(false);

		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				table.clearSelection();
				showItemBtn.setVisible(false);
				delBtn.setVisible(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}			
		});
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.tableModel = new DefaultTableModel(null,new String[] {"Client Number","Last Name","First Name","Phone Number","Email","Company"}){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}			
		};

		table = new JTable(this.tableModel);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				delBtn.setVisible(true);
				if(table.getSelectedRow()>=0){
					currentContact = c.getContacts().get(table.getSelectedRow());
					showItemBtn.setVisible(true);
				}
			}

		});
		initTable();
		scrollPane.setViewportView(table);
		tcm = table.getColumnModel();
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hide();
		oneContact = new JPanel();

		JLabel lblFirstName = new JLabel("First Name");

		JLabel lblLastName = new JLabel("Last Name");

		JLabel lblPhoneNumber = new JLabel("Phone Number");

		JLabel lblEmailAddress = new JLabel("Email Address");

		JLabel lblCompany = new JLabel("Company");

		lblFirstVal = new JLabel("");

		lblLastVal = new JLabel("");

		lblPhoneVal = new JLabel("");

		lblEmailVal = new JLabel("");

		lblCompanyVal = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(oneContact);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(38)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCompany, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFirstName)
								.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmailAddress))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCompanyVal, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmailVal, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPhoneVal, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLastVal, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFirstVal, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
										.addContainerGap(379, Short.MAX_VALUE))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(40)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFirstName)
								.addComponent(lblFirstVal))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLastName)
										.addComponent(lblLastVal))
										.addGap(18)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblPhoneNumber)
												.addComponent(lblPhoneVal))
												.addGap(18)
												.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblEmailAddress)
														.addComponent(lblEmailVal))
														.addGap(18)
														.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblCompany)
																.addComponent(lblCompanyVal)))
				);
		oneContact.setLayout(gl_panel);


	}

	public void initTable(){
		ArrayList<BusinessContact> currentContacts = c.getContacts();
		this.tableModel.setRowCount(0);
		for(BusinessContact b : currentContacts){
			this.tableModel.addRow(new Object[] {currentContacts.indexOf(b)+1,b.getLastName(),b.getFirstName(),b.getPhoneNumber(),
					b.getEmailAddress(), b.getCompany()});
		}
	}
	public void hide() {
		for(String columnName : detailColumns){
			int index = tcm.getColumnIndex(columnName);
			TableColumn column = tcm.getColumn(index);
			hidden.put(columnName, column);
			hidden.put(":" + columnName, new Integer(index));
			tcm.removeColumn(column);
		}
	}

	public void show() {
		for(String columnName : detailColumns){
			Object o = hidden.remove(columnName);
			if (o == null) {
				return;
			}
			tcm.addColumn((TableColumn) o);
			o = hidden.remove(":" + columnName);
			if (o == null) {
				return;
			}
			int column = ((Integer) o).intValue();
			int lastColumn = tcm.getColumnCount() - 1;
			if (column < lastColumn) {
				tcm.moveColumn(lastColumn, column);
			}
		}
	}

	public void updateOneContact(BusinessContact b){
		lblFirstVal.setText(b.getFirstName());
		lblLastVal.setText(b.getLastName());
		lblPhoneVal.setText(b.getPhoneNumber());
		lblEmailVal.setText(b.getEmailAddress());
		lblCompanyVal.setText(b.getCompany());
	}
}
