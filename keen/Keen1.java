import java.util.ArrayList;

class Keen1 extends Program{

    int[][] grid;

    int[][] arene;

    ArrayList<Coordonnee[]> _formes = new ArrayList<>();

    ArrayList<Bloc> blocs = new ArrayList<>();

    final int[] CODE_COLORS = new int[]{1, 2, 8, 20, 5, 52, 130, 0, 104, 12, 43, 208, 57, 18, 129, 30, 21, 32, 23, 34, 25, 36, 27, 38, 29, 93, 31, 22, 33, 24, 35, 26, 37, 28, 39, 10, 41, 88, 13, 44, 1, 54,17, 88, 9, 8, 19, 2};

    void testInitFormes(){
        initFormes();
        assertEquals(10, _formes.size());
        assertEquals("(0,0)", toString(_formes.get(0)[0]));
        assertEquals("(0,1)", toString(_formes.get(1)[1]));
        assertEquals("(1,0)", toString(_formes.get(2)[1]));
        assertEquals("(1,-1)", toString(_formes.get(4)[1]));
        /*assertEquals("(0,0)", toString(_formes.get(4)));
        assertEquals("(0,0)", toString(_formes.get(5)));
        assertEquals("(0,0)", toString(_formes.get(6)));
        assertEquals("(0,0)", toString(_formes.get(7)));
        assertEquals("(0,0)", toString(_formes.get(8)));
        assertEquals("(0,0)", toString(_formes.get(9)));
*/
    }

    void initFormes(){
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(0,1),
            newCoordonnee(1,0),
            newCoordonnee(1,1)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(1,0)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(0,1)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(1,-1),
            newCoordonnee(1,0)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(1,0),
            newCoordonnee(1,1)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(0,1),
            newCoordonnee(1,1)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(0,1),
            newCoordonnee(1,0)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(1,0),
            newCoordonnee(2,0)
        });
        _formes.add(new Coordonnee[]{
            newCoordonnee(0,0),
            newCoordonnee(0,1),
            newCoordonnee(0,2)
        });
    }
/*
 * ######################################################
 * #               Coordonnee                           #
 * ######################################################
*/

    void testnewCoordonnee(){
        Coordonnee c = newCoordonnee(2,3);
        assertEquals("(2,3)", toString(c));
    }

    Coordonnee newCoordonnee(int y, int x){
        Coordonnee c = new Coordonnee();
        c.y = y;
        c.x = x;
        return c;
    }

    void testToString(){
        Coordonnee c = newCoordonnee(2,3);
        assertEquals("(2,3)", toString(c));
    }

    String toString(Coordonnee c){
        return "(" + c.y + "," + c.x + ")";
    }

    void testGetX(){
        Coordonnee c = newCoordonnee(1,0);
        assertEquals(0, getX(c));
    }

    int getX(Coordonnee c){
        return c.x;
    }

    void testGetY(){
        Coordonnee c = newCoordonnee(1,0);
        assertEquals(1, getY(c));
    }

    int getY(Coordonnee c){
        return c.y;
    }

/*
 * ######################################################
 * #                     Contrainte                     #
 * ######################################################
*/
    void testCreerContrainte(){
        assertEquals("12+", toString(newContrainte(12, '+')));
    }

    Contrainte newContrainte(int clue, char operator){
        Contrainte c = new Contrainte();
        c.clue = clue;
        c.operator = operator;
        return c;
    }

    String toString(Contrainte c){
        return c.clue + "" + c.operator;
    }

    void testGetClue(){
        Contrainte c = newContrainte(12, '+');
        assertEquals(12, getClue(c));
        c = newContrainte(1, '*');
        assertEquals(1, getClue(c));
        c = newContrainte(5, '/');
        assertEquals(5, getClue(c));
    }

    int getClue(Contrainte c){
        return c.clue;
    }

    void testGetOperator(){
        Contrainte c = newContrainte(12, '+');
        assertEquals('+', getOperator(c));
        c = newContrainte(1, '*');
        assertEquals('*', getOperator(c));
        c = newContrainte(5, '/');
        assertEquals('/', getOperator(c));
    }

    char getOperator(Contrainte c){
        return c.operator;
    }


