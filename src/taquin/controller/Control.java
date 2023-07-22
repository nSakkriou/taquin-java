package taquin.controller;

import taquin.vue.*;
import taquin.*;
import taquin.model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

/** 
    Regroupe toutes les interactions vue - model
*/
public class Control implements KeyListener{

    private Grid model;
    private MainGame vue;

    public Control(Grid model, MainGame vue){
        this.model = model;
        this.vue = vue;
    }

    /** 
        Nous regardons si la postion est gagnante
        Grâce au bouton cliqué, nous remontons à la fenetre principale
        et nous ouvrons une pop up pour la victoire
        ou nous activons le mouvement
    */
    public void isWin(){
        if(this.model.isValid()){
            JOptionPane.showMessageDialog(this.vue, "Vous avez reussi !");
            this.vue.dispose();                
        }
        else{
            this.vue.dispose();                
            new MainGame(this.model);
        }  
    }

    // Action Game
    /** 
        Modifie le modele par rapport au choix de clic de l'utilisateur
            -> On vient récupérer le move correspondant au numéro de la case choisie
            -> On applique le move
        et on actualise la vue
    */
    public void actionButton(ActionEvent e){
        JButton btn = (JButton)e.getSource();

        int btnId = Integer.parseInt(btn.getText());

        ArrayList<ArrayList<Integer>> moves = this.model.getPossibleMove();

        for(int i = 0; i < moves.size(); i ++){
            if(moves.get(i).get(0) == btnId){
                this.model.doMove(moves.get(i));

                isWin();
            }
        }        
    }

    /** 
        Permet d'effectuer les mouvements de case grâce aux flèches directionnelles
    */
    @Override
    public void keyPressed(KeyEvent e){
        ArrayList<ArrayList<Integer>> moves = this.model.getPossibleMoveDirection();
        int keyCode = e.getKeyCode();

        switch(keyCode)
        {
            case KeyEvent.VK_LEFT:
                if(moves.get(3).get(0) != null){
                    this.model.doMove(moves.get(3));
                    isWin();
                }
                break;
            
            case KeyEvent.VK_Q:
                if(moves.get(3).get(0) != null){
                    this.model.doMove(moves.get(3));
                    isWin();
                }
                break;

            case KeyEvent.VK_RIGHT:
                if(moves.get(2).get(0) != null){
                    this.model.doMove(moves.get(2));
                    isWin();
                }
                break;

            case KeyEvent.VK_D:
                if(moves.get(2).get(0) != null){
                    this.model.doMove(moves.get(2));
                    isWin();
                }
                break;

            case KeyEvent.VK_UP:
                if(moves.get(1).get(0) != null){
                    this.model.doMove(moves.get(1));
                    isWin();
                }
                break;

            case KeyEvent.VK_Z:
                if(moves.get(1).get(0) != null){
                    this.model.doMove(moves.get(1));
                    isWin();
                }
                break;
                
            case KeyEvent.VK_DOWN:
                if(moves.get(0).get(0) != null){
                    this.model.doMove(moves.get(0));
                    isWin();
                }
                break;

            case KeyEvent.VK_S:
                if(moves.get(0).get(0) != null){
                    this.model.doMove(moves.get(0));
                    isWin();
                }
                break;
        }
    }

    // Méthodes définie mais à vide car l'interface en a besoin
    @Override
    public void keyTyped(KeyEvent e){
        
    }

    @Override
    public void keyReleased(KeyEvent e){
        
    }
}