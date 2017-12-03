package gui;

public final class AND_OR {
	private AND_OR(){ }
	
	public void check(String input) {
		input = input.replace(" ","");
		int index = 0;
		if(input.indexOf("AND") != -1) {
			searchAnd(input,input.indexOf("AND"));
		} else if(input.contains("OR")) {
			
		}
		else {
			
		}
	}
	
	public void searchAnd(String input,int andIndex) {
		
		String word = input.substring(0, andIndex-1);
		//search word
		for(int i=andIndex+3;i<input.length();) {
			if(input.indexOf("AND", i)!=-1) {
				andIndex = input.indexOf("AND", i);
				word = input.substring(i, andIndex-1);
				//search word
			}else {
				word = input.substring(i);
				//search word
			}
		}
	}
	
	public void searchOr(String input,int orIndex) {
		//carroORmoto
		String word = input.substring(0, orIndex-1);
		//search word
		for(int i=orIndex+2;i<input.length();) {
			if(input.indexOf("OR", i)!=-1) {
				orIndex = input.indexOf("OR", i);
				word = input.substring(i, orIndex-1);
				//search word
			}else {
				word = input.substring(i);
				//search word
			}
		}
	}
}
