class TP3_Exercice3 extends Program {
    /*void algorithm() {
	// placez ici le code de votre algorithme
	print("Combien de lignes ? ");
	int nombreDeLigne = readInt();
	final double TVA = 0.196;
	
	for ( int i  = 1; i <= nombreDeLigne; i++){
	    println(i + " euros HT = " + i*(1 + TVA) + " euros TTC.");
	}
	}*/
    void algorithm() {
	// placez ici le code de votre algorithme
	final double TVA = 0.196;

	print("Combien de lignes ? ");
	int nombreDeLigne = readInt();
	print("A partir de ? ");
	double depart = readDouble();
	
	for ( int i  = 0; i < nombreDeLigne ; i++){
	    println(depart + " euros HT = " + depart*(1 + TVA) + " euros TTC.");
	    depart += 0.5 ;
	}
    } 
}
