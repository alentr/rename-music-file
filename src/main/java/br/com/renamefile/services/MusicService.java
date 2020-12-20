package br.com.renamefile.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.renamefile.dtos.SuffleMusicDTO;

public class MusicService {

	public boolean shuffleMusic(SuffleMusicDTO suffleMusic) {

		if (removePrefix(suffleMusic))		
			return addPrefix(suffleMusic);
		else
			return false;
	}

	private boolean addPrefix(SuffleMusicDTO suffleMusic) {
		System.out.println("Iniciando a adição do prefixo...");
		
		String fileName;
		String prefix;
		
		Random random = new Random();
		List<Integer> randomNum = new ArrayList<>();
		
		File[] sourceFiles = findSourceFiles(suffleMusic);
		
		try {
			for (File file : sourceFiles) {
				int prefixNum;
				do {
					prefixNum = random.nextInt(501);
					
				} while(randomNum.contains(prefixNum));

				randomNum.add(prefixNum);
				
				prefix = String.valueOf(prefixNum);
				
				if (prefix.length() < 3) {
					do {
						prefix = "0".concat(prefix);
						
					} while(prefix.length() < 3);
				}
				
				prefix = prefix.concat(" - ");
				
				fileName = prefix.concat(file.getName());
				
				file.renameTo(new File(suffleMusic.getSourceFolder().concat("\\").concat(fileName)));
			}
		} catch (Exception e) {
			System.err.println("Erro ao renomear arquivo. Adicionar o prefixo.");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

	private boolean removePrefix(SuffleMusicDTO suffleMusic) {
		System.out.println("Iniciando remoção do prefixo...");
		
		File[] sourceFiles = findSourceFiles(suffleMusic);

		try {
			for (File file : sourceFiles) {
				String fileName = file.getName();

				fileName = fileName.replaceAll("\\d{3} - ", "");
				file.renameTo(new File(suffleMusic.getSourceFolder().concat("\\").concat(fileName)));
			}
		} catch (Exception e) {
			System.err.println("Erro ao renomear arquivo. Remover o prefixo.");
			e.printStackTrace();
			
			return false;
		}
		
		System.out.println(String.format("Quantidade de arquivos renomeados: %d", sourceFiles.length));
		
		return true;
	}

	private File[] findSourceFiles(SuffleMusicDTO suffleMusic) {
		File[] sourceFiles = null;
		File sourceFolder = null;

		try {
			sourceFolder = new File(suffleMusic.getSourceFolder());
		} catch (Exception e) {
			System.err.println("Erro ao obter pasta raiz.");
			e.printStackTrace();
		}

		try {
			sourceFiles = sourceFolder.listFiles();
		} catch (Exception e) {
			System.err.println("Erro ao obter as músicas da pasta raiz.");
			e.printStackTrace();

		}
		return sourceFiles;
	}

}
