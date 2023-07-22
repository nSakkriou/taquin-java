package taquin;

import taquin.model.*;
import taquin.vue.*;
import taquin.controller.*;

import java.util.*;
import java.io.*;

/** 
    Pour lancer le jeu dans le monde qu'on veut
*/
public class Main{
    public static void main(String[] args) throws IOException {

        System.out.println("Entrez 0 pour jouer dans le mode terminal, ou entrez n'importe qu'elle touche.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
        String input = reader.readLine();

        if(input.equals("0")){
            // Jeu en mode terminal
            Grid grid = new Grid();
            grid.terminalGame();
        }
        else{
            // Jeu en mode graphique
            new Window();
        }
    }
}