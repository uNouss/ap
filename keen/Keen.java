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
/*
 * ######################################################
 * #               Coordonnee                           #
 * ######################################################
*/

    void testCreerCoordonnee(){
        Coordonnee c = creerCoordonnee(2,3);
        assertEquals("(2,3)", toString(c));
    }

    void testToString(){
        Coordonnee c = creerCoordonnee(2,3);
        assertEquals("(2,3)", toString(c));
    }

    String toString(Coordonnee c){
        return "(" + c.abs + "," + c.ord + ")";
    }

    Coordonnee creerCoordonnee(int abs, int ord){
        Coordonnee c = new Coordonnee();
        c.abs = abs;
        c.ord = ord;
        return c;
    }

    void testGetAbscisse(){
        Coordonnee c = creerCoordonnee(1,0);
        assertEquals(1, getAbscisse(c));
    }

    int getAbscisse(Coordonnee c){
        return c.abs;
    }

    void testGetOrdonnee(){
        Coordonnee c = creerCoordonnee(1,0);
        assertEquals(0, getOrdonnee(c));
    }

    int getOrdonnee(Coordonnee c){
        return c.ord;
    }

/*
 * ######################################################
 * #                     Contrainte                     #
 * ######################################################
*/
    void testCreerContrainte(){
        assertEquals("12+", toString(creerContrainte(12, '+')));
    }

    String toString(Contrainte c){
        return c.clue + "" + c.operator;
    }

    Contrainte creerContrainte(int clue, char operator){
        Contrainte c = new Contrainte();
        c.clue = clue;
        c.operator = operator;
        return c;
    }

    void testGetClue(){
        Contrainte c = creerContrainte(12, '+');
        assertEquals(12, getClue(c));
        c = creerContrainte(1, '*');
        assertEquals(1, getClue(c));
        c = creerContrainte(5, '/');
        assertEquals(5, getClue(c));
    }

    int getClue(Contrainte c){
        return c.clue;
    }

    void testGetOperator(){
        Contrainte c = creerContrainte(12, '+');
        assertEquals('+', getOperator(c));
        c = creerContrainte(1, '*');
        assertEquals('*', getOperator(c));
        c = creerContrainte(5, '/');
        assertEquals('/', getOperator(c));
    }

    char getOperator(Contrainte c){
        return c.operator;
    }

/*
 * ######################################################
 * #                       Cell                         #
 * ######################################################
*/

    void testCreerCell(){
        Cell cell = creerCell(creerCoordonnee(0,0), 3);
        assertEquals("(0,0): 3", toString(cell));
    }
/*
    void testToString(){
        Cell cell = creerCell(creerCoordonnee(2,3),6);
        assertEquals("(2,3): 6", toString(cell));
    }
*/
    String toString(Cell cell){
        return toString(cell.coord) + ": " + cell.value;
    }

    Cell creerCell(Coordonnee coord, int value){
        Cell cell = new Cell();
        cell.coord = coord;
        cell.value = value;
        return cell;
    }

    void testgetCoordonnee(){
        Cell cell = creerCell(creerCoordonnee(0,1),0);
        assertEquals("(0,1)", toString(getCoordonnee(cell)));
    }

    Coordonnee getCoordonnee(Cell cell){
        return cell.coord;
    }

    void testGetValue(){
        Cell cell = creerCell(creerCoordonnee(1,0), 3);
        assertEquals(3, getValue(cell));
    }

    int getValue(Cell cell){
        return cell.value;
    }

    void testNumberOfCell(){
        Cell cell = creerCell(creerCoordonnee(2,2), 2);
        assertEquals(1, numberOfCell(cell));
        assertEquals(0, numberOfCell(cell.next);
    }

    int numberOfCell(Cell cell){
        if(cell == null) return 0;
        return 1 + numberOfCell(cell.next);
    }
/*
 * ######################################################
 * #                       Bloc                         #
 * ######################################################
*/

    void testCreerBloc(){
        Bloc b = creerBloc(ANSI_BLUE);
        assertEquals("ANSI_BLUE: ", toString(b));
    }

    Bloc creerBloc(String color, Contrainte contrainte, Cell listeCells){
        Bloc bloc = new Bloc();
        bloc.color = color;
        bloc.contrainte = creerContrainte(clue,op);
        bloc.cells = creerCell();
        return bloc;
    }

    String toString(Bloc bloc){
        String s = "";
        Bloc courant = bloc.cells;
        s += (bloc.color + ": " + toString(courant));
        for(int i = 1; i < numberOfCell(b.cells); i++){
            s += (", " + toString(courant));
            Bloc suivant = bloc.cells.next;
            courant = suivant;
        }
        return s;
    }

    void _algorithm(){
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
    int abs;
    int ord;
}

class Cell{
    int value = 0;
    Coordonnee coord;
    Cell next;
    Cell prev;
}

class Contrainte {
    int clue;
    char operator;
}

class Bloc{
    String color;
    Contrainte contrainte;
    Cell cells;
    Bloc next;
}
