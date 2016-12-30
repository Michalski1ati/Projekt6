package bomba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ZipBomb extends JFrame implements WindowListener, ActionListener {
	
	private JTextField text;
	
	public ZipBomb(){
		
		//basic parameters
		super("ZipBomb");
		setBounds(100,100,230,130);
		setLayout(null);
		
		//listener
		addWindowListener(this);
		
		//button declaration
		JButton button = new JButton("Start");
		button.setBounds(60, 50, 100, 30);
		button.addActionListener(this);
		button.setActionCommand("start");
		add(button);
		
		//text field declaration
		text = new JTextField();
		text.setBounds(60, 10, 100, 30);
		add(text);
		
		setVisible(true);
		
	}
	
	public void boom(int i){
		
		if(i <= 0){
			
			Thread[] thread = new Thread[10];
			
			for(int j = 0; j < thread.length; j++){
				thread[j] = new Thread(new ZipBombR());
				thread[j].start();
			}
			
			return;
		}
		
		Thread[] nextthread = new Thread[10];
		for(int j = 0; j < nextthread.length; j++){
			nextthread[j] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					boom(i-1);
				}
			});
			nextthread[j].start();
		}
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("start")){
			try{
				boom(Integer.parseInt(text.getText().trim()) - 1);
			}
			catch(NumberFormatException e1){
				
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
