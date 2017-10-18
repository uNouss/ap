class IE_Corrrection extends Program{
	void algorithm(){
		print("Entrez un mot: ");
		String saisie = readString();	
		println(concatEnteteRTL(saisie));
		println(concatEnQueueRTL(saisie));
		println(concatEnTeteLTR(saisie));
		println(concatEnQueueLTR(saisie));
	}
	String concatEnteteRTL(String saisie){
		String res = "";
		for(int i = 0; i < length(saisie); i++)
			res = charAt(saisie, length(saisie)-(i+1)) + "\n" + res;
		return res;
	}
	String concatEnQueueRTL(String saisie){
		String res = "";
		for(int i = 0; i < length(saisie); i++)
			res = res + charAt(saisie, i) + "\n";
		return res;
	}
	String concatEnTeteLTR(String saisie){
		String res = "";
		for(int i = length(saisie) - 1; i >= 0; i--)
			res = charAt(saisie, i) + "\n" + res;
		return res;
	} 
	String concatEnQueueLTR(String saisie){
		String res = "";
		for(int i = length(saisie) - 1; i >= 0 ; i--)
			res = res + charAt(saisie, length(saisie)-(i+1)) + "\n";
		return res;
	}
}