class SauteMouton extends Program{
    final int TAILLE = 7;
    final char B = '>';
    final char N = '<';
    final char V = ' ';
    final String BC = "\033[31;1;4;5;7m";
    final String NC = "\033[34;1;4;5;7m";
    final String NOC = "\033[0m";

    final String PROMPT = ">$: ";

    char[] prairie = new char[TAILLE];
    final char[] GAGNER = new char[]{N, N, N, V, B, B, B};
    void testInitialiser(){
        initialiser();
        assertArrayEquals(new char[]{B, B, B, V, N, N, N},prairie);
    }
    void initialiser(){
        for(int i = 0; i < length(prairie); i++){
            prairie[i] = ( i < length(prairie)/2)? B : N;
        }
        prairie[length(prairie)/2] = V;
    }

    void testAfficher(){
        initialiser();
        assertEquals("> > > _ < < <",afficher());
    }


    String afficher(){
        String maPrairie = "";
        for(int i = 0; i < length(prairie); i++){
            maPrairie += (prairie[i] + " ");
        }
        return substring(maPrairie,0,length(maPrairie)-1);
    }

    void afficherPrairie(){
        afficherEntete(length(prairie));
        afficheSeparateur(length(prairie));
        for(int i = 0; i < length(prairie); i++){
            if ( prairie[i] == B ) print(String.format("|%3s", BC+" "+prairie[i]+" "+NOC));
            else if (prairie[i] == N ) print(String.format("|%3s", NC+" "+prairie[i]+" "+NOC));
            else print(String.format("|%3s", prairie[i]+" "));
        }
        println("|");
        afficheSeparateur(length(prairie));
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


    void testEstValide(){
        initialiser();
        assertFalse(estValide(3));
        assertFalse(estValide(-1));
        assertFalse(estValide(8));
        assertFalse(estValide(7));
        assertFalse(estValide(7/2));
        assertTrue(estValide(1));
        assertTrue(estValide(0));
        assertTrue(estValide(2));
        assertTrue(estValide(4));
        assertTrue(estValide(5));
        assertTrue(estValide(6));
    }

    boolean estValide(int indice){
        return (indice >= 0 && indice < length(prairie) && prairie[indice] != V);
    }

    int saisie(){
        int saisie;
        do{
            print(PROMPT);
            saisie = readInt();
        }while(!estValide(saisie));
        return saisie;
    }


    void testAvancer(){
        initialiser();
        println(afficher());
        assertTrue(avancer(2));
        assertTrue(avancer(4));
        assertTrue(avancer(3));
        assertTrue(avancer(5));
        assertFalse(avancer(0));
        assertTrue(avancer(6));
        assertFalse(avancer(3));
    }

    int trouverIndiceVide(){
        for(int i = 0; i < length(prairie); i++){
            if(prairie[i] == V) return i;
        }
        return -1;
    }

    boolean avancer(int idxM){
        boolean estPossible = false;
        int indiceVide = trouverIndiceVide();
        if(prairie[idxM] == B ){
            if(( idxM + 1 < length(prairie)  && prairie[idxM+1] == V)
                    || (idxM + 2 < length(prairie)  && prairie[idxM+2] == V)){
                prairie[indiceVide]   = B;
                prairie[idxM]         = V;
                estPossible           = true;
                    }
        }
        else if (prairie[idxM] == N){
            if(( idxM - 1 >= 0  &&  prairie[idxM-1] == V )
                    || ( idxM - 2 >= 0  && prairie[idxM-2] == V)){
                prairie[indiceVide]   = N;
                prairie[idxM]         = V;
                estPossible           = true;
                    }
        }
        return estPossible;
    }

    void testVictoire(){
        initialiser();
        assertFalse(victoire());
        prairie[0] = N;
        prairie[1] = N;
        prairie[2] = N;
        prairie[3] = V;
        prairie[4] = B;
        prairie[5] = B;
        prairie[6] = B;
        assertTrue(victoire());
    }

    boolean victoire(){
        int i = 0;
        while(i < length(prairie) && prairie[i] == GAGNER[i]){
            i = i + 1;
        }
        if( i == length(prairie)) return true;
        return false;
    }

    void testBloque(){
        initialiser();
        println(afficher());
        assertFalse(bloque());
        prairie[0] = B;
        prairie[1] = B;
        prairie[2] = N;
        prairie[3] = B;
        prairie[4] = N;
        prairie[5] = N;
        prairie[6] = V;
        println(afficher());
        assertTrue(bloque());
    }

    boolean bloque(){
        int idxVide = trouverIndiceVide();
        if( idxVide - 2 >= 0 && prairie[idxVide - 2] == B) return false;
        if( idxVide - 1 >= 0 && prairie[idxVide - 1] == B) return false;
        if( idxVide + 1 < length(prairie) && prairie[idxVide + 1] == N) return false;
        if( idxVide + 2 < length(prairie) && prairie[idxVide + 2] == N) return false;
        return true;
    }


    void algorithm(){
        int idxM;
        initialiser();
        while ( !bloque() && !victoire()){
            afficherPrairie();
            idxM = saisie();
            avancer(idxM);
        }
        afficherPrairie();
        if(victoire()) println("win :)");
        else println("lose :(");
    }
}
