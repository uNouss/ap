import java.util.ArrayList;

class Keen1 extends Program{
    final String ANSI_PREFIX = "\033[1;48;5;";
    final String ANSI_POSTFIX = "m";

    int[][] grid;
    int[][] arene;

    ArrayList<Coordonnee[]> formes = new ArrayList<>();
    ArrayList<Bloc> blocs = new ArrayList<>();

    final int[] CODE_COLORS = new int[]{1, 2, 8, 20, 5, 52, 130, 0, 104, 12, 43, 208, 57, 18, 129, 30, 21, 32, 23, 34, 25, 36, 27, 38, 29, 93, 31, 22, 33, 24, 35, 26, 37, 28, 39, 10, 41, 88, 13, 44, 1, 54,17, 88, 9, 8, 19, 200};

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

    void testEquals(){
        assertTrue(equals(newCoordonnee(0,1), newCoordonnee(0, 1)));
        assertFalse(equals(newCoordonnee(0,1), newCoordonnee(1, 0)));
    }

    boolean equals(Coordonnee c1, Coordonnee c2){
        return getX(c1) == getX(c2) && getY(c1) == getY(c2);
    }

    void testCoordsToString(){
        Coordonnee[] coords = new Coordonnee[]{
            newCoordonnee(0,0),
                newCoordonnee(1,0),
                newCoordonnee(2,0)
        };
        assertEquals("(0,0)(1,0)(2,0)", coordsToString(coords));
    }

    String coordsToString(Coordonnee[] coords){
        String _coords = "";
        for(int idx = 0; idx < length(coords); idx++){
            _coords += toString(coords[idx]);
        }
        return _coords;
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
        setContrainte(b, newContrainte(12, '+'));
        setColor(b, ANSI_BLUE);
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
        setContrainte(b, newContrainte(12, '+'));
        assertEquals("12+", toString(getContrainte(b)));
    }

    Contrainte getContrainte(Bloc b){
        return b.contrainte;
    }

    void testGetColor(){
        Bloc b = newBloc(0, 4);
        setColor(b, ANSI_BLUE);
        assertEquals(ANSI_BLUE, getColor(b));
    }

    String getColor(Bloc b){
        return b.color;
    }

    boolean isFilled(Bloc b){
        int[] valuesBloc = getValuesBloc(arene, b);
        for(int idx = 0; idx < length(valuesBloc); idx++){
            if(valuesBloc[idx] == 0) return false;
        }
        return true;
    }

    /*
     * ######################################################
     * #                     ListeFormes                    #
     * ######################################################
     */
    //FIXME: completer l'implemetation de ListeForme et ListeBloc pour se passer des ArrayList
    /*ListeForme newListeForme(Coordonnee[] coords){
        ListeForme lf = new ListeForme();
        lf.coords = coords;
        lf.next = null;
        return lf;
    }

    boolean isEmpty(ListeForme lf){
        return lf == null;
    }

    ListeForme next(ListeForme lf){
        return lf.next;
    }

    int size(ListeForme lf){
        if(isEmpty(lf)) return 0;
        return 1+size(next(lf));
    }

    ListeForme add(ListeForme lf, Coordonnee[] coords){
        ListeForme _lf = new ListeForme();
        if(lf == null){
            _lf.coords = coords;
            _lf.next = null;
            return _lf;
        }
        else{
            _lf.coords = lf.coords;
            _lf.next = add(lf.next, coords);
            return _lf;
        }
    }

    Coordonnee[] get(ListeForme lf, int idx){
        if(idx < 0 || idx >= size(lf) || lf == null ) return null;
        if(idx == 0) return lf.coords;
        return get(lf.next, idx - 1);
    }

    String toString(ListeForme  f){
        String _f = "";
        for(ListeForme p = f; p != null ; p = p.next){
            _f = coordsToString(p.coords);
        }
        return _f;
    }

    /*
     * ######################################################
     * #                     ListeBloc                    #
     * ######################################################
     */

