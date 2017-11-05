/**
 * Controle TP - Mardi 3 novembre 2015 - Tous documents autorises
 * 
 * Lisez l'ensemble de ce programme avant de vous lancer dans la résolution
 * des differentes questions.
 * 
 * Remarque: ce n'est pas parce que les tests sont au vert que vous avez 
 * la solution la plus pertinente. Cependant, c'est quand meme une bonne 
 * option !
 * 
 * @author yann.secq@univ-lille1.fr
 */
class A_TURING_Alan extends Program {
    
    /**
     * PREMIERE PARTIE DU CONTROLE TP.
     * 
     * Dans cette premiere partie, on souhaite simuler un systeme de vote 
     * simplifie. Dans ce systeme, un vote correspond a un caractere: 'p' 
     * pour Pour, 'c' pour Contre, les autres caracteres sont consideres 
     * comme un vote Nul. 
     * 
     * Le vote d'un ensemble de citoyens est represente par une chaine de 
     * caracteres constituee de caracteres 'p', 'c' et d'autres caracteres 
     * (consideres comme Nul).
     */
    
    void testDecompteVote() {
        final String votes1 = "c*p**cp*cp*c";
        assertEquals(3,decompteVote(votes1, 'p'));
        assertEquals(4,decompteVote(votes1, 'c'));
        assertEquals(5,decompteVote(votes1, '*'));
    }
    
    /**
     * Cette fonction doit decompter le nombre de votes de type _choix_
     * contenus dans la chaine _votes_.
     * 
     * @param _votes_ une chaine de caracteres contenant les votes exprimes
     * @param _choix_ le vote a decompter dans la chaine de caracteres
     * @return le nombre de votes du type _choix_ dans la chaine _votes_
     */
    int decompteVote(String votes, char choix) {
	// a completer
	int compteur = 0;
	for(int i = 0; i < length(votes); i++){
	    if(charAt(votes,i) == choix ) compteur += 1;
	}
	return compteur;
    }
    
    void testPourcentageVote() {
        final String votes1 = "c*p**cp*cp*c"; // 12 votes exprimés
        assertEquals(0.25,   pourcentageVote(votes1, 'p')); // 3 sur 12
        assertEquals(0.3333, pourcentageVote(votes1, 'c')); // 4 sur 12
        assertEquals(0.4166, pourcentageVote(votes1, '*')); // 5 sur 12
    }
    
    /**
     * Cette fonction calcule le pourcentage d'un vote en retournant un reel 
     * compris entre 0.0 (0%) et 1.0 (100%).
     * 
     * @param _votes_ une chaine de caracteres contenant les votes exprimes
     * @param _choix_ le vote a decompte dans la chaine de caracteres
     * @return le pourcentage de _choix_ dans _votes_ (compris entre [0.0,1.0]
     */
    double pourcentageVote(String votes, char choix) {
	// a completer
	return decompteVote(votes,choix)*1.0/length(votes);
    }
    
    void testPlusDePourQueDeContre() {
        assertTrue( plusDePourQueDeContre("p"));
        assertTrue( plusDePourQueDeContre("pcpcpcpcp"));
        assertFalse(plusDePourQueDeContre("pcpcpcpc"));
        assertTrue( plusDePourQueDeContre("p*c p*cp$cp*cp"));
    }
    
    /**
     * Cette fonction determine si un vote est positif, c'est-a-dire si il 
     * y a plus de Pour exprimes que de Contre. Comme dans la vraie vie, 
     * on ne prend pas en consideration les votes Nul ...
     * 
     * @param _votes_ une chaine de caracteres contenant les votes exprimes
     * @return true si il y a plus de pour que contre et false sinon
     */
    
    boolean plusDePourQueDeContre(String votes) {
	// a completer
        return (pourcentageVote(votes,'p') > pourcentageVote(votes,'c'));
    }
    
    /**
     * DEUXIEME PARTIE DU CONTROLE TP.
     * 
     * Dans cette seconde partie, on cherche a resoudre un certain nombre 
     * de question portant sur des proprietes mathematiques.
     * 
     */
    
    void testCouples() {
        assertEquals("(0,0)", couples(0));
        assertEquals("(0,2)(1,1)(2,0)", couples(2));
        assertEquals("(0,3)(1,2)(2,1)(3,0)", couples(3));
    }
    
    /**
     * Cette fonction determine tous les couples d'entiers x et y dont la 
     * somme est egale a n.
     *
     * @param _n_ le nombre devant a etre egal a la somme des deux nombres du couple
     * @return une chaine de caracteres constituee des couples representes sous 
     * la forme (x,y) places les uns a la suite des autres.
     */

    String couples(int n) {
	//a completer
	String listeCouples = "(0,"+n+")";
	for(int i=n-1; i>=0; i--)
	    listeCouples += "("+(n-i)+","+i+")";
        return listeCouples;
    }
    
    /**
     * 6     = 1 + 2 + 3
     * 28    = 1 + 2 + 4 + 7 + 14
     * 496   = 1 + 2 + 4 + 8 + 16 + 31 + 62 + 124 + 248
     * 8 128 = 1 + 2 + 4 + 8 + 16 + 32 + 64 + 127 + 254 + 508 + 1016 + 2032 + 4064)
     */

    void testEstParfait() {
        assertTrue(estParfait(6));
        assertTrue(estParfait(28));
        assertTrue(estParfait(496));
        assertTrue(estParfait(8128));
        
        assertFalse(estParfait(7));
        assertFalse(estParfait(12));
    }
    
    /**
     * Un entier naturel n est parfait si il est egal a la somme de ses 
     * diviseurs stricts (ie. strictement inferieurs a n).
     * 
     * Exemple: 6 est parfait car ses diviseurs stricts sont 1, 2 et 3 et 
     * que leur somme vaut 1+2+3 = 6. D'autres exemples sont donnes  
     * dans le commentaire de la fonction 'testEstParfait'.
     * 
     * @param _n_ le nombre a tester s'il est parfait
     * @return true si _n_ est un nombre parfait, faux sinon
     */

    boolean estParfait(int n) {
	//a completer
	int somme = 1;
	for(int i = 2; i < (n/2) + 1 ; i++){
	    if( n%i == 0) somme += i;
	}
        return (somme == n);
    }
    
}
