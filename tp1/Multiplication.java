public class Multiplication{
    // éééé
    public static void main ( String args [] ) 
    {
	int a,b;

      if (args.length <2 )
	{
	  System.out.println("Vous devez donner deux nombres");
	  System.exit(0);
	}

      a=Integer.parseInt(args[0]);
      b=Integer.parseInt(args[1]);
      if (a >b)
	  {
	      int temp = a;
	      a=b;
	      b=temp;
	  }

      for (int j=a;j<=b;j++)
	  {
	      System.out.println("Affichage de la table " + j);
	      multiplier(j);
	      System.out.println("Fin d'affichage de la table ");
	  }
    }

    public static void multiplier(int numero)
    {
	for(int i=0;i<=10;i++)
	    {
		System.out.println(numero + " * " + i+" = " +(numero*i));
	    }
    }
}
