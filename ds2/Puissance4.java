class Puissance4 extends Program{
    final char O = 'O';
    final char X = 'X';
    final char V = ' ';

    final boolean JOUEUR1 = true;
    final boolean JOUEUR2 = false;

    boolean joueurCourant = ((int)(random()*2) == 1)? JOUEUR1: JOUEUR2;

    char[][] grille = new char[6][7];

    void initGrille(){
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            for(int idxC = 0; idxC < length(grille, 2); idxC++){
                grille[idxL][idxC] = V;
            }
        }
    }



    void printGrille(){
        afficherEntete(length(grille, 2));
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            afficheSeparateur(length(grille, 2));
            for(int idxC = 0; idxC < length(grille, 2); idxC++){
                print(String.format("|%3s", grille[idxL][idxC]));
            }
            println("| ");
        }
        afficheSeparateur(length(grille,2));
        //println("+---");
    }

    void afficherEntete(int n){
        for (int i = 0; i < n; i++){
            print("  "+(i+1)+" ");
        }
        println(" ");
    }
    void afficheSeparateur(int n) {
        for (int i = 0; i < n; i++){
            print("+---");
        }
        println("+");
    }



    void algorithm(){
        initGrille();
        //while(!bloque() && !victoire()){ // on joue
            printGrille(); //afficher grille
            // le joueurCourant joue;
            // il choisie sa colonne entre 1 et 7
            // si c'est possible le coup, tout est deplacer vers le bas
            // et on change de joueur
        //}
    }
}
