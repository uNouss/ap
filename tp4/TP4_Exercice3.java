class TP4_Exercice3 extends Program{
    void algorithm(){
	print("Entrez une chaine de caractère: ");
	String saisie = readString();

	afficherToutesLesSousChaine(saisie);
    }
    void afficherToutesLesSousChaine(String chaine){
	int taille = length(chaine);
	println("Les sous-chaines de \"" + chaine + "\" sont : ");
	for(int i = 0; i < taille; i++){
	    for(int j = i; j < taille; j++)
		println(substring(chaine, i, j+1));
	}
    }
}
