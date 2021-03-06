class TP4_Exercice2 extends Program{
    void algorithm(){
	print("Entrez la taille que vous souhaitez: ");
	int tailleFigure = readInt();

	print("Entrez le caractère que vous souhaitez: ");
	char carAAfficher = readChar();

	afficherRectPlein(carAAfficher,tailleFigure);
	println();
		
	afficherRectCreux(carAAfficher,tailleFigure);
	println();
		
	afficherTrianglePlein(carAAfficher,tailleFigure);
	println();
		
	afficherTriangleCreux(carAAfficher,tailleFigure);
	println();
		
	afficherCroix(carAAfficher,tailleFigure);
    }

    void afficherRectPlein(char car, int taille){
	for (int i = 0; i < taille; i++){
	    for(int j = 0; j < taille; j++){
		print(car);
	    }
	    println();
	}
    }

    void afficherRectCreux(char car, int taille){
	for (int i = 0; i < taille; i++){
	    for(int j = 0; j < taille; j++){
		if ((i == 0 || (i+1) == taille) || ( j == 0 || (j+1) == taille)  ) print(car);
		else print(" ");
	    }
	    println();
	}
    }

    void afficherTrianglePlein(char car, int taille){
	for (int i = 0; i < taille; i++){
	    for(int j = 0; j <= i; j++){
		print(car);
	    }
	    println();
	}
    }

    void afficherTriangleCreux(char car, int taille){
	for (int i = 0; i < taille; i++){
	    for(int j = 0; j <= i; j++){
		if ((j == 0 || (i+1) == taille) || ( j == i ) ) print(car);
		else print(" ");
	    }
	    println();
	}
    }

    void afficherCroix(char car, int taille){
	for (int i = 0; i < taille; i++){
	    for(int j = 0; j < taille; j++){
		if( i == j || taille - (j+1) == i) print(car);2
		else print(" ");
	    }
	    println();
	}
    }
}