    /*Forme newListeBloc(Bloc bloc){
        ListeBloc lb = new ListeBloc();
        lb.bloc = bloc;
        lb.next = null;
        return lb;
    }

    int size(ListeBloc lb){
        if(lb == null) return 0;
        return 1+size(lb.next);
    }

    String toString(ListeBloc  lb){
        String _lb = "";
        for(ListeBloc _b = lb; _b != null ; _b = lb.next){
            _lb = toString(lb);
        }
        return _lb;
    }

    void add(ListeBloc lb, Bloc b){
    }

    Bloc get(ListeBloc lb, int idx){
    }
    */

    /*
     * ######################################################
     * #                     Initialisation                 #
     * ######################################################
     */

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

    void testInitFormes(){
        initFormes();
        assertEquals("(0,0)", coordsToString(formes.get(0)));
        assertEquals("(0,0)(1,0)", coordsToString(formes.get(1)));
        assertEquals("(0,0)(0,1)", coordsToString(formes.get(2)));
        assertEquals("(0,0)(1,-1)(1,0)", coordsToString(formes.get(3)));
        assertEquals("(0,0)(1,0)(1,1)", coordsToString(formes.get(4)));
        assertEquals("(0,0)(0,1)(1,1)", coordsToString(formes.get(5)));
        assertEquals("(0,0)(0,1)(1,0)", coordsToString(formes.get(6)));
        assertEquals("(0,0)(1,0)(2,0)", coordsToString(formes.get(7)));
        assertEquals("(0,0)(0,1)(0,2)", coordsToString(formes.get(8)));
        assertEquals("(0,0)(0,1)(1,0)(1,1)", coordsToString(formes.get(9)));
        assertEquals("(0,0)(0,1)(0,2)(1,1)", coordsToString(formes.get(10)));
    }

    void initFormes(){
        Coordonnee[][] _formes = new Coordonnee[][]{
            {newCoordonnee(0,0)},
            {newCoordonnee(0,0), newCoordonnee(1,0)},
            {newCoordonnee(0,0), newCoordonnee(0,1)},
            {newCoordonnee(0,0), newCoordonnee(1,-1), newCoordonnee(1,0)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(1,0)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(2,0)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(0,2)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(1,0), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(0,2), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(2,0), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(1,-1), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(1,1), newCoordonnee(2,0), newCoordonnee(1,-1)}
        };
        for(int idx = 0; idx < length(_formes); idx++){
            formes.add(_formes[idx]);
        }
    }

    void initBlocs(){
        boolean[] helper = initHelper();
        int idxColor = 0;
        for(int l = 0; l < length(grid, 1); l++){
            for(int c = 0; c < length(grid, 2); c++){
                if(helper[l*length(grid, 1)+c]){
                    int idF, idx = 0;
                    Coordonnee[] coords;
                    boolean badForme;
                    do{
                        idx++;
                        idF = idx < formes.size()*formes.size() ? getRandom(formes.size()-1)+1:0;
                        coords = formes.get(idF);
                        badForme = isBadForme(coords, helper, l, c);
                    }while(badForme);
                    updateHelper(coords, helper, l, c);
                    updateBlocs(l, c, idF, idxColor);
                    idxColor++;
                }
            }
        }
    }

    void testInitHelper(){
        initTests();
        assertArrayEquals(new boolean[] {
            true, true, true,
                true, true, true,
                true, true, true} , initHelper());
    }

    boolean[] initHelper(){
        boolean[] helper = new boolean[length(arene, 1)*length(arene, 1)];
        for(int idx = 0; idx < length(helper); idx++){
            helper[idx] = true;
        }
        return helper;
    }

    void initContraintes(){
        for(int idx = 0; idx < blocs.size(); idx++){
            Bloc b = blocs.get(idx);
            putRandomContrainte(b, getValuesBloc(grid, b));
        }
    }

    void initTests(){
        initFormes();
        initAreneTest();
        initBlocsTest();
        addContrainte();
        addColor();
    }

    void initBlocsTest(){
        blocs.clear();
        blocs.add(newBloc(0,8));
        blocs.add(newBloc(3,1));
        blocs.add(newBloc(4,4));
        blocs.add(newBloc(5,0));
    }

