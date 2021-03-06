class EXO_Complement extends Program{
    // void algorithm(){
    // 	ticketCaisse();
    // 	println("====================================================");
    // 	intersectionEnsemble();
    // 	println("====================================================");
    // 	testEstBienParenthesee();
    // }

    // ==========================================================
    // ==========================================================
    // ==========================================================
    void ticketCaisse(){
	double prixArticle = 0.0, prixTotale = 0.0;
	String nomProduit = "", mesArticles = "";
	int quantiteArticle = 0;
	do{
	    print("Saisir le nom d'un article ou rien pour quitter: ");
	    nomProduit = readString();
	    if(equals(nomProduit,"")) break; 
	    print("Saisir la quantité pour cet article            : ");
	    quantiteArticle = readInt();
	    print("Saisir le prix pour un article de [" + nomProduit + "]:  ");
	    prixArticle = readDouble(); // problème avec readDouble , au lieu de . 
	    mesArticles += ("(" + nomProduit + ", " + quantiteArticle + ", " + prixArticle + ")\n");
	    prixTotale += (quantiteArticle*prixArticle);
	}while( quantiteArticle != 0 && !equals(nomProduit,""));
	println(mesArticles+
		"-------------------------------------------------------\n"+
		"\t\t\tvotre totale est de: " + prixTotale + " €");
    }
    // ==========================================================
    // ==========================================================
    // ==========================================================

    void testIntersectionEnsemble(){
	assertFalse(intersectionEnsemble(0, 2, 4, 6));
	assertTrue(intersectionEnsemble(0, 5, 4, 6));
	assertTrue(intersectionEnsemble(4, 7, 5, 6));
	assertTrue(intersectionEnsemble(4, 6, 5, 7));
	assertFalse(intersectionEnsemble(4, 6, 7, 8));
    }

    // boolean intersectionEnsemble(){
    // 	String i1 = saiseIntervalle(), i2 = saiseIntervalle();
    // 	println("Vos intervalles sont : " + i1 + " et " + i2);
    // 	if( ((int)charAt(i1, 3) < (int)charAt(i2, 1))
    // 	    /*&& ((int)charAt(i1, 1) < (int)charAt(i2, 3))*/){
    // 	    return false;
    // 	}else{
    // 	    return true;
    // 	}
    // }
    // String saiseIntervalle(){
    // 	int bornInf, bornSup;
    // 	print("Saisie des bornes d'un interval______\n\tSaisir la borne inférieur: ");
    // 	bornInf = readInt();
    // 	do{
    // 	    print("\tSaisir la borne supérieur( > "+ bornInf +"): ");
    // 	    bornSup = readInt();
    // 	}while(bornSup <= bornInf);
    // 	return "[" + bornInf + ";" + bornSup + "]";
    // }

    boolean intersectionEnsemble(int a1, int a2, int b1, int b2){
	boolean intersection = false;
	if( a2 > b1 || (a1 < b1 && b2 < a2) || (a1 < b2 && b1 < a1) )
	    intersection = true;
	return intersection;
    }

    // ==========================================================
    // ==========================================================
    // ==========================================================

    
    // boolean verificationParenthese(String s){
    // 	s = nettoyerString(s);
    // 	println(s);
    // 	boolean correct = true;
    // 	int i = 0;
    // 	while(correct){
    // 	    if(charAt(s,i) != charAt(s,length(s)-(i+1)))
    // 		correct = false;
    // 	    i = i + 1;
    // 	}
    // 	return correct;
    // }
    // String nettoyerString(String s){
    // 	String stringPropre = "";
    // 	for(int i = 0; i < length(s); i++){
    // 	    if(estUneParenthese(charAt(s,i)))
    // 		stringPropre += charAt(s,i);
    // 	}
    // 	return stringPropre;
    // }

    // boolean estUneParenthese(char c){
    // 	String mesParenthese = "\"\'(){}[]<>";
    // 	boolean estParenthese = false;
    // 	for(int i = 0; i < length(mesParenthese) && !estParenthese; i++){
    // 	    if(charAt(mesParenthese,i) == c)
    // 		estParenthese = true;
    // 	}
    // 	return estParenthese;
    // }
    // ==========================================================
    // ==========================================================
    // ========================================================== 
    boolean estBienParenthese(String s){
	boolean parentheseCorrect = false;
	int compteurDeParenthese = 0, compteurDeAccolade = 0, compteurDeCrochet = 0;
	for(int i = 0; i < length(s); i++){
	    char c = charAt(s,i);
	    switch (c){
	    case '(': compteurDeParenthese += 1; break;
	    case ')': compteurDeParenthese -= 1; break;
	    case '{': compteurDeAccolade += 1; break;
	    case '}': compteurDeAccolade -= 1; break;
	    case '[': compteurDeCrochet += 1; break;
	    case ']': compteurDeCrochet -= 1; break;
	    default: break;
	    }
	}
	return (compteurDeCrochet == 0
		&& compteurDeAccolade == 0
		&& compteurDeParenthese == 0);
	// une string pour stocker chaque caractère
    }
    void testEstBienParenthesee(){
	// print("Saisir une expression parenthesée: ");
	// String s = readString();
	// if(estBienParenthese(s))
	//     println(s+" est bien parenthesée");
	// else println(s+" n'est pas bien parenthesée");
	assertTrue(estBienParenthese("(a(b))c(d)"));
	assertTrue(estBienParenthese("a[b[c(d)e] (f[g])h]ij"));
	assertFalse(estBienParenthese("a[bc(d]e)"));
	assertFalse(estBienParenthese("a(b(c)d"));
    }
    // ==========================================================
    // ==========================================================
    // ==========================================================
    void testEstParfait() {
        assertTrue(estParfait(6));
        assertTrue(estParfait(28));
        assertTrue(estParfait(496));
        assertTrue(estParfait(8128));
        
        assertFalse(estParfait(7));
        assertFalse(estParfait(12));
    } 
    
    boolean estParfait(int n) {
	//a completer
	int somme = 1;
	for(int i = 2; i < n ; i++){
	    if( n%i == 0) somme += i;
	}
        return (somme == n);
    }
    // ==========================================================
    // ==========================================================
    // ==========================================================

    
}

//"(a(b))c(d)" correct; "a[b[c(d)e] (f[g])h]ij" correct; "a[bc(d]e)" incorrect; "a(b(c)d" incorrect
