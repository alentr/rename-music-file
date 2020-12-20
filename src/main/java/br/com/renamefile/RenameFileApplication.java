package br.com.renamefile;

import br.com.renamefile.dtos.SuffleMusicDTO;
import br.com.renamefile.services.MusicService;

public class RenameFileApplication {

	public static void main(String[] args) {
		MusicService musicService = new MusicService();
		
		String sourceFolder = "C:\\Users\\alexa\\Desktop\\Nova pasta";
		
		if (musicService.shuffleMusic(new SuffleMusicDTO(sourceFolder)))
			System.out.println("Arquivos renomeados com sucesso!!!");
		else
			System.out.println("Aconteceu algum erro ao renomear os arquivos.");

	}
}
