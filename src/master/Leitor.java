package master;

public class Leitor {
	private ArrayList<ItemIndex> indexadas;
	private Filter filtro;
	
	
	public Leitor (Filter filtro) {
		this.indexadas = new ArrayList<ItemIndex>();
		this.filtro = filtro;		
	}
	
	/**
	 * lê as palavras validas de 'caminho' e armazena em um array de indexItems que será retornado. 
	 * @param caminho caminho do do arquivo lido
	 * @return um array de indexItems com as palavras válidas encontradas.
	 */
	public ArrayList<ItemIndex> lerArquivo(String caminho)
	{
		
	}
	
	/**
	 * Ler ma lista de arquivos e armazena todas os Index Itens gerados em 'indexadas'.
	 * @param caminhos ArrayList<String> com o nome dos caminhos para os arquivos.
	 */
	public void lerArquivos(ArrayList<String> caminhos)
	{
		
	}
	
	/**
	 * @return os ItemIndex contidos nesse leitor.
	 */
	public ArrayList<ItemIndex> getIndexadas()
	{
		return this.indexadas;
	}
	
	

}
