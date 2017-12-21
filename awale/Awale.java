class Awale extends Program{
    final String NOCOLOR = "\033[0m";
    final String PROMPT = ">$: ";

    Joueur j1 = new Joueur();
    Joueur j2 = new Joueur();

    int[] plateau = new int[12] ;

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
            if ( plateau[idx] == 0 ) print(String.format("|%3s", "   "));
            else if(plateau[idx] < 10 ) print(String.format("|%3s", j1.color+" " +plateau[idx]+ " "+NOCOLOR ));
            else print(String.format("|%3s", j1.color+"" +plateau[idx]+ " "+NOCOLOR ));
        }
        println("|       Score# " + j1.color + j1.point + NOCOLOR);
        afficheSeparateur(length(plateau)/2);
        for(int idx = length(plateau)/2; idx < length(plateau); idx++){
            if ( plateau[idx] == 0 ) print(String.format("|%3s", "   "));
            else if(plateau[idx] < 10 ) print(String.format("|%3s",  j2.color+" " +plateau[idx]+" "+NOCOLOR));
            else print(String.format("|%3s", j2.color+"" +plateau[idx]+ " "+NOCOLOR ));
        }
        println("|       Score# " + j2.color + j2.point + NOCOLOR);
        afficheSeparateur(length(plateau)/2);
        afficherEntete(length(plateau), length(plateau)/2);
        println();
    }

    void afficherEntete(int n, int debut){
        for (int i = debut; i < n; i++){
            if(i < 10) print("  "+i+" ");
            else print("  "+i);
        }
        println(" ");
    }
    void afficheSeparateur(int n) {
        for (int i = 0; i < n; i++){
            print("+---");
        }
        println("+");
    }


    void monNombreDegraine(){
        int compteurJ1 = 0;
        int compteurJ2 = 0;
        for(int idx = 0; idx < length(plateau); idx++){
            if( idx < length(plateau)/2) compteurJ1 += plateau[idx];
            else compteurJ2 += plateau[idx];
        }
        j1.graines = compteurJ1;
        j2.graines = compteurJ2;
        println(j1.graines + " et " + j2.graines);
    }

    boolean estAffamee(){
        return (j1.tour)?(j2.graines == 0):(j2.graines == 0);
    }

    void nourrirAdversaire(){
    }

    boolean estBloque(){
        return false;
    }

    boolean estVictoire(){
        if (j1.point > 24 || j2.point  > 24)  return true;
        else if(j1.tour && j1.graines == 0) return true;
        else if(j2.tour && j2.graines == 0) return true;
        return false;
    }

    boolean estValide(int indice ){
        if(j1.tour) return ( indice >= 0 && indice < length(plateau)/2 && plateau[indice] != 0);
        else return (indice >= length(plateau)/2 && indice < length(plateau) && plateau[indice] != 0);
    }

    int deplacement(int indice){
        int nbGraine = plateau[indice];
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
            if( indiceDepart != indice ){
                plateau[indiceDepart] += 1;
                nbGraine -= 1;
            }
            indiceFin = indiceDepart;
            indiceDepart += pas;
        }
        return indiceFin;
    }

    void calculerGain(int indice){
        //println(indice);
        if(j1.tour){
            boolean peuxRamasser  = ((plateau[indice] == 2 || plateau[indice] == 3) && (indice >= length(plateau)/2 && indice < length(plateau)));
            while(peuxRamasser){
                j1.point += plateau[indice];
                plateau[indice] = 0;
                indice -= 1;
                peuxRamasser  = ((plateau[indice] == 2 || plateau[indice] == 3) && (indice >= length(plateau)/2 && indice < length(plateau)));
            }
        }else {
            boolean peuxRamasser  = ((plateau[indice] == 2 || plateau[indice] == 3) && (indice >= 0 && indice < length(plateau)/2));
            while(peuxRamasser){
                j2.point += plateau[indice];
                plateau[indice] = 0;
                indice += 1;
                peuxRamasser  = ((plateau[indice] == 2 || plateau[indice] == 3) && (indice >= 0 && indice < length(plateau)/2));
            }
        }
    }

    void algorithm(){
        initialize();

        print("Joueur 1, Entrez votre nom: ");
        j1.nom = readString();
        print("Joueur 2, Entrez votre nom: ");
        j2.nom = readString();

        j1.point = 0; j2.point = 0;
        j1.color = "\033[31m\033[1m\033[5m";
        j2.color = "\033[34m\033[1m\033[5m";

        if((int)(random()*2) == 1) { j1.tour = true; j2.tour = false; }
        else { j1.tour = false; j2.tour = true ; }

        while(!estBloque() && !estVictoire()){
            afficherPlateau();
            int indice;
            do {
                if(j1.tour) print(j1.color+j1.nom+" ["+0+" - "+((length(plateau)/2)-1)+"]"+PROMPT+NOCOLOR);
                else print(j2.color+j2.nom+" ["+((length(plateau)/2))+" - "+(length(plateau)-1)+"]"+PROMPT+NOCOLOR);
                indice = readInt();
            }while(!estValide(indice));
            //si adversaire affame, je dois le nourrir et si je ne peux pas on fait les compte et le jeux se termine.
            int positionStop = deplacement(indice);
            calculerGain(positionStop);
            if(j1.tour) { j1.tour = false; j2.tour = true; }
            else { j1.tour = true; j2.tour = false; }
        }
        afficherPlateau();
        if(estVictoire()){
            if(j1.point > j2.point) println(j1.nom+" WIN !!! Fin du jeu...");
            else println(j2.nom+" WIN !!! Fin du jeu...");
        }
        else{
            println("BLOCAGE");
        }
    }
}

class Joueur {
    boolean tour;
    int point;
    String nom;
    String color;
    int graines = 24;
}
// demo http://paste.ubuntu.com/26115253/
// http://paste.ubuntu.com/26115441/
// http://paste.ubuntu.com/26115472/
// http://paste.ubuntu.com/26115556/
// https://asciinema.org/a/KK0Itm2LQUh8gxNXAeleb5RXE
// http://paste.ubuntu.com/26119184/
// http://s.helan.free.fr/awale/conseils/
// http://s.helan.free.fr/awale/regles/
