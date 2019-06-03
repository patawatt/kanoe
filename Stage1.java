import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;

public class Stage1 implements KeyListener
{
	JFrame frame = new JFrame("STAGE 1: PORTAGE OF DEATH!!");
	Drawing Stage = new Drawing();
	Player Hero = new Player();
	Animate bear = new Animate();
	Creature [] kreaturz = new Creature[100];
	Powerup [] powerz = new Powerup[10];

	//H:\\JavaFiles\\OUTERSCLUBGAME\\
	//bear sprites
	ImageIcon Car = new ImageIcon ("bearsprite1i.png");
	ImageIcon Car2 = new ImageIcon ("bearsprite2iii.png");
	ImageIcon Car3 = new ImageIcon ("bearsprite3iii.png");
	ImageIcon Car4 = new ImageIcon ("bearsprite4iii.png");
	ImageIcon Car5 = new ImageIcon ("bearsprite5i.png");
	ImageIcon Car6 = new ImageIcon ("bearsprite6i.png");
	ImageIcon Car7 = new ImageIcon ("bearsprite7i.png");
	ImageIcon Car8 = new ImageIcon ("bearsprite8i.png");
	ImageIcon Car9 = new ImageIcon ("bearsprite9i.png");
	
	ImageIcon Hurt = new ImageIcon ("bearDamage1.png");
	ImageIcon Hurt2 = new ImageIcon ("bearDamage2.png");
	ImageIcon Hurt3 = new ImageIcon ("bearDamage3.png");
	ImageIcon Hurt4 = new ImageIcon ("bearDamage4.png");
	ImageIcon Hurt5 = new ImageIcon ("bearDamage5.png");
	ImageIcon Hurt6 = new ImageIcon ("bearDamage6.png");
	ImageIcon Hurt7 = new ImageIcon ("bearDamage7.png");
	ImageIcon Hurt8 = new ImageIcon ("bearDamage8.png");
	ImageIcon Hurt9 = new ImageIcon ("bearDamage9.png");
	
	ImageIcon Invincibile = new ImageIcon ("bearInvincibile1.png");
	ImageIcon Invincibile2 = new ImageIcon ("bearInvincibile2.png");
	ImageIcon Invincibile3 = new ImageIcon ("bearInvincibile3.png");
	ImageIcon Invincibile4 = new ImageIcon ("bearInvincibile4.png");
	ImageIcon Invincibile5 = new ImageIcon ("bearInvincibile5.png");
	ImageIcon Invincibile6 = new ImageIcon ("bearInvincibile6.png");
	ImageIcon Invincibile7 = new ImageIcon ("bearInvincibile7.png");
	ImageIcon Invincibile8 = new ImageIcon ("bearInvincibile8.png");
	ImageIcon Invincibile9 = new ImageIcon ("bearInvincibile9.png");
	
	//background images
	ImageIcon Forest = new ImageIcon ("BackDrop1.jpg");//BackDrop1,jpg
	ImageIcon Trees = new ImageIcon ("trees10.png");
	ImageIcon Plank = new ImageIcon ("plankwood.jpg");
	
	//life images
	ImageIcon Heart4 = new ImageIcon ("heartFull.png");
	ImageIcon Heart3 = new ImageIcon ("heart3Quarter2.png");
	ImageIcon Heart2 = new ImageIcon ("heartHalf2.png");
	ImageIcon Heart1 = new ImageIcon ("quarterHeart2.png");
	ImageIcon Heart0 = new ImageIcon ("emptyNail.png");
	
	//transparent screens
	ImageIcon Pause = new ImageIcon ("Pause1.png");
	ImageIcon Win = new ImageIcon ("Win.png");
	ImageIcon Lost = new ImageIcon("Lost.png");
	
	//Clouds
	ImageIcon Spray = new ImageIcon("bugSpray.png");
	ImageIcon Spray2 = new ImageIcon("bugSpray2.png");
	ImageIcon Spray3 = new ImageIcon("bugSpray3.png");
	ImageIcon WhiteCloud = new ImageIcon("whiteCloud.png");
	ImageIcon FireBall = new ImageIcon("fireBall2.png");
	
	//background width, x and y dimensions of windows, y value for ground
	int PicWidth = 2000;
	int MAXX = 1000;
	int MAXY = 700;
	int bottom = 630;
	
	//Background coordinates
	int x = 0;
	int x2 = PicWidth;
	int X = 0;
	int X2 = PicWidth;
	
	//total distance, sprite counter, enemy sprite counter, jump counter, frame counter
	int deltaD = 0;
	int car = 0;
	int enemyCar = 0;
	int upCount =0;
	int frameCount = 0;
	
	//direction key boolean variables
	boolean right = false;
	boolean up = false;
	boolean down = false;
	boolean fall = false;
	
	//pause, level complete and lost boolean variables
	boolean pause = false;
	boolean done = false;
	boolean lost = false;
	
	boolean invincibile = false;
	boolean hurt = false;
	boolean cheat = false;
	boolean showZones = false;
	boolean bugProof = false;
	int sprayCount = 0;
	int sprayCar = 0;
	int invinciCount= 0;
	int hurtCount = 0;
	int flashyCount = 0;
	int punchCount = 0;
	
