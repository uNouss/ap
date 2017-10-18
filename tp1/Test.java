class Test extends Program {
	void algorithm(){
		print("Entrez une ligne: ");
		int ligne = readInt();
		print("Entrez une colonne: ");
		int colonne = readInt();
		println("En (" + ligne + ", " + colonne + ") se trouve un '" + lettre(ligne, colonne) + "'");
		println("==================================================================================");
		afficherPartieTableAscii(); //
	}
	char lettre(int ligne, int colonne){
		char c = 'a'; 
		//if ( (ligne == 3 && colonne <= 4) || ( ligne == 1 && ( colonne != 2 && colonne != 3))) c = 'a';
		if ( (colonne == 2 || colonne == 3 ) && ligne != 3 ) c = 'b';
		if ( (colonne == 0 || colonne == 1 || colonne == 4 ) && (ligne == 1 || ligne == 2 ) ) c = 'c';
		return c;
	}

	void afficherPartieTableAscii(){
		for (int i = 65; i < (65 + 26) ; i++)
			print(i + " = "+ (char)i + ", ");
		println();
	}
}