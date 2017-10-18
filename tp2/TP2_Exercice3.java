class TP2_Exercice3 extends Program {
    void testSuffixe() {
	assertEquals("jour", suffixe("Bonjour", 4));
	assertEquals("ERREUR", suffixe("Bonjour", 10));
	assertEquals("Bonjour", suffixe("Bonjour", 7));
	assertEquals("onjour", suffixe("Bonjour", 6));
	assertEquals("eur :)", suffixe("String Minceur :)", 6));
    }
    String suffixe(String mot, int nbLettres) {
	// à compléter
	if ( nbLettres <= length(mot) ){
	    return substring(mot,length(mot) - nbLettres, length(mot));
	}else{
	    return "ERREUR";
	}
    }
}
