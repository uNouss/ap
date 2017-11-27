class Pendu extends Program{
    final int NB_TENTATIVE = 5;
    void algorithm(){
	final String[] MOT_A_DEVINER = new String[]{"unix","linux","pipe","pendu","algorithm"};
	int fin = length(MOT_A_DEVINER);
	int debut = 0;
	for(int i = 0; i < fin; i++){
	    int tmp = (int)(random()*fin);
	    pendu(MOT_A_DEVINER[tmp]);
	    String temp = MOT_A_DEVINER[fin-1];
	    MOT_A_DEVINER[fin - 1] = MOT_A_DEVINER[tmp];
	    
	}
    }

    String tabToString(char[] tab){
    	String s = "";
    	for(int i = 0; i < length(tab); i++){
    	    s += tab[i];
    	}
    	return s;
    }
   
    boolean[] initialize(int taille){
    	boolean[] tab = new boolean[taille];
    	for(int i = 0; i < length(tab); i++){
    	    tab[i] = false;
    	}
    	return tab;
    }

    boolean motTrouve(boolean[] tab){
	int index = 0;
	while(index < length(tab) && tab[index]){
	    index += 1;
	}
	return index == length(tab);
    }

    String motEtoile(String motSecret, boolean[] tab){
	String mot = "";
	for(int i = 0; i < length(tab); i++){
	    if(tab[i]) mot += charAt(motSecret,i);
	    else mot += "*";
	}
	return mot;
    }
    
    void pendu(String motsecret){
	boolean[] verif = initialize(length(motsecret));
	int nbTentative = NB_TENTATIVE;

	while(nbTentative > 0 && !motTrouve(verif)){
	    println("Il vous reste "+ nbTentative +" tentatives: " + motEtoile(motsecret,verif));
	    print("Entrez un caractère: ");
	    char c = readChar();
	    boolean aRemplacer = false;
	    for(int i = 0; i < length(motsecret); i++){
		if(charAt(motsecret,i) == c){
		    verif[i] = true;
		    aRemplacer = true;
		}
	    }
	    if(!aRemplacer) nbTentative -= 1;
	}
    	if(nbTentative > 0 && motTrouve(verif)){
    	    println("Vous avez gagné ! Il fallait trouver: "+motsecret);
    	}else{
    	    println("Vous avez perdu ! Il fallait trouver: "+motsecret);
    	}
    }
    
}
