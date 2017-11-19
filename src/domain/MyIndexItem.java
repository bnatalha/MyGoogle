package domain;

import java.util.ArrayList;

/**
 * Se tiver mais de uma palavra por linha?
 * @author Natália Azevedo de Brito
 */
public class MyIndexItem
{
	/**
	 * Palavra indexada 	 
	 */
	private final String word; // ?
	
	/**
	 * Armazena um conjunto de informações referentes aos arquivos fonte onde este item foi encontrado.
	 */
	private ArrayList<IndexSource> sources;
	
	/**
	 * Constrói um MyIndexItem a partir de uma palavra.
	 */
	public MyIndexItem(String word)
	{
		this.word = word;
		this.sources = new ArrayList<IndexSource>();
	}
	
	/**
	 * Adiciona uma ocorrencia do item na linha 'line' do arquivo 'filename'.
	 * @param filename nome do arquivo em que a ocorrência será adicionada
	 * @param line linha da ocorrência
	 */
	public void addOcurrenceAt(String filename, int line)
	{
		IndexSource in_src = findSource(filename);	// pesquisa pelo IndexSource de 'filename'
		
		// caso não encontre, cria um IndexSource para este arquivo e o adiciona as fontes
		if(in_src == null)	
		{
			in_src = new IndexSource("filename");	
			sources.add(in_src);
		}
		
		in_src.addLine(new Integer(line));	// adiciona a ocorrência ao IndexSource do arquivo.
	}
	
	/**
	 * Pesquisa pelo IndexSource de um arquivo em 'sources'.
	 * @param filename
	 * @return Se encontrar, retorna uma referência para o IndexSource. Caso não encontre, retorna 'null'. 
	 */
	private IndexSource findSource(String filename)
	{
		for(IndexSource s : sources)	// Procura pelo IndexSource do arquivo 'filename'
			if(s != null)
				if(s.getFilename().equals(filename))	// se Achou uma IndexSource para este arquivo
					return s;
		
		return null;
	}
	
	/**
	 * Lugares onde podem ser encontrados a palavra indexada
	 */
	public class IndexSource
	{		
		/**
		 * Nome do arquivo referente as ocorrências
		 */
		private final String filename;
				
		/**
		 * Linhas onde a palavra foi encontrada
		 */		
		private ArrayList<Integer> lines;
		
		/**
		 * Constrói um MyIndexItem para um arquivo .txt
		 * @param filename nome do arquivo .txt
		 */
		public IndexSource(String filename)
		{
			this.filename = filename;
			this.lines = new ArrayList<Integer>();
		}
		
		/**
		 * Adiciona uma ocorrência na linha 'line'.
		 */
		public void addLine(int line) {
			lines.add(line);
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
			return lines;
		}
	}	
	
}
