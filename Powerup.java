import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;

class Powerup
{
	ImageIcon POWER;

	int type;//life, repellent, invincibility
	int location;
	int size;
	boolean visible = false;
	
	int xPos = 1000;
	int xPos2= 1050;
	int yPos = 580;
	int yPos2 = 630;
	
	public Powerup(int x,int y)
	{
		type = x;
		location = y;
	
		if (type == 1)//health
		{
			size = 4;//one heart
			POWER = new ImageIcon ("heartRefill.png");
		}
		else if (type == 2)//bugspray
		{
			size = 1;//quarter refill
			POWER = new ImageIcon ("sprayBottle.png");
		}
		else if (type == 3)//Invincibility
		{
			size = 100;//10 seconds
			POWER = new ImageIcon ("mushrooms.png");
		}
	}
	
	public void tradeInCoupon()
	{
		visible = true;
	}
	public void dissapear()
	{
		visible = false;
	}	
}
