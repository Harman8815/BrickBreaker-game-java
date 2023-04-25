package BrickBreakerGame;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int height=1300,width=800;
		JFrame obj=new JFrame();
		Gaming gp=new Gaming(width,height);
		Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");    
		obj.setIconImage(icon); 
		obj.setBounds(100, 20, height,width);
		obj.setTitle("Game by Harman	");
		obj.setResizable(true);
		obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gp);
	}

}
