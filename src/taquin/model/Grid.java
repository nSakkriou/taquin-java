package taquin.model;

import java.util.*;
import java.io.*;

/** 
    Classe de la grille de jeu
*/
public class Grid{
    
    private int row, column, nbElem;
    private ArrayList<Integer> elem = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> tab = new ArrayList<>();

    /** 
        Initialise la grille en fonction de la taille passé en arguments
    */
    public Grid(int row, int column){
        this.row = row;
        this.column = column;
        initGrid();
    }

    /** 
        Initialise une grille de 3x3
    */
    public Grid(){
        this.row = 3;
        this.column = 3;
        initGrid();
    }

    // Getter --------------

    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
    public int getNbElem(){
        return this.nbElem;
    }
    public ArrayList<Integer> getElem(){
        return this.elem;
    }  
    public ArrayList<ArrayList<Integer>> getTab(){
        return this.tab;
    }

    // Methods -------------
    /**
        Créer tout les élément du tanquin et les stocks dans une ArrayList
    */
    public void loadElem(){  
        this.nbElem = row*column-1;
        for(int i = 0; i < this.nbElem; i++){
            this.elem.add(i+1);
        }
    }

    /** 
        Mélange tout les éléments 
    */
    public void shuffleElem(){
        Collections.shuffle(this.elem);
    }  

    /**
        On créer la matrice en fonction des dimensions passé dans le constructeur
    */
    public void initTab(){
        int count = 0;
        for(int i = 0; i < this.row; i ++){

            ArrayList<Integer> arrayRow = new ArrayList<>();
            
            for(int j = 0; j < this.column; j++){
                arrayRow.add(elem.get(count));

                count ++;
            }
            this.tab.add(arrayRow);
        }
    }

    /**
        On effectue dans l'ordre toutes les actions nécessaires pour créer une matrice mélangée
        On y ajoute également la case vide, qui aura l'id 0
        Elle sera toujours placée en bas à droite
    */
    public void initGrid(){
        loadElem();
        shuffleElem();
        this.elem.add(0);
        initTab();
    }

