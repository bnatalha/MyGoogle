package domain;

import java.util.Scanner;

/**
 * Simula um programa que, no terminal, pergunte ao usu�rio palavras para adicionar �
 * �rvore, at� que ele digite a palavra: sair. Quando o usu�rio sair, a estrutura dever� ser
 * impressa no terminal.
 * 
 * @author Nat�lia Azevedo de Brito
 *
 */
public class Main {

	public static void main(String[] args) {
		Trie t = new Trie(); // cria uma �rvore digital vazia
		
		System.out.println("Digite as strings que ser�o adicionadas a Trie. Digite 'sair' para finalizar.");
		
		Scanner sc = new Scanner(System.in); // cria um scanner pra entrada do console
	    
		while (sc.hasNextLine()) // enquanto existir linhas, armazenas as strings na trie at� que uma delas seja igual a "sair".
	    {
			String s = new String(sc.nextLine());
			if(s.equals("sair")) break;
			t.insertWord(s);
	    }
		
		sc.close();	// encerra o scanner.
		
		System.out.println("Imprimindo �rvore:");
		for (String s : t.getStrings())	System.out.println(s); // Imprime as strings da �rvore.
	}

}
