class Syracuse extends Program{
	void algorithm(){
		int dureDunVol = 0, dureDeVolEnAltitude = 0;
		boolean valCourSousValDepart = false;
		//int facteurExpansion;
		print("Entrez un nombre: ");
		int n = readInt();
		int valCourante = n;
		int altitudeMax = n;
		do {
			print(valCourante + ", ");
			if ( valCourante%2 == 0 ) valCourante /= 2; 
			else valCourante = 3*valCourante + 1;
			if ( altitudeMax < valCourante ) altitudeMax =  valCourante;
            dureDunVol += 1;
			if(!valCourSousValDepart) {
				dureDeVolEnAltitude += 1;
			} 
			if ( valCourante < n ) valCourSousValDepart = true;
		} while( valCourante > 1 );

		println(1 + ", ...");
		println("durée d'un vol = " + dureDunVol);
		println("durée de vol en altitude = " + dureDeVolEnAltitude);
		println("altitude maximal = " + altitudeMax);
		println("facteur d'expansion = " + (double)altitudeMax/n);
	}
}