/*
 * ######################################################
 * #                       Bloc                         #
 * ######################################################
*/

    void testCreerBloc(){
        Bloc b = newBloc(0,3);
        assertEquals("0:3", toString(b));
        b.contrainte = newContrainte(12, '+');
        b.color = ANSI_BLUE;
        assertEquals(ANSI_BLUE+" 0:3 12+", toString(b));
    }

    Bloc newBloc(int org, int type){
        Bloc b = new Bloc();
        b.org = org;
        b.type = type;
        return b;
    }

    String toString(Bloc b){
        String s = "";
        s += (getColor(b) != null ) ? getColor(b)+" ": "";
        s += b.org+":"+b.type;
        s += (getContrainte(b) != null ) ? " "+toString(getContrainte(b)): "";
        return s;
    }

    void testSetContrainte(){
        Contrainte c = newContrainte(12, '+');
        Bloc b = newBloc(0, 4);
        setContrainte(b, c);
        assertEquals("12+", toString(getContrainte(b)));
    }

    void setContrainte(Bloc b, Contrainte c){
        b.contrainte = c;
    }

    void testSetColor(){
        String color = ANSI_BLUE;
        Bloc b = newBloc(0, 4);
        setColor(b, color);
        assertEquals(ANSI_BLUE, getColor(b));
    }

    void setColor(Bloc b, String color){
        b.color = color;
    }

    void testGetOrg(){
        Bloc b = newBloc(0, 4);
        assertEquals(0, getOrg(b));
        b = newBloc(3,0);
        assertEquals(3, getOrg(b));
    }

    int getOrg(Bloc b){
        return b.org;
    }

    void testGetType(){
        Bloc b = newBloc(0, 4);
        assertEquals(4, getType(b));
        b = newBloc(3,0);
        assertEquals(0, getType(b));
    }

    int getType(Bloc b){
        return b.type;
    }

    void testGetContrainte(){
        Bloc b = newBloc(0, 4);
        b.contrainte = newContrainte(12, '+');
        assertEquals("12+", toString(getContrainte(b)));
    }

    Contrainte getContrainte(Bloc b){
        return b.contrainte;
    }

    void testGetColor(){
        Bloc b = newBloc(0, 4);
        b.color = ANSI_BLUE;
        assertEquals(ANSI_BLUE, getColor(b));
    }

    String getColor(Bloc b){
        return b.color;
    }

    /*
     * #########################################################
     * #########################################################
     */


    int initSize(){
        int n;
        do {
            print("Saisir taille grille [3-9]: ");
            n = readInt();
        }while( n < 3 || n > 9);
        return n;
    }

    void initialisation(){
        int size = initSize();
        grid = new int[size][size];
        arene = new int[length(grid, 1)][length(grid, 2)];

        int[][] tmp;
        do{
            tmp = initGrid(0, new int[length(grid, 1)][length(grid, 2)], 0, new int[length(grid, 1)][length(grid, 2)]);
        }while(tmp == null);
        grid = tmp;
    }

    int[][] initGrid(int r, int[][] rows, int c, int[][] cols){
        if( r == length(grid, 1) && c == 0){
            return rows;
        }
        int n = getURandom(rows[r],cols[c]);
        if( n == -1) return null;
        else{
            rows[r][c] = n;
            cols[c][r] = n;
            if(c == length(grid, 2) - 1) r++;
            return initGrid(r, rows, (c+1)%length(grid, 2),cols);
        }
    }

    void testInArray(){
        assertTrue(inArray(CODE_COLORS, 130));
        assertFalse(inArray(CODE_COLORS, 300));
        assertFalse(inArray(CODE_COLORS, -1));
        assertTrue(inArray(CODE_COLORS, 88));
    }
    boolean inArray(int[] tab, int value){
        int idx = 0;
        while(idx < length(tab) && tab[idx] != value)
            idx++;
        return (idx == length(tab)) ? false: true;
    }

    int getURandom(int[] rows, int[] cols){
        int idx = 0;
        int idxMax = length(grid, 1)*length(grid, 2);
        int r;
        do{
            if(idx > idxMax) return -1;
            r = getRandom(length(grid,1)) + 1;
            idx++;
        }while(inArray(cols,r) || inArray(rows,r));
        return r;
    }

    int getRandom(int max){
        return (int)(random()*max);
    }

    void testIsValid(){
        grid = new int[5][7];
        assertFalse(isNoValid(0, 4));
        assertFalse(isNoValid(4,6));
        assertFalse(isNoValid(0, 0));
        assertTrue(isNoValid(-1,3));
        assertTrue(isNoValid(3, 8));
        assertTrue(isNoValid(-2,6));
        assertTrue(isNoValid(7, 10));
    }

    boolean isNoValid(int y, int x){
        return x < 0
            || y < 0
            || x >= length(grid, 2)
            || y >= length(grid, 1);
    }

    void initBlocs(){
        boolean[] helper = new boolean[length(grid, 1)*length(grid, 1)];
        for(int idx = 0; idx < length(helper); idx++){
            helper[idx] = true;
        }
        int idxColor = 0;
        for(int l = 0; l < length(grid, 1); l++){
            for(int c = 0; c < length(grid, 2); c++){
                if(helper[l*length(grid, 1)+c]){
                    int idF;
                    Coordonnee[] coords;
                    boolean badForme;
                    int idx = 0;
                    do{
                        idx++;
                        idF = idx < 100 ? getRandom(9)+1:0;
                        coords = _formes.get(idF);
                        badForme = false;
                        for(int idxco = 0; idxco < length(coords); idxco++){
                            int y = l + getY(coords[idxco]);
                            int x = c + getX(coords[idxco]);
                            if (isNoValid(y, x) || !helper[y*length(grid, 1)+x]){
                                badForme = true;
                                break;
                            }
                        }
                    }while(badForme);
                    for(int idxco = 0; idxco < length(coords); idxco++){
                        int idxC = getX(coords[idxco]);
                        int idxL = getY(coords[idxco]);
                        helper[(l+idxL)*length(grid, 1)+(c+idxC)] = false ;
                    }
                    Bloc b = newBloc(l*length(grid, 1)+c, idF);
                    setColor(b, "\033[1;48;5;"+CODE_COLORS[idxColor++]+"m");
                    blocs.add(b);
                }
            }
        }
    }

    void testGetTotal(){
        assertEquals(20, getTotal(new int[]{1,2,3,4,5,6,-1}, '+'));
        assertEquals(20, getTotal(new int[]{100,10,20,30,40,-40,20}, '-'));
        assertEquals(20, getTotal(new int[]{1,2,5,2,1}, '*'));
        assertEquals(20, getTotal(new int[]{40,1,2}, '/'));
    }

    int getTotal(int[] tab, char op){
        int res = tab[0];
        for(int idx = 1; idx < length(tab); idx++){
            switch(op){
             case '+' : res += tab[idx]; break;
             case '-' : res -= tab[idx]; break;
             case '*' : res *= tab[idx]; break;
             case '/' : res /= tab[idx]; break;
            }
        }
        return res;
    }

    void putRandomContrainte(Bloc b, int[] tab){
        if( length(tab) ==  1 ) {
            setContrainte(b, newContrainte(tab[0], '='));
        }
        else if ( length(tab) == 2 ) {
            int tmp = tab[0];
            tab[0] = max(tab[0],tab[1]);
            tab[1] = min(tmp, tab[1]);
        }

        if( length(tab) >= 2){
        boolean isDec = tab[0]%tab[1] != 0;

        int op;
        do{
            op = getRandom(4);
        }while(((op == 1 || op == 3) && length(tab) != 2) || ( op== 3 && isDec));

        String operation = "+-*/";
        Contrainte c = newContrainte(getTotal(tab, charAt(operation, op)), charAt(operation,op));
        setContrainte(b, c);
        }
    }


    void complete(Bloc b){
        Coordonnee[] coords = _formes.get(getType(b));
        int orgX = getOrg(b)%length(grid, 1);
        int orgY = getOrg(b)/length(grid, 2);

        int[] tmp = new int[length(coords)];

        for(int idx =  0; idx < length(coords); idx++){
            int y = orgY + getY(coords[idx]);
            int x = orgX + getX(coords[idx]);
            tmp[idx] = grid[y][x];
        }
        putRandomContrainte(b,tmp);
    }

    void initContraintes(){
        for(int idx = 0; idx < blocs.size(); idx++){
            complete(blocs.get(idx));
        }
    }


    void printCoords(Coordonnee[] tab){
        for(int idx = 0; idx < length(tab) ; idx++){
            print(String.format("%4s",toString(tab[idx])));
        }
    }

    void printBlocs(){
        for(int i = 0; i < blocs.size(); i++){
            print(toString(blocs.get(i))+" : ");
            printCoords(_formes.get(getType(blocs.get(i))));
            println(ANSI_RESET);
        }
    }

    void printFormes(){
        for(int i = 0; i < _formes.size(); i++){
            print(i+" :  ");
            for(int j = 0; j < length(_formes.get(i)); j++){
                print(toString(_formes.get(i)[j])+"  ");
            }
            println();
        }
    }

    Coordonnee[] getEltBloc(Bloc b){
        Coordonnee[] coords = _formes.get(getType(b));

        Coordonnee[] res = new Coordonnee[length(coords)];

        int orgX = getOrg(b)%length(arene, 1);
        int orgY = getOrg(b)/length(arene, 2);

        for(int idx = 0; idx < length(coords); idx++){
            int y = orgY + getY(coords[idx]);
            int x = orgX + getX(coords[idx]);
            res[idx] = newCoordonnee(y,x);
        }
        return res;
    }


    int[] getColumn(int col){
        int[] column = new int[length(arene, 1)];
        for(int idxL = 0; idxL < length(column); idxL++){
            column[idxL] = arene[idxL][col];
        }
        return column;
    }

    int[] getRow(int row){
        return arene[row];
    }

        //FIXME: affichage de l'arène avec ce template
