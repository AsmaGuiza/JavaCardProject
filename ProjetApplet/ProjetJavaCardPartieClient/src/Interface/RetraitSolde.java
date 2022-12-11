package Interface;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.sun.javacard.apduio.Apdu;

import Client.MonClient;

public class RetraitSolde extends JPanel implements ActionListener  {		JButton jButton1;
		JButton jButton2;
		JButton jButton3;
		JButton jButton4;
		JButton jButton5;
		JButton jButton6;
		JButton jButton8;
		JLabel jLabel1;
		private CardLayout card;
		private JFrame Mainframe;
		public MonClient client;
		Apdu apdu, apdu1;

		public static final byte CLA_MONAPPLET = (byte) 0xB0;
		public static final byte INS_TEST_CODE_PIN = 0x00;
		public static final byte INS_INTERROGER_COMPTE = 0x01;
		public static final byte INS_INCREMENTER_COMPTE = 0x02;
		public static final byte INS_DECREMENTER_COMPTE = 0x03;
		public static final byte INS_INITIALISER_COMPTE = 0x04;

		public final static short MAX_BALANCE = 0x7FFF;
		public final static byte MAX_MONTANT_TRANSACTION = (byte)127;


		public final static byte MAX_ERROR_PIN = (byte) 0x03;

		public final static byte MAX_PIN_LENGTH = (byte) 0x04;   
	public RetraitSolde(JFrame MainFrame,CardLayout card,MonClient client) {
		this.card=card;
		this.Mainframe=MainFrame;
        this.client=client;
	      //instance des composants jLabel2=new JLabel();
			 jLabel1 = new JLabel();
			 jButton1 = new javax.swing.JButton();
			 jButton2 = new javax.swing.JButton();
			 jButton3 = new javax.swing.JButton();
			 jButton4 = new javax.swing.JButton();			 
			 jButton5 = new javax.swing.JButton();
			 jButton6 = new javax.swing.JButton();
			 jButton8 = new javax.swing.JButton();
			//Mosification sur les composants 
	         jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
	         jButton1.setText("10");
	         jButton1.setBackground(new java.awt.Color( 63, 98, 102));
	         jButton1.setForeground(Color.white);
	         jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
	         jButton2.setText("20");
	         jButton2.setBackground(new java.awt.Color( 63, 98, 102));
	         jButton2.setForeground(Color.white);
	         jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
	         jButton3.setText("30");
	         jButton3.setBackground(new java.awt.Color( 63, 98, 102));
	         jButton3.setForeground(Color.white);
	         jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
	         jButton4.setText("40");
	         jButton4.setBackground(new java.awt.Color( 63, 98, 102));
	         jButton4.setForeground(Color.white);
	         jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
	         jButton5.setText("50");
	         jButton5.setBackground(new java.awt.Color( 63, 98, 102));
	         jButton5.setForeground(Color.white);
	         jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
	         jButton6.setText("100");
	         jButton6.setBackground(new java.awt.Color( 63, 98, 102));
	         jButton6.setForeground(Color.white);
	         jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
	         jButton8.setText("Retour");
	         jButton8.setBackground(new java.awt.Color( 63, 98, 102));
	         jButton8.setForeground(Color.white);
	         //Associer les évènnements des boutons
	         	jButton1.addActionListener(this);
	         	jButton2.addActionListener(this);
	         	jButton3.addActionListener(this);
	         	jButton4.addActionListener(this);	         	
	         	jButton5.addActionListener(this);
	         	jButton6.addActionListener(this);
	         	jButton8.addActionListener(this);
	           //Image background 
	            jLabel1.setIcon(new javax.swing.ImageIcon("C:\\\\JavaCard\\\\workspace\\\\ProjetJavaCardFinale\\\\ret.jpg")); // NOI18N
	            jLabel1.setBounds(0,0,564,564);
	           //Ajout des composants 
	            
	            JPanel panel1=new JPanel();
	            jButton1.setBounds(0, 200, 120, 30);
	            jButton2.setBounds(0, 250, 120, 30); 
	            jButton3.setBounds(0, 300, 120, 30);
	            jButton4.setBounds(450, 200,120, 30); 
	            jButton5.setBounds(450, 250,120, 30);
	            jButton6.setBounds(450, 300, 120, 30); 
	            jButton8.setBounds(450, 450, 80, 30);
	            jLabel1.add(jButton1);
	            jLabel1.add(jButton2);
	            jLabel1.add(jButton3);
	            jLabel1.add(jButton4);
	            jLabel1.add(jButton5);
	            jLabel1.add(jButton6);
	            jLabel1.add(jButton8);
	            panel1.add(jLabel1);
	            
	            add(panel1);
	         
		}
	
	
	
	
	
	

	public void actionPerformed(ActionEvent e) {
        
		JButton source = (JButton) e.getSource();
				if (source==jButton8)
		{
	    	card.show(Mainframe.getContentPane(),"AppClient") ;
		}
		else {

			String mo=source.getText();
			int moi = Integer.parseInt(mo); 
			byte[] montant = new byte[] {(byte) moi};

	try {
				apdu1 = client.Msg(AppClient.INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
			    String data1 = "" +apdu1.dataOut[1];
			    int data1i = Integer.parseInt(data1); 
			    System.out.println(data1i);
			if (data1i>moi){
				try {
					apdu = client.Msg(INS_DECREMENTER_COMPTE, (byte) 0x01, montant, (byte) 0x7f);
					if (apdu.getStatus() != 0x9000) {
						System.out.println("Erreur : status word different de 0x9000");
						} 
					 else 
						{System.out.println("OK");}
					} catch (Exception e1) {e1.printStackTrace();}}
			else {
			    	showMessageDialog(null, "solde Insuffiasant");
			     } }
	catch (Exception e1) {e1.printStackTrace();}
			
		  
}}
	}
