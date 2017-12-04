class Keen extends Program {
    final String PROMPT = ">$: ";

    int TAILLE;
    int[][] grille = new int[TAILLE][TAILLE];
    Bloc[] tabBlocs;

    void initialize(){
        for(int idxL; idxL < length(grille, 1); idxL++){
            for(int idxC; idxC < length(grille, 2); idxC++){
                
            }
        }
    }

    void algorithm(){
        initialize();
        while(!victoire()){
            printGrid();
            print("case "+PROMPT);
            int cellule = readInt();
            int row = (cellule - 1)/length(grille,1);
            int col = (cellule - 1) % length(grille,2);
            print("nombre "+PROMPT);
            int nombre = readInt();
            poser(nombre, row, col);
        }
        printGrid();
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
}
