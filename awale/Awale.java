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
        //  je recupere les coordonnes de la case de depart
        //  je compte le nombre de graine qui s'y trouve
        //  je calcule de combien de case dans le sens de mon deplacement possible je suis du bord
        //  je forme des paques de 6 avec le nombre de graine que j'ai dans la case choisie
        //  sachant que le premier paquet ne fait pas 6 mais 6-l'ecart qui me separe de mon bord.
        //  ensuite je fais une boucle pour aller d'un camp à l'autre selon de nombre de paquet obtenu: p1 + p2 + p3 + ... + pn
        //  je calcule aussi ma dernière position d'où je me suis arreté pour après pouvoir ammasser mes graines gagnées
        //
        int row = saisie / length(plateau, 2);
        int col = saisie % length(plateau, 2);
        int adistribuer = plateau[row][col];
        println(row+", "+col+", "+adistribuer);
        int distanceDuBord = (joueurCourant == JOUEUR_1) ? col:length(plateau,2) - 1 - col;
        // je fabrique les paquets
        int premierPaquet = distanceDuBord;
        int nbpaquetComplet = (adistribuer-premierPaquet)/length(plateau,2);
        int dernierPaquer = (adistribuer-premierPaquet) % length(plateau,2);
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

    void calculerGain(int indiceArret){
        // si je dans le camp adverse
        // je verifie si j'ai 2 ou 3 graine dans la case sur la quelle je viens de terminer de semer, si oui je les prends et je les ajoute dans mon tas et prend en reculant toute les cases où  j'ai entre 2 et 3 graine du camp de mon adversaire.
        // j'arrête de prendre quand je suis plus dans le camp de mon adversaire ou j'arrive sur une case avec mois de 2 ou plus de 3 graine.
    }

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

/*
class Joueur {
    int point;
    int nom;
    int camp;
}
*/
