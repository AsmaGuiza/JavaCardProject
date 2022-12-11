package Interface;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import com.sun.javacard.apduio.Apdu;

import Client.MonClient;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadTransportException;

public class ConsulterSolde extends JPanel implements ActionListener{
	JLabel jLabel1, jLabel2;
	JLabel montant ; 
	JButton jButton5, jButton6;
	   Apdu apdu;
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

	public ConsulterSolde(JFrame MainFrame,CardLayout card, MonClient client ) {
		
		this.card=card;
		this.Mainframe=MainFrame;
        this.client=client;
        
	    jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton5 = new JButton();
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jButton5.setText("Retour");
        jButton5.setBackground(new java.awt.Color( 63, 98, 102));
        jButton5.setForeground(Color.white);
        jButton5.addActionListener(this);
        jButton6 = new JButton();
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jButton6.setText("Refraichir");
        jButton6.setBackground(new java.awt.Color( 63, 98, 102));
        jButton6.setForeground(Color.white);
        jButton6.addActionListener(this);
        
        montant  = new JLabel(); 
      //Image background
        jLabel2.setFont( new Font("Times New Roman", 1, 20));
        jLabel2.setText("VOTRE SOLDE EST : 0 ");
        jLabel1.setForeground(new java.awt.Color(25, 93, 158));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\\\JavaCard\\\\workspace\\\\ProjetJavaCardFinale\\\\imgeeee.jpg")); // NOI18N
        jLabel1.setBounds(0,0,564,564);
        JPanel panel1=new JPanel();
        jLabel2.setBounds(270, 130, 250, 20);
        montant.setBounds(270, 190, 250, 22);
        jButton5.setBounds(350, 250, 100, 20);
        jButton6.setBounds(200, 250, 100, 20);
        
        jLabel1.add(jLabel2);
        jLabel1.add(montant);
        jLabel1.add(jButton5);
        jLabel1.add(jButton6);
        panel1.add(jLabel1);
        add(panel1);
		
		
		
	}	
	
	public void actionPerformed(ActionEvent e) {
		 JButton source = (JButton) e.getSource();
		 if (source==jButton5) { card.show(Mainframe.getContentPane(),"AppClient") ;}
		 else {
			 
				try {
				    apdu = client.Msg(AppClient.INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
							if (apdu.getStatus() != 0x9000) {
						System.out.println("Erreur : status word different de 0x9000");
					} 
							else {
								String data1=""+ apdu.dataOut[1];
								jLabel2.setText("VOTRE SOLDE EST :" + data1);
							
					}
		
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
		 }
        
   
        
	}


}
