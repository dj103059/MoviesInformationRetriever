package graphisme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Main_window extends JFrame implements ActionListener,DocumentListener,FocusListener,KeyListener {


	private static final long serialVersionUID = 1L;

	/*Component for the second tab*/
	
	protected ButtonGroup bg = new ButtonGroup();
	protected JRadioButton jbr1 = new JRadioButton("Yes");
	protected JRadioButton jbr2 = new JRadioButton("No");
	protected JButton confirm = new JButton("OK");


	//A component that lets the user switch between a group of components by clicking on a tab with a given title and/or icon
	/** The onglet. */
	private JTabbedPane onglet;
	
	/*Component for the second tab*/

	/** The coche21. */
	protected JCheckBox coche21=new JCheckBox() ;

	/** The coche22. */
	protected JCheckBox coche22=new JCheckBox() ;

	//On cr�e des zones de saisie
	/** The saisie11. */
	protected JTextField saisie11=new JTextField("0",3);

	/** The saisie12. */
	protected JTextField saisie12=new JTextField("0",4);

	/** The saisie13. */
	protected JTextField saisie13=new JTextField("0",3);

	/** The saisie21. */
	protected JTextField saisie21=new JTextField("0",3);

	/** The saisie22. */
	protected JTextField saisie22=new JTextField("0",4);


	//On cree un JPanel
	/** The t pan. */
	private JPanel[] tPan = {new JPanel(), new JPanel()};


	/**
	 * Instantiates a new fenetre.
	 */
	public Main_window(){

		//D�finit un titre pour notre fen�tre
		this.setTitle("Main window");
		//D�finit sa taille : 1000 pixels de large et 400 pixels de haut
		this.setSize(1000, 400);
		//Nous demandons maintenant � notre objet de se positionner au centre.
		this.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Emp�che le redimensionnement
		this.setResizable(false);

		this.setAlwaysOnTop(false);
		
		//Creation de notre conteneur d'onglets
		onglet = new JTabbedPane();
		int i = 0;
		String tab[]={"First tab","Second tab"};
		for(JPanel pan : tPan){
			//M�thode d'ajout d'onglet
			onglet.add(tab[i++], pan);
			//Vous pouvez aussi utiliser la m�thode addTab :
			//onglet.addTab("Onglet n� "+(++i), pan);
		}
		//On passe ensuite les onglets au content pane
		this.getContentPane().add(onglet);

		//////////////////////////////////////////////////////////////////////

		//mise en forme de l'onglet: First tab
		//On cree un conteneur avec gestion horizontale
		Box b1 = Box.createHorizontalBox();

		b1.add(new JLabel("Are you searching an actor ?"));

		Box b2 = Box.createHorizontalBox();
		bg.add(jbr1);
		bg.add(jbr2);
		b2.add(jbr1);
		b2.add(jbr2);
		
		Box b3 = Box.createHorizontalBox();
		b3.add(new JLabel("\n\n"));
		
		Box b4 = Box.createHorizontalBox();
		b4.add(confirm);

		//On cree un conteneur avec gestion verticale
		Box b5 = Box.createVerticalBox();
		b5.add(b1);
		b5.add(b2);
		b5.add(b3);
		b5.add(b4);

		tPan[0].add(b5);


		//////////////////////////////////////////////////////////////////

		//mise en forme de l'onglet: Second tab
		//On cr�e un conteneur avec gestion horizontale
		Box b211 = Box.createHorizontalBox();
		b211.add(coche21);
		b211.add(new JLabel("--------------Le fil des possibles, 2015, 14, Espace art concret, Mouans Sartoux "));
		b211.add(saisie21);
		saisie21.setEditable(false);
		//Idem
		Box b221 = Box.createHorizontalBox();
		b221.add(coche22);
		b221.add(new JLabel("------------------------------------De Giotto � Caravage, 2015, 321, Mus�e Jacquemard, Paris "));
		b221.add(saisie22);
		saisie22.setEditable(false);

		//On cr�e un conteneur avec gestion verticale
		Box b241 = Box.createVerticalBox();
		b241.add(b211);
		b241.add(b221);

		tPan[1].add(b241);

		//////////////////////////////////////////////////////////////


		//on place tous les ecouteurs pour que chaque composants puisse ecouter les interactions.
		coche21.addActionListener((ActionListener) this);
		coche22.addActionListener((ActionListener) this);
		saisie11.getDocument().addDocumentListener(this); //il faut mettre getdocument pour �couter un �v�nement de type Document!
		saisie12.getDocument().addDocumentListener(this); //idem
		saisie13.getDocument().addDocumentListener(this); //idem
		saisie21.getDocument().addDocumentListener(this); //idem
		saisie22.getDocument().addDocumentListener(this); //idem
		saisie11.addKeyListener((KeyListener) this);
		saisie12.addKeyListener((KeyListener) this);
		saisie13.addKeyListener((KeyListener) this);
		saisie21.addKeyListener((KeyListener) this);
		saisie22.addKeyListener((KeyListener) this);
		saisie11.addFocusListener((FocusListener) this);
		saisie12.addFocusListener((FocusListener) this);
		saisie13.addFocusListener((FocusListener) this);
		saisie21.addFocusListener((FocusListener) this);
		saisie22.addFocusListener((FocusListener) this);
		
		jbr1.addActionListener((ActionListener) this);
		jbr2.addActionListener((ActionListener) this);
		confirm.addActionListener((ActionListener) this);

		this.setVisible(true);//on rend la fenetre visible
	}

	//on implemente la methode actionPerformed permettant de definir le comportement des CheckBox, et du bouton de confirmation(dans l'onglet: Mon panier).
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jbr1){//on impl�mente l'action du coche21(CheckBox2.1)
			System.out.println("User say yes");
		}
		
		if(e.getSource()==jbr2){//on impl�mente l'action du coche21(CheckBox2.1)
			System.out.println("User say no");
		}
		
		if(e.getSource()==confirm){//on impl�mente l'action du coche21(CheckBox2.1)
			System.out.println("User press ok");
		}
		

		if(e.getSource()==coche21){//on impl�mente l'action du coche21(CheckBox2.1)
			System.out.println("action sur la case 2.1");
			if(coche21.isSelected()){
				saisie21.setEditable(true);}
			else{
				saisie21.setText("0");
				saisie21.setEditable(false);
			}
		}
		if(e.getSource()==coche22){//on impl�mente l'action du coche22(CheckBox2.2)
			System.out.println("action sur la case 2.2");
			if(coche22.isSelected()){
				saisie22.setEditable(true);}
			else{
				saisie22.setText("0");
				saisie22.setEditable(false);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	//les attributs nbPlaceAReserver sont chang�s en temps r�el quand on ins�re des caract�res dans les boites de dialogue, tout comme l'onglet: Mon panier.
	public void insertUpdate(DocumentEvent arg0) {

	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	//idem que la m�thode ci-dessus mais quand on enl�ve des caract�res.
	public void removeUpdate(DocumentEvent arg0) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("focus sur saisie");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("perte focus saisie:(validation saisie)");

	}



//	public static void main(String[] args) {
//		Main_window fen = new Main_window();
//	}

}
