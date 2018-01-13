class TestList extends Program{
    int size(ListeForme lf){
        if(isEmpty(lf)) return 0;
        return 1+size(next(lf));
    }

    boolean isEmpty(ListeForme lf){
        return lf == null;
    }

    ListeForme next(ListeForme lf){
        return lf.next;
    }

    ListeForme add(ListeForme lf, Coordonnee[] coords){
        ListeForme _lf = new ListeForme();
        if(isEmpty(lf)){
            _lf.coords = coords;
            _lf.next = null;
            return _lf;
        }
        else{
            _lf.coords = lf.coords;
            _lf.next = add(next(lf), coords);
            return _lf;
        }
    }

    Coordonnee[] get(ListeForme lf, int idx){
        if(idx < 0 || idx >= size(lf) || isEmpty(lf) ) return null;
        if(idx == 0) return lf.coords;
        return get(next(lf), idx - 1);
    }

    void algorithm(){
        Coordonnee c1 = new Coordonnee(), c2 = new Coordonnee(), c3 = new Coordonnee();
        c1.y = 0; c1.x = 0; c2.y = 1; c2.x = 0; c3.y = 2; c3.x = 1;
        Coordonnee[] coords = new Coordonnee[]{c1, c2};
        ListeForme lf = null;
        println(size(lf));
        lf = new ListeForme();
        lf.coords = coords;
        lf.next = null;
        println(size(lf)+"  0:"+coords);
        coords = new Coordonnee[]{c1, c3, c2};
        /*ListeForme lf1 = new ListeForme();
        lf1.coords = coords;
        lf1.next = null;
        lf.next = lf1;*/
        lf = add(lf, coords);
        println(size(lf)+"  1:"+coords);
        coords = new Coordonnee[]{c2, c3, c1};
        lf = add(lf, coords);
        println(size(lf)+"  2:"+coords+"   \n\t  ");
        println("1:"+get(lf,1)+"  0:"+get(lf,0)+"   2:"+get(lf,2));

    }
}
