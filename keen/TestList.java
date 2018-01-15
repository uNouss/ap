class TestList extends Program{
    Coordonnee[][] _formes;
    void initFormes(){
        _formes = new Coordonnee[][]{
            {newCoordonnee(0,0)},
            {newCoordonnee(0,0), newCoordonnee(1,0)},
            {newCoordonnee(0,0), newCoordonnee(0,1)},
            {newCoordonnee(0,0), newCoordonnee(1,-1), newCoordonnee(1,0)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(1,0)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(2,0)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(0,2)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(1,0), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(0,1), newCoordonnee(0,2), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(2,0), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(1,-1), newCoordonnee(1,1)},
            {newCoordonnee(0,0), newCoordonnee(1,0), newCoordonnee(1,1), newCoordonnee(2,0), newCoordonnee(1,-1)}
        };
    }

    Coordonnee newCoordonnee(int y, int x){
        Coordonnee c = new Coordonnee();
        c.y = y;
        c.x = x;
        return c;
    }

    Bloc newBloc(int org, int type){
        Bloc b = new Bloc();
        b.org = org;
        b.type = type;
        return b;
    }

    // ArrayBloc
    ArrayBloc newArrayBloc(){
        ArrayBloc ab = new ArrayBloc();
        ab.blocs = new Bloc[81];
        ab.size = 0;
        return ab;
    }

    int size(ArrayBloc ab){
        return ab.size;
    }

    boolean isEmpty(ArrayBloc ab ){
        return ab.size == 0;
    }

    void add(ArrayBloc ab, Bloc b){
        ab.blocs[ab.size] = b;
        ab.size++;
    }

    Bloc get(ArrayBloc ab, int pos){
        return ab.blocs[pos];
    }

    //ArrayForme
    ArrayForme newArrayForme(){
        ArrayForme af = new ArrayForme();
        af.coords = new Coordonnee[16][];
        af.size = 0;
        return af;
    }

    int size(ArrayForme af){
        return af.size;
    }

    boolean isEmpty(ArrayForme af ){
        return af.size == 0;
    }

    void add(ArrayForme af, Coordonnee[] coords){
        af.coords[af.size] = coords;
        af.size++;
    }

    Coordonnee[] get(ArrayForme af, int pos){
        return af.coords[pos];
    }

    //TEST
    void algorithm(){
        initFormes();
        ArrayForme af = newArrayForme();
        ArrayBloc ab = newArrayBloc();

        println(size(af)+" ");

        for(int idx = 0; idx < length(_formes, 1); idx++){
            add(af, _formes[idx]);
            print(size(af)+" ");
        }
        println();
        for(int idx = 0; idx < size(af); idx++){
            println(get(af, idx));
        }

        add(ab, newBloc(0,9));

        print(ab.blocs[0].org+":"+ab.blocs[0].type+", "+get(af, ab.blocs[0].type));
    }
}
