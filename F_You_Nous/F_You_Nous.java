/**
 * Algorithmique et Programmation - Controle TP 2 - Janvier 2018
 * 
 * Le but de cet exercice est de realiser une calculatrice simplifiee
 * pouvant evaluer des expressions arithmetiques impliquant des 
 * additions et des soustractions.
 * 
 * Afin de réaliser cela, la premiere partie consiste a ecrire un 
 * certain nombre de fonctions qui sont ensuite utilisees afin 
 * de realiser la calculatrice.
 * 
 * La plupart du temps, vous avez a ecrire le corps des fonctions 
 * et parfois aussi les tests associes.
 * 
 * A la fin du TP, vous devez rendre une archive contenant les deux
 * fichiers a creer (celui que vous etes en train de lire mais renommer 
 * avec votre groupe, nom et prenom) et le type Token dont on a besoin
 * pour la seconde partie du programme.
 * 
 * ATTENTION : 
 * - avant toute chose renommer ce fichier avec votre groupe, nom et 
 *   prenom 'Groupe_NOM_Prenom' (ex : A_TURING_Alan.java)
 * - le premier objectif du TP est de retrouver toutes les signatures 
 *   des fonctions appelees dans les tests afin que votre programme 
 *   compile et qu'ils puissent etre executes avec tous les tests en rouge
 *   Pour cela, commencez par lire attentivement l'ensemble des tests pour
 *   determiner les signature, puis creez le type Token (toujours selon les
 *   tests !)
 * - le second objectif consiste a ecrire le corps des fonctions dont vous 
 *   avez trouve les signatures.
 * 
 * UN PROGRAMME RENDU QUI NE COMPILE PAS ENTRAINERA UNE FORTE PENALISATION !
 * 
 * IL EST INTERDIT DE MODIFIER LES TESTS !
 */
class F_You_Nous extends Program {

    void testEstUnChiffre() {
        for (int chiffre = 0; chiffre < 10; chiffre ++) {
            assertTrue(estUnChiffre((char) ('0' + chiffre)));
        }
        assertFalse(estUnChiffre('+'));
        assertFalse(estUnChiffre('-'));
    }

    /**
     * Retourne true si le caractere correspond a un chiffre 
     * et false sinon.
     */
    // signature de estUnChiffre(...
    boolean estUnChiffre(char car){
        return car >= '0' && car <= '9';
    }

    void testEstUnOperateur() {
        for (int chiffre = 0; chiffre < 10; chiffre ++) {
            assertFalse(estUnOperateur((char) ('0' + chiffre)));
        }
        assertTrue(estUnOperateur('+'));
        assertTrue(estUnOperateur('-'));
    }

    /**
     * Retourne true si le caractere correspond a un operateur 
     * (+ ou -) et false sinon.
     */
    // signature de estUnOperateur(...
    boolean estUnOperateur(char car){
        boolean estOperateur = false;
        return carEstDans("+-*/", car);
    }

    boolean carEstDans(String s, char c){
        if(length(s) == 0) return false;
        if(charAt(s,0) == c) return true;
        return carEstDans(substring(s,1,length(s)), c);
    }

    void testEstUnNombre() {
        assertTrue (estUnNombre("42"));
        assertFalse(estUnNombre("42+"));
    }

    /**
     * Retourne true si le la chaine n'est constituee que de 
     * chiffres et false sinon.
     */
    // signature de estUnNombre(...
    boolean estUnNombre(String s){
        for(int idx = 0; idx < length(s); idx++){
            if( ! estUnChiffre(charAt(s, idx))) return false;
        }
        return true;
    }

    void testEntierEnChaineVersEntierEnInt() {
        assertEquals(42, entierEnChaineVersEntierEnInt("42"));
        assertEquals(0,  entierEnChaineVersEntierEnInt("0"));
    }

