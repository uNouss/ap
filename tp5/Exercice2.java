class Exercice2 extends Program{
    // 1.
    void testCreerTableauOrdonne() {
	assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10},creerTableauOrdonne());
    }

    int[] creerTableauOrdonne(){
	int[] tab = new int[10];
	for(int i = 0; i < length(tab); i++){
	    tab[i] = i+1;
	}
	return tab;
    }
    
    //2.

    void shift(int[] tab){
	// int precedente = tab[0], courante = precedente;
	// tab[0] = tab[length(tab)-1];
	// for(int i = 1; i < length(tab)-1; i++){
	//     courante = tab[i];
	//     tab[i] = precedente;
	//     precedente = courante;
	// }
	// tab[length(tab)-1] = courante;

	int tmp = tab[length(tab)-1];
	for (int i = length(tab)-1; i > 0; i--){
	    tab[i] = tab[i-1];
	}
	tab[0] = tmp;
    }
    void testShift() {
	int[] tab = creerTableauOrdonne();
	shift(tab);
	assertArrayEquals(new int[]{10,1,2,3,4,5,6,7,8,9}, tab);
	tab = new int[]{2, 5, 3, 1, 7};
	shift(tab);
	assertArrayEquals(new int[]{7, 2, 5, 3, 1}, tab);
    }
    //3.

    // int[] creerTableauAvecParam(int taille){
    // 	int[] tab = new int[taille];
    // 	for(int i = 0; i < length(tab); i++){
    // 	    if( i < taille/2) tab[i] = 1;
    // 	    else tab[i] = 2;
    // 	}
    // 	return tab;	

    // }

    // //4.
    // int[] creerTableauAleatoire(int taille){
    // 	int[] tab = new int[taille];
    // 	for(int i = 0; i < length(tab); i++){
    // 	    tab[i] = (int)(random()*21);
    // 	}
    // 	return tab;	
    // }

    // //5.
    // void testCreerTableauAleatoire(){
    // 	int[] tab = creerTableauAleatoire(10);
    // 	for(int i = 0; i < length(tab); i++){
    // 	    assertTrue(tab[i] >= 0 && tab[i] <= 20);
    // 	}
    // }
    
}
