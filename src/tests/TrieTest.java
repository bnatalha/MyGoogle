package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Domain.Trie;	// Classe a ser testada

/**
 * @author Natália Azevedo de Brito
 */
class TrieTest {

	private Trie test;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		test = new Trie();
		test.insertWord("aba");
		test.insertWord("abe");
	}

	/**
	 * Testa se a busca consegue achar uma palavra inserida.
	 */
	@Test
	void testinsertion1() {
		assertEquals(true,test.search("aba"));
	}
	
	/**
	 * Testa uma busca inválida.
	 */
	@Test
	void testsearch1() {
		assertEquals(false,test.search("asa"));
	}
	
	@Test
	void testremove1() {
		test.removeWord("aba");
		assertEquals(true,test.search("abe"));
	}
	
	@Test
	void testremove2() {
		test.removeWord("aba");
		assertEquals(false,test.search("aba"));
	}

}
