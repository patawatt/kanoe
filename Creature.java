import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;

public class Creature
{
	ImageIcon ENEMY;
	ImageIcon ENEMY2;
	ImageIcon ENEMY3;
	ImageIcon ENEMY4;
	ImageIcon ENEMY5;
	ImageIcon ENEMY6;
	ImageIcon ENEMY7;

	//snake, mosquitos, beaver...
	int type;
	int location;
	int enemyCar;
	int movement;
	int damage;
	
	int xPos;
	int xPos2;
	int yPos;
	int yPos2;
	
	int xPosS;
	int xPos2S;
	int yPosS;
	int yPos2S;
			
	int xPosW;
	int xPos2W;
	int yPosW;
	int yPos2W;
	
	public boolean alive = false;
	public boolean showBody = false;
	
	public Creature(int x,int y)
	{
		type = x;
		location = y;
		
		if(type ==3)
		{
			ENEMY = new ImageIcon ("cougarsprite1.png");
			ENEMY2 = new ImageIcon ("cougarsprite2.png");
			ENEMY3 = new ImageIcon ("cougarsprite3.png");
			ENEMY4 = new ImageIcon ("cougarsprite4.png");
			ENEMY5 = new ImageIcon ("cougarsprite3.png");
			ENEMY6 = new ImageIcon ("cougarsprite2.png");
			ENEMY7 = new ImageIcon ("cougarDead.png");
			xPos = 1100;
			xPos2 = 1450;
			yPos= 290;
			yPos2 = 410;
			
			//Strong area dimensions
			xPosS = 1100;
			xPos2S = 1180;
			yPosS = 335;
			yPos2S = 410;
			
			//Weak area dimensions
			xPosW = 1180;
			xPos2W = 1400;
			yPosW = 335;
			yPos2W = 410;
			
			movement = 30;
			damage = 4;
		}
		else if(type ==2)
		{
			ENEMY = new ImageIcon ("beaversprite1.png");
			ENEMY2 = new ImageIcon ("beaversprite2.png");
			ENEMY3 = new ImageIcon ("beaversprite3.png");
			ENEMY4 = new ImageIcon ("beaversprite4.png");
			ENEMY5 = new ImageIcon ("beaversprite5.png");
			ENEMY6 = new ImageIcon ("beaversprite4.png");
			ENEMY7 = new ImageIcon ("beaversDead3.png");
			xPos = 1100;
			xPos2 = 1500;
			yPos= 500;
			yPos2 = 630;
			
			//Strong area dimensions
			xPosS = 1105;
			xPos2S = 1100;
			yPosS = 517;
			yPos2S = 587;
			
			//Weak area dimensions
			xPosW = 1100;
			xPos2W = 1430;
			yPosW = 517;
			yPos2W = 542;
			
			movement = 20;
			damage = 2;
		}
		else if(type ==1)
		{
			ENEMY = new ImageIcon ("snake.png");
			ENEMY2 = new ImageIcon ("snakesprite2.png");
			ENEMY3 = new ImageIcon ("snakesprite3.png");
			ENEMY4 = new ImageIcon ("snakesprite4.png");
			ENEMY5 = new ImageIcon ("snakesprite3.png");
			ENEMY6 = new ImageIcon ("snakesprite2.png");
			ENEMY7 = new ImageIcon ("deadSnake.png");
			
			//Picture Dimensions
			xPos = 1100;
			xPos2 = 1280;
			yPos= 580;
			yPos2 = 630;
			
			//Strong area dimensions
			xPosS = 1100;
			xPos2S = 1125;
			yPosS = 587;
			yPos2S = 637;
			
			//Weak area dimensions
			xPosW = 1125;
			xPos2W = 1250;
			yPosW = 587;
			yPos2W = 612;
			
			movement = 10;
			damage = 1;
		}
		else if(type ==5)
		{
			ENEMY = new ImageIcon ("velociraptor.png");
			ENEMY2 = new ImageIcon ("velociraptor.png");
			ENEMY3 = new ImageIcon ("velociraptor.png");
			ENEMY4 = new ImageIcon ("velociraptor.png");
			ENEMY5 = new ImageIcon ("velociraptor.png");
			ENEMY6 = new ImageIcon ("velociraptor.png");
			ENEMY7 = new ImageIcon ("velociraptor.png");
			xPos = 1100;
			xPos2 = 1300;
			yPos= 500;
			yPos2 = 630;
			
			//Strong area dimensions
			xPosS = 1100;
			xPos2S = 1250;
			yPosS = 537;
			yPos2S = 637;
			
			movement = 50;
			damage = 6;
		}
		else if(type ==4)
		{
			ENEMY = new ImageIcon ("bugs1.png");
			ENEMY2 = new ImageIcon ("bugs2.png");
			ENEMY3 = new ImageIcon ("bugs3.png");
			ENEMY4 = new ImageIcon ("bugs1.png");
			ENEMY5 = new ImageIcon ("bugs2.png");
			ENEMY6 = new ImageIcon ("bugs3.png");
			ENEMY7 = new ImageIcon ("bugsDead.png");
			xPos = 1100;
			xPos2 = 1300;
			yPos= 80;
			yPos2 = 280;
			
			//Strong area dimensions
			xPosS = 1100;
			xPos2S = 1300;
			yPosS= 80;
			yPos2S = 280;
			
			movement = 5;
			damage = 1;
		}
	}
	
	public void tradeInCoupon()
	{
		alive = true;
	}
	public void kill()
	{
		alive = false;
	}
	
}
