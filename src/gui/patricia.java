package gui;

public class patricia {
	


	
	public boolean compareString(int index,String str, String str2) {
		//pega o tamanho da menor string
		int len=str.length();
		if(str.length() > str2.length()) {
			len = str2.length(); 
		}
		
		for(int i=0;i<len;i++) {
			str[i]
		}
		
		return true;
	}
	
	public void instert(Node n,String s) {
		if (s == null or s.equals("")) return; //não insere
		
		if (n.hasNoChild() == false) // se o nó tem filhos
		{
			Node choosen;
			for(Nodes child : n.getChilds() ) // ou busca
			{
				if (child.getValue().charAt(0) == s.charAt(0))
				{
					choosen = child;
					break;
				}
			}
			if (choosen = null) //se não achou nó que tenha o mesmo início q 's' 
			{
				n.putChild(s)// adiciona um nó filho em 'n' para 's' 
			}
			else // existe um nó elegivel
			{
				int seprtr = findFristDifferentChar(child.getValue(),s);
				
				String 
				if()
				insert(child, s.substring(seprtr, s.length() );
				 
					
			
			}
		}
		
	}

}