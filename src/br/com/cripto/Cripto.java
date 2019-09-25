package br.com.cripto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.ValidationException;

public class Cripto {
	
	/**
	 * @local Faculdade UPIS
	 * @materia Segurança da Informação
	 * @professor Olivier
	 * @author Faris Saleh Ahmad
	 * @data 25 de setembro de 2019
	 */

	public static void main(String[] args) throws Exception {
		String in = "FPOCLAPPAPFLMCZAUNPNPVPNTONQYVMNJVULYVLNFVAOGHATP";
		Cripto cripto = new Cripto();
		
		System.out.println("Texto Cripotgrafado :");
		System.out.println(in);
		
		System.out.println("\nTexto antes da função :");
		String texto = cripto.funcaoFixa(in);
		System.out.println(texto);
		
		System.out.println("\nTexto antes da permutação(7412356):");
		String key = cripto.findInversa("7412356");
		texto = cripto.permutacao(key, texto);
		System.out.println(texto);
		
		System.out.println("\nTexto antes da transposição :");
		texto = cripto.decodeTP(texto, 7);
		System.out.println(texto);
		
		System.out.println("\nTexto antes do padrão de ceasar:");
		texto = cripto.decodeDPC(texto);
		System.out.println(texto);
		
		System.out.println("\nTexto antes da permutação(7412563):");
		String key2 = cripto.findInversa("7412563");
		texto = cripto.permutacao(key2, texto);
		System.out.println(texto);
		
//		System.out.println("Texte"+cripto.funcaoFixa(in));
		//Trasnposição 
		//Crifração
		
//		String tx = cripto.permutacao("7412356", "ABCDEFGHIJKLMN");
//		String k = cripto.findInversa("7412356");
//		String f = cripto.permutacao(k, tx);
//		System.out.println(f);
	}

	//Baseado no método de congruência com a função f(x) 
	private String funcaoFixa(String texto) {
		int L;
		StringBuilder b = new StringBuilder();
		
		
		for (char s : texto.toCharArray()) {
			int n = 1 ;	
			L =((int) s -64);
			int mod = 1;
			int div = 0;
			while (mod != 0) {
				int fxUp = L + 8 + (26*n) ;
				int fxDown = -7;
				mod = fxUp % fxDown;
				if (mod ==0) {
					div = fxUp / fxDown;
					
					while(div < 1) {
						div= div + 26;
					}
					while(div > 26) {
						div= div - 26;
					}
//					System.out.println(div);
					b.append((char) (div + 64));
				}
				n++;
			} 
		}
		return b.toString();
	}

	//Métdo para encontrar P-1 da chave de permutação
	private String findInversa(String key) {
		StringBuilder n = new StringBuilder();
		for (int i = 0; i < key.length(); i++) {
			int var = key.indexOf((i +1)+"");
			n.append(var+1);
		}
		return n.toString();
	}

	//Método de permutação
	private String permutacao(String key, String txt) {
		StringBuilder n = new StringBuilder();
		List<String> lista = new ArrayList<String>();
		
		if (txt.length() > key.length()) {
			lista = Arrays.asList(txt.split("(?<=\\G.{" + key.length() + "})"));			
		}else {
			lista.add(txt);
		}
		for (String s : lista) {
			for (int i = 0; i < s.length(); i++) {
				char c = key.charAt(i);
				n.append(s.charAt(c - '0' -1));
				
			}
			
		}
		return n.toString();
	}

	//Método que 'codifca' texto usando método de transaposição
	private String encodeTP(String txt, int key) {
		return transposicao(txt, key);
	}

	//Método que 'decodifca' texto usando método de transaposição
	private String decodeTP(String txt, int key) {
		return transposicao(txt, txt.length()/ key);
	}

	private String transposicao(String txt, int chave) {
		List<String> strings = new ArrayList<String>();
		strings.addAll(Arrays.asList(txt.split("(?<=\\G.{" + chave + "})")));
		StringBuilder tp = new StringBuilder();
		for (int i = 0; i < chave; i++) {
			for (String s : strings) {
				if (i < s.length()) {
					tp.append(s.charAt(i));
				}
			}

		}
		return tp.toString();
	}

	//Método que gera o HASH de um texto baseado nas lógica vista em sala
	private String hash(String texto) throws Exception {
		int tamCasasBin = 8;
		char[] a = texto.toUpperCase().toCharArray();
		List<String> listaBin = new ArrayList<String>();
		for (char c : texto.toUpperCase().toCharArray()) {
			listaBin.add(this.preenhcerZeroEsquerda(tamCasasBin, this.decimalToBinaryString(c)));
		}
		StringBuilder hash = new StringBuilder();
		for (int i = 0; i < tamCasasBin; i++) {
			int sum = 0;
			for (String s : listaBin) {
				sum += s.charAt(i) - '0';
			}
			if (sum % 2 == 0) {
				hash.append(0);
			} else {
				hash.append(1);
			}
		}
		return hash.toString();
	}

	//Método que 'codifica' texto baseado no PADRÃO DE CEASAR
	private String encodeDPC(String frase) {
		return dpc(frase.toUpperCase(), true);
	}

	//Método que 'decodifica' texto baseado no PADRÃO DE CEASAR
	private String decodeDPC(String frase) {
		return dpc(frase.toUpperCase(), false);
	}

	// encode => x+3
	// !encode => x-3
	private String dpc(String frase, boolean encode) {
		int varDpc = 3;
		StringBuilder builder = new StringBuilder();
		for (char c : frase.toCharArray()) {
			int num;
			if (encode) {
				num = ((int) c) + varDpc;
			} else {
				num = ((int) c) - varDpc;
			}
			int limSup = 90;
			if (num > limSup) {
				int dif = num - limSup;
				num = 64 + dif;
			} else {
				int limInf = 65;
				if (num < limInf) {
					int dif = limInf - num;
					num = 91 - dif;
				}
			}
			builder.append((char) num);
		}
		return builder.toString();
	}

	//Utils
	private String preenhcerZeroEsquerda(int casas, String tex) {
		long n = Long.parseLong(tex);
		String s = "";
		s = String.format("%0" + casas + "d", n);
		return s;
	}

	//converte valores maiores que zero para binário 
	private String decimalToBinaryString(int num) throws Exception {
		if (num < 1) {
			throw new ValidationException("Não é permitido zero ou números negativos");
		}
		StringBuilder res = new StringBuilder();
		while (num > 1) {
			res.append(num % 2);
			num = num / 2;
		}
		res.append(num);
		return res.reverse().toString();
	}
}
