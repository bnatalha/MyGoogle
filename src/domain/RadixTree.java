package domain;

import java.util.ArrayList;

/**
 * Simula uma Árvore Radix. Obedece a ordem natural das strings.
 *  
 * @author Natália Azevedo de Brito
 */
public class RadixTree
{

	/**
	 * Nó raiz
	 */
	private RadixNode root;
	
	/**
	 * Tamanho do alfabeto
	 */
	private final int ALPHABET_SIZE;
	
	// ================================================ METODOS ================================================
	
	/**
	 * Constrói uma árvore radix vazio com o alfbeto de tamanho 26.
	 */
	public RadixTree() 
	{
		this.ALPHABET_SIZE = 26;
		this.root = new RadixNode(); 
	}
	
	/**
	 * Constrói uma árvore radix vazia com o alfbeto de tamanho N.
	 * @param N tamanho do alfabeto dessa árvore.
	 */
	public RadixTree(int N) 
	{
		this.ALPHABET_SIZE = N;
		this.root = new RadixNode(); 
	}
	
	// ================================================ NÓ ================================================
	
	/**
	 * Simula um nó de uma árvore radix. Adaptado para o projeto.
	 * @author Natália Azevedo de Brito
	 */
	public class RadixNode 
	{
		/**
		 * Contem o pedaço de string que serve de caminho para este nó a partir do seu pai. (??)
		 */
		private String label;
		
		/**
		 * Armazena os filhos desse nó
		 */
		private ArrayList<RadixNode> childs;
		
		/**
		 * Guarda o número máximo de filhos que um nó pode ter.
		 */
		private int MAX_CHILDS = ALPHABET_SIZE;
		
		/**
		 * Indica se esse nó é o fim de uma string.
		 */
		private boolean endOfString;
		
		/**
		 * Guarda informações sobre a origem de uma palavra. 
		 */
		private MyIndexItem indexitem;
		
		
		/**
		 * Constrói um nó vazio.
		 */
		public RadixNode()
		{
			this.endOfString = false;
			this.childs = new ArrayList<RadixNode>(MAX_CHILDS);
		}
		
		/**
		 * Constrói um nó com um label.
		 */
		public RadixNode(String label)
		{
			this.endOfString = false;
			this.label = label;
			this.childs = new ArrayList<RadixNode>(MAX_CHILDS);
		}

		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * @param label the label to set
		 */
		public void setLabel(String label) {
			this.label = label;
		}

		/**
		 * @return the childs
		 */
		public ArrayList<RadixNode> getChilds() {
			return childs;
		}

		/**
		 * @param childs the childs to set
		 */
		public void setChilds(ArrayList<RadixNode> childs) {
			this.childs = childs;
		}
		
		/**
		 * @return the endOfString
		 */
		public boolean isEndOfString() {
			return endOfString;
		}

		/**
		 * @param eos the endOfString to set
		 */
		public void setEndOfString(boolean eos) {
			this.endOfString = eos;
		}
		
		/**
		 * @return the indexitem
		 */
		public MyIndexItem getIndexItem() {
			return indexitem;
		}

		/**
		 * @param indexitem the indexitem to set
		 */
		public void setMyIndexItem(MyIndexItem indexitem) {
			this.indexitem = indexitem;
		}		
	}
}
