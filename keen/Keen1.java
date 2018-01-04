import java.util.ArrayList;

class Keen1 extends Program{

    int[][] grid;

    int[][] arene;

    ArrayList<Coordonnee[]> _formes = new ArrayList<>();

    ArrayList<Bloc> blocs = new ArrayList<>();

    final int[] CODE_COLORS = new int[]{1, 2, 8, 20, 5, 6, 52, 93, 40, 12, 43, 16, 57, 18, 129, 30, 21, 32, 23, 34, 25, 36, 27, 38, 29, 20, 31, 22, 33, 24, 35, 26, 37, 28, 39, 10, 41, 88, 13, 44, 1, 54,17, 88, 9, 8, 19, 2};

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

    void testToString(){
        Coordonnee c = newCoordonnee(2,3);
        assertEquals("(2,3)", toString(c));
    }

    String toString(Coordonnee c){
        return "(" + c.y + "," + c.x + ")";
    }

    Coordonnee newCoordonnee(int y, int x){
        Coordonnee c = new Coordonnee();
        c.y = y;
        c.x = x;
        return c;
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

    String toString(Contrainte c){
        return c.clue + "" + c.operator;
    }

    Contrainte newContrainte(int clue, char operator){
        Contrainte c = new Contrainte();
        c.clue = clue;
        c.operator = operator;
        return c;
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
        assertEquals("0:3 12+", toString(b));
    }

    Bloc newBloc(int org, int type){
        Bloc b = new Bloc();
        b.org = org;
        b.type = type;
        return b;
    }

    String toString(Bloc b){
        String s = "";
        s += (getColor(b) != "" ) ? getColor(b): "";
        s += b.org+":"+b.type+" ";
        s += (getContrainte(b) != null ) ? toString(getContrainte(b)): "null";
        return s;
    }


    void setContrainte(Bloc b, Contrainte c){
        b.contrainte = c;
    }

    void setColor(Bloc b, String color){
        b.color = color;
    }

    int getOrg(Bloc b){
        return b.org;
    }

    int getType(Bloc b){
        return b.type;
    }

    Contrainte getContrainte(Bloc b){
        return b.contrainte;
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


    boolean isValid(int y, int x){
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
                            if (isValid(y, x) || !helper[y*length(grid, 1)+x]){
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


    int calculNB(int[] tab, char op){
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
        Contrainte c = newContrainte(calculNB(tab, charAt(operation, op)), charAt(operation,op));
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

    void printArene(){
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
        printHead();
        printSeparator();
        char car = 'A';
        int idxB = 0;
        String color;
        for(int l = 0; l < length(arene, 1); l++){
            print(car+"|");
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
                    print(String.format("%8s",ANSI_WHITE+ctr+ANSI_RESET+color+" "+disp+ANSI_RESET+"|"));
                    idxB += 1;
                }
                else print(String.format("%8s","     "+disp+ANSI_RESET+"|"));
            }
            println();printSeparator();
        }
    }

    void printHead(){
        print("  ");
        for(int i = 0; i < length(arene, 1); i++){
            print("   "+i+"   ");
        }
        println();
    }
    void printSeparator(){
        for(int i = 0; i < length(arene, 1)*7+2; i++){
            print("-");
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

    void algorithm(){
        initFormes();
        initialisation();
        initBlocs();
        initContraintes();
        do{
            printArene();
            String input;
            do{
                print("saisie [A0:1]: ");
                input = readString();
            }while(!isValidInput(input));
            int y = (int)(charAt(toUpperCase(input),0)) - 65;
            int x = stringToInt(substring(input,1,2));
            arene[y][x] = stringToInt(substring(input,3,length(input)));
        }while(!isWin());
        printArene();
        println("Victoire ^^");
    }
}
// https://asciinema.org/a/mUuas0YoQUVhxxbfKTwlgXwgT
// TODO: ajouter fonction pour avertir qu'une valeur est déjà présente dans la colonne et/ou ligne ou qu'elle ne permet d'avoir la contrainte si tout le bloc est rempli
