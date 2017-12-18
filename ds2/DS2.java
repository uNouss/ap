class DS2 extends Program{
    void testInversionChaine(){
        assertEquals( "fedcba", inversionChaine("abcdef"));
        assertEquals( "radar", inversionChaine("radar"));
        assertEquals("rueisnom", inversionChaine("monsieur"));
    }

    String inversionChaine(String s){
        return inversionChaineAux(s, "");
        /*if(length(s) == 0) return "";
          return inversionChaine(substring(s,1,length(s))) + charAt(s,0);*/
    }

    String inversionChaineAux(String s, String s_){
        if(length(s) == 0) return s_;
        return inversionChaineAux(substring(s,1,length(s)),charAt(s,0)+s_);
    }


    void testInversionTab(){
        assertArrayEquals( new char[]{'f','e','d','c','b','a'},
                inversionTab(new char[]{'a','b','c','d','e','f'}));
        assertArrayEquals( new char[]{'r','a','d','a','r'},
                inversionTab(new char[]{'r','a','d','a','r'}));
        assertArrayEquals( new char[]{'r','u','e','i','s','n','o','m'},
                inversionTab(new char[]{'m','o','n','s','i','e','u','r'}));
    }

    char[] inversionTab(char[] tab){
        return inversionTabAux(tab, 0);
    }

    void echanger(char[] tab, int idx1, int idx2){
        char tmp = tab[idx1];
        tab[idx1] = tab[idx2];
        tab[idx2] = tmp;
    }

    char[] inversionTabAux(char []tab, int pos){
        if(pos == length(tab)/2) return tab;
        else {
            echanger(tab, pos, length(tab) - 1 - pos);
            return inversionTabAux(tab, pos+1);
        }
    }
}
