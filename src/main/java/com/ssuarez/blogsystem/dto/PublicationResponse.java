package com.ssuarez.blogsystem.dto;

import java.util.List;

public class PublicationResponse {

	private List<PublicationDTO> contentList;
	private int numberPage;
	private int sizePage;
	private long totalItems;
	private int totalPages;
	private boolean lastPage;

	public List<PublicationDTO> getContentList() {
		return contentList;
	}

	public void setContentList(List<PublicationDTO> contentList) {
		this.contentList = contentList;
	}

	public int getNumberPage() {
		return numberPage;
	}

	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	public int getSizePage() {
		return sizePage;
	}

	public void setSizePage(int sizePage) {
		this.sizePage = sizePage;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public PublicationResponse() {
		super();
	}
	
}
