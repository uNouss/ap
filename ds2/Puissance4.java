class Puissance4 extends Program{
    final char O = 'O';
    final char X = 'X';
    final char V = ' ';
    final String PROMPT = ">$: ";

    char[][] grille = new char[6][7];

    void initialisationGrille(){
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            for(int idxC = 0; idxC < length(grille, 2); idxC++){
                grille[idxL][idxC] = V;
            }
        }
    }

    void afficherGrille(){
        afficherEntete(length(grille, 2));
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            afficheSeparateur(length(grille, 2));
            for(int idxC = 0; idxC < length(grille, 2); idxC++){
                print(String.format("|%3s", " "+grille[idxL][idxC]+" "));
            }
            println("|");
        }
        afficheSeparateur(length(grille,2));
    }

    void afficherEntete(int n){
        for (int i = 0; i < n; i++){
            print("  "+(i+1)+" ");
        }
        println(" ");
    }
    void afficheSeparateur(int n) {
        for (int i = 0; i < n; i++){
            print("+---");
        }
        println("+");
    }

    boolean estPleine(){
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            for(int idxC = 0; idxC < length(grille, 2); idxC++){
                if(grille[idxL][idxC] == V) return false;
            }
        }
        return true;
    }

    int calculIdentiqueVerticale(char symbole, int row, int col){
        int compteur = 1; // prise en compte de la case courante
        int sens = 1;
        boolean suffisant = false;
        while( (row+sens) < length(grille, 1) && symbole == grille[row+sens][col] && !(suffisant=(compteur >= 4))){
            compteur += 1;
            sens += 1;
        }
        if(suffisant) return compteur;
        sens = -1;
        while((row+sens) >= 0 && symbole == grille[row+sens][col] && !(suffisant=(compteur >= 4))){
            compteur += 1;
            sens -= 1;
        }
        return compteur;
    }

    int calculIdentiqueHorizontale(char symbole, int row, int col){
        int compteur = 1; // prise en compte de la case courante
        int sens = 1;
        boolean suffisant = false;
        while( (col+sens) < length(grille, 2) && symbole == grille[row][col+sens] && !(suffisant=(compteur >= 4))){
            compteur += 1;
            sens += 1;
        }
        if(suffisant) return compteur;
        sens = -1;
        while((col+sens) >= 0 && symbole == grille[row][col+sens] && !(suffisant=(compteur >= 4))){
            compteur += 1;
            sens -= 1;
        }
        return compteur;
    }

    boolean estVictoire(int position){
        int col, row = position/length(grille, 2), position%length(grille, 2);
        char courant = grille[row][col];
        // calculer verticalement le nombre d'occurence successive de la valeur courante
        if( ( calculIdentiqueVerticale(courant, row, col) >= 4)
            || ( calculIdentiqueHorizontale(courant, row, col) >= 4 )
            || ( calculIdentiqueDiagonaleLTR(courant, row, col) >= 4 )
            || ( calculIdentiqueDiagonaleRTL(courant, row, col) >= 4 ) ) return true;
        else return false;
        // calculer horizontalement le nombre d'occurence successive de la valeur courante
        // calculer diagonalement dans le sens haut gauche bas droite le nombre d'occurence de la valeur courante
        // calculer diagonalement dans le sens haut droit bas gauche le nombre d'occurence de la valeur courante
        // et si on trouve un nombre d'au moins 4 occurances identiques dans un des sens, il y a victoire
        // essayer de simplifier le code en essayant de factoriser de partie de code semblable
    }
    boolean estVictoire_(){
        char courant, suivant = V, suivantDuSuivant = V, suivantDuSuivantDuSuivant = V;
        for(int idxL = 0; idxL < length(grille,1); idxL++){
            for(int idxC = 0; idxC < length(grille,2); idxC++){
                courant = grille[idxL][idxC];
                if(courant != V){
                    if(idxL+1 < length(grille, 1)){ // je fixe une colonne et varie la ligne à la recherche d'un alignement
                        suivant = grille[idxL+1][idxC];
                        if(idxL+2 < length(grille, 1)){
                            suivantDuSuivant = grille[idxL+2][idxC];
                            if( idxL+3 < length(grille, 1)){
                                suivantDuSuivantDuSuivant = grille[idxL+3][idxC];
                                if( courant == suivant
                                        && suivant == suivantDuSuivant
                                        && suivantDuSuivant == suivantDuSuivantDuSuivant ) return true;
                            }
                        }
                    }
                    if(idxC+1 < length(grille, 2)){ // je fixe une ligne et fait varier la colonne à la recherche d'un alignement
                        suivant = grille[idxL][idxC+1];
                        if(idxC+2 < length(grille, 2)){
                            suivantDuSuivant = grille[idxL][idxC+2];
                            if( idxC+3 < length(grille, 2)){
                                suivantDuSuivantDuSuivant = grille[idxL][idxC+3];
                                if( courant == suivant
                                        && suivant == suivantDuSuivant
                                        && suivantDuSuivant == suivantDuSuivantDuSuivant ) return true;
                            }
                        }
                    }
                    if(idxL+1 < length(grille,1) && idxC+1 < length(grille, 2)){ // je fais bouger ligne et colonne pour regarder dans la 1ere diagonale
                        suivant = grille[idxL+1][idxC+1];
                        if(idxL+2 < length(grille, 1) && idxC+2 < length(grille, 2)){
                            suivantDuSuivant = grille[idxL+2][idxC+2];
                            if(idxL+3 < length(grille, 1) && idxC+3 < length(grille, 2)){
                                suivantDuSuivantDuSuivant = grille[idxL+3][idxC+3];
                                if( courant == suivant
                                        && suivant == suivantDuSuivant
                                        && suivantDuSuivant == suivantDuSuivantDuSuivant) return true;
                            }
                        }
                    }
                    if(idxL+1 < length(grille,1) && idxC-1 >= 0){ // je fais bouger ligne et colonne pour regarder dans la 2nd diagonale
                        suivant = grille[idxL+1][idxC-1];
                        if(idxL+2 < length(grille,1) && idxC-2 >= 0){
                            suivantDuSuivant = grille[idxL+2][idxC-2];
                            if(idxL+3 < length(grille,1) && idxC - 3 >= 0){
                                suivantDuSuivantDuSuivant = grille[idxL+3][idxC-3];
                            if( courant == suivant
                                    && suivant == suivantDuSuivant
                                    && suivantDuSuivant == suivantDuSuivantDuSuivant) return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean estPleineColonne(int col){
        for(int idxL = 0; idxL < length(grille, 1); idxL++){
            if(grille[idxL][col] == V ) return false;
        }
        return true;
    }

    boolean estValide(int col){
        return (col >= 0 && col < length(grille, 2) && !estPleineColonne(col));
    }

    char changer(char joueur){
        return (joueur == 'X') ? 'O': 'X';
    }

    int jouerALaColonne(char joueur, int col){
        int pos = length(grille, 1) - 1;
        while(grille[pos][col] != V){
            pos -= 1;
        }
        grille[pos][col] = joueur;
        return pos*length(grille, 2) + col;
    }

    int joue(char joueur){
        int col = -1;
        do{
            print("JOUEUR_"+joueur+" "+PROMPT);
            col = readInt();
        }while(!estValide(col-1));
        return jouerALaColonne(joueur,col-1);
    }

    void algorithm(){
        initialisationGrille();
        char joueur = ((int)(random()*2) == 1) ? 'O' : 'X'; // TIRAGE AU SORT DU DEBUTANT
        int position = -1;
        do{ // on joue
            afficherGrille(); //afficher grille
            position = joue(joueur); // le joueur courant joue
            joueur = changer(joueur); // on change de joueur
        }while(!estPleine() && !estVictoire(position));
        afficherGrille();
        if(estVictoire(position)){
            println("JOUEUR_" + changer(joueur) + " a gagné");
        }else{
            println("match null");
        }
    }
}
