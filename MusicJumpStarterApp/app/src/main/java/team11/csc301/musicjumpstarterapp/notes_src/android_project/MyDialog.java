package android_project;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MyDialog extends Dialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	JTextArea input = new JTextArea();
    JButton back = new JButton("save and back");
    JButton delete = new JButton("delete this note");
    Notes notes;
    int curKey;
    
    MyDialog(JFrame f,String s, int key){
        super(f,s);
        notes = (Notes) f;
        curKey = key;
        this.setLayout(null);
        this.setBounds(600,400,360,540);
        
        back.setBounds(0, 30, 180, 40);
        back.addActionListener(this);
        back.setActionCommand("back");
        
        delete.setBounds(180, 30, 180, 40);
        delete.addActionListener(this);
        delete.setActionCommand("delete");
        
        input.setBounds(3, 70, 360, 460);
        this.add(back);
        this.add(delete);
        
        if(key != -1) {
        	input.append(notes.contents.get(curKey));
        }
        this.add(input);
        
    }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String text = input.getText();
		
		if(arg0.getActionCommand().equals("back")) {
			if(curKey == -1) 
				curKey = notes.contents.size();
			
			notes.contents.put(curKey, text);
			//Serialization code here
			
		} else if(arg0.getActionCommand().equals("delete")) {
			if(curKey != -1) {
				System.out.println(curKey);
				notes.contents.remove(curKey);
			}
			//Serialization code here
		}
		
		notes.dispose();
		if(curKey != -1) {
			for(int i = 0; i < notes.buttons.size(); i++)
				notes.remove(notes.buttons.get(i));
			notes.buttons = new LinkedList<JButton>();
		}
		notes.init();
	}

}
