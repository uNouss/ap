class TD2_Exercice2 extends Program{
    void algorithm() {
	println("Bienvenue dans le SuperLogicielDeLanTroisMille");
	println("");
	println("1. Ouvrir un document existant.");
	println("2. Créer un nouveau document.");
	println("3. Enregistrer le document courant.");
	println("4. Quitter ce magnifique logiciel.");
	println("");

	int choix;
	print("Veuillez entrer votre choix: ");
	choix = readInt();
	String menuChoisi = "Rien";
	if ( choix >= 1 && choix <= 4){
	    if (choix == 1){
		menuChoisi = "Ouvrir un document existant.";
	    }
	    else if (choix == 2) {
		menuChoisi = "Créer un nouveau document.";
	    }
	    else if (choix == 3){
		menuChoisi = "Enregistrer le document courant.";
	    }
	    else {
		menuChoisi = "Quitter ce magnifique logiciel.";
	    }
	}
	println("Vous avez choisi: \"" + menuChoisi + "\"");
    }
}
