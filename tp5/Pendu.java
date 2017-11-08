class Pendu extends Program{
    void algorithm(){
	final String[] MOT_A_DEVINER = new String[]{"unix","linux","pipe","pendu","algorithm"};
	pendu(MOT_A_DEVINER[(int)(random()*length(MOT_A_DEVINER))]);
    }

    String tabToString(char[] tab){
	String s = "";
	for(int i = 0; i < length(tab); i++){
	    s += tab[i];
	}
	return s;
    }
   
    char[] etoiler(int taille, char c){
	char[] tabCar = new int[taille]; 
	for(int i = 0; i < length(tabCar); i++){
	     tabCar[i] = c;
	}
	return tabCar;
    }
    
    
    void pendu(String secret){
	int nbtentative = 5;
	char[] cache = etoiler(length(secret), '*');

	char c;
	while(!equals(secret,tabToString(cache)) && nbtentative > 0){
	    println("Il vous reste "+ nbtentative +" tentatives: " + cache);
	    print("Entrez un caractère: ");
	    c = readChar();
	    
	    for( int i = 0; i < length(secret); i++){
		if(charAt(secret,i) == c){
		    cache[i] = c;
		}else{
		    nbtentative -= 1;
		}
	    }	   
	}
    }
}
