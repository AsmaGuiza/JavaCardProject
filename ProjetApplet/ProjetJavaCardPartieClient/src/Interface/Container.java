package Interface;
import java.awt.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.sun.javacard.apduio.CadTransportException;

import Client.MonClient;

public class Container  extends JFrame  {
	public CardLayout card;

	
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
	
	MonClient client = new MonClient();

	public Container() {
	 	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(560,560);
        card=new CardLayout();
        this.getContentPane().setLayout(card); 
        
        this.getContentPane().add("LoginUser",new LoginUser(this,card,client));
        this.getContentPane().add("AppClient",new AppClient(this,card,client));
        this.getContentPane().add("RetraitSolde",new RetraitSolde(this,card,client));
        this.getContentPane().add("AjoutSolde",new AjoutSolde(this,card,client));
        this.getContentPane().add("ConsulterSolde",new ConsulterSolde(this,card,client));
        card.show(this.getContentPane(), "LoginUser");
} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Container  c1= new Container();
		MonClient client = new MonClient();
		client.Connect();
		try {
			client.Select();
		} catch (IOException | CadTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
