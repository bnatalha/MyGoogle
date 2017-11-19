package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.RadixTree;

class RadixTest {
	
	RadixTree tree;
	final int ALPHABET_SIZE = 26;

	@BeforeEach
	void setUp() throws Exception {
		tree = new RadixTree(ALPHABET_SIZE);
	}
	
	/**
	 * Busca em árvore vazia
	 */
	@Test
	void testSearch1() {
		assertEquals(false,tree.searchWord("a"));
	}

	/**
	 * Inserção numa árvore vazia.
	 */
	@Test
	void testIsertion1() {
		tree.insertWord("a");
		assertEquals(true,tree.searchWord("a"));
	}
	
	/**
	 * Remoção numa árvore vazia.
	 */
	@Test
	void testRemove1() {
		tree.removeWord("a");
		assertEquals(false,tree.searchWord("a"));
	}
	
	/**
	 * Inserção de "a" em uma árvore com "ab"
	 */
	@Test
	void testIsertion2() {
		tree.insertWord("ab");
		tree.insertWord("a");
		assertEquals(true,tree.searchWord("a"));
		assertEquals(true,tree.searchWord("ab"));
	}
	
	/**
	 * Remoção de uma folha que possua irmãos e que tenha um pai que é fim de string. 
	 */
	@Test
	void testRemove2() {
		tree.insertWord("abc");
		tree.insertWord("abd");
		tree.insertWord("abe");
		
		tree.removeWord("abd");
		
		assertEquals(true,tree.searchWord("abc"));
		assertEquals(false,tree.searchWord("abd"));
		assertEquals(true,tree.searchWord("abe"));
	}
	
	/**
	 * Inserção de "abc" em uma árvore com "abd". Buscar por "ab" e "a". "abc" e "abd"
	 */
	@Test
	void testIsertion3() {
		tree.insertWord("abc");
		tree.insertWord("abd");
		assertEquals(false,tree.searchWord("a"));
		assertEquals(false,tree.searchWord("ab"));
		assertEquals(true,tree.searchWord("abc"));
		assertEquals(true,tree.searchWord("abd"));
	}
	
	/**
	 * Remoção de uma folha que possua um irmão com filhos,
	 * onde o pai não é fim de string.
	 */
	@Test
	void testRemove3() {
		tree.insertWord("a"); //Avô de "X"
		//"abc": Pai de "X"
		tree.insertWord("abf");	// folha "X" ser removida  
		tree.insertWord("abde"); // irmão de "X"
		tree.insertWord("abdeg"); // "sobrinho de "X"
		tree.insertWord("abdeh"); // "sobrinho de "X"
		
		tree.removeWord("abf");
		
		
		assertEquals(true,tree.searchWord("a"));
		//
		assertEquals(false,tree.searchWord("abf"));
		assertEquals(true,tree.searchWord("abde"));
		assertEquals(true,tree.searchWord("abdeg"));
		assertEquals(true,tree.searchWord("abdeh"));
	}
	
	/**
	 * Inserção de "abc" em uma árvore com "abd" e "a". Buscar por "ab", "a", "b", "abc" e "abd".
	 */
	@Test
	void testIsertion4() {
		tree.insertWord("abc");
		tree.insertWord("abd");
		tree.insertWord("a");
		assertEquals(true,tree.searchWord("a"));
		assertEquals(false,tree.searchWord("b"));
		assertEquals(false,tree.searchWord("ab"));
		assertEquals(true,tree.searchWord("abc"));
		assertEquals(true,tree.searchWord("abd"));
	}

}
