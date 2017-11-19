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
	 * Inserção de a em uma árvore com "ab"
	 */
	@Test
	void testIsertion2() {
		tree.insertWord("ab");
		tree.insertWord("a");
		assertEquals(true,tree.searchWord("a"));
		assertEquals(true,tree.searchWord("ab"));
	}

}
