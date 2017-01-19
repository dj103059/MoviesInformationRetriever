//current package
package akinator.graphic_design;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

//imports for graphism

//Actions
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Components
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




/**
 * The Class Main_window.
 */
public class Main_window extends JFrame implements ActionListener {

	/**
	 * The Class Compteur.
	 */
	//intern class: each instance increment the attribut compteur
	public static class Compteur {
		
		/** The compteur. */
		public static int compteur = 0;

		/**
		 * Instantiates a new compteur.
		 */
		public Compteur() {
			compteur++;
		}

		/**
		 * Gets the compteur.
		 *
		 * @return the compteur
		 */
		public int getCompteur(){
			return compteur;
		}		  

	}

	/** ****ATTRIBUTS*****. */

	private static final long serialVersionUID = 1L;

	/** The yes button. */
	/*Components of the window*/
	protected JButton yes = new JButton("Yes");
	
	/** The no button. */
	protected JButton no = new JButton("No");
	
	/** The noanswer button. */
	protected JButton noanswer = new JButton("I don't know");
	
	/** The question JLabel. */
	protected JLabel question = new JLabel();

	/** The pan JPanel. */
	//On create a JPanel
	private JPanel pan = new JPanel();


	/**
	 * ****CONSTRUCTORS*****.
	 */

	/**
	 * Instantiates a new Main_window.
	 */
	public Main_window(){
		
		//set the text of the JLabel (the first question)
		question.setText("Is the film released in 2000 ?");

		/***Set the window***/
		//Define a title to the window
		this.setTitle("MovieRetriever");
		//Define the size of the window : 400 pixels width and 400 pixels height
		this.setSize(400, 400);
		//We now ask our window to position itself at the center.
		this.setLocationRelativeTo(null);
		//Complete the process when clicking on the red cross
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Prevents resizing
		this.setResizable(false);
		//The windows will be always on top 
		this.setAlwaysOnTop(false);


		//add the JPanel pan to the window
		this.getContentPane().add(pan);

	    //Centering the components in the window
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;


		//////////////////////////////////////////////////////////////////////
		//Formatting content of the window

		//Creating a Container with Horizontal Management
		Box b1 = Box.createHorizontalBox();
		b1.add(question);

		Box b2 = Box.createHorizontalBox();
		b2.add(new JLabel("<html><br><br></html>"));

		//Creating a Container with Horizontal Management
		Box b3 = Box.createHorizontalBox();
		b3.add(yes);
		b3.add(new JLabel("  "));
		b3.add(no);
		b3.add(new JLabel("  "));
		b3.add(noanswer);

		//Creating a Container with Vertical Management
		Box b4 = Box.createVerticalBox();
		b4.add(b1);
		b4.add(b2);
		b4.add(b3);


		pan.add(b4);

		//We put all the listeners so that each component can listen to the interactions.

		yes.addActionListener((ActionListener) this);
		no.addActionListener((ActionListener) this);
		noanswer.addActionListener((ActionListener) this);

		this.setVisible(true);//Make the window visible
	}
	
	
	/**
	 * ****METHODS*****.
	 *
	 * @param e the e
	 */

	//We implement the actionPerformed method to define the behavior of the buttons.
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==yes){//We implement the action of the button yes
			System.out.println("User say: yes");
			Compteur compteur = new Compteur();
			if(compteur.getCompteur()==1){
				question.setText("Are you searching a man ?");
			}
			else{
				question.setText("Other questions");
			}
		}

		if(e.getSource()==no){//We implement the action of the button yes
			System.out.println("User say: no");
			Compteur compteur = new Compteur();
			if(compteur.getCompteur()==1){
				question.setText("Are you searching a man ?");
			}
			else{
				question.setText("Other questions");
			}
		}

		if(e.getSource()==noanswer){//We implement the action of the button noanswer
			System.out.println("User press: I don't know");
			Compteur compteur = new Compteur();
			if(compteur.getCompteur()==1){
				question.setText("Are you searching a man ?");
			}
			else{
				question.setText("Other questions");
			}

		}

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
//	public static void main(String[] args) {
//		Main_window fen = new Main_window();
//	}

}