	//statistics
	int creaturesKilled = 0;
	int creaturesDodged = 0;
	int numJumps = 0;
	float time = 0;
	boolean stats = false;
	boolean sound = false;
	boolean stopSound = false;
	int what;
	int where;
	
	public Stage1()
	{
		frame.setSize(MAXX,MAXY);
		frame.addKeyListener(this);
		
		for (int p = 0; p<kreaturz.length;p++)
		{
			what = rType();
			where = rLocation(what);
		
			kreaturz[p] = new Creature(what,where);
		}
		kreaturz[0] = new Creature(1,1);
		kreaturz[1] = new Creature(5,800);
		kreaturz[2] = new Creature(5,600);
		kreaturz[3] = new Creature(1,30);
		kreaturz[4] = new Creature(1,50);
		kreaturz[5] = new Creature(5,990);
		
		for (int p = 0; p<powerz.length;p++)
		{
			if(p>=0 && p<=5)
				powerz[p] = new Powerup(1,rLocationP(1));
			if(p>5 && p<=9)
				powerz[p] = new Powerup(2,rLocationP(2));
			if(p==9)	
				powerz[p] = new Powerup(3,rLocationP(3));
		}
		powerz[0] = new Powerup(1,10);
		sBear();		
		frame.getContentPane().add(Stage,"Center");		
		frame.setVisible(true);
		bear.start();
	}
	
