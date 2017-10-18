class ConditionEntier extends Program {
    void algorithm() {
	// declaration des variables
	int x;
	// debut du corps de l'algorithme
	print("Saisir un entier: ");
	x = readInt();
	println("La conditiont vaut " + (3 < 4));
	println("La conditiont vaut " + (37 <= 11));
	println("La conditiont vaut " + (x == 5));
	println("La conditiont vaut " + ((x % 5) == 0));
	println("La conditiont vaut " + (length("Hello") != 4));
	println("La conditiont vaut " + ((0 <= x ) && (x <= 20)));
	println("La conditiont vaut " + ((x >= 0) || (x <= 20)));
	println("La conditiont vaut " + ((x >= 0) && !(x > 5)));

	// debut saisie de la chaine de caractère
	print("Saisir une chaîne: ");
	String ch = readString();
	println("La conditiont vaut " + ((length(ch) % 2) == 0));
    }
}
