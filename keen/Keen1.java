import java.util.ArrayList;

class Keen1 extends Program{
    final int SIZE = 7;
    int[][] grid = new int[SIZE][SIZE];

    ArrayList<String> formes = new ArrayList<String>();

    ArrayList<Coordonnee[]> _formes = new ArrayList<>();

    ArrayList<Bloc> blocs = new ArrayList<>();


    void initForme(){
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
        return b.org+":"+b.type+" "+toString(b.contrainte);
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

    void initialisation(){
        int[][] tmp;
        do{
            tmp = initGrid(0, new int[length(grid, 1)][length(grid, 2)], 0, new int[length(grid, 1)][length(grid, 2)]);
        }while(tmp == null);
        grid = tmp;
    }

    void printGrid(){
        print("  ");
        for(int i = 0; i < length(grid, 1); i++){
            print(i+" ");
        }
        println();
        for(int i = 0; i < length(grid, 1)*2+1; i++){
            print("-");
        }
        println();
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


    void initFormes(){

        boolean[] helper = new boolean[length(grid, 1)*length(grid, 1)];
        for(int idx = 0; idx < length(helper); idx++){
            helper[idx] = true;
        }

        for(int l = 0; l < length(grid, 1); l++){
            for(int c = 0; c < length(grid, 2); c++){
                if(helper[l*SIZE+c]){
                    int idF;
                    Coordonnee[] coords;
                    boolean badForme;
                    int idx = 0;
                    do{
                        idx++;
                        idF = idx < 100 ? getRandom(9)+1:0;
                        print("idxF random = "+idF+" , et les formes: ");
                        printCoords(_formes.get(idF));
                        coords = _formes.get(idF);
                        badForme = false;
                        for(int idxco = 0; idxco < length(coords); idxco++){
                            int y = l + getY(coords[idxco]);
                            int x = c + getX(coords[idxco]);
                            if( x < 0
                                    || y < 0
                                    || x >= length(grid, 2)
                                    || y >= length(grid, 1)
                                    || !helper[y*SIZE+x]){
                                badForme = true;
                                break;
                                    }
                        }
                    }while(badForme);
                    for(int idxco = 0; idxco < length(coords); idxco++){
                        int idxC = getX(coords[idxco]);
                        int idxL = getY(coords[idxco]);
                        helper[(l+idxL)*SIZE+(c+idxC)] = false ;
                    }
                    println((l*length(grid,1)+c)+":"+idF);
                    blocs.add(newBloc(l*SIZE+c, idF));
                }
            }
        }
    }
    int car2Int(char c){
        return (int)(c) - 48;
    }

    void printCoords(Coordonnee[] tab){
        for(int idx = 0; idx < length(tab) ; idx++){
            print(String.format("%4s",toString(tab[idx])));
        }
        println();
    }

    void algorithm(){
        initForme();
        initialisation();
        printGrid();
        initFormes();

        for(int i = 0; i < _formes.size(); i++){
            print(i+" :  ");
            for(int j = 0; j < length(_formes.get(i)); j++){
                print(toString(_formes.get(i)[j])+"  ");
            }
            println();
        }
        Bloc b = newBloc(0,2);
        setContrainte(b, newContrainte(12,'+'));
        setColor(b,ANSI_COLORS[1]);
        println("un bloc :\n\t"+b.org+":"
                +getType(b)+" de color "
                +toUpperCase(getColor(b))
                +" dont les coordonnees sont: "
                +toString(_formes.get(getOrg(b))[0])
                +" avec une contrainte liées "+toString(getContrainte(b)));

        b = newBloc(3,1);
        setContrainte(b, newContrainte(9,'*'));
        setColor(b,ANSI_COLORS[4]);
        println("un bloc :\n\t"+b.org+":"
                +getType(b)+" de color "
                +toUpperCase(getColor(b))
                +" dont les coordonnees sont: "
                +toString(_formes.get(getType(b))[3])
                +" avec une contrainte liées "+toString(getContrainte(b)));
    }
}

class Bloc{
    int org;
    int type;
    Contrainte contrainte;
    String color;
}

class Contrainte{
    int clue;
    char operator;
}


class Coordonnee{
    int y;
    int x;
}
