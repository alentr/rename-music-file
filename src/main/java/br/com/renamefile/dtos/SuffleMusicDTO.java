package br.com.renamefile.dtos;

public class SuffleMusicDTO {
	
	public SuffleMusicDTO(String sourceFolder) {
		super();
		this.sourceFolder = sourceFolder;
	}

	private String sourceFolder;
	
	
	

	public String getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

}
