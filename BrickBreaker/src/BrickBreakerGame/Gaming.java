package BrickBreakerGame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gaming extends JPanel implements KeyListener,ActionListener {
	
	 private ImageIcon titleImage;

	 Image it;
	
	 private boolean play =true;
	 private int score=0;
	 private int totalbricks=324;
	 private Timer timer;
	 private int delay=0;
	 
	 private int playerx=100; private int playery=700;
	 
	 private int ballposx=250; private int ballposy=590;
	 
	 private int balldirx=-2; private int balldiry=-2;
	 private int ballsize=15;
	 private mapBuilder map;
	
	 private int height=0,width=0;
	 
	 
	 public Gaming(int height,int width) {
		 this.height=height;
		 this.width=width;
		 map=new mapBuilder(15,15);
		 addKeyListener(this);
		 setFocusable(true);
		 setFocusTraversalKeysEnabled(false);
		 timer =new Timer(delay,this);
		 timer.start();
	 }
	 public void paint(Graphics g) {
		 
			 g.setColor(Color.black);
			 g.fillRect(0, 0, width, height);
			 

				titleImage =new ImageIcon("backgoriunf.jpg");
				titleImage.paintIcon(this, g, 0,0);

			
			 g.setColor(Color.YELLOW);
			 g.fillRect(10, 20, 10, height-200); 
			 g.fillRect(10, 20, width-32, 10); 
			 g.fillRect(width-32, 20, 10, height-200);
	//rect
			 g.setColor(Color.BLUE);
			 g.fillRect(playerx,playery,150,20);
	//ball
			 g.setColor(Color.green);
			 g.fillOval(ballposx, ballposy, ballsize, ballsize);
			 
			  map.draw((Graphics2D)g);
//			 for (int i = 0; i < map.map.length; i++) {
//					for (int j=0; j< map.map[0].length;j++) {
//						if(map.map[i][j] > 0) {
//						 Toolkit t=Toolkit.getDefaultToolkit();  
//						         
//						          
//                            if(map.map[i][j]==1)
//                              it=t.getImage("red.gif"); 
//							else if(map.map[i][j]==2)
//			                  it=t.getImage("light.png");
//							else if(map.map[i][j]==3)
//				              it=t.getImage("blue.png");
//							else
//							  it=t.getImage("yellow.png");
//							
//                         g.drawImage(it, j*map.brickWidth + 13, i*map.brickHeight + 50,this);
//					  }
//					}
//					
//				}
			 
			 g.setColor(Color.red);
			 g.setFont(new Font("serif",Font.BOLD,22));
			 g.drawString("SCORE :-  "+score,width-190,50);
			 
			 
			 if(totalbricks==0){

				 play=false;
				 balldirx=0;balldiry=0;

				 g.setColor(Color.green);
				 g.setFont(new Font("serif",Font.BOLD,40));
				 g.drawString("WELL PLAYED ",width/2-130,450);
				 g.setColor(Color.WHITE);
				 g.setFont(new Font("serif",Font.BOLD,35));
				 g.drawString("SCORE :-  "+score,width/2-70,500);
			 }
			 if(ballposy>height)
			 {
				 play=false;
				 balldirx=0;balldiry=0;

				 g.setColor(Color.red);
				 g.setFont(new Font("serif",Font.BOLD,35));
				 g.drawString("GAME    OVER ",width/2-130,450);
				 g.setColor(Color.RED);
				 g.setFont(new Font("serif",Font.BOLD,25));
				 g.drawString("SCORE :-  "+score,width/2-70,500);
			 }
			 g.dispose();
		 
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(play) {
			if(new Rectangle(ballposx,ballposy,ballsize,ballsize).intersects(new Rectangle(playerx,playery,150,20))) {
				balldiry=-balldiry;
			}
			for( int i = 0; i<map.map.length; i++) {
				for(int j = 0; j<map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j*map.brickWidth + 50;
						int brickY = i*map.brickHeight + 80;
						int brickWidth= map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposx, ballposy, 10,10);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect) ) {
							map.setBrickValue(0, i, j);
							totalbricks--;
							score+=5;
							
							if(ballposx + 19 <= brickRect.x || ballposx +1 >= brickRect.x + brickRect.width) 
								balldirx = -balldirx;
							 else {
								balldiry = -balldiry;
							}
						}
						
					}
					
				}
			}
			if(ballposx>width-45||ballposx<20){
				balldirx=-balldirx;
			}
			if(ballposy<20) {
				balldiry=-balldiry;
			}
			
		   ballposx+=balldirx;
		   ballposy+=balldiry;
		}
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
			 if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
				 if(playerx>=width-190)
				 {
					 playerx=width-190;}
					 else {
						 movesRight();
				 }
			 }
			 if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
				 if(playerx<=30)
				 {
					 playerx=30;}
					 else {
						 movesLeft();
				 }
			 }
			 if(arg0.getKeyCode()==KeyEvent.VK_UP) {
				 if(playery<500||playery>800) {
					 playery=500;
				 }
				 else {
					 moveup();
				 }
			 }
			 if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
				 if(playery>750) {
					 playery=750;
				 }
				 else {
					 movedown();
				 }
			 }
			 if(arg0.getKeyCode()==KeyEvent.VK_V){
				 if(!play) {
					 score=0;
					 totalbricks=35;
					 ballposx=250;ballposy=600;
					 balldirx=-1;balldiry=-1;
					 map=new mapBuilder(15,15);
				 }
				
			 }
		 }
		public void movesRight()
		{
			play=true;
			playerx+=10;
		}
		public void movesLeft()
		{
			play=true;
			playerx-=10;
		}
		public void moveup() {
			play=true;
					playery-=10;
		}
		public void movedown() {
			play=true;
			playery+=10;
		}
		
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
