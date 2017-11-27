class SauteMouton extends Program{
    final int TAILLE = 7;
    final char B = '>';
    final char N = '<';
    final char V = '_';
    char[] prairie = new char[TAILLE];
    final char[] GAGNER = new char[]{N, N, N, V, B, B, B};
    void testInitialiser(){
	initialiser();
	assertArrayEquals(new char[]{B, B, B, V, N, N, N},prairie);
    }
    void initialiser(){
	for(int i = 0; i < length(prairie); i++){
	    prairie[i] = ( i < length(prairie)/2)? B : N;
	}
	prairie[length(prairie)/2] = V;
    }

    void testAfficher(){
	initialiser();
	assertEquals(">>>_<<<",afficher());
    }
    
    String afficher(){
	String maPrairie = "";
	for(int i = 0; i < length(prairie); i++){
	    maPrairie += prairie[i];
	}
	return maPrairie;
    }


    void testEstValide(){
	initialiser();
	assertFalse(estValide(3));
	assertFalse(estValide(-1));
	assertFalse(estValide(8));
	assertFalse(estValide(7));
	assertFalse(estValide(7/2));
	assertTrue(estValide(1));
	assertTrue(estValide(0));
	assertTrue(estValide(2));
	assertTrue(estValide(4));
	assertTrue(estValide(5));
	assertTrue(estValide(6));
    }

    boolean estValide(int indice){
	return (indice >= 0 && indice < length(prairie) && prairie[indice] != V);
    }

    int saisie(){
	int saisie;
	do{
	    print("> ");
	    saisie = readInt();
	}while(!estValide(saisie));
	return saisie;
    }

    // void algorithm(){
    // 	initialiser();
    // 	prairie[3] = B;
    // 	prairie[2] = V;
    // 	int n = saisie();
    // }

    void testAvancer(){
    	initialiser();
	println(afficher());
    	assertTrue(avancer(2));
	println();
   	print("2: ");
	println(afficher());
    	assertTrue(avancer(4));
   	print("4: ");
	println(afficher());
    	assertTrue(avancer(3));
   	print("3: ");
	println(afficher());
    	assertTrue(avancer(5));
   	print("5: ");
	println(afficher());
    	assertFalse(avancer(0));
   	print("0: ");
	println(afficher());
    	assertTrue(avancer(6));
   	print("6: ");
	println(afficher());
    	assertFalse(avancer(3));
   	print("3: ");
	println(afficher());
    }

    int trouverIndiceVide(){
    	for(int i = 0; i < length(prairie); i++){
    	    if(prairie[i] == V) return i;
    	}
    	return -1;
    }
    
    boolean avancer(int idxM){
    	boolean estPossible = false;
    	int indiceVide = trouverIndiceVide();
    	if(prairie[idxM] == B ){
	    if(( idxM + 1 < length(prairie)  && prairie[idxM+1] == V)
	       || (idxM + 2 < length(prairie)  && prairie[idxM+2] == V)){
		prairie[indiceVide]   = B;
		prairie[idxM]         = V;
		estPossible           = true;
	    }
	}
	else if (prairie[idxM] == N){
	    if(( idxM - 1 >= 0  &&  prairie[idxM-1] == V )
	       || ( idxM - 2 >= 0  && prairie[idxM-2] == V)){
		prairie[indiceVide]   = N;
		prairie[idxM]         = V;
		estPossible           = true;
	    }
	}
    	return estPossible;
    }

    void testVictoire(){
	initialiser();
	assertTrue(victoire());
	//prairie = {N, N, N, V, B, B, B};
	assertFalse(victoire());
    }

    boolean victoire(){
	int i = 0;
	while(i < length(prairie) && prairie[i] == GAGNER[i]){
	    i = i + 1;
	}
	if( i == length(prairie)) return true;
	return false;
    }
}
