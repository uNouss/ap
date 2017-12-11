class Exercice1 extends Program{
    void algorithm(){
        for(int idxL = 0; idxL < 3; idxL++){
            for(int idxC = 0; idxC < 4; idxC++){
                print(((idxL)*4 + idxC + 1)+"\t");
            }
            println();
        }
    }
}
