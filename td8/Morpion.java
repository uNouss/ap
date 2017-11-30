class Morpion extends Program {
    final int TAILLE = 3;
    final char O = 'O';
    final char X = 'X';
    final char V = '_';

    final String PROMPT = "$: ";

    char [][] grille = new char[TAILLE][TAILLE];

    void testInit(){
        initialize();
        char[][] grilleVide = new char[][]{
            {V, V, V},
                {V, V, V},
                {V, V, V}
        };
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
        assertTrue(isAlignement());

        initialize();
        set(0,2,O);
        set(1,1,O);
        set(2,0,O);
        assertTrue(isAlignement());

        initialize();
        set(0,2,O);
        set(0,1,O);
        set(0,0,O);
        assertTrue(isAlignement());

        initialize();
        set(0,2,O);
        set(1,2,O);
        set(2,2,O);
        assertTrue(isAlignement());

        initialize();
        set(0,2,O);
        set(1,2,X);
        set(2,2,O);
        assertFalse(isAlignement());
    }

    boolean isAlignement(){
        // 1er cas: j'ai la même chose sur la 1ere diagone
        if(grille[0][0] != V && grille[0][0] == grille[1][1] && grille[1][1] == grille[2][2]){
            return true;
        }
        // 2eme cas: j'ai la même chose sur la deuxième diagonale
        if(grille[0][2] != V && grille[0][2] == grille[1][1] && grille[1][1] == grille[2][0]){
            return true;
        }
        // 3eme cas: même chose sur même ligne et colonne differente
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            if(grille[idxL][0] != V && grille[idxL][0] == grille[idxL][1] && grille[idxL][1] == grille[idxL][2])
                return true;
        }
        // 4eme cas: même chose sur ligne differente et même colonne
        for(int idxC = 0; idxC < length(grille, 2); idxC++){
            if(grille[0][idxC] != V && grille[0][idxC] == grille[1][idxC] && grille[1][idxC] == grille[2][idxC])
                return true;
        }

        return false;
    }


    void testIsFilled(){
        initialize();
        char[][] uneGrille = new char[][]{
            {X, O, X},
                {O, O, X},
                {O, X, O}
        };
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
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            for(int idxC = 0; idxC < length(grille, 2); idxC++){
                print(grille[idxL][idxC] + " ");
            }
            println();
        }
    }

    // verification de la valider du jeu
    boolean isValid(int row, int col){
        return row  >= 0 && row < length(grille, 1) && col >= 0 && col < length(grille, 2) && grille[row][col] == V;
    }

    void jouer(char player){
        int row;
        int col;
        do{
            print(player + " , donner un numero de case entre 1 et " + (TAILLE*TAILLE) + " : ");
            int numCase = readInt();// utiliser la division et le modulo pour trouver les coordonnées
            row = (numCase - 1)/length(grille, 1);
            col = (numCase - 1) % length(grille, 1);
            //est-ce que je peux jouer à cette case ?
        }while(!isValid(row, col));
        set(row, col, player);
    }

    void algorithm(){
        initialize();
        char player = O;
        do{
            printGrid();
            /*println("au tour de "+player+ ":");
              print(PROMPT);
              int row = readInt();
              print(PROMPT);
              int col = readInt();*/
            jouer(player);
            player = swap(player);
        }while(!isFilled() && ! isAlignement());
        printGrid();
        if(isAlignement()) println(swap(player) + " gagne"); // il faut swaper pour avoir le bon gagnant ?
        else println("match nul");
    }
}