	class Drawing extends JComponent
	{
		public void paint (Graphics g)
		{
			//loops backdrops
			g.drawImage(Forest.getImage(), x,-150, PicWidth, MAXY, this);
			g.drawImage(Forest.getImage(), x2,-150, PicWidth, MAXY, this);
			g.drawImage(Trees.getImage(), X,-40, PicWidth, MAXY, this);
			g.drawImage(Trees.getImage(), X2,-40, PicWidth, MAXY, this);
			
			
			for(int i= 0; i<kreaturz.length; i++)
			{
				 if (!kreaturz[i].alive && kreaturz[i].showBody)
					g.drawImage(kreaturz[i].ENEMY7.getImage(),kreaturz[i].xPos,kreaturz[i].yPos,kreaturz[i].xPos2 - kreaturz[i].xPos,kreaturz[i].yPos2 - kreaturz[i].yPos,this);
			}
			
			//Draws character
			if(car==0)
			{
				if(hurt && flashyCount==0)
				 	g.drawImage(Hurt.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}
			else if (car ==1)
			{
				if(hurt&& flashyCount==0)
				 	g.drawImage(Hurt2.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile2.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car2.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}
			else if (car ==2)
			{
				if(hurt&& flashyCount==0)
				 	g.drawImage(Hurt3.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile3.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car3.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}
			else if(car==3)
			{
				if(hurt&& flashyCount==0)
				 	g.drawImage(Hurt4.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile4.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car4.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}
			else if(car ==4)
			{
				if(hurt&& flashyCount==0)
				 	g.drawImage(Hurt5.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile5.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car5.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}
			else if(car ==5)
			{
				if(hurt&& flashyCount==0)
				 	g.drawImage(Hurt6.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile6.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car6.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}		
			else if(car ==6)
			{
				if(hurt&& flashyCount==0)
				 	g.drawImage(Hurt7.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile7.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car7.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}		
			else if(car ==7)
			{
				if(hurt&& flashyCount==0)
				 	g.drawImage(Hurt8.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile8.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car8.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}
			else if(car ==8)
			{
				if(hurt&& flashyCount==0)
				 	g.drawImage(Hurt9.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else if(invincibile&& flashyCount==0)
				 	g.drawImage(Invincibile9.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
				else
					g.drawImage(Car9.getImage(),Hero.xPos,Hero.yPos,Hero.xPos2-Hero.xPos,Hero.yPos2 -Hero.yPos,this);
			}		
			
			//sets font
			Font Words = new Font("Book Antiqua",Font.PLAIN,12);
			g.setFont(Words);
			
			//draws health and statistics board
			g.drawImage(Plank.getImage(), 25,25, 350, 100, this);
			g.setColor(Color.white);
			g.drawString("Distance:" + (1000 -deltaD) + "m", 285,50);
			g.drawString("Life: " + Main.attempts, 285,70);
			g.drawString("Points: " + Main.points + "", 285,90);
			g.drawString("Time: "+ Math.round(time) + "s", 285,110);			
			
			//draws bugspray metre
			g.setColor(Color.black);
			g.fillRect(375,25,30,100);
			g.setColor(Color.green);
			g.fillRect(375,25,30,(Hero.bugspray*25));
			
			//draw empty nail
			g.drawImage(Heart0.getImage(), 40,30, 80, 80, this);
			g.drawImage(Heart0.getImage(), 120,30, 80, 80, this);
			g.drawImage(Heart0.getImage(), 200,30, 80, 80, this);
			
			//Draws Lives
			if (Hero.life>=4)//one whole heart
			{
				g.drawImage(Heart4.getImage(), 40,30, 80, 80, this);
				if (Hero.life>=8)//two whole hearts
				{
					g.drawImage(Heart4.getImage(), 120,30, 80, 80, this);
					if (Hero.life>=12)//three whole hearts
						g.drawImage(Heart4.getImage(), 200,30, 80, 80, this);
					else//divide into fractions of hearts
					{
						if (Hero.life==11)
							g.drawImage(Heart3.getImage(), 200,30, 80, 80, this);
						else if (Hero.life==10)
							g.drawImage(Heart2.getImage(), 200,30, 80, 80, this);
						else if (Hero.life==9)
							g.drawImage(Heart1.getImage(), 200,30, 80, 80, this);
					}
				}
				else//divide into fractions of hearts
				{
					if (Hero.life==7)
						g.drawImage(Heart3.getImage(), 120,30, 80, 80, this);
					else if (Hero.life==6)
						g.drawImage(Heart2.getImage(), 120,30, 80, 80, this);
					else if (Hero.life==5)
						g.drawImage(Heart1.getImage(), 120,30, 80, 80, this);
				}
			}
			else//divide into fractions of hearts
			{
				if (Hero.life==3)
					g.drawImage(Heart3.getImage(), 40,30, 80, 80, this);
				else if (Hero.life==2)
					g.drawImage(Heart2.getImage(), 40,30, 80, 80, this);
				else if (Hero.life==1)
					g.drawImage(Heart1.getImage(), 40,30, 80, 80, this);
			}
			
			
			//draws creature sprites
			for(int i= 0; i<kreaturz.length; i++)
			{
				if(kreaturz[i].alive)
				{
					if(kreaturz[i].enemyCar ==0)
						g.drawImage(kreaturz[i].ENEMY.getImage(),kreaturz[i].xPos,kreaturz[i].yPos,kreaturz[i].xPos2 - kreaturz[i].xPos,kreaturz[i].yPos2 - kreaturz[i].yPos,this);
					else if(kreaturz[i].enemyCar ==1)
						g.drawImage(kreaturz[i].ENEMY2.getImage(),kreaturz[i].xPos,kreaturz[i].yPos,kreaturz[i].xPos2 - kreaturz[i].xPos,kreaturz[i].yPos2 - kreaturz[i].yPos,this);
					else if(kreaturz[i].enemyCar ==2)
						g.drawImage(kreaturz[i].ENEMY3.getImage(),kreaturz[i].xPos,kreaturz[i].yPos,kreaturz[i].xPos2 - kreaturz[i].xPos,kreaturz[i].yPos2 - kreaturz[i].yPos,this);
					else if(kreaturz[i].enemyCar ==3)
						g.drawImage(kreaturz[i].ENEMY4.getImage(),kreaturz[i].xPos,kreaturz[i].yPos,kreaturz[i].xPos2 - kreaturz[i].xPos,kreaturz[i].yPos2 - kreaturz[i].yPos,this);
					else if(kreaturz[i].enemyCar ==4)
						g.drawImage(kreaturz[i].ENEMY5.getImage(),kreaturz[i].xPos,kreaturz[i].yPos,kreaturz[i].xPos2 - kreaturz[i].xPos,kreaturz[i].yPos2 - kreaturz[i].yPos,this);
					else if(kreaturz[i].enemyCar ==5)
						g.drawImage(kreaturz[i].ENEMY6.getImage(),kreaturz[i].xPos,kreaturz[i].yPos,kreaturz[i].xPos2 - kreaturz[i].xPos,kreaturz[i].yPos2 - kreaturz[i].yPos,this);

					//draws rectangles indicating creature's strong and weak areas
					if(showZones)
					{
						g.setColor(Color.white);
						g.fillRect(kreaturz[i].xPosW,kreaturz[i].yPosW,kreaturz[i].xPos2W-kreaturz[i].xPosW,kreaturz[i].yPos2W-kreaturz[i].yPosW);
						g.setColor(Color.red);
						g.fillRect(kreaturz[i].xPosS,kreaturz[i].yPosS, kreaturz[i].xPos2S-kreaturz[i].xPosS,kreaturz[i].yPos2S-kreaturz[i].yPosS);
					}
					
					if(stats)
					{
						g.drawString("Creature xW: " + kreaturz[i].xPosW, 340,380);
						g.drawString("Creature xW2: " + kreaturz[i].xPos2W, 340,400);
						g.drawString("Creature yW: " + kreaturz[i].yPosW, 340,420);
						g.drawString("Creature yW2: " + kreaturz[i].yPos2W, 340,440);
					}
				}
			}
			
			//draws powerups
			for(int i=0; i<powerz.length;i++)
			{
				if (powerz[i].visible)
				{
					g.drawImage(powerz[i].POWER.getImage(),powerz[i].xPos,powerz[i].yPos,powerz[i].xPos2 - powerz[i].xPos,powerz[i].yPos2 - powerz[i].yPos,this);
				}
			}
		
			//draws rectangles indicating hero's strong and weak areas
			if(showZones)
			{
				g.setColor(Color.white);
				g.fillRect(140,Hero.yPosW,200,Hero.yPos2W-Hero.yPosW);
				g.setColor(Color.red);
				g.fillRect(Hero.xPosS,Hero.yPosS,Hero.xPos2S-Hero.xPosS,Hero.yPos2S- Hero.yPosS);
			}
			
			//draws bugSpray cloud
			if(bugProof)
			{
				if(sprayCar == 0)
					g.drawImage(Spray.getImage(),150,Hero.yPos2-250,200,200,this);
				else if(sprayCar == 1)
					g.drawImage(Spray2.getImage(),150,Hero.yPos2-250,200,200,this);
				else if(sprayCar == 2)
					g.drawImage(Spray3.getImage(),150,Hero.yPos2-250,200,200,this);
			}
			
			//draws pause screen and statistics board
			if(!done && !lost && pause)
			{
				g.drawImage(Pause.getImage(), 0,-40, 1000, 700, this);
			}
			
			if(stats)
			{
				g.setColor(Color.black);
				g.fillRect(300,200,400,300);
				g.setColor(Color.white);
				g.drawString("Statistics:", 320,220);
				g.drawString("Points:" + Main.points, 340,240);
				g.drawString("Time: " + time, 340,460);
				g.drawString("# of creatures killed:" + creaturesKilled, 340,260);
				g.drawString("# of creatures dodged:" + creaturesDodged, 340,280);
				g.drawString("Hero xW: " + Hero.xPosW, 340,300);
				g.drawString("Hero xW2: " + Hero.xPos2W, 340,320);
				g.drawString("Hero yW: " + Hero.yPosW, 340,340);
				g.drawString("Hero yW2: " + Hero.yPos2W, 340,360);
			}
			
	//		g.drawImage(Bugs.getImage(), 400,100, 200, 200, this);
			
			//draws losing screen
			if(lost)
			{
				g.drawImage(Lost.getImage(), 0,-40, 1000, 700, this);
				Font BigWords = new Font("Book Antiqua",Font.PLAIN,20);
				g.setFont(BigWords);
				g.setColor(Color.black);
				if(Hero.life <= 0)
					g.drawString("You ran out of life!", 400,400);
				else
					g.drawString("You ran out of time!", 400,400);	
			}
			//draws win screen
			if(done)
				g.drawImage(Win.getImage(), 0,-40, 1000, 700, this);
		}
	}
	
	public static void main (String [] args)
	{
		new Stage1();
	}
	
	//returns random number between 1 & 5 whihc determines type of creature
	public int rType()
	{
		int rand = (int)(52*Math.random())+1;
		int kind = 0;
		
		// makes velociraptors more rare than snakes
		if(rand >=1 &&rand <=20)
			kind = 1;
		else if(rand>20 && rand<=35)
			kind = 2;
		else if(rand>35 &&rand <=45)
			kind = 3;
		else if (rand>45 && rand<=50)
			kind = 4;
		else if  (rand>50)
			kind = 4;
	
		return kind;//kind;
	}
	public int rLocation(int what)
	{
		int where = 0;
		
		if(what ==1)//stronger creatures appear as the level progresses
			where =(int)((49*Math.random())+1)*20;
		else if(what ==2)
			where =(int)((44*Math.random())+1)*20 + 100;
		else if(what ==3)
			where =(int)((40*Math.random())+1)*20 + 200;
		else if(what ==4)
			where =(int)((30*Math.random())+1)*20 + 400;
		else if(what ==5)
			where =(int)((20*Math.random())+1)*20 + 600;
			
		return where;	
	//	return (int)((49*Math.random())+1)*20;
	}
	public int Random ()
	{
		return (int)((49*Math.random())+1)*20;
	}
	
	public int rLocationP(int what)//for powerups rather than creatures
	{
		int where = 0;
		
		if(what ==1)//stronger creatures appear as the level progresses
			where =(int)((49*Math.random())+1)*20;
		else if(what ==2)
			where =(int)((30*Math.random())+1)*20 + 400;
		else if(what ==3)
			where =(int)((25*Math.random())+1)*20 + 500;
			
		return where;	
	}
	//rounds time to the nearest hundreth of a second
	public void addPoints (int q)
	{
		if(kreaturz[q].type ==1)
			Main.points+=50;
		else if(kreaturz[q].type ==2)
			Main.points+=100;
		else if(kreaturz[q].type ==3)
			Main.points+=300;
		else if(kreaturz[q].type ==4)
			Main.points+=200;
		else if(kreaturz[q].type ==5)
			Main.points+=500;
	}
	
	public int berührtSich(int q)//German for 'is touching'
	{
		if((Hero.xPosS>=kreaturz[q].xPosW && Hero.xPosS<=kreaturz[q].xPos2W)||(Hero.xPos2S>=kreaturz[q].xPosW && Hero.xPos2S<=kreaturz[q].xPos2W)||(Hero.xPosAvS>=kreaturz[q].xPosW && Hero.xPosAvS<=kreaturz[q].xPos2W))
		//if hero's strong area and creature's weak area overlap horizontally
		{
			if((Hero.yPosS>=kreaturz[q].yPosW && Hero.yPosS<=kreaturz[q].yPos2W)||(Hero.yPos2S>=kreaturz[q].yPosW && Hero.yPos2S<=kreaturz[q].yPos2W))
			// and vertically
				return 1; 
				//Hero kills creature
			else return 0;
		}
		else if((kreaturz[q].xPosS >=Hero.xPosW && kreaturz[q].xPosS<=Hero.xPos2W)||(kreaturz[q].xPos2S >=Hero.xPosW && kreaturz[q].xPos2S<=Hero.xPos2W))
		////if creatures's strong area and hero's weak area overlap horizontally
		{
			if((kreaturz[q].yPosS >=Hero.yPosW && kreaturz[q].yPosS<=Hero.yPos2W)||(kreaturz[q].yPos2S >=Hero.yPosW && kreaturz[q].yPos2S<=Hero.yPos2W))
			//and vertically
				return 2;
				//creature hurts hero
			else return 0;
		}
		else if((Hero.xPosS>=kreaturz[q].xPosS && Hero.xPosS<=kreaturz[q].xPos2S)||(Hero.xPos2S>=kreaturz[q].xPosS && Hero.xPos2S<=kreaturz[q].xPos2S))
		//if hero's strong area and creature's strong area overlap horizontally
		{
			if((Hero.yPosS>=kreaturz[q].yPosS && Hero.yPosS<=kreaturz[q].yPos2S)||(Hero.yPos2S>=kreaturz[q].yPosS && Hero.yPos2S<=kreaturz[q].yPos2S))
			// and vertically
				return 2;
				//creature hurts hero
			else return 0;
		}
		else if((kreaturz[q].xPosW >=Hero.xPosW && kreaturz[q].xPosW<=Hero.xPos2W)||(kreaturz[q].xPos2W >=Hero.xPosW && kreaturz[q].xPos2W<=Hero.xPos2W))
		////if creatures's weak area and hero's weak area overlap horizontally
		{
			if((kreaturz[q].yPosW >=Hero.yPosW && kreaturz[q].yPosW<=Hero.yPos2W)||(kreaturz[q].yPos2W >=Hero.yPosW && kreaturz[q].yPos2W<=Hero.yPos2W))
			//and vertically
				return 1;
				//Hero kills creature
			else return 0;
		}
		else return 0;
	}
	
	public void collision(int q)
	{	
		if (invincibile)
		{
			if(berührtSich(q)==1 || berührtSich(q)==2)
			{
				sPunch();
				kreaturz[q].kill();
				kreaturz[q].showBody = true;
				addPoints(q);
				creaturesKilled++;
			}
		}
		else if (!hurt)
		{	
			if(berührtSich(q)==1 || berührtSich(q)==2 && kreaturz[q].type ==4 && bugProof)
			{
				sPunch();
				kreaturz[q].kill();
				kreaturz[q].showBody = true;
				addPoints(q);
				creaturesKilled++;
			}
			else if(berührtSich(q)==1)
			{
				sPunch();
				kreaturz[q].kill();
				kreaturz[q].showBody = true;
				addPoints(q);
				creaturesKilled++;
			}
			else if (berührtSich(q)==2)
			{
				Hero.life-=kreaturz[q].damage;
				if(Hero.life<=0)
				{
					lost = true;
				}
				hurtCount = 40;//makes user invincible for 1 and a half seconds
				hurt = true;
				sHit();				
			}
		}
	}
	
	public void collisionP (int q)//collision with powerups
	{
		if(powerz[q].xPos>= Hero.xPos && powerz[q].xPos<= Hero.xPos2 || powerz[q].xPos2>= Hero.xPos && powerz[q].xPos2<= Hero.xPos2)
		{
			if(powerz[q].yPos>= Hero.yPos && powerz[q].yPos<= Hero.yPos2 || powerz[q].yPos2>= Hero.yPos && powerz[q].yPos2<= Hero.yPos2)
			{
				pickUp(q);
				sThunder();
			}
		}
	}
	
	public void pickUp(int q)
	{
		if(powerz[q].type ==1)
		{
			if(Hero.life<=8)
				Hero.life+= powerz[q].size;
			else Hero.life=12;
			powerz[q].dissapear();
		}
		else if (powerz[q].type ==2)
		{
			if(Hero.bugspray<=3)
				Hero.bugspray+= powerz[q].size;
			else Hero.bugspray = 4;
			powerz[q].dissapear();
		}
		else
		{
			invinciCount+= powerz[q].size;
			powerz[q].dissapear();
		}
	}
	
	//sprays bugspray, reduces total bugspray, makes invincibile to bugs, starts spraying countdown
	public void spray()
	{
		if(Hero.bugspray>0)
		{
			Hero.bugspray--;
			bugProof = true;
			sprayCount = 50;
			sSpray();
		}
	}
	
	//moves backdrops different distances horizontally to give layered effect
	//adds to total distance and changes sprite
	public void move()
	{
		x-=10;
		x2-=10;
		X-=20;
		X2-=20;
		deltaD+=1;
		car++;
		if(car>3)
			car =0;
			
		if(x2==0)
			x = PicWidth;
		if( x==0)
			x2 = PicWidth;
			
		if(X2==0)
			X = PicWidth;
		if( X==0)
			X2 = PicWidth;
		
		if(!up && !fall)	
			Hero.yPosW = 400;
	}
	public void jump()
	{
		Hero.yPos-=20;
		Hero.yPos2-=20;
		Hero.yPosS-=20;
		Hero.yPos2S-=20;
		Hero.yPosW-=20;
		Hero.yPos2W-=20;
		car = 4;
	}
	public void fall()
	{
         
		if (Hero.yPos2<630)
		{
			Hero.yPos+=20;
			Hero.yPos2+=20;
			Hero.yPosS+=20;
			Hero.yPos2S+=20;
			Hero.yPosW+=20;
			Hero.yPos2W+=20;
		}
		car = 5;
	}
	public void duck()
	{
		car = 6;
		Hero.yPosW = 450;
	}
	public void crawl()
	{
		x-=5;
		x2-=5;
		X-=10;
		X2-=10;
		deltaD+=1;
		car++;
		if(car>8)
			car =6;
			
		if(x2==0)
			x = PicWidth;
		if( x==0)
			x2 = PicWidth;
			
		if(X2==0)
			X = PicWidth;
		if( X==0)
			X2 = PicWidth;
			
		Hero.yPosW = 450;
	}
	
	/////////////////////////////////////////////////////SOUNDS////////////////////////////////
	public void sCougar()
	{
		if(sound)
		{
			try
      	{
          	 AudioClip end  = Applet.newAudioClip(new URL("file:Cougar3.wav"));
         	 end.play();
      	} 
      	catch (MalformedURLException murle) 
      	{};
      }
   }
   
   public void sSnake()
	{
		if(sound)
		{
			try
      	{
          	AudioClip end  = Applet.newAudioClip(new URL("file:Snake.wav"));
          	end.play();
      	} 
     	   catch (MalformedURLException murle) 
      	{};
      }
   }
   
   public void sBeaver()
	{
		if(sound)
		{
			try
      	{
          	AudioClip end  = Applet.newAudioClip(new URL("file:Beaver.wav"));
          	end.play();
      	} 
     	   catch (MalformedURLException murle) 
      	{};
      }
   }
   
   public void sBugs()
	{
		if(sound)
		{
			try
      	{
          	AudioClip end  = Applet.newAudioClip(new URL("file:Mosquitoes.wav"));
          	end.play();
      	} 
     	   catch (MalformedURLException murle) 
      	{};
      }
   }
   
   public void sVelociraptor()
	{
		if(sound)
		{
			try
      	{
          	AudioClip end  = Applet.newAudioClip(new URL("file:Velociraptor.wav"));
          	end.play();
      	} 
     	   catch (MalformedURLException murle) 
      	{};
      }
   }
   
   public void sBear()
	{
			try
      	{
          	AudioClip end  = Applet.newAudioClip(new URL("file:grizzlybear.wav"));
          	end.play();
      	} 
     	   catch (MalformedURLException murle) 
      	{};
   }
   
   public void sWolves()
	{
			try
      	{
          	AudioClip end  = Applet.newAudioClip(new URL("file:Wolves.wav"));
          	end.play();
      	} 
     	   catch (MalformedURLException murle) 
      	{};
   }
   
   public void sSpray()
	{
		if(sound)
		{
			try
      	{
          	AudioClip end  = Applet.newAudioClip(new URL("file:aerosolspray2.wav"));
          	end.play();
      	} 
     	   catch (MalformedURLException murle) 
      	{};
      }
   }
   
   public void sThunder()
	{
		if(sound)
		{
			try
      	{
          	AudioClip end  = Applet.newAudioClip(new URL("file:thunder2.wav"));
          	end.play();
      	} 
     	   catch (MalformedURLException murle) 
      	{};
      }
   }
   
   public void sHit()
	{
		if(sound)
		{
			if(punchCount == 0)
			{
				try
      		{
          		AudioClip end  = Applet.newAudioClip(new URL("file:Hit1.wav"));
          		end.play();
      		} 
     	   	catch (MalformedURLException murle) 
      		{};
      		punchCount++;
      	}
      	else if (punchCount == 1)
      	{
				try
      		{
          		AudioClip end  = Applet.newAudioClip(new URL("file:Hit2.wav"));
          		end.play();
      		} 
     	  	   catch (MalformedURLException murle) 
      		{};
      		punchCount++;
      	}
      	else
      	{
				try
      		{
          		AudioClip end  = Applet.newAudioClip(new URL("file:Hit3.wav"));
          		end.play();
      		} 
     	  	   catch (MalformedURLException murle) 
      		{};
      		punchCount =0;
      	}
      }
   }
   
   public void sPunch()
	{
		if(sound)
		{
			if(punchCount == 0)
			{
				try
      		{
          		AudioClip end  = Applet.newAudioClip(new URL("file:punch1.wav"));
          		end.play();
      		} 
     	   	catch (MalformedURLException murle) 
      		{};
      		punchCount++;
      	}
      	else if (punchCount == 1)
      	{
				try
      		{
          		AudioClip end  = Applet.newAudioClip(new URL("file:punch2.wav"));
          		end.play();
      		} 
     	  	   catch (MalformedURLException murle) 
      		{};
      		punchCount++;
      	}
      	else
      	{
				try
      		{
          		AudioClip end  = Applet.newAudioClip(new URL("file:punch3.wav"));
          		end.play();
      		} 
     	  	   catch (MalformedURLException murle) 
      		{};
      		punchCount =0;
      	}
      }
   }
   
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public void keyPressed(KeyEvent e)
   {
     	int typed = e.getKeyCode();

     	if(typed == KeyEvent.VK_RIGHT)
     	{
			right=true;
     	}
     	if(typed == KeyEvent.VK_DOWN)
     	{
			down = true;
     	}
     	
     	if(typed == KeyEvent.VK_UP)
		{
			if (!fall)
				up=true;
     	}
     	if(typed == KeyEvent.VK_P)
		{
			if(pause)
			{
				pause = false;
			}
			else
				pause = true;
     	}
     	if(typed == KeyEvent.VK_D)
     	{
			if(invincibile)
			{
				invincibile = false;
				cheat = false;
			}
			else
			{
				invincibile = true;
				cheat = true;
			}
     	}
     	if(typed == KeyEvent.VK_Q)
     	{
     		if(showZones)
     			showZones=false;
     		else
     			showZones=true;
     	}
     	if(typed == KeyEvent.VK_S)
     	{
     		if(sound)
     			sound=false;
     		else
     			sound=true;
     	}
     	if(typed == KeyEvent.VK_A)
     	{
     		if(stats)
     			stats=false;
     		else
     			stats=true;
     	}
     	if(typed == KeyEvent.VK_1)
     	{
     		Hero.life++;
     	}
     	if(typed == KeyEvent.VK_2)
     	{
     		Hero.life--;
     	}
     	if(typed == KeyEvent.VK_CONTROL)
     	{
     		spray();
     	}
   }
    // called when a key that was pressed is released
	public void keyReleased(KeyEvent e)
	{
		int released = e.getKeyCode();
		
		if(released == KeyEvent.VK_RIGHT)
     	{
			right=false;
			if(!pause)
				car = 0;	
     	}
     	if(released == KeyEvent.VK_UP)
     	{
			up=false;
			fall = true;	
     	}
     	if(released == KeyEvent.VK_DOWN)
     	{
			down=false;
			
			if((x%10)!=0) 
			{
				x-=5;
				x2-=5;
				X-=10;
				X2-=10;

				deltaD+=1;
			}
			
			if(!pause && !done &&!lost && !fall && !up)
			{
				car = 0;
				Hero.yPosW = 400;	
			}
     	}
  	}
   
   // called when a key is pressed and released immediatley (typed)
  	public void keyTyped(KeyEvent e)
   {        
	}
	public void actionPerformed(ActionEvent e)
	{
	}
	
	class Animate extends Thread
   {
      public void run()
      {
         try
         {
            while(true)
            {
            	if(!lost)
            	{
            	if(!done)
            	{
            	if (!pause)
            	{
            		if (right && !down)
            		{
            			move();
            		}
            		else if (right && down)
            		{
            			crawl();
            		}
            		if(up == true)
            		{
            			jump();
            			if(frameCount<15)
            				frameCount++;
            			else
            			{
            				up = false;
            				fall = true;
            			}
            		}
            		if (fall ==true)
            		{
							fall();
							if(frameCount>0)
								frameCount--;
							else
							{
								fall = false;
								car = 0;
							}
            		}
            		if(down == true && !right && !up && !fall)
            			duck();
            		
            		//enemy spawning
            		for(int q = 0;q<kreaturz.length;q++)
            		{
            			if(kreaturz[q].location == deltaD)
            			{
            				kreaturz[q].tradeInCoupon();
            				
								if(kreaturz[q].xPos == 1100)
								{
									if(kreaturz[q].type==1)
										sSnake();
									else if(kreaturz[q].type==2)
										sBeaver();
									else if(kreaturz[q].type==3)
										sCougar();
									else if(kreaturz[q].type==4)
										sBugs();
									else
										sVelociraptor();
								}
            			}
            		}
            		
            		//powerup spawning
            		for(int q = 0;q<powerz.length;q++)
            		{
            			if(powerz[q].location == deltaD)
            				powerz[q].tradeInCoupon();
            		}

            		//enemy movement
            		for(int q = 0;q<kreaturz.length;q++)
            		{
            			if(kreaturz[q].alive)
            			{
            			
            				if(kreaturz[q].type ==4)//mosquito movement
            				{
            				
            					if(kreaturz[q].yPos2<580)//descending from above
            					{
            						kreaturz[q].yPos += kreaturz[q].movement;
            						kreaturz[q].yPos2 += kreaturz[q].movement;
            						kreaturz[q].yPosS += kreaturz[q].movement;
            						kreaturz[q].yPos2S += kreaturz[q].movement;
            					}
            					
            					if(kreaturz[q].xPos>150)//stopping at player
            					{
            						kreaturz[q].xPos -= kreaturz[q].movement;
            						kreaturz[q].xPos2 -= kreaturz[q].movement;
            						kreaturz[q].xPosS -= kreaturz[q].movement;
            						kreaturz[q].xPos2S -= kreaturz[q].movement;
            					}
            					else
            					{
            						if((Hero.yPos2-50)<kreaturz[q].yPos2)//going up when player jumps
            						{
            							kreaturz[q].yPos2 -= 20;
            							kreaturz[q].yPos -= 20;
            							kreaturz[q].yPosS -= 20;
            							kreaturz[q].yPos2S -= 20;
            						}
            						else if ((Hero.yPos2-50)>kreaturz[q].yPos2)//going down when player falls
            						{
            							kreaturz[q].yPos2 += 20;
            							kreaturz[q].yPos += 20;
            							kreaturz[q].yPosS += 20;
            							kreaturz[q].yPos2S += 20;
            						}
            					}
            				}//movement of other creatures
            				else
            				{
            					kreaturz[q].xPos -= kreaturz[q].movement;
            					kreaturz[q].xPos2 -= kreaturz[q].movement;
            					kreaturz[q].xPosW -= kreaturz[q].movement;
            					kreaturz[q].xPos2W -= kreaturz[q].movement;
            					kreaturz[q].xPosS -= kreaturz[q].movement;
            					kreaturz[q].xPos2S -= kreaturz[q].movement;
            				}
            				//extra movement if player moves forward
            				if(right && !(kreaturz[q].type ==4 && kreaturz[q].xPos<=150))
            				{
            					kreaturz[q].xPos -= 20;
            					kreaturz[q].xPos2 -= 20;
            					kreaturz[q].xPosW -= 20;
            					kreaturz[q].xPos2W -= 20;
            					kreaturz[q].xPosS -= 20;
            					kreaturz[q].xPos2S -= 20;
            					if(down)//or crawls
            					{
            						kreaturz[q].xPos += 10;
            						kreaturz[q].xPos2 += 10;
            						kreaturz[q].xPosW += 10;
            						kreaturz[q].xPos2W += 10;
            						kreaturz[q].xPosS += 10;
            						kreaturz[q].xPos2S += 10;
            					}
            				}
            				
            				//cycles through enemy sprites
            				kreaturz[q].enemyCar++;
            				if(kreaturz[q].enemyCar>5)
            					kreaturz[q].enemyCar = 0;
            				if(kreaturz[q].xPos<-400)
            				{
            					kreaturz[q].kill();
            					kreaturz[q].showBody = false;
            				}
            				
            				//checks if collision occurs and subtracts health
            				collision(q);
            			}
            			else if (!kreaturz[q].alive && kreaturz[q].showBody)//moves dead body
            			{
            				
            				if(kreaturz[q].yPos2<900)
            				{
            					kreaturz[q].yPos+=10;
            					kreaturz[q].yPos2+=10;
            				}
            				if(right)
            				{
            					kreaturz[q].xPos -= 20;
            					kreaturz[q].xPos2 -= 20;
            					kreaturz[q].xPosW -= 20;
            					kreaturz[q].xPos2W -= 20;
            					kreaturz[q].xPosS -= 20;
            					kreaturz[q].xPos2S -= 20;
            					if(down)//or crawls
            					{
            						kreaturz[q].xPos += 10;
            						kreaturz[q].xPos2 += 10;
            						kreaturz[q].xPosW += 10;
            						kreaturz[q].xPos2W += 10;
            						kreaturz[q].xPosS += 10;
            						kreaturz[q].xPos2S += 10;
            					}
            				}
            			}
            		}
            		
            		//moves powerups
            		for(int q = 0;q<powerz.length;q++)
            		{
            			if (powerz[q].visible)
            			{
            				if(right)
            				{
            					powerz[q].xPos -= 20;
            					powerz[q].xPos2 -= 20;
            					if(down)//or crawls
            					{
            						powerz[q].xPos += 10;
            						powerz[q].xPos2 += 10;
            					}
            				}
            				collisionP(q);
            			}
            		}
            		
            		if(invinciCount>0)
            		{
            			invincibile = true;
            			invinciCount--;
            		}
           			else if(!cheat)
          				invincibile = false;	
          				
          			if(hurtCount>0)
          			{
          				hurt = true;
          				hurtCount--;
          			}
          			else
          				hurt = false;
          				
          			if(sprayCount>0)
          			{
          				bugProof = true;
          				sprayCount--;
          				if(sprayCar<2)
          					sprayCar++;
          				else
          					sprayCar=0;
          			}
          			else
          				bugProof=false;
          				
          			if(flashyCount==0)
          			{
          				flashyCount++;
          			}
          			else
          				flashyCount--;
            		
            		time+=0.05;//(1000/20)/1000
						
						if(Math.round(time)==80)
						{
							lost = true;
						}
						if(lost)
						{
							Stage.repaint();
							sleep(500);
							sWolves();
							sleep(6000);
							if(Main.attempts>1)
							{
								Main.attempts--;
								new Stage1();
							}
							else
								System.exit(1);
							
							frame.setVisible(false);	
						}
            		else if(deltaD==1000)
            		{
            			done = true;
            			sCougar();
            			Stage.repaint();
            			sleep(6000);
            			System.exit(1);
            		}
            	}
            	}
            	}
            	Stage.repaint();
               sleep(1000/20);
            }
         } catch (InterruptedException e)
            {};
      }
   }
}
/*
	Left to do:
	- fix collision for cougars
	- music
*/
