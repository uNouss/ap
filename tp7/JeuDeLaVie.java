class JeuDeLaVie extends Program{
    final int TAILLE = 20;
    final boolean MORTE = false;
    final boolean VIVANTE = true;
    final String USAGE = "USAGE: Enter for play / Q for quit";

    boolean[][] monde = new boolean[TAILLE][TAILLE];
    boolean[][] mondeParallele = new boolean [length(monde,1)][length(monde,2)];

    void initialize(){
        for(int idxL = 0; idxL < length(monde, 1); idxL++){
            for(int idxC = 0; idxC < length(monde, 2); idxC++){
                monde[idxL][idxC] = ((int)(random()*2) == 1)?VIVANTE:MORTE;
            }
        }
    }

    void printMonde(){
        afficherEntete(length(monde, 2));
        for(int idxL = 0; idxL < length(monde, 1); idxL++){
            afficheSeparateur(length(monde, 2));
            for(int idxC = 0; idxC < length(monde, 2); idxC++){
                //print(monde[idxL][idxC] + " ");
                String cellule = (monde[idxL][idxC] == VIVANTE ) ? String.format("|%3s", "O "): String.format("|%3s", "  ");
                print(cellule);
            }
            println("| " + idxL);
        }
        afficheSeparateur(length(monde,1));
        println();
    }

    void afficherEntete(int n){
        for (int i = 0; i < n; i++){
            if( i < 10) print("  "+i+" ");
            else if ( i < 100) print(" "+i+" ");
            else if ( i < 1000 ) print(" "+i);
            else print(i);
        }
        println(" ");
    }
    void afficheSeparateur(int n) {
        for (int i = 0; i < n; i++){
            print("+---");
        }
        println("+");
    }

    boolean finDuMonde(){
        for(int idxL = 0; idxL < length(monde, 1); idxL++){
            for(int idxC = 0; idxC < length(monde, 2); idxC++){
                if(monde[idxL][idxC] == VIVANTE ) return false;
            }
        }
        return true;
    }

    int calculeNBVoisineVivante(int row, int col){
        int nbVoisineVivante = 0;

        int dRow = (row - 1 >= 0) ? (row - 1): 0;
        int fRow = (row + 1 < length(monde, 1)) ? ( row + 1) : length(monde, 1) - 1;
        int dCol = (col - 1 >= 0) ? (col - 1): 0;
        int fCol = (col + 1 < length(monde, 2)) ? ( col + 1) : length(monde, 2) - 1;

        //println("pour ["+row+";"+col+"] = "+dRow+", "+fRow+"     "+dCol+", "+fCol);

        for(int idxL = dRow; idxL <= fRow; idxL++){
            for(int idxC = dCol; idxC <= fCol; idxC++){
                //println("\t("+idxL+", "+idxC+")");
                if(monde[idxL][idxC] == VIVANTE)
                    nbVoisineVivante += 1;
            }
        }
        return nbVoisineVivante;
    }

    void evolutionMonde(){
        for(int idxL = 0; idxL < length(monde, 1); idxL++){
            for(int idxC = 0; idxC < length(monde, 2); idxC++){
                int nbVoisineVivante = calculeNBVoisineVivante(idxL, idxC);
                // là on peut appliquer les règles d'évolution
                boolean cellule = monde[idxL][idxC];
                if( cellule == MORTE && nbVoisineVivante == 3){
                    mondeParallele[idxL][idxC] = VIVANTE;
                }
                else if( cellule == VIVANTE && ( nbVoisineVivante == 2 || nbVoisineVivante == 3  )){
                    mondeParallele[idxL][idxC] = VIVANTE;
                }
                else mondeParallele[idxL][idxC] = MORTE;
            }
        }
        monde = mondeParallele;
    }

    void algorithm(){
        // règle d'évolution:
        // si une cellule donnée est morte mais qu'elle possède exactement 3 voisines vivantes
        //      alor elle renait
        // si une cellule donnée est vivante mais qu'elle possède moins de 2 ou plus de 3 voisines vivantes
        //      alors elle meurt

        initialize();
        printMonde();

        println(USAGE);
        String STOP = toUpperCase(readString());

        while(!equals("Q", STOP) && !finDuMonde()){
            evolutionMonde();
            printMonde();
            STOP = toUpperCase(readString());
        }
        //printMonde();
    }

}
// demo : https://asciinema.org/a/OgLmlGgAOYY5LWpm6bsK5cveA