    void initAreneTest(){
        arene = new int[][]{
            {2, 1, 3},
                {3, 2, 1},
                {1, 3, 2}
        };
    }

    /*
     * ######################################################
     * #                     Controleurs                    #
     * ######################################################
     */

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

    void testIsNoValid(){
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

    boolean isBadForme(Coordonnee[] coords, boolean[] helper, int l, int c){
        boolean badForme = false;
        for(int idxco = 0; idxco < length(coords); idxco++){
            int y = l + getY(coords[idxco]);
            int x = c + getX(coords[idxco]);
            if (isNoValid(y, x) || !helper[y*length(grid, 1)+x]){
                badForme = true;
                break;
            }
        }
        return badForme;
    }

    void testIsFindCoord(){
        initTests();
        assertTrue(isFindCoord(formes.get(3), newCoordonnee(1,-1)));
        assertFalse(isFindCoord(formes.get(3), newCoordonnee(-1,-1)));
    }

    boolean isFindCoord(Coordonnee[] tabCoord, Coordonnee c){
        for(int idx = 0; idx < length(tabCoord); idx++){
            if(equals(c, tabCoord[idx])) return true;
        }
        return false;
    }

    void testIsMoreOne(){
        assertFalse(isMoreOne(CODE_COLORS, 2));
        assertFalse(isMoreOne(CODE_COLORS, 255));
        assertTrue(isMoreOne(new int[]{1, 2, 3, 4, 5, 6, 2}, 2));
        assertTrue(isMoreOne(new int[]{1, 2, 3, 4, 5, 6, 3, 9, 10, 3}, 3));
    }

    boolean isMoreOne(int[] tab, int value){
        int idx = 0;
        int nbOccur = 0;
        while(idx < length(tab) && nbOccur < 2){
            if(tab[idx++] == value) nbOccur++;
        }
        return nbOccur > 1;
    }

    void testIsDigit(){
        assertTrue(isDigit('0'));
        assertFalse(isDigit('O'));
    }

    boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    void testIsValidInput(){
        arene = new int[5][5];
        assertTrue(isValidInput("A0:3"));
        assertFalse(isValidInput("Z0:6"));
        assertFalse(isValidInput("2"));
        assertFalse(isValidInput("A0:03"));
    }

    boolean isValidInput(String input){
        return length(input) == 4
            && charAt(input, 0) >= 'A'
            && (charAt(input, 0) < 'A' + length(arene, 1))
            && isDigit(charAt(input, 1))
            && stringToInt(charAt(input, 1)+"") >= 0
            && stringToInt(charAt(input, 1)+"") < length(arene, 1)
            && charAt(input, 2) == ':'
            && isDigit(charAt(input, 3))
            && stringToInt(charAt(input, 3)+"") > 0
            && stringToInt(charAt(input, 3)+"") <= length(arene, 1);
    }

    void testIsValidContrainteBloc(){
        initTests();
        assertTrue(isValidContrainteBloc(blocs.get(0)));
        setContrainte(blocs.get(3), newContrainte(2, '='));
        assertFalse(isValidContrainteBloc(blocs.get(3)));
        setContrainte(blocs.get(3), newContrainte(1, '='));
    }

    boolean isValidContrainteBloc(Bloc b){
        char op = getOperator(getContrainte(b));
        int[] valuesBloc = getValuesBloc(arene, b);
        if(length(valuesBloc) == 2 && ( op == '-' || op == '/' )){
            permuter(valuesBloc, 0, 1);
        }
        int total = getTotal(valuesBloc, op);
        return total == getClue(getContrainte(b));
    }

    void testIsValidSuduku(){
        initTests();
        assertTrue(isValidSuduku());
    }

    boolean isValidSuduku(){
        int comparateur = sommeSuite(length(arene, 1));
        for (int l = 0; l < length(arene, 1); l++){
            for(int c = l; c <= l ; c++){
                int totalC = getTotal(getColumn(c), '+');
                int totalL = getTotal(getRow(l), '+');
                if( totalC != comparateur || totalL != comparateur) return false;
            }
        }
        return true;
    }

    void testIsValidContraintes(){
        initTests();
        assertTrue(isValidContraintes());
    }

    boolean isValidContraintes(){
        for(int idxB = 0; idxB < blocs.size(); idxB++){
            if( ! isValidContrainteBloc(blocs.get(idxB)))
                return false;
        }
        return true;
    }

    void testIsWin(){
        initTests();
        assertTrue(isWin());
    }

    boolean isWin(){
        return isValidSuduku() && isValidContraintes();
    }

    /*
     * ######################################################
     * #                     Utilitaires                    #
     * ######################################################
     */

    void addContrainte(){
        setContrainte(blocs.get(0), newContrainte(6, '+'));
        setContrainte(blocs.get(1), newContrainte(4, '+'));
        setContrainte(blocs.get(2), newContrainte(12, '*'));
        setContrainte(blocs.get(3), newContrainte(1, '='));
    }

    void addColor(){
        for(int idx = 0; idx < blocs.size(); idx++){
            setColor(blocs.get(idx), ANSI_PREFIX+CODE_COLORS[idx]+ANSI_POSTFIX);
        }
    }

    void testUpdateHelper(){
        initTests();
        boolean[] helper = initHelper();
        updateHelper(formes.get(9), helper, 1, 0);
        assertFalse(helper[3]);
        assertFalse(helper[4]);
        assertFalse(helper[6]);
        assertFalse(helper[7]);
    }

    void updateHelper(Coordonnee[] coords, boolean[] helper, int l, int c){
        for(int idxco = 0; idxco < length(coords); idxco++){
            int idxC = getX(coords[idxco]);
            int idxL = getY(coords[idxco]);
            helper[(l+idxL)*length(arene, 1)+(c+idxC)] = false ;
        }
    }

    void updateBlocs(int l, int c, int idF, int idxColor){
        Bloc b = newBloc(l*length(grid, 1)+c, idF);
        setColor(b, ANSI_PREFIX+CODE_COLORS[idxColor]+ANSI_POSTFIX);
        blocs.add(b);
    }

    void testPermuter(){
        int[] tab = new int[]{3, 4, 1, 2, 7, 5, 6, 0};
        permuter(tab, 0, 7);
        assertArrayEquals(new int[]{3, 4, 1, 2, 7, 5, 6, 0}, tab);
        permuter(tab, 1, 2);
        assertArrayEquals(new int[]{3, 4, 1, 2, 7, 5, 6, 0}, tab);
    }

    void permuter(int[] tab, int idx1, int idx2){
        int tmp = tab[idx1];
        tab[idx1] = max(tab[idx1],tab[idx2]);
        tab[idx2] = min(tmp, tab[idx2]);
    }

    void testGetValuesBloc(){
        initTests();
        Bloc b = blocs.get(0);
        assertArrayEquals(new int[]{2, 1, 3}, getValuesBloc(arene, b));
    }

    int[] getValuesBloc(int[][] tab, Bloc b){
        Coordonnee[] coords = formes.get(getType(b));
        int orgX = getOrg(b)%length(arene, 1);
        int orgY = getOrg(b)/length(arene, 2);

        int[] valuesBloc = new int[length(coords)];

        for(int idx =  0; idx < length(coords); idx++){
            int y = orgY + getY(coords[idx]);
            int x = orgX + getX(coords[idx]);
            valuesBloc[idx] = tab[y][x];
        }
        return valuesBloc;
    }

    void testGetEltBloc(){
        initTests();
        Bloc b = blocs.get(0);
        assertEquals("(0,0)(0,1)(0,2)", coordsToString(getEltBloc(b)));
    }

    Coordonnee[] getEltBloc(Bloc b){
        Coordonnee[] coords = formes.get(getType(b));

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

    void testGetColumn(){
        arene = new int[][]{
            {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 18, 20}
        };
        assertArrayEquals(new int[]{3, 8, 13, 18}, getColumn(2));
    }

    int[] getColumn(int col){
        int[] column = new int[length(arene, 1)];
        for(int idxL = 0; idxL < length(column); idxL++){
            column[idxL] = arene[idxL][col];
        }
        return column;
    }

    void testGetRow(){
        arene = new int[][]{
            {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 18, 20}
        };
        assertArrayEquals(new int[]{11, 12, 13, 14, 15}, getRow(2));
    }

    int[] getRow(int row){
        return arene[row];
    }

    void testFindColor(){
        initTests();
        assertEquals(ANSI_PREFIX+CODE_COLORS[0]+ANSI_POSTFIX, findColor(newCoordonnee(0,0)));
    }

    String findColor(Coordonnee c){
        for(int idxB = 0; idxB < blocs.size(); idxB++){
            Bloc b = blocs.get(idxB);
            if (isFindCoord(getEltBloc(b), c)) return getColor(b);
        }
        return ANSI_WHITE_BG;
    }

    void testGetTotal(){
        assertEquals(20, getTotal(new int[]{1,2,3,4,5,6,-1}, '+'));
        assertEquals(20, getTotal(new int[]{100,10,20,30,40,-40,20}, '-'));
        assertEquals(20, getTotal(new int[]{1,2,5,2,1}, '*'));
        assertEquals(20, getTotal(new int[]{40,1,2}, '/'));
    }

    int getTotal(int[] tab, char op){
        int res = tab[0];
        if( op == '=' ) return res;
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

    void testSommeSuite(){
        assertEquals(6, sommeSuite(3));
        assertEquals(10*(10+1)/2, sommeSuite(10));
    }

    int sommeSuite(int n){
        int somme = 0;
        for(int i = 1; i <= n; i++){
            somme += i;
        }
        return somme;
    }

    void putRandomContrainte(Bloc b, int[] tab){
        if( length(tab) ==  1 ) {
            setContrainte(b, newContrainte(tab[0], '='));
        }
        else if ( length(tab) == 2 ) {
            permuter(tab, 0, 1);
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

    void testGetDuration(){
        assertEquals("9min 36s", getDuration(576000));
    }

    String getDuration(long milliseconds){
        int seconds = (int) (milliseconds / 1000) % 60 ;
        int minutes = (int) ((milliseconds / (1000*60)) % 60);
        int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
        String duration = (hours > 0) ? hours+"h ": "";
        duration += (minutes > 0) ? minutes+"min ": "";
        duration += (seconds > 0) ? seconds+"s":"";
        return duration;
    }

    /*
     * ######################################################
     * #                  Affichages                        #
     * ######################################################
     */

    void printArene(int y, int x){
        printHead();
        printSeparator();
        char car = 'A';
        int idxB = 0, value = arene[y][x];
        String color;
        boolean hLT = (isMoreOne(getColumn(x), value) || isMoreOne(getRow(y), value)) ? true: false;
        for(int l = 0; l < length(arene, 1); l++){
            print(ANSI_BOLD+ANSI_BLUE+car+ANSI_RESET+"|");
            car += 1;
            for(int c = 0; c < length(arene, 2); c++){
                color = findColor(newCoordonnee(l,c));
                String colhLT = (hLT && (l == y || c == x) && arene[l][c] == value) ? ANSI_BLINK_SLOW : "";
                String disp = (arene[l][c] == 0) ? " ":arene[l][c]+"";
                print(color+ANSI_BOLD);
                if( idxB < blocs.size()
                        && l*length(arene,1)+c == getOrg(blocs.get(idxB))){
                    String clue = toString(getContrainte(blocs.get(idxB)));
                    String colhLB = (isFilled(blocs.get(idxB)) && !isValidContrainteBloc(blocs.get(idxB))) ? ANSI_BLINK_SLOW : "";
                    print(ANSI_WHITE+colhLB+clue+ANSI_RESET+color+colhLT+String.format("%"+(7-length(clue))+"s",disp)+ANSI_RESET+"|");
                    idxB += 1;
                        }
                else print(colhLT+String.format("%7s", disp)+ANSI_RESET+"|");
            }
            println();printSeparator();
        }
    }

    void printHead(){
        print("  ");
        for(int i = 0; i < length(arene, 1); i++){
            print("   "+ANSI_BOLD+ANSI_RED+i+ANSI_RESET+"    ");
        }
        println();
    }

    void printSeparator(){
        print(" +");
        for(int i = 0; i < length(arene, 1); i++){
            print("-------+");
        }
        println();
    }

    void printGrid(){
        for(int l = 0; l < length(grid, 1); l++){
            for(int c = 0; c < length(grid, 2); c++){
                print(ANSI_BLACK_BG+ANSI_BLACK+grid[l][c]+" "+ANSI_RESET);
            }
            print("  ");
        }
        println();
    }

    void printTab(int[] tab){
        for(int i = 0; i < length(tab); i++)
            print(tab[i]+" ");
        println();
    }

    void printCoords(Coordonnee[] tab){
        for(int idx = 0; idx < length(tab) ; idx++){
            print(String.format("%4s",toString(tab[idx])));
        }
    }

    void printBlocs(){
        for(int i = 0; i < blocs.size(); i++){
            print(toString(blocs.get(i))+" : ");
            printCoords(formes.get(getType(blocs.get(i))));
            println(ANSI_RESET);
        }
    }

    void printFormes(){
        for(int i = 0; i < formes.size(); i++){
            print(i+" :  ");
            for(int j = 0; j < length(formes.get(i)); j++){
                print(toString(formes.get(i)[j])+"  ");
            }
            println();
        }
    }

    /*
     * ######################################################
     * #                   Saisies                          #
     * ######################################################
     */

    int initSize(){
        int n;
        do {
            print("Saisir taille grille [3-9]: ");
            n = readInt();
        }while( n < 3 || n > 9);
        return n;
    }

    String input(){
        String input;
        do{
            print("saisie "
                    +ANSI_BOLD+ANSI_BLUE+"[A-"+(char)('A'+length(arene,1)-1)+"]"+ANSI_RED
                    +"[0-"+(length(arene,2)-1)+"]"+ANSI_RESET+":"
                    +ANSI_BOLD+ANSI_WHITE+"[1-"+length(arene,1)+ANSI_RESET+"] : ");
            input = readString();
        }while(!isValidInput(input));
        return input;
    }

    /*
     * ######################################################
     * #                      Main                          #
     * ######################################################
     */

    void algorithm(){
        initFormes();
        initialisation();
        initBlocs();
        initContraintes();
        printGrid();
        int x = 0, y = 0;
        long start = getTime();
        do{
            printArene(y, x);
            String input = input();
            y = (int)(charAt(input,0)) - 65;
            x = stringToInt(substring(input,1,2));
            arene[y][x] = stringToInt(substring(input,3,length(input)));
        }while(!isWin());
        String duration = getDuration(getTime() - start);
        printArene(y, x);
        println(ANSI_BLUE_BG+ANSI_BOLD+"VICTOIRE en "+duration+" ^^"+ANSI_RESET);
    }
}

// https://asciinema.org/a/mUuas0YoQUVhxxbfKTwlgXwgT
// https://asciinema.org/a/HMi858kuezszd84XotFhL2cT6
// https://asciinema.org/a/DexW5D7p2iHwaEwqbdYdMoSBS
// https://asciinema.org/a/FwGtvD8p0nmof3j7EsKZO2o5q
// https://asciinema.org/a/SHub0zT5cOwfaqXZiKgDZcdM8
// https://asciinema.org/a/iEF51do9YsEaHy7o4dYr2pWZZ
// https://asciinema.org/a/nMXMqCLgQ0E1LeAp5s93kDgl1
// https://asciinema.org/a/IFGxEExh6obXtubTFzHlT5zOn
// TODO:
//     -  ajouter fonction pour avertir qu'une valeur est déjà présente dans la colonne et/ou ligne ou qu'elle ne permet d'avoir la contrainte si tout le bloc est rempli
//     -  ajouter fonction qui detecte violation contrainte suduku
//     -  ajouter fonction qui detecte violation contrainte bloc
//     -  amelioration de l'affichage comme sur le template
//     -  definir structure de donnée liste pour remplacer ArrayList