    /**
     * QUESTION BONUS : ecrivez cette fonction sans utiliser stringToInt ! 
     * 
     * Il est conseille de d'abord passer cette question et y revenir plus
     * tard si vous etes bloques sur d'autres questions plus loin dans 
     * l'enonce.
     * 
     * ON SUPPOSE QUE LA CHAINE NOMBRE NE CONTIENT QUE DES CHIFFRES
     * 
     * Convertit le nombre exprime sous la forme d'une chaine de 
     * caractere en une information de type entier.
     */
    int entierEnChaineVersEntierEnInt(String nombre) {
        return stringToInt(nombre);
    }

    /** 
     * Un token est soit un nombre, soit un operateur. 
     */
    void testNombreDeTokens() {
        assertEquals(0, nombreDeTokens(""));
        assertEquals(1, nombreDeTokens("42"));
        assertEquals(1, nombreDeTokens("+"));
        assertEquals(2, nombreDeTokens("42+"));
        assertEquals(3, nombreDeTokens("42+42"));
    }

    /** 
     * Retourne le nombre de tokens present dans la chaine.
     */
    // signature de nombreDeTokens(...
    int nombreDeTokens(String s){
        /*int nbTokens = 0;
        boolean marque = false;
        for(int idx = 0; idx < length(s); idx++){
            int debut = idx;
            String tmp = "";
            while(debut < length(s) && !marque){
                if ( estUnCartmp += charAt(s, debut);
            }
        }
        return nbTokens;*/
        int compteur = 0;
        boolean estUnChiffre = false;
        boolean estOperateur = false;
        for(int i = 0; i < length(s); i++){
            if (!estUnChiffre && estUnChiffre(charAt(s,i)))
                compteur++;
            else if ( !estOperateur && estUnOperateur(charAt(s,i)))
                compteur++;
            estUnChiffre = estUnChiffre(charAt(s,i));
            estOperateur = estUnOperateur(charAt(s,i));
        }
        return compteur;
    }

    void testDecoupeExpression() {
        assertArrayEquals(new String[]{"42"}, decoupeExpression("42"));
        assertArrayEquals(new String[]{"42", "+"}, decoupeExpression("42+"));
        assertArrayEquals(new String[]{"42", "+", "421", "-", "7"}, 
                decoupeExpression("42+421-7"));
    }

    /**
     * Retourne l'expression passee en parametre sous la forme d'un 
     * tableau de chaines de caracteres dont chaque case ne contient 
     * qu'un seul token.
     */
    //signature de decoupeExpression(...
    String[] decoupeExpression(String s){
        String[] res = new String[nombreDeTokens(s)];
        int idx = 0;
        int debut = 0;
        while(idx < length(res)){
            boolean estUnChiffre = false;
            res[idx] = "";
            while( debut < length(s) && !estUnChiffre){
                if( estUnChiffre(charAt(s,debut)))
                    res[idx] += charAt(s,debut);
                estUnChiffre = estUnChiffre(charAt(s,debut));
                debut += 1;
            }
            idx += 1;
        }
        return res;
    }

    /**
     * On souhaite maintenant pouvoir manipuler les tokens sous 
     * la forme d'un nouveau type Token, constitue de deux 
     * champs/attributs : un pour representer le fait que ce token
     * est un operateur ou pas, un autre pour stocker la valeur de
     * de token. Lisez attentivement la fonction de test ci-dessous 
     * pour définir votre type token : INTERDIT DE CHANGER QUOIQUE 
     * CE SOIT DANS LE CODE DU TEST !
     */
    void testNewToken() {
        Token nombre = newToken(false, "42");
        assertFalse(nombre.estOperateur);
        assertEquals("42", nombre.valeur);
        Token operateur = newToken(true, "+");
        assertTrue(operateur.estOperateur);
        assertEquals("+", operateur.valeur);
    }

    /**
     * Construit une nouvelle structure initialisee avec les informations
     * passees en parametre.
     */
    //signature de newToken(...
    Token newToken(boolean estOperateur, String valeur){
        Token t = new Token();
        t.estOperateur = estOperateur;
        t.valeur = valeur;
        return t;
    }
    void testToString() {
        Token nombre = newToken(false, "42");
        assertEquals("42", toString(nombre));
        Token operateur = newToken(true, "+");
        assertEquals("(+)", toString(operateur));
    }

