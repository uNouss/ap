class TP4_Exercice4 extends Program {
    void testCompterMots() {
	assertEquals(1, compterMots("test"));
	assertEquals(1, compterMots("     test"));
	assertEquals(1, compterMots("test     "));
	assertEquals(2, compterMots("test     test"));
	assertEquals(3, compterMots("Les jours heureux !"));
	assertEquals(2, compterMots("Test-Test"));
    }
    int compterMots(String chaine){
	int compteur = 0;
	boolean estUneLettre = false;
	
	for(int i = 0; i < length(chaine); i++){
	    if (!estUneLettre && isAlpha(charAt(chaine,i)))
		compteur++;
	    estUneLettre = isAlpha(charAt(chaine,i));
	}
	/*	 
	    char c = charAt(chaine, i);
	    if(isAlpha(c)){
		estUneLettre = true; 
	    }
	    else if ( estUneLettre && !isAlpha(c)){
		compteur++;
		estUneLettre = false;
	    }

	}
	if(estUneLettre) compteur++;*/
	return compteur;
    }
    
    boolean isAlpha(char car){
	int c = (int)car;
	return (c > 64 && c < 91) || (c > 96 && c < 123);

    }
}
