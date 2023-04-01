package com.p.service.pojo;

public class Word {
	
	private int id;
	
	private String word;

	/**
	 * 
	 */
	public Word() {
		super();
	}

	/**
	 * @param id
	 * @param word
	 */
	public Word(int id, String word) {
		super();
		this.id = id;
		this.word = word;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	

}
