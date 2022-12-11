package Interface;
import javax.swing.*;

import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;
import java.io.IOException;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadTransportException;

import Client.MonClient;

public class LoginUser extends JPanel implements ActionListener
{		JButton jButton1;
		JPasswordField jpasswordfield1;
		JLabel jLabel1;
		JLabel jLabel2 ;
		AppClient c; 
		private CardLayout card;
		private JFrame Mainframe;
		public MonClient client; 
		public static final byte CLA_MONAPPLET = (byte) 0xB0;
		public static final byte INS_TEST_CODE_PIN = 0x00;
		public static final byte INS_INTERROGER_COMPTE = 0x01;
		public static final byte INS_INCREMENTER_COMPTE = 0x02;
		public static final byte INS_DECREMENTER_COMPTE = 0x03;
		public static final byte INS_INITIALISER_COMPTE = 0x04;

		public final static short MAX_BALANCE = 0x7FFF;// le maximum de la balance

		public final static byte MAX_MONTANT_TRANSACTION = (byte)127;// maximum montant
																// qu'on peut
																// transiter

		public final static byte MAX_ERROR_PIN = (byte) 0x03;// maximum de code pin
																// erroner

		public final static byte MAX_PIN_LENGTH = (byte) 0x04;
		
		public LoginUser(JFrame MainFrame,CardLayout card, MonClient client)
{	//initialisation du fenetre
		this.card=card;
		this.Mainframe=MainFrame;
        this.client=client;
	        
	//instance des composants 
         jLabel2=new JLabel();
		 jLabel1 = new JLabel();
		 jpasswordfield1 = new JPasswordField(10);
		 jButton1 = new javax.swing.JButton();
	 //Mosification sur les composants 
         jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
         jButton1.setText("Valider");
		 jLabel1.setFont( new Font("Times New Roman", 1, 24));
         jLabel1.setText("Entrez Votre Code Pin");
         jLabel1.setForeground(new java.awt.Color(25, 93, 158));
         
     //Associer les évènnements des boutons
         jButton1.addActionListener(this);
	         
     //Image background 
         jLabel2.setIcon(new javax.swing.ImageIcon("C:\\\\JavaCard\\\\workspace\\\\ProjetJavaCardFinale\\\\imge.jpg")); // NOI18N
         jLabel1.setBounds(0,0,564,564);
	         
     //conteuneur et contenus 

         JPanel panel1=new JPanel();
         panel1.setPreferredSize(new java.awt.Dimension(500, 500));
         jLabel1.setBounds(270, 130, 250, 20);
         jpasswordfield1.setBounds(270, 190, 250, 22);
         jButton1.setBounds(270, 250, 200, 20); 
         jLabel2.add(jLabel1);
         jLabel2.add(jpasswordfield1);
         jLabel2.add(jButton1);
         panel1.add(jLabel2);
         
         add(panel1);
       }
      
    //ActionListenner
     	@Override
    	public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String pwd = new String(jpasswordfield1.getPassword());
            
            byte[] pin_ok ;
			
			int pin=Integer.parseInt(pwd.toString());
			byte x1=(byte)((int)(pin/1000)%10);
			byte x2=(byte)((int)(pin/100)%10);
			byte x3=(byte)((int)(pin/10)%10);
			byte x4=(byte)(pin%10);
			pin_ok= new byte[] { x1,x2,x3,x4 };
			
			try {
			Apdu apdu = client.Msg(LoginUser.INS_TEST_CODE_PIN, (byte) 0x04, pin_ok, (byte) 0x7f);
			 if (apdu.getStatus() == 0x6300) {
    				showMessageDialog(null, "Code_PIN INCORRECT!");
    				
    			} else
                	card.show(Mainframe.getContentPane(),"AppClient") ;
    		}
			catch(Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
    	}



}
