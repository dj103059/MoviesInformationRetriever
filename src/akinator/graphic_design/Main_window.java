//current package
package akinator.graphic_design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;

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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import akinator.init.Initialisation;
import akinator.init.StoredComponent;
import akinator.sparqlEngine.filter.Filter;
import akinator.sparqlEngine.request.Request;
import akinator.sparqlEngine.translator.Translator;




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

	public int compteur = 0;

	java.util.ArrayList <StoredComponent> listStoredcomponent = new java.util.ArrayList <StoredComponent>();

	public Initialisation initialisation= new Initialisation(); //Load the file and the ontology 
	public Translator t =  new Translator();
	public Filter f = new Filter(); //instanciate a new filter to construct the filter of the query
	public Request r  = new Request(); //instanciate a translator to create the question in a natural language

	protected String questionString;

	boolean reponse;

	/** The yes button. */
	/*Components of the window*/
	protected JButton yes = new JButton("Yes");

	/** The no button. */
	protected JButton no = new JButton("No");

	/** The noanswer button. */
	protected JButton noanswer = new JButton("I don't know");

	/** The question JLabel. */
	protected JLabel question = new JLabel();
	
	protected JTextArea textPane = new JTextArea("Hello Master!", 21, 33);
		
	/** The pan JPanel. */
	//On create a JPanel
	private JPanel pan = new JPanel();
	
	protected int window_width; //width of the window
	
	protected int window_height; //height of the window


	/**
	 * ****CONSTRUCTORS*****.
	 */

	/**
	 * Instantiates a new Main_window.
	 */
	public Main_window(){
		
		window_width=this.getWidth();
	    window_height=this.getHeight();

		this.questionString = "Chargement...";

		listStoredcomponent.add(new StoredComponent("hasActor", "Orlando_Bloom", ""));
		listStoredcomponent.add(new StoredComponent("isTypeOf", "Pirate", ""));
		listStoredcomponent.add(new StoredComponent("wasReleasedIn", "2002", "rdfs"));

		//set the text of the JLabel (the first question)
		//question.setText(questionString);

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

	/******GETTERS AND SETTERS******/

	public String getQuestionString() {
		return questionString;
	}


	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}


	public boolean isReponse() {
		return reponse;
	}


	public void setReponse(boolean reponse) {
		this.reponse = reponse;
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
			reponse=true;
			
		}

		if(e.getSource()==no){//We implement the action of the button yes
			System.out.println("User say: no");
			reponse=false;
			
		}

		if(e.getSource()==noanswer){//We implement the action of the button noanswer
			System.out.println("User press: I don't know");
			

		}
		this.MainEngine(reponse);
	}

	public String setQuestion(String newQuestion){
		this.question.setText(newQuestion); 
		return newQuestion;
	}
	
	public void translateAndShow(){
		String question = t.translate(listStoredcomponent.get(compteur)); // use the storedcomponent to create the question in a natural language (PB: nullPointeurException, is the problem due to the issue in the load method ?)
		System.out.println(question);
		this.setQuestion(question);
	}
	
	public void MainEngine(boolean reponse){
		f.constructFilter(listStoredcomponent.get(this.compteur), reponse);
		r.addFilter(f);
		//System.out.println(r.getResult());
		this.compteur++;
		if(r.getResult()=="NONE"){
			this.translateAndShow();
		}
		else{
			this.showResult();
		}
			//System.out.println("final :"+r.getResult());
	}
	
	public void showResult(){
		r.setResult();
		String result = r.result;
		result = result.replace("<br/>","\n\nSummary: \n\n");
		result = result.replace("<html>","");
		result = result.replace("</html>","");
		pan.removeAll(); //remove all the components in the JPanel
		pan.setBounds(window_width,window_height, 400, 400); //Set the dimension of the JPanel
		textPane.setLineWrap(true); //Sets the line-wrapping policy of the text area. If set to true the lines will be wrapped if they are too long to fit within the allocated width.
		textPane.setText(result);//Set the text of the textPane with the comments of the film
		pan.add(new JScrollPane(textPane)); //add the scroll
		pan.setBackground(Color.BLUE);//Set the background color of the pan
		pan.validate();// Validate our new JPanel
		pan.repaint(); //redraw our new JPanel
		
		System.out.println(result); //print the result
		//enlever les boutons et le remplacer 
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
