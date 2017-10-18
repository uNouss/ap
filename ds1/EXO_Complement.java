class EXO_Complement extends Program{
    void algorithm(){
	ticketCaisse();
    }
    
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
		"\t\t\tvotre totale est de: " + prixTotale);
    }
    // void intersectionEnsemble(){
    // }
}
