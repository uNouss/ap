class Exercice3 extends Program {
    void testReadVector() {
	// En supposant que l’utilisateur saisit les nombres 1, 2 ,3 ...
	assertArrayEquals(new int[]{1,2,3}, saisirVecteur());
    }
    // Ecrire ici la fonction saisirVecteur ...
    int[] saisirVecteur(){
	final int COORD = 3;
	int[] tab = new int[COORD];
	for(int i = 0; i < COORD; i++){
	    print("saisir la "+i+" ème coordonnée: ");
	    tab[i] = readInt();
	}
	return tab;
    }
    
    void testVectorToString() {
	int[] v1 = new int[]{1,2,3};
	assertEquals("[1,2,3]", vectorToString(v1));
    }
    // Ecrire ici la fonction vecteurToString
    String vectorToString(int[] v){
	String coordonne = "[";
	for(int i = 0 ; i < length(v)-1; i++){
	    coordonne += (v[i]+",");
	}
	return coordonne+v[length(v)-1]+"]";
    }

    void testAdditionner() {
	int[] v1 = new int[]{1,2,3}, v2 = new int[]{-1,-2,-3};
	assertArrayEquals(new int[]{0,0,0}, add(v1,v2));
    }
    // Ecrire ici la fonction add
    int[] add(int[] v1, int[] v2){
	int[] sommeVect = new int[length(v1)];
	for(int i = 0; i < length(sommeVect); i++){
	    sommeVect[i] = v1[i] + v2[i];
	}
	return sommeVect;
    }
    
    void testScalarProduct() {
	assertEquals(6, scalarProduct(new int[]{1,1,1},
				      new int[]{1,2,3}));
	assertEquals(-14, scalarProduct(new int[]{1,2,3},
					new int[]{-1,-2,-3}));
    }
    // Ecrire ici la fonction scalarProduct
    int scalarProduct(int[] v1, int[] v2){
	int produitScalaire = 0;
	for(int i = 0; i < length(v1); i++){
	    produitScalaire += (v1[i]*v2[i]);
	}
	return produitScalaire;
    }
    
    void testEquals() {
	assertTrue(equals(new int[]{1,1,1}, new int[]{1,1,1}));
	assertFalse(equals(new int[]{1,1,2}, new int[]{1,1,1}));
    }
    // Ecrire ici la fonction equals (en ne faisant pas de calculs inutiles !)
    boolean equals(int[] v1, int[] v2){
	return equals(vectorToString(v1),vectorToString(v2));
    }
}
