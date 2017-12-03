
class JeuDeNim2017 extends Program {

    void algorithm() {
        final int NB_STYLOS_AU_DEBUT = 42;
        final boolean JOUEUR_1 = true;
        final boolean JOUEUR_2 = false;

        int stylos = NB_STYLOS_AU_DEBUT;
        int nbStylosARetirer;
        boolean joueurCourant = JOUEUR_1;
        boolean jeuFini = false;

        // tant que le jeu n'est pas fini
        while (!jeuFini) {
            //   affichage des stylos
            println(stylos);
            //   demander au joueurCourant un nb de stylos entre 1 et 3
            do {
                if (joueurCourant == JOUEUR_1) {
                    print("Joueur 1 : ");
                } else {
                    print("Joueur 2 : ");
                }
                nbStylosARetirer = readInt();
            } while (nbStylosARetirer < 1 || nbStylosARetirer > 3);
            //   on enlève le nb de stylos donnés par le joueur du jeu
            stylos = stylos - nbStylosARetirer;
            //   on passe à l'autre joueur
            if (joueurCourant == JOUEUR_1) {
                joueurCourant = JOUEUR_2;
            } else {
                joueurCourant = JOUEUR_1;
            }
            //joueurCourant = ! joueurCourant;
            // détecter la fin de partie pour sortir de la boucle
            if (stylos <= 1) {
                jeuFini = true;
            }
        }
        // afficher qui a gagné ?
    }

}
