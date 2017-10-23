class DS1 extends Program{
    final static int NB_OCTECT = 8; // avec 8e bit comme bit de signe
    // void algorithm(){
    // 	// assertFalse( estLettre('Z'));
    // 	// assertTrue ( estLettre('a'));
    // 	// assertTrue ( estLettre('v'));
    // 	// assertFalse( estLettre('!'));

    // 	// assertEquals( -1, positionLettre('c', "azerty") );
    // 	// assertEquals( 3, positionLettre('r', "azerty") );
	
    // 	// assertEquals( "onjour", supprimeLettre(0, "bonjour") );
    // 	// assertEquals( "onour",supprimeLettre(2, "onjour") );
	
    // 	// assertEquals("abcdefghijklmnopqrstuvwxyz", genereAlphabet());
	
    // 	testEstPangramme();
    // 	print("Entrez un entier à convertir: ");
    // 	int n = readInt();
    // 	String binaire = dec2bin(n);
    // 	println(n + " en binaire est = " + binaire );
    // 	println("et sa representation decimale est = "+ bin2dec(binaire));
    // }
    
    void testEstPangramme() {
	assertTrue( estPangramme("portez ce vieux whisky au juge blond qui fume"));
	assertTrue( estPangramme("monsieur jack vous dactylographiez bien mieux que votre ami wolf"));
	assertTrue( estPangramme("buvez de ce whisky que le patron juge fameux"));
	assertFalse( estRemarquable("buvez de ce whisky que le patron juge fameux"));
    }

    
    
    boolean estLettre(char c){
	return ( c > 'a' && c <= 'z');
    }
    int positionLettre(char c, String chaine){
	int position = -1, taille = length(chaine);
	boolean trouve = false;
	for(int i = 0; i < taille && !trouve; i++){
	    if(charAt(chaine,i) == c){
		position = i;
		trouve = true;
	    }
	}
	return position;
    }
    String supprimeLettre(int n, String s){
	String chaine = "";
	for(int i = 0; i < length(s); i++){
	    if (i != n)
		chaine += charAt(s,i);
	}
	return chaine;
    }
    String genereAlphabet(){
	String alphabet = "";
	for(char c = 'a'; c <= 'z'; c++)
	    alphabet += c;
	return alphabet;
    }

    boolean estPangramme(String s){
	//boucler sur l'alphabet
	//prendre lettre par lettre sur l'alphabet
	//pour chacune de ces lettres chercher sa position dans s
	//si une position differente de -1 est renvoyé on passe au suivant sinon cette lettre n'est pas presente et on peut sortir de la boucle
	boolean isPangram = false;
	String alphabet = genereAlphabet();
	for(int i = 0; i < 26 && ( i < length(s)) ; i++){
	    char c = charAt(alphabet,i);
	    int n = positionLettre(c, s);
	    if( n != -1){
		s =  supprimeLettre(n, s);
	    }
	    else return false;
	}
	return true;
    }
    
    boolean estRemarquable(String s){
	// String alphabet = genereAlphabet();
	// for(int i = 0; i < 26 && ( i < length(s)) ; i++){
	//     char c = charAt(alphabet,i);
	//     int n = positionLettre(c, s);
	//     if( n != -1){
	// 	s =  supprimeLettre(n, s);
	//     }
	//     else return false;
	// }
	// println(s);
    	// for(int i = 0; i < length(s); i ++){
    	//     if( estLettre(charAt(s,i)))
    	// 	return false;
    	// }
    	// return true; 


	// AUTRE FACON DE FAIRE
	// Parcourir la chaine et ne garder que les lettres
	// Ensuite verifier que cette chaine à une longueur de 26 tout juste
	// si c'est le cas vérifier que c'est un pangramme
	String nChaine = "";
	for(int i = 0; i < length(s); i++){
	    if (estLettre(charAt(s,i))) nChaine += charAt(s,i);
	}
	if ( length(nChaine) != 26 ) return false;
	return estPangramme(nChaine);
    }
    
    /*******************************************************************
     # EXO 2 
     # Conversion de base en base: 
     # base 10 en base 2
    *******************************************************************/
    void testDec2bin() {
	assertEquals("00000111", dec2bin(7));
    }

    String dec2bin(int n){
	//println("Conversion decimal binaire");
	// si n >= 0, on trouve sa répresentation binaire
	// sinon on represente NOT(|n|) + 1

	// verifier que nombre est compris entre -128 et 127 pour 8 bits
	if(n < -1*puissanceDe2(NB_OCTECT - 1) || n > (puissanceDe2(NB_OCTECT - 1) - 1) ) return "pas possible sur 7bits + 1bit de signe [OVERFLOW]";
	String chaineBinaire = "";
	int entier = ( n < 0) ? -1*n: n;
	//println(entier);
	while(entier/2 != 0){
	    chaineBinaire = entier%2 + chaineBinaire;
	    entier = entier/2;
	}
	chaineBinaire = entier%2 + chaineBinaire; // FIXME: ça déconne à ce niveau là ou plus haut 
	//println("== " + chaineBinaire + " ==");

	if ( n < 0) {
	    chaineBinaire = inverserBitEtCompleterA1(chaineBinaire);
	}
	chaineBinaire = completerBinaire(chaineBinaire, (n < 0)) + chaineBinaire;
		    
	//println("double c = "+2*'0');
	return chaineBinaire;
    }

    String completerBinaire(String s, boolean signe){
	char prefixe = (signe)?'1':'0';
	String chaineBinaire = "";
	for(int i=0;i < (NB_OCTECT - 1) - length(s); i++)
	    chaineBinaire = prefixe + chaineBinaire;
	chaineBinaire = prefixe + chaineBinaire;
	return chaineBinaire;
    }
    
    String inverserBit(String s){
	//println("**"+s);
	String chaineBinaire = "";
	for(int i = 0; i < length(s); i++){
	    if (charAt(s,i) == '1') chaineBinaire += '0';
	    else chaineBinaire += '1';
	}
	//println("++"+chaineBinaire);
	return chaineBinaire;
    }
    
    String inverserBitEtCompleterA1(String chaineBinaire){
	String chaineConvertie = "";
	boolean findFirstUn = false;
	int position = -1;
	for(int i = length(chaineBinaire) - 1; i >= 0 && !findFirstUn; i--){
	    if( !findFirstUn ){
		chaineConvertie = charAt(chaineBinaire,i) + chaineConvertie;
	    }
	    if( charAt(chaineBinaire,i) == '1' ){
		findFirstUn = true;
		position = i;
		//println("je suis dans le if ");
	    }
	}
	//println("%%% "+chaineConvertie);
	chaineConvertie = inverserBit(substring(chaineBinaire,0,position)) + chaineConvertie;
	//println("%%% "+chaineConvertie);
	//println("position du premier 1 = "+position);
	return chaineConvertie;
    }

    int bin2dec(String chaineBinaire){
	// on recupere le bit de poids fort pour tester s'il a 1 ( entier negatif) ou 0 (entier positif)
	// s'il est positif on fait une conversion normal
	// sinon on inverse chaque bit et on fait le complémenent à 2 cad NOT(|n|) + 1
	//println("je suis celui qu'on converti en dec "+chaineBinaire);
	if(length(chaineBinaire) > NB_OCTECT) return -1111111111;
	int nombre = 0;
	char signe = charAt(chaineBinaire,0);
	if(signe == '1') chaineBinaire = inverserBitEtCompleterA1(chaineBinaire);
	//println("je suis toujours le même "+chaineBinaire);
	for(int i = 1; i < length(chaineBinaire); i++){
	    if ( charAt(chaineBinaire,i) != '0'){
		nombre += puissanceDe2(length(chaineBinaire) - (i+1));
	    }
	}
	//println("je suis le nombre obtenue" + nombre);
	
	return (signe == '1')?(-1*nombre):nombre;
    }

    int puissanceDe2(int n){
	int puissance = 1;
	for(int i = 1; i <= n; i++){
	    puissance *= 2;
	}
	return puissance;
    }
    
}

