class TP3_Exercice2 extends Program {
    void testRepeterFor() {
	assertEquals("hello\n", repeterFor("hello", 1));
	assertEquals("hello\nhello\n", repeterFor("hello", 2));
	assertEquals("hello\nhello\nhello\n", repeterFor("hello", 3));
	assertEquals("hello\nhello\nhello\nhello\n", repeterFor("hello", 4));
    }
    String repeterFor(String phrase, int n) {
	String phraseRepeterNfois = phrase + "\n";
	for(int i = 2; i <= n ; i++){
	    phraseRepeterNfois += (phrase + "\n");
	}
	return phraseRepeterNfois;
    }
    String repeterWhile(String phrase, int n) {
	String phraseRepeterNfois = phrase + "\n";
	while ( n > 1 ){
	    phraseRepeterNfois += (phrase + "\n");
	    n--;
	}
	return phraseRepeterNfois;
    }

    /*
    String reperterDoWhile(){

    }
    */
    
    void testRepeterWhile() { // à compléter en copiant testRepeterFor
	assertEquals("hello\n", repeterWhile("hello", 1));
	assertEquals("hello\nhello\n", repeterWhile("hello", 2));
	assertEquals("hello\nhello\nhello\n", repeterWhile("hello", 3));
	assertEquals("hello\nhello\nhello\nhello\n", repeterWhile("hello", 4));
    }
}
