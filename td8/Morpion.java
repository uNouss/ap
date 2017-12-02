//import extensions.*;

class Morpion extends Program {
    final int TAILLE = 3;
    final char O = 'O';
    final char X = 'X';
    final char V = ' ';

    final String PROMPT = "$: ";

    char [][] grille = new char[TAILLE][TAILLE];

    void testInitalize(){
        initialize();
        char[][] grilleVide = new char[TAILLE][];
        for(int idxL = 0; idxL < length(grilleVide, 1); idxL++){
            grilleVide[idxL] = new char[length(grilleVide, 1)];
            for(int idxC = 0; idxC < length(grilleVide[idxL]); idxC++){
                grilleVide[idxL][idxC] = V;
            }
        }
        assertArrayEquals(grilleVide, grille);
    }

    void initialize(){
        for(int idxL = 0; idxL < length(grille, 1); idxL++)
            for(int idxC = 0; idxC < length(grille, 2); idxC++)
                grille[idxL][idxC] = V;
    }

    void testSet(){
        set(1, 2, O);
        assertEquals(O, grille[1][2]);
        set(1, 1, X);
        assertEquals(X, grille[1][1]);
        set(2, 2, O);
        assertEquals(O, grille[2][2]);
    }

    void set(int idxL, int idxC, char car){
        grille[idxL][idxC] = car;
    }

    void testIsAlignement(){
        initialize();
        set(0,0,X);
        set(1,1,X);
        set(2,2,X);
        //println();printGrid();
        assertTrue(isAlignement());

        initialize();
        set(0,2,O);
        set(1,1,O);
        set(2,0,O);
        //println();printGrid();
        assertTrue(isAlignement());

        initialize();
        set(0,2,O);
        set(0,1,O);
        set(0,0,O);
        //println();printGrid();
        assertTrue(isAlignement());

        initialize();
        set(0,2,O);
        set(1,2,O);
        set(2,2,O);
        //println();printGrid();
        assertTrue(isAlignement());

        initialize();
        set(0,2,O);
        set(1,2,X);
        set(2,2,O);
        //println();printGrid();
        assertFalse(isAlignement());
    }

    boolean isAlignement(){
        char current, next = V, nextNext = V;
        for(int idxL = 0; idxL < length(grille,1); idxL++){
            for(int idxC = 0; idxC < length(grille,2); idxC++){
                current = grille[idxL][idxC];
                if(current != V){
                    if(idxL+1 < length(grille, 1)){
                        next = grille[idxL+1][idxC];
                        if(idxL+2 < length(grille, 1)){
                            nextNext = grille[idxL+2][idxC];
                            if( current == next && next == nextNext) return true;
                        }
                    }
                    if(idxC+1 < length(grille, 2)){
                        next = grille[idxL][idxC+1];
                        if(idxC+2 < length(grille, 2)){
                            nextNext = grille[idxL][idxC+2];
                            if( current == next && next == nextNext) return true;
                        }
                    }
                    if(idxL+1 < length(grille,1) && idxC+1 < length(grille, 2)){
                        next = grille[idxL+1][idxC+1];
                        if(idxL+2 < length(grille, 1) && idxC+2 < length(grille, 2)){
                            nextNext = grille[idxL+2][idxC+2];
                            if( current == next && next == nextNext) return true;
                        }
                    }
                    if(idxL+1 < length(grille,1) && idxC-1 >= 0){
                        next = grille[idxL+1][idxC-1];
                        if(idxL+2 < length(grille,1) && idxC-2 >= 0){
                            nextNext = grille[idxL+2][idxC-2];
                            if( current == next && next == nextNext) return true;
                        }
                    }
                    //if( current == next && next == nextNext) return true;
                }
            }
        }
        return false;
    }


    void testIsFilled(){
        initialize();
        char[][] uneGrille = new char[TAILLE][];
        for(int idxL = 0; idxL < length(uneGrille, 1); idxL++){
            uneGrille[idxL] = new char[length(uneGrille, 1)];
            for(int idxC = 0; idxC < length(uneGrille[idxL]); idxC++){
                uneGrille[idxL][idxC] = ((int)(random()*2)==1)?O:X;
            }
        }
        grille = uneGrille;
        println();printGrid();
        assertTrue(isFilled());
        initialize();
        assertFalse(isFilled());
        set(2,0,O);
        assertFalse(isFilled());
        grille = uneGrille;
        set(1,1,V);
        assertFalse(isFilled());
    }
    boolean isFilled(){
        for(int idxL = 0; idxL < length(grille, 1); idxL++)
            for(int idxC = 0; idxC < length(grille, 2); idxC++)
                if(grille[idxL][idxC] == V) return false;
        return true;
    }

    void testSwap(){
        assertEquals('X', swap('O'));
        assertEquals('O', swap('X'));
    }

    char swap(char c){
        return ( c == X) ? O: X;
    }

    void printGrid(){
        afficherEntete(length(grille, 2));
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            afficheSeparateur(length(grille, 2));
            for(int idxC = 0; idxC < length(grille, 2); idxC++){
                //print(grille[idxL][idxC] + " ");
                print(String.format("|%3s", grille[idxL][idxC]+" "));
            }
            println("| " + idxL);
        }
        afficheSeparateur(length(grille,1));
        println();
    }

    void afficherEntete(int n){
        for (int i = 0; i < n; i++){
            print("  "+i+" ");
        }
        println(" ");
    }
    void afficheSeparateur(int n) {
        for (int i = 0; i < n; i++){
            print("+---");
        }
        println("+");
    }
    // verification de la valider du jeu
    boolean isValid(int row, int col){
        return row  >= 0 && row < length(grille, 1) && col >= 0 && col < length(grille, 2) && grille[row][col] == V;
    }

    void jouer(char player){
        int row;
        int col;
        do{
            print("JOUEUR_[" + player + "] , num_case [1; " + (TAILLE*TAILLE) + "] : ");
            int numCase = readInt();// utiliser la division et le modulo pour trouver les coordonnées
            row = (numCase - 1)/length(grille, 1);
            col = (numCase - 1) % length(grille, 1);
            //est-ce que je peux jouer à cette case ?
        }while(!isValid(row, col));
        set(row, col, player);
    }

    void algorithm(){
        initialize();
        //Image ihm = newImage(640,640);
        char player = ((int)(random()*2)==1)?O:X; // tirage au sort du debutant
        do{
            printGrid();
            jouer(player);
            player = swap(player);
        }while(!isFilled() && ! isAlignement());
        printGrid();
        //show(ihm);
        //readString();
        if(isAlignement()) println(swap(player) + " gagne"); // il faut swaper pour avoir le bon gagnant ?
        else println("match nul");
    }

}