    /** 
        Retourne le couple de coordonnée de la case vide
    */
    public ArrayList<Integer> getEmptyTile(){
        ArrayList<Integer> couple = new ArrayList<>();

        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.column; j++){
                if(this.tab.get(i).get(j).equals(0)){
                    couple.add(i);
                    couple.add(j);
                    break;
                }
            }
        }

        return couple;
    }

    /**
        Un déplacement est représenté comme :
            - Une ArrayList avec 5 valeurs :
                1) id de la case se déplaçant
                2) coordonnée i de cette case (Ligne)
                3) coordonnée j de cette case (Colonne)
                4) coordonnée i de la case vide (Ligne)
                5) coordonnée j de la case vide (Colonne)

        Cette fonction renvoi Une liste contenant tout les déplacements possible
    */
    public ArrayList<ArrayList<Integer>> getPossibleMove(){

        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        // Position cellule vide
        ArrayList<Integer> emptyTile = getEmptyTile();
        int iCoordEmpty = emptyTile.get(0);
        int jCoordEmpty = emptyTile.get(1);

        // Calcul des moves par rapport à la case vide
        // Au maximum 4 possibilités en partant de la case vide
        // i + 1 | i - 1 | j + 1 | j - 1
        if(iCoordEmpty + 1 < this.column){
            ArrayList<Integer> firstMove = new ArrayList<>(
                Arrays.asList(
                    this.tab.get(iCoordEmpty + 1).get(jCoordEmpty),
                    iCoordEmpty + 1,
                    jCoordEmpty,
                    iCoordEmpty,
                    jCoordEmpty
            ));

            moves.add(firstMove);
        }
        if(iCoordEmpty - 1 >= 0){
            ArrayList<Integer> secondMove = new ArrayList<>(
                Arrays.asList(
                    this.tab.get(iCoordEmpty - 1).get(jCoordEmpty),
                    iCoordEmpty - 1,
                    jCoordEmpty,
                    iCoordEmpty,
                    jCoordEmpty
            ));

            moves.add(secondMove);
        }

        if(jCoordEmpty + 1 < this.row){
            ArrayList<Integer> thirdMove = new ArrayList<>(
                Arrays.asList(
                    this.tab.get(iCoordEmpty).get(jCoordEmpty + 1),
                    iCoordEmpty,
                    jCoordEmpty + 1,
                    iCoordEmpty,
                    jCoordEmpty
            ));

            moves.add(thirdMove);
        }
        if(jCoordEmpty - 1 >= 0){
            ArrayList<Integer> fourthMove = new ArrayList<>(
                Arrays.asList(
                    this.tab.get(iCoordEmpty).get(jCoordEmpty - 1),
                    iCoordEmpty,
                    jCoordEmpty - 1,
                    iCoordEmpty,
                    jCoordEmpty
            ));

            moves.add(fourthMove);
        }

        return moves;
    }

    /**
        Prend en entré, un move (qui provient de getPossibleMove)
        Et déplace transpose la case concernée avec la case vide
    */
    public void doMove(ArrayList<Integer> move){
        int valueCase = move.get(0);
        int i = move.get(1);
        int j = move.get(2);

        int iVoid = move.get(3);
        int jVoid = move.get(4);
        
        this.tab.get(iVoid).set(jVoid, valueCase);

        this.tab.get(i).set(j, 0);
    }

    /**
        Méthode permettant de savoir si la figure est complétée ou non
        Oui -> true
        Non -> false
    */
    public boolean isValid(){

        int count = 1;
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.column; j++){
                if(count == this.nbElem){
                    break;
                }
                if(!this.tab.get(i).get(j).equals(count)){
                    return false;
                }

                count ++;
            }
        }

        return true;
    }

    /**
    Méthodes uniquement utile pour une utilisation en ligne de commande
    Elles permettent de d'interagir avec la matrice
    */

    /**
        Affichage de la matrice de jeu
    */  
    public void showTab(){
        
        for(int i = 0; i < this.row; i++){
            ArrayList<Integer> arrayRow = this.tab.get(i);
            String text = "";
            for(int j = 0; j < arrayRow.size(); j++){
                text += " " + arrayRow.get(j);
            }
            System.out.println(text);
        }
    }

    /** 
        Affiche en console les déplacements possibles
    */
    public void showPossibleMove(){
        ArrayList<ArrayList<Integer>> moves = getPossibleMoveDirection();
        int count = 0;

        String[] corespondance = new String[] {"s", "z", "d", "q"};

        System.out.println("\nVoici les deplacement licites : ");

        for(int i = 0; i < 4; i++){
            if( moves.get(i).get(0) != null){
                System.out.println(corespondance[i] + " | Deplacer [ " + moves.get(i).get(0) + " ]");
            }
        }
    }

    /** 
        Propose les choix possibles à l'utillisateur
    */
    public ArrayList<Integer> selectPossibleMove(){
        System.out.println("\nFaites votre choix en entrant le numero de la proposition : ");
        Scanner input = new Scanner(System.in);

        String id = input.nextLine();
        ArrayList<ArrayList<Integer>> moves = getPossibleMoveDirection();

        if(id.equals("z")){
            if(moves.get(1).get(0) == null){
                System.out.println("Entrez une valeur correcte !");
            }
            else{
                return moves.get(1);
            }
        }
        else if(id.equals("s")){
            if(moves.get(0).get(0) == null){
                System.out.println("Entrez une valeur correcte !");
            }
            else{
                return moves.get(0);
            }
            
        }
        else if(id.equals("q")){
            if(moves.get(3).get(0) == null){
                System.out.println("Entrez une valeur correcte !");
            }
            else{
                return moves.get(3);
            }
            
        }
        else if(id.equals("d")){
            if(moves.get(2).get(0) == null){
                System.out.println("Entrez une valeur correcte !");
            }
            else{
                return moves.get(2);
            }
            
        }
        System.out.println("Entrez une valeur correcte !");
        ArrayList<Integer> nullMove = new ArrayList<>(Arrays.asList(9999));
        return nullMove;
    }

    /** 
        Nettoie la console pour plus de visibilité
    */
    public void clearConsole(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch(Exception e){
        }
    }

    /** 
        Lance une partie dans le terminal
    */
    public void terminalGame(){
        
        clearConsole();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(!isValid()){
            showTab();
            showPossibleMove();
            ArrayList<Integer> move = selectPossibleMove();
            if(move.get(0) != 9999){
                doMove(move);
            }
            clearConsole();
        }

        System.out.println("Bravo !!!");
    }

    /** 
        Méthode utile pour facilité les vues
    */
    /** 
        On récupére dans une liste la matrice
    */
    public ArrayList<Integer> getTabToRow(){
        ArrayList<Integer> arrayRow = new ArrayList<>();

        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.column; j++){
                arrayRow.add(this.tab.get(i).get(j));
            }
        }

        return arrayRow;
    }

    /**
        Comme getPossibleMove mais sur celle ci, la liste des mouvements sera toujours composé
        de 4 mouvements
        Si un mouvement n'existe pas, il sera juste null

        Cela va nous permettre de connaître la position des cases par rapport 
        a la case vide

        id :
            0 = SUD
            1 = NORD
            2 = EST
            3 = OUEST
    */
    public ArrayList<ArrayList<Integer>> getPossibleMoveDirection(){

        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();

        ArrayList<Integer> emptyTile = getEmptyTile();
        int iCoordEmpty = emptyTile.get(0);
        int jCoordEmpty = emptyTile.get(1);

        ArrayList<Integer> nullMove = new ArrayList<>(Arrays.asList(null, null, null, null, null));

        // Calcul des moves par rapport à la case vide
        // Au maximum 4 possibilités en partant de la case vide
        // i + 1 | i - 1 | j + 1 | j - 1
        if(iCoordEmpty + 1 < this.column){
            ArrayList<Integer> firstMove = new ArrayList<>(
                Arrays.asList(
                    this.tab.get(iCoordEmpty + 1).get(jCoordEmpty),
                    iCoordEmpty + 1,
                    jCoordEmpty,
                    iCoordEmpty,
                    jCoordEmpty
            ));

            moves.add(firstMove);
        }
        else{
            moves.add(nullMove);
        }

        if(iCoordEmpty - 1 >= 0){
            ArrayList<Integer> secondMove = new ArrayList<>(
                Arrays.asList(
                    this.tab.get(iCoordEmpty - 1).get(jCoordEmpty),
                    iCoordEmpty - 1,
                    jCoordEmpty,
                    iCoordEmpty,
                    jCoordEmpty
            ));

            moves.add(secondMove);
        }
        else{
            moves.add(nullMove);
        }

        if(jCoordEmpty + 1 < this.row){
            ArrayList<Integer> thirdMove = new ArrayList<>(
                Arrays.asList(
                    this.tab.get(iCoordEmpty).get(jCoordEmpty + 1),
                    iCoordEmpty,
                    jCoordEmpty + 1,
                    iCoordEmpty,
                    jCoordEmpty
            ));

            moves.add(thirdMove);
        }
        else{
            moves.add(nullMove);
        }

        if(jCoordEmpty - 1 >= 0){
            ArrayList<Integer> fourthMove = new ArrayList<>(
                Arrays.asList(
                    this.tab.get(iCoordEmpty).get(jCoordEmpty - 1),
                    iCoordEmpty,
                    jCoordEmpty - 1,
                    iCoordEmpty,
                    jCoordEmpty
            ));

            moves.add(fourthMove);
        }
        else{
            moves.add(nullMove);
        }

        return moves;
    }
}