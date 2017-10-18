class TP4_Exercice1 extends Program {
    void algorithm() {
	print("Entrez un entier positif: ");
	int entier = readInt();
	diviseurs(entier); // Affiche: 9, 3, 1,
    }
    void diviseurs(int n){
	for(int i = 1; i <= n; i++){
	    if( n%i == 0) print(i + ", ");
	}
	println();
    }
}
