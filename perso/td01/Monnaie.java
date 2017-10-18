public class Monnaie{
    static final int[] val = {100, 50, 20, 10, 5, 2, 1};
    static final int n = 7;
    
    void affiche (int[] rep){
    	//int n = rep.length;
        for (int i = 0; i <= n ; ++i)
            System.out.println("Nbre de billets de  " +  
                val[i] + "  euros:  " + rep[i]);
    }
    
    static int[] calcul (int somme){
        int[] r = new int[n];
        int m = somme;
        for (int i = 0; (i < n && m != 0) ; ++i)
            while (m >= val[i]) 
               r[i]++; m = m - val[i];
            
    }

    static void main(String[] arg){
       int a = parseInt(arg[1]);
       int t = calcul(a);
       affiche(t);
    }
}
