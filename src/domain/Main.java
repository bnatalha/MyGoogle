package domain;

import java.util.Scanner;

/**
 * Simula um programa que, no terminal, pergunte ao usuário palavras para adicionar à
 * árvore, até que ele digite a palavra: sair. Quando o usuário sair, a estrutura deverá ser
 * impressa no terminal.
 * 
 * @author Natália Azevedo de Brito
 *
 */
public class Main {

	public static void main(String[] args) {
		Trie t = new Trie(); // cria uma árvore digital vazia
		
		System.out.println("Digite as strings que serão adicionadas a Trie. Digite 'sair' para finalizar.");
		
		Scanner sc = new Scanner(System.in); // cria um scanner pra entrada do console
	    
		while (sc.hasNextLine()) // enquanto existir linhas, armazenas as strings na trie até que uma delas seja igual a "sair".
	    {
			String s = new String(sc.nextLine());
			if(s.equals("sair")) break;
			t.insertWord(s);
	    }
		
		sc.close();	// encerra o scanner.
		
		System.out.println("Imprimindo árvore:");
		for (String s : t.getStrings())	System.out.println(s); // Imprime as strings da árvore.
	}

}
