class RenduMonnaie extends Program {
    void testRendreMonnaie() {
	assertArrayEquals(new int[]{0, 0, 0, 0, 1}, rendreMonnaie(1));
	assertArrayEquals(new int[]{0, 0, 0, 2, 0}, rendreMonnaie(4));
	assertArrayEquals(new int[]{0, 0, 1, 0, 0}, rendreMonnaie(5));
	assertArrayEquals(new int[]{0, 1, 0, 1, 1}, rendreMonnaie(13));
	assertArrayEquals(new int[]{1, 0, 1, 1, 1}, rendreMonnaie(28));
	assertArrayEquals(new int[]{7, 1, 1, 0, 1}, rendreMonnaie(156));
    }
    int[] rendreMonnaie(int somme) {
	int nb20, nb10, nb5, nb2, nb1, reste;

	nb20 = somme / 20 ;
	reste = somme % 20;

	nb10 = reste / 10;
	reste = reste % 10;

	nb5 = reste / 5;
	reste = reste % 5;

	nb2 = reste / 2;
	reste = reste % 2;

	nb1 = reste / 1;

	return new int[]{nb20, nb10, nb5, nb2, nb1};
    }
}
