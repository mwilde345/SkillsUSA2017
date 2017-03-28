import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;


public class SetDirectory extends JPanel{
	JButton go;

	JFileChooser chooser;
	String choosertitle;

	public SetDirectory(ContactsMain c) {
		int result;
		choosertitle="Set Working Directory";
		chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);
		//    
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
			c.setWorkingDir(chooser.getCurrentDirectory().toString());
		}
	}
}