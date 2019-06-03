import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class HowTo implements ActionListener
{

   JButton back = new JButton("Back");
   JButton next = new JButton("Next");
   JButton play = new JButton("Play");
   JFrame frame = new JFrame("How to play KATACLYSMIC KANOEING: THE PREQUEL SEQUEL");
   
   Animate bear = new Animate();
   
   ImageIcon Car = new ImageIcon ("bearsprite1i.png");//H:\\JavaFiles\\OUTERSCLUBGAME\\
	ImageIcon Car2 = new ImageIcon ("bearsprite2iii.png");
	ImageIcon Car3 = new ImageIcon ("bearsprite3iii.png");
	ImageIcon Car4 = new ImageIcon ("bearsprite4iii.png");
   ImageIcon Page = new ImageIcon("How1.jpg");
   ImageIcon Page2 = new ImageIcon("How2.jpg");
	ImageIcon Page3 = new ImageIcon("How3.jpg");
	ImageIcon Page4 = new ImageIcon("How4.jpg");
   
   int car = 0;
   int count = 0;
   
   Drawing draw = new Drawing();
   
   public HowTo()
   {
   	frame.setSize(500, 700);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel panel = new JPanel();
      
      next.setBackground(Color.green);
      next.setForeground(Color.white);
      back.setBackground(Color.red);
      back.setForeground(Color.white);
      play.setEnabled(false);
      play.setBackground(Color.lightGray);
      
		panel.add(next);
		panel.add(back);
		panel.add(play);
		panel.setBackground(Color.black);

		frame.getContentPane().add(draw);
      frame.getContentPane().add(panel,"South");
      
      play.addActionListener(this);
      back.addActionListener(this);
      next.addActionListener(this);
      
      frame.setVisible(true);
      bear.start();
   }
   
   class Drawing extends JComponent
   {
   	public void paint (Graphics g)
   	{
   		if(count==0)
   		{
   			g.drawImage(Page.getImage(), -5,0, 500, 630, this);
   		}
   		else if (count==1)
   		{
   			g.drawImage(Page2.getImage(), -5,0, 500, 630, this);
   		}
   		else if(count==2)
   		{
   			g.drawImage(Page3.getImage(), -5,0, 500, 630, this);
   		}
   		else if(count==3)
   		{
   			g.drawImage(Page4.getImage(), -5,0, 500, 630, this);
   		}
   	}
   }
   
   public void actionPerformed(ActionEvent e)
   {	
      if (e.getSource() == play)
      {
         new Stage1();
         frame.setVisible(false);
      }
      else if (e.getSource() == next)
      {
      	if(count<3)
         	count++;
         else if(count==3)
         {
         	next.setEnabled(false);
         	next.setBackground(Color.gray);
         	play.setEnabled(true);
         	play.setBackground(Color.green);
      		play.setForeground(Color.white);
         }
      }
      else if (e.getSource() == back)
      {
      	if(count>0)
         	count--;
        	else
        	{
        		new Title();
        		frame.setVisible(false);
        	}
      }
   }
   
   public static void main(String[] args)
   {
      new HowTo();
   }
   
   class Animate extends Thread
   {
      public void run()
      {
         try
         {
            while(true)
            {
            	car++;
					if(car>3)
						car =0;
            	
               draw.repaint();
               sleep(1000/10);
            }
         } catch (InterruptedException e)
            {};
      }
   }
} 

