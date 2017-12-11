class Keen extends Program {
    final String PROMPT = ">$: ";

    int TAILLE = 4;

    int[][] grille  = new int[TAILLE][TAILLE];
    int[][] arene   = new int[TAILLE][TAILLE];

    boolean[]   helper  = new boolean[TAILLE*TAILLE];

    Bloc[] tabBlocs;

    String[]  bordure = new String[]{"0123", "123", "013", "012", "023" , "01", "02", "03", "12", "13", "23", "1", "2", ""};

    // O: top, 1: right, 2: bottom, 3: left

    void initialize(){
        // initialiser une grille de suduku valide
        // initialiser une grille de contraintes ou le jeu se deroule
        creerBloc();
        //creerGrille();
        //creerArene();
    }


    int piocher(){
        return (int)(random()*14);
    }

    void creerBloc(){
        for(int i = 0; i < length(helper); i++){
            helper[i] = true;
        }
        tabBlocs = new Bloc[TAILLE*TAILLE];
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            for(int idxC = 0; idxC < length(grille, 2); idxC++){
                int indice = piocher();
                println(bordure[indice]);
            }
        }
    }

    /*void creerGrille(){
        for(int idxL; idxL < length(grille, 1); idxL++){
            for(int idxC; idxC < length(grille, 2); idxC++){
                int valeur;
                do{
                    valeur = (int)(random*length(grille,1)+1);
                }while(!possible(idxL, idxC, valeur));
                grille[idxL][idxC] = valeur;
            }
        }
    }

    boolean possible(int row, int col, int val){
        return (inArrayRow(row, value) || inArrayCol(col,value));
    }

    boolean inArrayRow(int row, int value){
        for(int idxC = 0; idxC < length(grille, 1); idxC++){
            if(grille[row][idxC] == value) return true;
        }
        return false;
    }


    boolean inArrayCol(int col, int value){
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            if(grille[idxL][col] == value) return true;
        }
        return false;
    }*/

    void algorithm(){
        initialize();
        /*while(!victoire()){
            printGrid();
            print("case "+PROMPT);
            int cellule = readInt();
            int row = (cellule - 1)/length(grille,1);
            int col = (cellule - 1) % length(grille,2);
            print("nombre "+PROMPT);
            int nombre = readInt();
            poser(nombre, row, col);
        }
        printGrid();*/
        println("Bravo, vous avez gagné");
    }

}

class Coordonnee {
    int abscisse;
    int ordonnee;
}

class Contrainte {
    int nombre;
    char operateur;
}

class Bloc{
    Contrainte contrainte;
    Coordonnee[] coordonnees;
    String color;
}