    /**
     * Retourne une representation sous forme de chaines de caracteres
     * du token passe en parametre. Lisez bien le test ci-dessus pour 
     * savoir comment afficher un nombre et un operateur.
     * 
     */
    //signature de toString(...
    String toString(Token t){
        return (t.estOperateur) ? "("+t.valeur+")" : t.valeur;
    }
    void testConvertir() {
        Token[] t1 = convertir(new String[]{"42"});
        assertTrue(length(t1) == 1);
        assertFalse(t1[0].estOperateur);
        assertEquals("42", t1[0].valeur);

        Token[] t2 = convertir(new String[]{"42", "+"});
        assertTrue(length(t2) == 2);
        assertFalse(t2[0].estOperateur);
        assertEquals("42", t2[0].valeur);
        assertTrue(t2[1].estOperateur);
        assertEquals("+", t2[1].valeur);

        //Token[] t3 = convertir(decoupeExpression("42+421"));
        Token[] t3 = convertir(new String[]{"42", "+" , "421"});
        assertTrue(length(t3) == 3);
        assertFalse(t3[0].estOperateur);
        assertEquals("42", t3[0].valeur);
        assertTrue(t3[1].estOperateur);
        assertEquals("+", t3[1].valeur);
        assertFalse(t3[2].estOperateur);
        assertEquals("421", t3[2].valeur);
    }

    /**
     * Retourne un tableau de token construit à partir de l'expression
     * passee sous la forme d'une chaine de caracteres. Il est judicieux 
     * d'utiliser des fonctions deja ecrites ...
     */
    //signature de convertir(...
    Token[] convertir(String[] s){
        Token[] t = new Token[length(s)];
        for(int i = 0; i < length(t); i++){
            boolean isOp = (estUnNombre(s[i])) ? false: true;
            t[i] = newToken(isOp, s[i]);
        }
        return t;
    }
    /**
     * On souhaite maintenant evaluer une expression exprimee sous la forme 
     * d'un tableau de tokens. On suppose que l'expression est valide, c'est
     * a-dire quelle ne contient que des operandes de nombres entiers et qu'il
     * n'y a pas d'erreur de syntaxe. On peut donc parcourir les tableau de 
     * tokens et effectuer le calcul de gauche a droite en evaluant les 
     * differents operandes et operateurs rencontres au fur et à mesure du 
     * parcours.
     */
    void testEvaluer() {
        //assertEquals(6, evaluer(convertir(decoupeExpression("1+2+3"))));
        assertEquals(6, evaluer(convertir(new String[]{"1", "+", "2", "+", "3"})));
        //assertEquals(1, evaluer(convertir(decoupeExpression("6-3-2"))));
        assertEquals(1, evaluer(convertir(new String[]{"6","-", "3", "-", "2"})));
        //assertEquals(0, evaluer(convertir(decoupeExpression("1+2-3"))));
        assertEquals(0, evaluer(convertir(new String[]{"1", "+", "2", "-", "3"})));
    }

    /**
     * Retourne un entier correspondant au resultat de l'evaluation de 
     * l'expression donnee sous la forme d'un tableau de tokens. 
     */    
    //signature de  evaluer(T...
    int evaluer(Token[] t){
        int res = entierEnChaineVersEntierEnInt(t[0].valeur);
        for(int i = 2; i < length(t); i += 2){
            char op = charAt(t[i-1].valeur,0);
            switch(op){
                case '+' : res += entierEnChaineVersEntierEnInt(t[i].valeur); break;
                case '-' : res -= entierEnChaineVersEntierEnInt(t[i].valeur); break;
                case '*' : res *= entierEnChaineVersEntierEnInt(t[i].valeur); break;
                case '/' : res *= entierEnChaineVersEntierEnInt(t[i].valeur); break;
            }
        }
        return res;
    }
}

class Token{
    boolean estOperateur;
    String valeur;
}
