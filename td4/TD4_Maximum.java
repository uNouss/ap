class TD4_Maximum extends Program{
    void algorithm(){
	int[] res = maxMinMoyenne();
	println("Max = " + res[0] + ", Min = " + res[1] + ", Moyenne = " + res[2]);
    }

    int[] maxMinMoyenne(){
	int min, max, entierSaisie;
	int moyenne;
	//int nbEntier;
	
	print("Saisir un entier ou 0 pour quitter: ");

	entierSaisie = readInt();
	max = entierSaisie;
	min = entierSaisie;
	//nbEntier = 0;
	moyenne = entierSaisie;
	
	while ( entierSaisie != 0 ){
	    if( entierSaisie > max ) max = entierSaisie;
	    if( entierSaisie < min ) min = entierSaisie;
	    moyenne = (moyenne + entierSaisie)/2;
	    //moyenne += entierSaisie;
	    //nbEntier += 1;
	    print("Saisir un entier ou 0 pour quitter: ");
	    entierSaisie = readInt();
	}
	//println("nbEntier = " + nbEntier + ", Moyenne = " + moyenne);
	//moyenne /= nbEntier;
	return new int[]{max, min, moyenne};
    }
}
// valeurs saisie: 1, -1, 2, 3, 6, 89, 20, -2, 0
// min = -2, max = 89, moyenne = 14.75 normalement ( 14 pour un entier )
// est-il mieux de faire le calcul de moyenne avec les instructions commentées ? 
