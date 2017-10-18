class TP3_Exercice1 extends Program {
    void testSomme() {
	assertEquals(1, somme(1));
	assertEquals(3, somme(2));
	assertEquals(6, somme(3));
	assertEquals(10, somme(4));
    }
    int somme(int n) {
	// à compléter
	int sommation = 0;
	/*for(int i = 1; i <= n; i++)
	    sommation += i;
	
	while ( n >= 1 ){
	    sommation += n;
	    n -= 1;
	    }*/
	return sommation;
    }
}
