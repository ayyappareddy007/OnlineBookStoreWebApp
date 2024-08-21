package com.obs.model;

public class BookModel {
	private int id;
	private String title;
	private String author;
	private Double price;
	private String description;
	private String genre;
	private String image;
	
	public BookModel() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "BookModel [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", description="
				+ description + ", genre=" + genre + ", image=" + image + "]";
	}
	
	
}
