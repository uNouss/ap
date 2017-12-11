import extensions.CSVFile;

class Exercice2_CSVFile extends Program  {
    void algorithm(){
        CSVFile csv = loadCSV("./USPresident.csv");
        int nbRow = rowCount(csv);
        int nbCol = columnCount(csv);

        String[][] tabCSV = new String[nbRow][2];

        for(int idxL = 0; idxL < length(tabCSV, 1); idxL++){
            for(int idxC = 0; idxC < length(tabCSV, 2) ; idxC++){
                tabCSV[idxL][idxC] = getCell(csv, idxL, 5);
                print(String.format("%3s", getCell(csv, idxL, 5))+" ");
            }
            println();
        }
    }
}
