package Interface ; 
import javax.swing.*;

import org.w3c.dom.css.RGBColor;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadTransportException;
import com.sun.javacard.jpcsclite.APDU;

import Client.MonClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class AppClient extends JPanel implements ActionListener {
	
	JButton jButton1;
	JButton jButton2;
	JButton jButton3;

	
	JLabel jLabel1;
	RetraitSolde c ; 
	private CardLayout card;
	private JFrame Mainframe;
	public MonClient client;
	   Apdu apdu;
	
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
	JFrame f ; 

	public AppClient(JFrame Mainframe,CardLayout card, MonClient client) {
		this.card=card;
		this.Mainframe=Mainframe;
        this.client=client;

		
      //instance des composants jLabel2=new JLabel();
		 jLabel1 = new JLabel();
		 jButton1 = new javax.swing.JButton();
		 jButton2 = new javax.swing.JButton();
		 jButton3 = new javax.swing.JButton();
		//Mosification sur les composants 
         jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
         jButton1.setText("Retrait Argent");
         jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
         jButton2.setText("Consulter Solde");
         jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
         jButton3.setText("Ajouter Solde");
         //Associer les évènnements des boutons
         	jButton1.addActionListener(this);
         	jButton2.addActionListener(this);
         	jButton3.addActionListener(this);
           //Image background 
            jLabel1.setIcon(new javax.swing.ImageIcon("C:\\\\JavaCard\\\\workspace\\\\ProjetJavaCardFinale\\\\imgee.jpg")); // NOI18N
            jLabel1.setBounds(0,0,564,564);
           //Ajout des composants 
            
            JPanel panel1=new JPanel();
            jLabel1.setBounds(270, 130, 250, 20);
            jButton2.setBounds(270, 190, 250, 22);
            jButton1.setBounds(270, 250, 250, 20); 
            jButton3.setBounds(270, 310, 250, 20); 
            jLabel1.add(jButton2);
            jLabel1.add(jButton1);
            jLabel1.add(jButton3);

            panel1.add(jLabel1);
            
            add(panel1);
         
	}



	public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        
        if (source ==jButton1)
        	
        {
        	card.show(Mainframe.getContentPane(),"RetraitSolde") ;
        	
        }
        else    if (source ==jButton2) 
        {
        	

			card.show(Mainframe.getContentPane(),"ConsulterSolde") ;
        	
        }
        else {
        	card.show(Mainframe.getContentPane(),"AjoutSolde") ;
        	
        }
	}
}
