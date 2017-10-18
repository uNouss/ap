class Exercice4 extends Program {
    void testNature() {
	assertEquals("ALPHABETIQUE", nature('t'));
	assertEquals("NUMERIQUE", nature('0'));
	assertEquals("AUTRE", nature('$'));
	assertEquals("AUTRE", nature('('));
	assertEquals("ALPHABETIQUE", nature('y'));
    }
    String nature(char c) {
	// à compléter
	if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')){
	    return "ALPHABETIQUE";
	}else if(('0' <= c && c <= '9')){
	    return "NUMERIQUE";
	}else {
	    return "AUTRE";
	}
    }
}
