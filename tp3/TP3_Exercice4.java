class TP3_Exercice4 extends Program{
    void algorithm(){
	print("Veuillez entrer une phrase: ");
	String phrase = readString();

	int taille = length(phrase);
	
	/*for(int i = 0; i < taille; i++){
	    //println(charAt(phrase,i));
	}*/
	int i = 0;
	while(i < taille){
	    println(substring(phrase,i,i+1));
	    i++;
	}
    }
}
