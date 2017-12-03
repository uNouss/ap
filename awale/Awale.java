class Awale extends Program{
    final String PROMPT = ">$: ";
    final boolean JOUEUR_1 = true;
    final boolean JOUEUR_2 = false;

    boolean joueurCourant = (((int)random()*2) == 1 )?JOUEUR_1:JOUEUR_2;//tirage au sort

    int[][] plateau = new int[2][6];

    int TAS_1 = 0;
    int TAS_2 = 0;

    void testInitialize(){
        initialize();
        int[][] tab = new int[][]{
            {4,4,4,4,4,4},
                {4,4,4,4,4,4}
        };
        println();afficherPlateau();
        assertArrayEquals(tab, plateau);
    }
    void initialize(){
        for(int idxL = 0; idxL < length(plateau,1); idxL++){
            for(int idxC = 0; idxC < length(plateau, 2); idxC++){
                plateau[idxL][idxC] = 4;
            }
        }
    }
    void afficherPlateau(){
        afficherEntete(length(plateau, 2));
        for(int idxL = 0; idxL < length(plateau, 1); idxL++){
            afficheSeparateur(length(plateau, 2));
            for(int idxC = 0; idxC < length(plateau, 2); idxC++){
                //print(plateau[idxL][idxC] + " ");
                print(String.format("|%3s", plateau[idxL][idxC]+" "));
            }
            int tas = (idxL == 0) ? TAS_1: TAS_2;
            println("| TAS JOUEUR_" + (idxL+1) + " = " + tas);
        }
        afficheSeparateur(length(plateau,2));
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

    boolean estBloque(){
        return false;
    }

    boolean estVictoire(){
        if (TAS_1 > 24 || TAS_2  > 24)  return true;
        return false;
    }

    boolean estValide(int saisie, boolean joueurCourant){
        if(joueurCourant == JOUEUR_1) return ( saisie >= 0 && saisie < length(plateau, 2));
        else return (saisie >= length(plateau, 2) && saisie < 2*length(plateau, 2));
    }

    int deplacement(int saisie){
        //println();
        int row = saisie / length(plateau, 2);
        int col = saisie % length(plateau, 2);
        int adistribuer = plateau[row][col];
        println(row+", "+col+", "+adistribuer);
        int reste = 0;
        int marqueDernier = -1;
        if(row == 0){
            reste = adistribuer - col;
            if((adistribuer - col ) > 0) {// je reste dans mon camp
                plateau[row][col] = 0;
                for(int i = col-1; i >= 0; i--){
                    plateau[row][i] += 1;
                    adistribuer -= 1;
                }
                if(adistribuer > 0){
                    col = 0; row = 1;
                    while(adistribuer > 0){
                        plateau[row][col++] += 1;
                        adistribuer -= 1;
                    }
                }
            }else{
               println();
            }
        }
        else {
            if((adistribuer + col ) < length(plateau, 2)) {// je reste dans mon camp
                plateau[row][col] = 0;
                for(int i = col+1; i < length(plateau); i--){
                    plateau[row][i] += 1;
                    adistribuer -= 1;
                }
                if(adistribuer > 0){
                    col = 0; row = 1;
                    while(adistribuer > 0){
                        plateau[row][col++] += 1;
                        adistribuer -= 1;
                    }
                }
            }else{
                reste = length(plateau,2) - 1 - col;
                for(int i = col+1; i < length(plateau, 2); i++){
                    plateau[row][i] += 1;
                    adistribuer -= 1;
                }
                if(adistribuer > 0){
                    col = length(plateau, 2) - 1; row = 0;
                    while(adistribuer > 0){
                        plateau[row][col--] -= 1;
                        adistribuer -= 1;
                    }
                }
            }
        }
        return marqueDernier;
    }

    void calculerGain(int indiceArret{}
    void algorithm(){
        initialize();

        while(!estBloque() && !estVictoire()){
            afficherPlateau();
            int saisie;
            do {
                if(joueurCourant == JOUEUR_1) print("JOUEUR_1 "+PROMPT);
                else print("JOUEUR_2 "+PROMPT);
                saisie = readInt();
            }while(!estValide(saisie));
            //println(saisie);
            int positionStop = deplacement(saisie);
            calculerGain(positionStop);
            joueurCourant = ( joueurCourant == JOUEUR_1 )? JOUEUR_2: JOUEUR_1;

        }

    }
}
