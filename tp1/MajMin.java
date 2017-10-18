; -*- coding: utf-8 -*
class MajMin extends Program {
    void testMinToMaj() {
	assertEquals('A', minToMaj('a'));
	assertEquals('F', minToMaj('f'));
    }
    char minToMaj(char c) {
	return (char)(c-32);
    }
    void testMajToMin() {
	assertEquals('a', majToMin('A'));
	assertEquals('p', majToMin('P'));
    }
    char majToMin(char c) {
	return (char)(c+32);
    }
    void testLettreAuHasard() {
	char lettre = lettreAuHasard(); println(lettre);
	assertGreaterThanOrEqual('a', lettre);
	assertLessThanOrEqual('z', lettre);
	// ou: assertTrue('a' <= lettre && lettre <= 'z');
    }
    char lettreAuHasard() {
	return (char)(random()*26+97);
    }
}
