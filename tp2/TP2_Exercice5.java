class TP2_Exercice5 extends Program {
    void testTri() {
	int[] nombresTries = new int[]{1, 2, 3};
	assertArrayEquals(nombresTries, tri(1, 2, 3));
	assertArrayEquals(nombresTries, tri(1, 3, 2));
	assertArrayEquals(nombresTries, tri(2, 1, 3));
	assertArrayEquals(nombresTries, tri(2, 3, 1));
	assertArrayEquals(nombresTries, tri(3, 1, 2));
	assertArrayEquals(nombresTries, tri(3, 2, 1));
    }
    int[] tri(int a, int b, int c) {
	// à compléter
	int lePlusPetit;
	if ( a > b) { lePlusPetit = b; b = a; a = lePlusPetit; } 
	if ( b > c) { lePlusPetit = c; c = b; b = lePlusPetit; }
	if ( a > b) { lePlusPetit = b; b = a; a = lePlusPetit; } 
	
	return new int[]{a, b, c}; // Interdit de modifier cette ligne !
    }
}
