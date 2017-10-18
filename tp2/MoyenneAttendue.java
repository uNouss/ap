class MoyenneAttendue extends Program{
    void algorithm(){
	double moyenne;
	print("Saisir une moyenne: ");
	moyenne = readInt();
	boolean moyenneAtteinte = false;
	if ( moyenne >= 10 )
	    moyenneAtteinte = true;
	if (moyenneAtteinte)
	    println("La note est supérieure ou égale à la moyenne :)");
	else
	    println("La note est inférieure à la moyenne :(");
    }
}
