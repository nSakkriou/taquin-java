package taquin.vue;

import taquin.model.*;
import taquin.controller.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
Fenetre de jeu ( du taquin ) jouable
 */
public class MainGame extends JFrame {

    /**Modele de jeu -> Grid */
    private Grid model;
    /**ArrayList permettant de stocker tout les boutons représentant les cases */
    private ArrayList<JButton> buttonList;
    private Control controller;
    JPanel contentPane;

    /** 
    Initialise la fenetre de jeu en fonction du modèle et l'affiche
    */
    public MainGame(Grid model){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);

        this.model = model;
        this.controller = new Control(model, this);

        this.contentPane = new JPanel();
        setContentPane(this.contentPane);
        this.contentPane.setLayout(new GridLayout(model.getRow(), model.getColumn()));

        this.buttonList = new ArrayList<>();
    
        initButton();
        positionButton();

        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this.controller);

        setVisible(true);
    }

    /** 
        Crée les boutons avec leurs numéros
        les desactives si il ne sont pas concerné par les déplacements possibles
        ou leurs ajoute un actionListener pour récuperer la saisie

        et les stocks dans buttonList
    */
    public void initButton(){
        for (int i : this.model.getTabToRow()){
            JButton btn = new JButton(String.valueOf(i));

            ArrayList<ArrayList<Integer>> moves = this.model.getPossibleMove();
            ArrayList<String> idMoves = new ArrayList<>();

            for(ArrayList<Integer> move : moves){
                idMoves.add(String.valueOf(move.get(0)));
            }

            if(!idMoves.contains(btn.getText())){
                btn.setEnabled(false);
            }
            else {
                btn.addActionListener((e) -> this.controller.actionButton(e));
            }

            this.buttonList.add(btn);
        }
    }

    /** 
        Place les éléments dans l'ordre, grâce au layout Grid
    */
    public void positionButton(){
        for(JButton btn : this.buttonList){
            this.contentPane.add(btn);
        }
    }

}