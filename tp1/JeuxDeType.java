class JeuxDeType extends Program {
    void testAddition() {
	assertEquals(9, addition(4,5));
    }
    int addition(int operande1, int operande2) {
	return operande1 + operande2;
    }
    void testCalculPrixTTC() {
	assertEquals(1913.6, calculPrixTTC(1600, 19.6));
    }
    double calculPrixTTC(double prixHT, double TVA) {
	return prixHT * (1 + TVA/100);
    }
    void testSaluer() {
	assertEquals("Bonjour Alan", saluer("Alan"));
    }
    String saluer(String prenom) {
	return "Bonjour "+prenom;
    }
    void testInitiales() {
	assertEquals("A.T.", initiales("Alan", "Turing"));
    }
    String initiales(String prenom, String nom) {
	char initialeDuNom = charAt(nom, 0);
	char initialeDuPrenom = charAt(prenom, 0);
	String lesInitiales = initialeDuPrenom+"."+initialeDuNom+".";
	//println(lesInitiales);
	return lesInitiales;
    }
}
