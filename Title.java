import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;

public class Title implements ActionListener
{

   JButton play = new JButton("Play");
   JButton how = new JButton("How to Play");
   JButton quit = new JButton("Quit");
   JButton none = new JButton("This Button Does Nothing!?");
   JFrame frame = new JFrame("KATACLYSMIC KANOEING: THE PREQUEL SEQUEL!!");
   Drawing title = new Drawing();
   
   ImageIcon Outers = new ImageIcon("OutersClub2.jpg");//H:\\JavaFiles\\OUTERSCLUBGAME\\
   ImageIcon Title = new ImageIcon("KANOEING.jpg");//H:\\JavaFiles\\OUTERSCLUBGAME\\
   ImageIcon Title2 = new ImageIcon("KANOEING2.jpg");//H:\\JavaFiles\\OUTERSCLUBGAME\\
   
   public Title()
   {
   	frame.setSize(500, 700);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel panel = new JPanel();
      JPanel panel2 = new JPanel();
      
      play.setBackground(Color.green);
      play.setForeground(Color.white);
      quit.setBackground(Color.red);
      quit.setForeground(Color.white);
      how.setBackground(Color.blue);
      how.setForeground(Color.white);
      none.setBackground(Color.black);
      none.setForeground(Color.white);
      
      panel.add(play);
      panel.add(how);
      panel.add(none);
      panel.add(quit);
      panel.setBackground(Color.black);
      
      frame.getContentPane().add(title, "Center");
      frame.getContentPane().add(panel, "South");

      play.addActionListener(this);
      how.addActionListener(this);
      quit.addActionListener(this);
      
      frame.setVisible(true);
   }
   
   class Drawing extends JComponent
   {
   	public void paint (Graphics g)
   	{
   		g.setColor(Color.black);
   		g.fillRect(0,0,500,700);

   		g.drawImage(Title.getImage(), 50,10, 400, 100, this);
   		
   		Font somth2 = new Font("Serif",Font.PLAIN,25);
   		g.setFont(somth2);
   		g.drawString("THE PREQUEL SEQUEL!!", 110,605);
   		g.drawImage(Title2.getImage(), 50,575, 400, 100, this);
   		
   		g.drawImage(Outers.getImage(), 50,50, 400, 525, this);
   	}
   }
   
   public void actionPerformed(ActionEvent e)
   {	
      if (e.getSource() == play)
      {
         new Stage1();
         frame.setVisible(false);
      }
      else if (e.getSource() == how)
      {
         new HowTo();
         frame.setVisible(false);
      }
      else if (e.getSource() == quit)
     	   System.exit(1); 
   }
   
   public static void main(String[] args)
   {
      new Title();
   }
} 

