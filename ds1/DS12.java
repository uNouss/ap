class DS12 extends Program{
    void algorithm(){
	// dessineBarresVerticales(5);
	// dessineBarresVerticales(8);
	// dessineBarresVerticales(4);
	// println();
	// dessineCarreCroix(5);println();
	// dessineCarreCroix(7);println();
	// dessineCarreCroix(8);println();
	// println();
	dessineZigZag(5);println(); 
	dessineZigZag(8);println();
	dessineZigZag(3);println();
    }
    
    void dessineBarresVerticales(int taille){
	for(int i = 0; i < taille; i++){
	    for(int j = 0; j < taille; j++){
		if( j%2 == 0){
		    print(".");
		}else{
		    print("*");
		}
	    }
	    println();
	}
    }

    void dessineCarreCroix(int taille){
	for(int i = 0; i < taille; i++){
	    for(int j = 0; j < taille; j++){
		if( i == 0 || i == (taille - 1) || i == j || j == 0 || j == (taille -1) || i == (taille - (j + 1))){
		    print("*");
		}else print(".");
	    }
	    println();
	}
    }

    void dessineZigZag(int taille){
	for(int i = 0; i < taille; i++){
	    for(int j = 0; j < taille; j++){
		//if(j  == ((taille/2) + 1)) print(taille/2+1);
		if( j > taille/2 || (taille/2 == j && i%2 != 0)){
		    print(" ");
		}else if((j == 5) || (j == (taille -1)) || (taille/2 == j && i%2 == 0)){
		    print("*");
		}else print(".");
	    }
	    println("");
	}

	// for (int i = 0; i < taille; i++) {
	//     int indice = 0;
	//     if ((i % 2) == 0) {
	// 	indice = taille/2;
	//     } else if ((i % 4) == 3) {
	// 	indice = taille/2 + 1;
	//     } else if ((i % 2) == 1) {
	// 	indice = taille/2 - 1;
	//     }
	    
	//     while(indice > 0) {
	// 	print(".");
	// 	indice = indice - 1;
	//     }
	//     print("*");
	//     println();
	// }
    }
}
