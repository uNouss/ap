
class F_SOW_Younoussa extends Program {

    /**
     * ---------------------------------------------------------------- 
     * CONTROLE TP1 - saison 2017-2018
     * ---------------------------------------------------------------- 
     * VEUILLEZ LIRE TRES ATTENTIVEMENT CE QUI SUIT 
     *   a) renommer le fichier en respectant la convention suivante: 
     *      Groupe_NOM_Prenom.java 
     *      (Exemple: A_TURING_Alan.java)
     *   b) n'oubliez pas de rendre ce fichier a l'issue de l'epreuve en 
     *      le deposant dans le cours sous Moodle
     *   c) vous pouvez utiliser les ressources de l'IUT (cours, TD, TP 
     *      ainsi que vos notes), mais interdit d'aller chercher de l'info 
     *      sur le web
     * ---------------------------------------------------------------- 
     * LE CODE MORSE
     * ----------------------------------------------------------------
     * L'objectif de ce controle est de realiser un codeur et decodeur 
     * pour le code Morse. Comme vous le savez probablement, ce code 
     * repose sur deux symboles, point ('·') et tiret ('-') afin de 
     * representer les lettres de l'alphabet, et permet de communiquer 
     * (relativement) facilement a l'aide de signaux lumineux.
     *
     * Dans un premier temps, nous representerons les symboles par des 
     * chiffres : '·' par 1 et '-' par 2, ainsi que la separation entre 
     * deux symboles par un espace ' ' represente par 0.
     *
     * Voici la table de correspondance des lettres de l'alphabet et du 
     * code Morse represente par notre codage sous forme de chiffres :
     *
     * | a  | b    | c    | d   | e | f    | g   | h    | i  | j    |
     * | 12 | 2111 | 2121 | 211 | 1 | 1121 | 221 | 1111 | 11 | 1222 |
     *
     * | k   | l    | m  | n  | o   | p    | q    | r   | s   | t |
     * | 212 | 1211 | 22 | 21 | 222 | 1221 | 2212 | 121 | 111 | 2 |
     *
     * | u   | v    | w   | x    | y    | z    |
     * | 112 | 1112 | 122 | 2112 | 2122 | 2211 |
     *
     * Ainsi, le fameux mot "sos" se code ainsi "111022201110".
     */
    
    /**
     * Q1 (1pt) : ecrivez une fonction permettant de convertir un chiffre 
     * vers le caractere correspondant.
     */
    void testIntToChar() {
        assertEquals(' ', digitToChar(0));
        assertEquals('.', digitToChar(1));
        assertEquals('-', digitToChar(2));
        assertEquals('?', digitToChar(3));
    }
    char digitToChar(int digit) {
	char c;
	if( digit > 2 || digit < 0 ) c = '?';
	else if ( digit == 0) c = ' ';
	else if ( digit == 1) c = '.';
	else c = '-';
        return c;
    }

    /**
     * Q2 (1pt) :ecrivez une fonction permettant de convertir un caractere 
     * vers le chiffre correspondant.
     */
    void testcharToDigit() {
        assertEquals( 0, charToDigit(' '));
        assertEquals( 1, charToDigit('.'));
        assertEquals( 2, charToDigit('-'));
        assertEquals(-1, charToDigit('$'));
    }
    int charToDigit(char symbol) {
	int digit = 42;
	if ( symbol == ' ') digit = 0;
	else if ( symbol == '.' ) digit = 1;
	else if ( symbol == '-') digit = 2;
	else if ( symbol == '$') digit = -1;
        return digit;
    }

    /**
     * Q3 (2pts) : ecrivez une fonction permettant de convertir une lettre 
     * de l'alphabet vers un indice de telle maniere que pour la 
     * lettre 'a' l'indice soit 0 et que cela soit 25 pour pour 'z'.
     */
    void testLetterAsIndex() {
        assertEquals( 0, letterAsIndex('a'));
        assertEquals( 2, letterAsIndex('c'));
        assertEquals(23, letterAsIndex('x'));
        assertEquals(25, letterAsIndex('z'));
    }
    int letterAsIndex(char letter) {
        return (int)letter - 97;
    }

