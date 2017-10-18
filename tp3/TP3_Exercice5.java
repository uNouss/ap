class TP3_Exercice5 extends Program{
    void testOccurrence() {
	String phrase = "Hello World !";
	assertEquals(2, occurrenceFor(phrase, 'o'));
	assertEquals(3, occurrenceFor(phrase, 'l'));
	assertEquals(0, occurrenceFor(phrase, 'y'));
    }
    int occurrenceFor(String phrase, char c) {
	int taille = length(phrase);
	int compteur = 0;
	for(int i = 0; i < taille; i++){
	    if (charAt(phrase,i) == c ) compteur++;
	}
	return compteur;
    }
}
