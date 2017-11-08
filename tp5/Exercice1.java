class Exercice1 extends Program{
    // void algorithm(){
    // 	//printlnTab(new int[]{1, 4, 5, 2, 3});
    // 	assertArrayEquals(new int[]{1,1,1,1,1,2,2,2,2,2},creerTableau());
    // 	assertArrayEquals(new int[]{1,1,1,1,1,2,2,2,2,2},creerTableauAvecParam(10));
    // 	//printlnTab(creerTableauAleatoire(10));
    // 	testCreerTableauAleatoire();
    // }
    // 1.
    void printlnTab(int[] tab) {
	for(int i = 0; i < length(tab); i++){
	    print(tab[i]+" ");
	}
	println();
    }

    //2.

    int[] creerTableau(){
	// int[] tab = new int[10];
	// for(int i = 0; i < length(tab); i++){
	//     if( i < 5) tab[i] = 1;
	//     else tab[i] = 2;
	// }
	// return tab;
	return creerTableauAvecParam(10);
    }

    //3.

    int[] creerTableauAvecParam(int taille){
	int[] tab = new int[taille];
	for(int i = 0; i < length(tab); i++){
	    if( i < taille/2) tab[i] = 1;
	    else tab[i] = 2;
	}
	return tab;	

    }

    //4.
    int[] creerTableauAleatoire(int taille){
	int[] tab = new int[taille];
	for(int i = 0; i < length(tab); i++){
	    tab[i] = (int)(random()*21);
	}
	return tab;	
    }

    //5.
    void testCreerTableauAleatoire(){
	int[] tab = creerTableauAleatoire(10);
	for(int i = 0; i < length(tab); i++){
	    assertTrue(tab[i] >= 0 && tab[i] <= 20);
	}
    }
    
}
