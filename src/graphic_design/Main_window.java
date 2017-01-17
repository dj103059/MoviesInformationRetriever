//current package
package graphic_design;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

//import for graphism

//Actions
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Components
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Main_window extends JFrame implements ActionListener {

	//intern class: each instance increment the attribut compteur
	public static class Compteur {
		public static int compteur = 0;

		public Compteur() {
			compteur++;
		}

		public int getCompteur(){
			return compteur;
		}		  

	}

	/******ATTRIBUTS******/

	private static final long serialVersionUID = 1L;

	/*Component the window*/
	protected JButton yes = new JButton("Yes");
	protected JButton no = new JButton("No");
	protected JButton noanswer = new JButton("I don't know");
	protected JLabel question = new JLabel();

	//On cree un JPanel
	private JPanel pan = new JPanel();


	/******CONSTRUCTORS******/

	/**
	 * Instantiates a new Main_window.
	 */
	public Main_window(){

		question.setText("Are you searching an actor ?");

		/***Set the window***/
		//Define a title to the window
		this.setTitle("Main window");
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

		//on place tous les ecouteurs pour que chaque composants puisse ecouter les interactions.

		yes.addActionListener((ActionListener) this);
		no.addActionListener((ActionListener) this);
		noanswer.addActionListener((ActionListener) this);

		this.setVisible(true);//on rend la fenetre visible
	}

	//on implemente la methode actionPerformed permettant de definir le comportement des CheckBox, et du bouton de confirmation(dans l'onglet: Mon panier).
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==yes){//We implement the action of the button yes
			System.out.println("User say: yes");
		}

		if(e.getSource()==no){//We implement the action of the button yes
			System.out.println("User say: no");
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

	public static void main(String[] args) {
		Main_window fen = new Main_window();
	}

}
