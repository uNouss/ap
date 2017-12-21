class Keen extends Program {
    final String PROMPT = ">$: ";

    int TAILLE = 7;

    int[][] grille  = new int[TAILLE][TAILLE];
    int[][] arene   = new int[TAILLE][TAILLE];

    boolean[]   helper  = new boolean[TAILLE*TAILLE];

    Bloc[] tabBlocs;

    String[]  bordure = new String[]{"0123", "123", "013", "012", "023" , "01", "02", "03", "12", "13", "23", "1", "2", ""};

    // O: top, 1: right, 2: bottom, 3: left



    void swapRow(int r1, int r2){
        int[] tmp = grille[r1];
        grille[r1] = grille[r2];
        grille[r2] = tmp;
    }

    int[] copyCol(int col){
        int[] tab = new int[length(grille, 1)];
        for(int l = 0; l < length(grille, 1); l++){
            tab[l] = grille[l][col];
        }
        return tab;
    }

    void swapCol(int c1, int c2){
        int[] tmp = copyCol(c1);
        for(int l = 0; l < length(grille, 1); l++){
            grille[l][c1] = grille[l][c2];
        }
        for(int l = 0; l < length(grille, 1); l++){
            grille[l][c2] = tmp[l];
        }
    }


    void initSuduku(){
        for(int l = 0; l < length(grille, 1); l++){
            for(int c = 0; c < length(grille, 2); c++){
                grille[l][c] = ((l+c)%length(grille, 1)) + 1;
            }
        }
        //int idxL1 = (int)(random()*length(grille, 1));
        //int idxL2 = (int)(random()*length(grille, 1));
        // faire une boucle pour echanger des lignes et des colonnes un certain nombre de fois.
        //int l1 = getUniqueRow(length(grille, 1));
        //int l2 = getUniqueRow(length(grille, 1));
        displayGrid();
        println();
        println();
        swapRow(1, 5);
        swapRow(0,2);
        swapRow(3,4);
        swapCol(0,3);
        swapCol(2,4);
        swapCol(1,6);
        displayGrid();
        /*echangerColonne(c1, c2);
        int[] tmp;
        tmp = grille[1];
        grille[1] = grille[6];
        grille[6] = tmp;
        tmp = grille[3];
        grille[3] = grille[4];
        grille[4] = tmp;
        tmp = grille[0];
        grille[0] = grille[5];
        grille[5] = tmp;
         */
    }

    void displayGrid(){
        // pour l'affichage à mettre dans une nouvelle fonction
        print("   ");
        for(int i = 0; i < length(grille, 1); i++){
            print((i+1)+" ");
        }
        println();
        for(int i = 0; i < length(grille, 1)*2+3; i++){
            print("-");
        }
        println();
        char car = 'A';
        for(int l = 0; l < length(grille, 1); l++){
            print(car+"| ");
            car += 1;
            for(int c = 0; c < length(grille, 2); c++){
                print(grille[l][c]+" ");
            }
            println();
        }
    }
    /*void initialize(){
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
        //initialize();
        initSuduku();
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
        //println("Bravo, vous avez gagné");
    }

}

class Coordonnee {
    int abscisse;
    int ordonnee;
}

class NoeudCoord{
    Coordonnee valeur;
    NoeudCoord suivant;
}


class Contrainte {
    int nombre;
    char operateur;
}

class Bloc{
    Contrainte contrainte;
    NoeudCoord listeCoordonees;
    String color;
}

