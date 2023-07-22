package taquin.vue;

import taquin.vue.*;
import taquin.model.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
Fenêtre de choix de taille du taquin
 */
public class Window extends JFrame {

	private JPanel contentPane;

	JComboBox comboBox = new JComboBox();

	/**
	 * Constructeur qui initialise la fenetre
	*/
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);


		this.contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 1));
		
		displayText();


		setVisible(true);
	}

	/** 
		Créer et affiche tout les componsants graphiques
	*/
	public void displayText(){

		JLabel lblNewLabel_1 = new JLabel("Bienvenue : Jeux Taquin");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("By Yanis/Alan/Nathan");
		lblNewLabel_2.setForeground(Color.RED);
		this.contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("De quelle taille voulez-vous que soit votre carre de jeu : ");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.contentPane.add(lblNewLabel_3);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"3", "4", "5", "6", "7", "8", "9", "10"}));
		this.contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setToolTipText("");
		btnNewButton.setSelectedIcon(null);
		btnNewButton.setIcon(null);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setForeground(Color.RED);
		this.contentPane.add(btnNewButton);

		btnNewButton.addActionListener((e) -> readInput(e));
	}

	/** 
		Récupére le taille de la grille et lance le jeu
	*/
	public void readInput(ActionEvent e){
		int size = Integer.parseInt((String)comboBox.getSelectedItem());
		dispose();
		MainGame game = new MainGame(new Grid(size, size));
	}
}


