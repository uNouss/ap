import java.util.HashMap;

class Keen extends Program {

    int[][] table;
    int width = 4;
    //final int width = 5;


    HashMap<String, String> constraints = new HashMap<>();

    void algorithm() {

        table = getEmptyTable();
        fillConstraints();

        int r = 0;
        int c = 0;



        while (r<width && c<width) {
	    //System.out.println("row = "+r+", col = "+c+" = "+table[r][c]);

            if (table[r][c] < width) {// si la case est plus petite que la valeur max ( cad width )
                table[r][c]++; // on incremente la valeur
                if (isValid(r, c)) { // on verifie que la valeur est valide
                    c++; // si la case est valide on passe à la colonne suivante
                    if (c == width) { // si on a parcourue toute la colonne on se deplace
                        c = 0;//à la 1ere colonne
                        r++;//de la prochaine ligne
                    }
                }
            } else {//si la valeur de la case est plus grand que la valeur max ( genre width)
                table[r][c] = 0;// on le met à 0
                c--;//parce que nous allons repasser à la colonne precedente
                if (c < 0) {// si on a pas de colonne à gauche on se place
                    c = width - 1;// à la dernière colonne
                    r--;//de la ligne precedente
                }
            }

        }
       
        printTable();// on affiche le resultat

    }
   
    // keen constraints
    void fillConstraints() {
	// constraints.put("0-0 0-1", "1 -"); 
        // constraints.put("0-2 1-1 1-2", "9 +");
        // constraints.put("0-3 0-4", "2 /"); 
        // constraints.put("1-0 2-0 3-0 2-1", "40 *"); 
        // constraints.put("1-3 1-4", "1 -"); 
        // constraints.put("2-2 2-3", "1 -"); 
        // constraints.put("2-4 3-4", "5 *"); 
        // constraints.put("4-0 3-1 4-1", "8 +");
        // constraints.put("3-2 4-2", "2 /");
        // constraints.put("3-3 4-3 4-4", "80 *");
	


	constraints.put("0-0 0-1 0-2", "7 +"); 
        constraints.put("0-1 0-2 1-2", "3 *");
        constraints.put("0-3 1-3", "1 -"); 
        constraints.put("1-1 2-1", "1 -"); 
        constraints.put("2-2 2-3", "2 /"); 
        constraints.put("3-0 3-1", "12 *"); 
        constraints.put("3-2 3-3", "2 /");
	
        // constraints.put("0-0 0-1", "1 -"); 
        // constraints.put("0-1 1-1 1-2", "6 +");
        // constraints.put("0-2 0-3", "1 -"); 
        // constraints.put("1-3 2-3", "2 /"); 
        // constraints.put("2-0 3-0", "2 /"); 
        // constraints.put("3-1 3-2 3-3", "24 *"); 
        // constraints.put("2-1 2-2", "12 *");
        // constraints.put("0-0 0-1 1-1", "16 *");
        // constraints.put("0-2 0-3 1-2", "7 +"); 
        // constraints.put("1-0 2-0", "2 -"); 
        // constraints.put("2-1 3-0 3-1", "12 *"); 
        // constraints.put("2-2 2-3", "2 /"); 
        // constraints.put("3-2 3-3", "2 /"); 
        // constraints.put("1-3", "4 =");

	

    }

    

    boolean isValid(int r, int c) {
	// cette fonction verifie que la valeur d'une case est valide pour une colonne et une ligne donnée ( genre une coordonnée)

        // on verifie la validité de la ligne
        for (int i = 0; i < width; i++) {
            if (i != c && table[r][c] == table[r][i])
                return false;
        }

        // la validité de la colonne
        for (int i = 0; i < width; i++) {
            if (i != r && table[r][c] == table[i][c])
                return false;
        }

        // la validité d'une contrainte
        for (String key : constraints.keySet()) { // on parcours la position de chaque clé
           
            if (key.contains(r + "-" + c)) {

                String constraint = constraints.get(key); // on recup une contrainte genre "16 *" qui veut dire que la multiplication des valeur du bloc donne 16

                String operator = constraint.substring(constraint.indexOf(" ") + 1);
                int num = Integer.parseInt(constraint.substring(0, constraint.indexOf(" ")));
                double result = 0;

                String[] positions = key.split(" ");
                for (int i = 0; i < positions.length; i++) {

                   
                    int posR = Integer.parseInt(positions[i].substring(0, positions[i].indexOf("-"))); 
                    int posC = Integer.parseInt(positions[i].substring(positions[i].indexOf("-") + 1)); 
                   
                    if (table[posR][posC] == 0) return true;

                    if (operator.equals("="))
                        return (table[posR][posC] == num);
                    else if (operator.equals("+"))
                        result = result + table[posR][posC];
                    else if (operator.equals("*")) {
                        if (result == 0)
                            result = 1;
                        result = result * table[posR][posC];
                    } else if (operator.equals("-")) {
                        result = result - table[posR][posC];
                        if (result < 0)
                            result = result * -1;
                    } else if (operator.equals("/")) {
                        if (result == 0) {
                            result = 1;
                        } else {
                            if (result % table[posR][posC] != 0 && table[posR][posC] % result != 0)
                                return false;
                        }
                        result = result / table[posR][posC];
                        if (result < 1) {
                            result = 1 / result;
                        }

                    }

                }

                return result == num;

            }
        }

        return true;

    }

   
   
    int[][] getEmptyTable() {
        int[][] table = new int[width][];

        for (int i = 0; i < width; i++) {
            table[i] = new int[width];
            for (int num : table[i]) {
                num = 0;
            }
        }
        return table;
    }
   
   
   
   

    void printTable() {
       
        for (int i = 0; i < table.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < width; j++) {
                if (sb.toString().length() > 0)
                    sb.append(" ");
                sb.append(table[i][j]);
            }
            println(sb.toString());
        }

    }
   
   

}


/*

0 0 0 0 
0 0 0 0
0 0 0 0
0 0 0 0

16 cases
0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0 0 
16 - random(1,4)

//Bloc bloc = new Bloc();


 */
