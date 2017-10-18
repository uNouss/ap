class TP2_Exercice6 extends Program {
    void testBissextile() {
	int[] anneesBissextiles = new int[]{2012, 2008, 2004, 2000,
					    1996, 1992, 1988, 1984, 1980, 1976, 1972};
	int annee;
	//assertTrue(bissextile1(2012));

	for (int i=0; i<length(anneesBissextiles); i=i+1) {
	    annee = anneesBissextiles[i];
	    assertTrue(bissextile1(annee));
	    assertEquals(bissextile1(annee), bissextile2(annee));
	    assertEquals(bissextile2(annee), bissextile3(annee));
	    assertEquals(bissextile3(annee), bissextile4(annee));
	}
	// d’autres dates non bissextile
	assertFalse(bissextile1(2013));
	assertFalse(bissextile2(2006));
	assertFalse(bissextile3(2005));
	assertFalse(bissextile4(1999));
    }
    boolean bissextile1(int annee) {
	boolean estBissextile = false;
	if (annee%4 == 0) estBissextile = true;
	if (annee%100 == 0) estBissextile = false;
	if (annee%400 == 0) estBissextile = true;
	return estBissextile;
    }
    // Utiliser le même schéma pour bissextile2/3/4
    boolean bissextile2(int annee) {
	boolean estBissextile = false;
	// à compléter
	if ( (annee%100 != 0 || annee%400 == 0) && (annee%4 == 0) ) estBissextile = true; 
	return estBissextile;
    }
    boolean bissextile3(int annee) {
	boolean estBissextile = false;
	// à compléter
	if (annee%400 == 0){
	    estBissextile = true;
	} else if (annee%100 == 0){
	    estBissextile = false;
	} else if (annee%4 == 0) {
	    estBissextile = true;
	} else {
	    estBissextile = false;
	}
	return estBissextile;
    }
    boolean bissextile4(int annee) {
	boolean estBissextile = false;
	// à compléter
	//println("bissextile4 "+ annee);
	if ( annee%4 == 0) {
	    estBissextile = true;
	} else if ( annee%100 == 0 ){
	    estBissextile = false;
	} else if ( annee%400 == 0 ){
	    estBissextile = true;
	}
	else {
	    estBissextile = false;
	}
	return estBissextile;
    }
}
