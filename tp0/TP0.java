public class TP0 extends Program {
    void testF() {
        assertEquals(f(7), 42);
    }

    // Calcul de f(x) = 7 * x - 7
    int f(int x) {
        int f_x;
        f_x = 7 * x - 7;
        return f_x;
    }
}