    /**
     * Q4 (2pts) : definissez en extension un tableau d'entiers permettant 
     * de representer le tableau suivant : 
     * 
     * | a  | b    | c    | d   | e | f    | g   | h    | i  | j    |
     * | 12 | 2111 | 2121 | 211 | 1 | 1121 | 221 | 1111 | 11 | 1222 |
     *
     * | k   | l    | m  | n  | o   | p    | q    | r   | s   | t |
     * | 212 | 1211 | 22 | 21 | 222 | 1221 | 2212 | 121 | 111 | 2 |
     *
     * | u   | v    | w   | x    | y    | z    |
     * | 112 | 1112 | 122 | 2112 | 2122 | 2211 |
     * 
     * Dans ce tableau, la case d'indice 0 contiendra le nombre 
     * correspondant a la lettre 'a' (12) tandis que celle d'indice 25 
     * correspondra au 'z' (2211).
     * 
     */
    void testMORSE_AS_INT() {
        assertEquals(  26, length(MORSE_AS_INT));
        assertEquals(  12, MORSE_AS_INT[0]);
        assertEquals(2211, MORSE_AS_INT[25]);
    }
    final int[] MORSE_AS_INT = new int[]{12, 2111, 2121, 211, 1, 1121, 221, 1111, 11, 1222, 212, 1211, 22, 21, 222, 1221, 2212, 121, 111, 2, 112, 1112, 122, 2112, 2122, 2211}; 
    // remplacer 'null' par un tableau d'entiers declare en extendion
    
    /**
     * Q5 (3pts): ecrivez une fonction permettant de coder un message 
     * donne sous la forme d'une chaine de caracteres de lettres vers 
     * une chaine de caracteres ne contenant que les chiffres 0, 1, 2 
     * correspondant a la representation obtenue grace au tableau 
     * MORSE_AS_INT.
     */
    void testCodeToInt() {
        assertEquals(          "12", codeToInt("a"));
        assertEquals(        "2211", codeToInt("z"));
        assertEquals("111022201110", codeToInt("s o s "));
    }
    String codeToInt(String message) {
	String resultat = "";
	char car;
	int indice;
	for ( int i = 0; i < length(message); i++){
	    car = charAt(message,i);
	    if( car == ' ' || car == '-' || car == '.')
		resultat = resultat + charToDigit(car);
	    else resultat = resultat + MORSE_AS_INT[letterAsIndex(car)];
	}
        return resultat;
    }
    
    /**
     * Q6 (3pts): ecrivez une fonction convertissant un nombre naturel 
     * represente sous la forme d'une chaine de caracteres (String) 
     * en un nombre entier (int).
     */
    void testConvert() {
        assertEquals(  0, convert("0"));
        assertEquals( 42, convert("42"));
        assertEquals(128, convert("128"));
    }

    int puissance10(int entier, int exposant){
	int pow = 1;
	for ( int i = 1; i <= exposant; i++){
	    pow = entier*pow;
	}
	return pow;
    }
    
    int convert(String number) {
	int nbre = 0;
	for (int i = 0; i < length(number); i++){
	    int tmp = (int)charAt(number, i) - 48;
	    nbre = nbre + tmp*puissance10(10, length(number) - (i+1));
	}
        return nbre;
    }
    
    /**
     * Q7 (4pts): ecrivez une fonction convertissant une chaine de 
     * caracteres representant un message utilisant le code de Morse 
     * (numerique) vers un tableau d'entiers.
     */
    void testMorseStringToInts() {
        assertArrayEquals(         new int[]{12}, morseStringToInts("120"));
        assertArrayEquals(       new int[]{2211}, morseStringToInts("22110"));
        assertArrayEquals(new int[]{111,222,111}, morseStringToInts("111022201110"));
    }

    int nbreOccurence(String s, char c){
	int compteur = 0;
	for (int i = 0 ; i < length(s); i++){
	    if(charAt(s,i) == c )
		compteur = compteur + 1;
	}
	println(compteur);
	return compteur;
    }
    String recupererMot(String Mot, int debut){
	String tabMot = "";

	//
	
	return tabMot;
    }
    
    int[] morseStringToInts(String morseMessage) {
	int tailleMot = nbreOccurence(morseMessage, '0');
	int[] tabEntier = new int[tailleMot];
	int debut = 0;
	
        for ( int i = 0; i < tailleMot; i++){
	    tabEntier[i] = convert(recupererMot(morseMessage, debut));
	}

        return tabEntier;
    }
    
    /**
     * Q8 (4pts): ecrivez une fonction permettant de decoder un message 
     * donne sous la forme d'une chaine de caracteres d'entiers vers 
     * une chaine de caracteres ne contenant que les lettres.
     */
    void testDecodeFromInt() {
        assertEquals(    "a ", decodeFromInt("120"));
        assertEquals(    "z ", decodeFromInt("22110"));
        assertEquals("s o s ", decodeFromInt("111022201110"));
    }
    String decodeFromInt(String morseMessage) {
	String message = "";
	for(int i = 0; i < length(morseMessage); i++){
	    message = message;
	}
        return message;
    }
    
}
