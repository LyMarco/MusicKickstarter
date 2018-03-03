package android_project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notes extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	LinkedList<JButton> buttons = new LinkedList<JButton>();
    JButton addNew = new JButton("Add New Note");
    MyDialog dialog;
    HashMap<Integer, String> contents = new HashMap<Integer, String>();
    JPanel jp = new JPanel();
    
    Notes() {
        init();
    }
    
    public void init() {
        
        this.setBounds(600, 400, 360, 540);
        this.setLayout(null);
        
        addNew.setBounds(0, 0, 360, 40);
        addNew.setBackground(new Color(169,140,238));
        addNew.setActionCommand("Add New");
        addNew.addActionListener(this);
        this.add(addNew);
        buttons.add(addNew);
        HashMap<Integer, String> contents1 = new HashMap<Integer, String>();
        
        //adjust the buttons, so that noteButtons can still hold position from 0 to size of contents
        Iterator<Integer> keys = contents.keySet().iterator();
        for(int i = 0; i < contents.size(); i++) {
        	contents1.put(i, contents.get(keys.next()));
        }
        contents = contents1;
        
        for(int i = 0; i < contents.size(); i++) {
        	if(contents.containsKey(i)) {
	        	JButton noteButton = new JButton(contents.get(i));
	        	noteButton.setBounds(0, i*30+40, 360, 30);
	        	noteButton.setBackground(new Color(169,140,238));
	        	noteButton.setActionCommand(String.valueOf(i));
	        	noteButton.addActionListener(this);
	            this.add(noteButton);
	            buttons.add(noteButton);
        	}
        }
        
        this.background();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Add New")) {
	        dialog = new MyDialog(this, "adding new notes", -1);
			dialog.setVisible(true);
		} else {
			int key = Integer.parseInt(arg0.getActionCommand());
			dialog = new MyDialog(this, "editting old notes "+key, key);
			dialog.setVisible(true);
		}
		
		//this.dispose();
		//this.setVisible(false);
		//this.init();
	}
	
	public void background() {
		
		ImageIcon background = new ImageIcon("background.jpg");
		JLabel label = new JLabel(background);
		//this.setSize(background.getIconWidth(), background.getIconHeight());
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
	    this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	    jp = (JPanel)this.getContentPane();
	    jp.setOpaque(false);
	}
	
    @SuppressWarnings("unused")
	public static void main(String[] args) {
    	Notes note = new Notes();
    }
}
