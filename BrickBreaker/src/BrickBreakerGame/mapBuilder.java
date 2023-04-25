package BrickBreakerGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class mapBuilder  {

	public int map [][];
	public int brickWidth;
	public int brickHeight;

	private int width=1300,height=800;
	Random random=new Random();
	
	public mapBuilder(int row, int col) {
		
		map = new int [row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j=0; j< map[0].length;j++) {
				map[i][j] = random.nextInt(5);
			}
		}
		
		brickWidth = (width-170)/col;
		brickHeight = (height-450)/row;
		System.out.println(brickWidth+80);System.out.println(brickHeight+50);
	}
	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j=0; j< map[0].length;j++) {
				if(map[i][j] > 0) {
					
					if(map[i][j]==1)
						g.setColor(Color.yellow);
					else if(map[i][j]==2)
						g.setColor(Color.red);
					else if(map[i][j]==3)
						g.setColor(Color.blue);
					else
						g.setColor(Color.green);
					g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.black);
					g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
				}
			}
			
		}}
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

}

