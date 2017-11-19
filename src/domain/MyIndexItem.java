package domain;

import java.util.ArrayList;

/**
 *  Se tiver mais de uma palavra por linha?
 * @author Natália Azevedo de Brito
 */
public class MyIndexItem
{
	/**
	 * Palavra indexada 	 
	 */
	private String word;
	
	/**
	 * Armazena um conjunto de informações referentes aos arquivos fonte onde este item foi encontrado.
	 */
	private ArrayList<OcurrenceFile> sources;
	
	/**
	 * 
	 */
	public MyIndexItem(String word)
	{
		this.word = word;
		this.sources = new ArrayList<OcurrenceFile>();
	}
	
	/**
	 * Adiciona uma ocorrencia do item na linha 'line' do arquivo 'filename'.
	 * @param filename
	 * @param line
	 */
	public void addOcurrenceAt(String f, int line)
	{
		
	}
	
	/**
	 * Lugares onde podem ser encontrados a palavra indexada
	 */
	public class OcurrenceFile
	{
		
		/**
		 * Nome do arquivo referente as ocorrências
		 */
		private final String filename;
				
		/**
		 * Linhas onde a palavra foi encontrada
		 */		
		private ArrayList<Integer> ocurrences;
		
		/**
		 * Constrói um MyIndexItem para um arquivo .txt
		 * @param filename nome do arquivo .txt
		 */
		public OcurrenceFile(String filename)
		{
			this.filename = filename;
			this.ocurrences = new ArrayList<Integer>();
		}

		/**
		 * @return the filename
		 */
		public String getFilename() {
			return filename;
		}
		
		/**
		 * @return the ocurrences
		 */
		public ArrayList<Integer> getOcurrences() {
			return ocurrences;
		}
	}	
	
}
