class Keen1 extends Program{
    final int SIZE = 7;
    int[][] grid = new int[SIZE][SIZE];


    void initialisation(){
        int[][] rows = new int[length(grid, 1)][length(grid, 2)];
        int[][] cols = new int[length(grid, 1)][length(grid, 2)];
        do{
            grid = initGrid(0, rows, 0, cols);
        }while(grid == null);
    }

    void printGrid(){
        print("  ");
        for(int i = 0; i < length(grid, 1); i++){
            print((i+1)+" ");
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
        if( r == length(grid, 1) && c == 0) return rows;
        int n = getURandom(rows[r],cols[c]);
        println("random n = "+n);
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

    void algorithm(){// main program
        initialisation();
        printGrid();
    }
}