/*

      0       1       2       3       4       5       6
  +-------+-------+-------+-------+-------+-------+-------+
  | 12 +  |  12 + |  12 + |  12 + |  12 + |  12 + |  12 + |
A |   3   |   3   |    3  |    3  |    3  |    3  |    3  |
  |       |       |       |       |       |       |       |
  +-------+-------+-------+-------+-------+-------+-------+
  | 12 +  |  12 + |  12 + |  12 + |  12 + |  12 + |  12 + |
B |   3   |   3   |    3  |    3  |    3  |    3  |    3  |
  |       |       |       |       |       |       |       |
  +-------+-------+-------+-------+-------+-------+-------+
  | 12 +  |  12 + |  12 + |  12 + |  12 + |  12 + |  12 + |
C |   3   |   3   |    3  |    3  |    3  |    3  |    3  |
  |       |       |       |       |       |       |       |
  +-------+-------+-------+-------+-------+-------+-------+
  | 12 +  |  12 + |  12 + |  12 + |  12 + |  12 + |  12 + |
D |   3   |   3   |    3  |    3  |    3  |    3  |    3  |
  |       |       |       |       |       |       |       |
  +-------+-------+-------+-------+-------+-------+-------+
  | 12 +  |  12 + |  12 + |  12 + |  12 + |  12 + |  12 + |
E |   3   |   3   |    3  |    3  |    3  |    3  |    3  |
  |       |       |       |       |       |       |       |
  +-------+-------+-------+-------+-------+-------+-------+
  | 12 +  |  12 + |  12 + |  12 + |  12 + |  12 + |  12 + |
F |   3   |   3   |    3  |    3  |    3  |    3  |    3  |
  |       |       |       |       |       |       |       |
  +-------+-------+-------+-------+-------+-------+-------+
  | 12 +  |  12 + |  12 + |  12 + |  12 + |  12 + |  12 + |
G |   3   |   3   |    3  |    3  |    3  |    3  |    3  |
  |       |       |       |       |       |       |       |
  +-------+-------+-------+-------+-------+-------+-------+

  jouer >$: A1:4 ( permet de jouer à la coordonnée ('A',1) la valeur 4
*/
    void printArene(boolean highlight){
        printHead();
        printSeparator();
        char car = 'A';
        int idxB = 0;
        String color;
        String COLOR_HIGHLIGHT = (highlight) ? ANSI_YELLOW: "";
        for(int l = 0; l < length(arene, 1); l++){
            print(ANSI_BOLD+ANSI_BLUE+car+ANSI_RESET+"|");
            car += 1;
            for(int c = 0; c < length(arene, 2); c++){
                color = findColor(newCoordonnee(l,c));
                String disp = (arene[l][c] == 0) ? " ":arene[l][c]+"";
                print(color+ANSI_BOLD);
                if( idxB < blocs.size()
                        && l*length(arene,1)+c == getOrg(blocs.get(idxB))){
                    String ctr = toString(getContrainte(blocs.get(idxB)));
                    switch(length(ctr)){
                    case 2: ctr += "  "; break;
                    case 3: ctr += " "; break;
                    default: ctr = ctr; break;
                    }
                    print(String.format("%8s",ANSI_WHITE+ctr+ANSI_RESET+color+" "+COLOR_HIGHLIGHT+disp+ANSI_RESET+"|"));
                    idxB += 1;
                }
                else print(String.format("%8s","     "+COLOR_HIGHLIGHT+disp+ANSI_RESET+"|"));
            }
            println();printSeparator();
        }
    }

    void printHead(){
        print("  ");
        for(int i = 0; i < length(arene, 1); i++){
            print("   "+ANSI_BOLD+ANSI_RED+i+ANSI_RESET+"   ");
        }
        println();
    }
    void printSeparator(){
        print(" +");
        for(int i = 0; i < length(arene, 1); i++){
            print("------+");
        }
        println();
    }

    void printGrid(){
        printHead();
        char car = 'A';
        for(int l = 0; l < length(grid, 1); l++){
            print(car+"|");
            car += 1;
            for(int c = 0; c < length(grid, 2); c++){
                print(grid[l][c]+" ");
            }
            println();
        }
    }

    boolean isWin(){
        //FIXME: verfier que chaque valeur est present une fois sur la même ligne et colonne
        // et que toutes les valeurs d'un bloc verifie la contrainte lié au bloc
        for (int idxL = 0; idxL < length(arene, 1); idxL++){
            for(int idxC = 0; idxC < length(arene, 2); idxC++){
                if(arene[idxL][idxC] != grid[idxL][idxC])
                    return false;
            }
        }
        return true;
    }

    boolean isValidInput(String input){
        return length(input) == 4
            && (charAt(toUpperCase(input), 0) < 'A' + length(arene, 1))
            && Character.isDigit(charAt(input, 1))
            && stringToInt(charAt(input, 1)+"") >= 0
            && stringToInt(charAt(input, 1)+"") < length(arene, 1)
            && charAt(input, 2) == ':'
            && Character.isDigit(charAt(input, 3))
            && stringToInt(charAt(input, 3)+"") > 0
            && stringToInt(charAt(input, 3)+"") <= length(arene, 1);
    }


    String findColor(Coordonnee c){
        for(int idxB = 0; idxB < blocs.size(); idxB++){
            Bloc b = blocs.get(idxB);
            if (isFindCoord(getEltBloc(b), c)) return getColor(b);
        }
        return ANSI_WHITE;
    }

    boolean isFindCoord(Coordonnee[] tabCoord, Coordonnee c){
        for(int idx = 0; idx < length(tabCoord); idx++){
            if(equals(c, tabCoord[idx])) return true;
        }
        return false;
    }

    boolean equals(Coordonnee c1, Coordonnee c2){
        return c1.x == c2.x && c1.y == c2.y;
    }

    boolean isValidContrainteBloc(Bloc b){
        char op = getOperator(getContrainte(b));
        Coordonnee[] coords = getEltBloc(b);
        int[] total = new int[length(coords)];
        for(int i = 0; i < length(coords); i++){
            int y = getX(coords[i]);
            int x = getX(coords[i]);
            if(arene[y][x] == 0) return false;
            total[i] = arene[y][x];
        }
        return getTotal(total, op) == getClue(getContrainte(b));
    }

    void algorithm(){
        initFormes();
        initialisation();
        initBlocs();
        initContraintes();
        boolean highlight = false;
        printCoords(getEltBloc(blocs.get(3)));
        println();
        do{
            printArene(highlight);
            String input;
            do{
                print("saisie "
                        +ANSI_BOLD+ANSI_BLUE+"[A-"+(char)('A'+length(arene,1)-1)+"]"+ANSI_RED
                        +"[0-"+(length(arene,2)-1)+"]"+ANSI_RESET+":"
                        +ANSI_BOLD+ANSI_WHITE+"[1-"+length(arene,1)+ANSI_RESET+"] : ");
                input = readString();
            }while(!isValidInput(input));
            int y = (int)(charAt(toUpperCase(input),0)) - 65;
            int x = stringToInt(substring(input,1,2));
            int value = stringToInt(substring(input,3,length(input)));
            highlight = (inArray(getColumn(y), value) || inArray(getRow(x), value)) ? true: false;
            arene[y][x] = value;
        }while(!isWin());
        printArene(false);
        println("Victoire ^^");
    }
}
// https://asciinema.org/a/mUuas0YoQUVhxxbfKTwlgXwgT
// https://asciinema.org/a/HMi858kuezszd84XotFhL2cT6
// TODO: ajouter fonction pour avertir qu'une valeur est déjà présente dans la colonne et/ou ligne ou qu'elle ne permet d'avoir la contrainte si tout le bloc est rempli
