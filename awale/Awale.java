class Awale extends Program{
    final String PROMPT = ">$: ";
    final boolean JOUEUR_1 = true;
    final boolean JOUEUR_2 = false;

    boolean joueurCourant = (((int)random()*2) == 1 )?JOUEUR_1:JOUEUR_2;//tirage au sort

    int[] plateau = new int[12] ;

    int TAS_1 = 0;
    int TAS_2 = 0;

    void testInitialize(){
        initialize();
        int[] tab = new int[]{4,4,4,4,4,4,4,4,4,4,4,4};
        println();afficherPlateau();
        assertArrayEquals(tab, plateau);
    }
    void initialize(){
        for(int idx = 0; idx < length(plateau); idx++){
                plateau[idx] = 4;
        }
    }

    void afficherPlateau(){
        afficherEntete(length(plateau)/2, 0);
        afficheSeparateur(length(plateau)/2);
        for(int idx = 0; idx < length(plateau)/2; idx++){
            print(String.format("|%3s", plateau[idx] + " "));
        }
        println("| TAS JOUEUR_" + 0 + " = " + TAS_1);
        afficheSeparateur(length(plateau)/2);
        for(int idx = length(plateau)/2; idx < length(plateau); idx++){
            //print(plateau[idxL][idxC] + " ");
            print(String.format("|%3s", plateau[idx]+" "));
        }
        println("| TAS JOUEUR_" + 1 + " = " + TAS_2);
        afficheSeparateur(length(plateau)/2);
        afficherEntete(length(plateau), length(plateau)/2);
        println();
    }

    void afficherEntete(int n, int debut){
        for (int i = debut; i < n; i++){
            if(i < 10) print("  "+i+" ");
            else print(" "+i+" ");
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

    boolean estValide(int indice ){
        if(joueurCourant == JOUEUR_1) return ( indice >= 0 && indice < length(plateau)/2);
        else return (indice >= length(plateau)/2 && indice < length(plateau));
    }

    int deplacement(int indice){
        int nbGraine = plateau[indice];
        //println(nbGraine);
        plateau[indice] = 0;
        int pas = (indice < length(plateau)/2) ? -1: 1;
        int indiceDepart = indice + pas;
        int indiceFin = -1;
        while(nbGraine != 0){
            if(indiceDepart < 0){
                indiceDepart = length(plateau)/2;
                pas = 1;
            }else if( indiceDepart >= length(plateau)){
                indiceDepart = (length(plateau) - 1) - length(plateau)/2;
                pas = -1;
            }
            indiceFin = indiceDepart;
            plateau[indiceDepart] += 1;
            nbGraine -= 1;
            indiceDepart += pas;
        }
        //println(indiceFin);
        return indiceFin;
    }

    void calculerGain(int indiceArret){
        // si je dans le camp adverse
        // je verifie si j'ai 2 ou 3 graine dans la case sur la quelle je viens de terminer de semer, si oui je les prends et je les ajoute dans mon tas et prend en reculant toute les cases où  j'ai entre 2 et 3 graine du camp de mon adversaire.
        // j'arrête de prendre quand je suis plus dans le camp de mon adversaire ou j'arrive sur une case avec mois de 2 ou plus de 3 graine.
        println(indiceArret);
        if(joeurCourant == JOUEUR_1 && (indiceArret >= 0 && indiceArret < length(plateau)/2){
            while(jepeuxramasser()){
                // je ramasse jusqu'à ce que je sois bloqué
            }
        }else{
            
        }
    }

    void algorithm(){
        initialize();

        while(!estBloque() && !estVictoire()){
            afficherPlateau();
            int indice;
            do {
                if(joueurCourant == JOUEUR_1) print("JOUEUR_1 "+PROMPT);
                else print("JOUEUR_2 "+PROMPT);
                indice = readInt();
            }while(!estValide(indice));
            //println(saisie);
            int positionStop = deplacement(indice);
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
