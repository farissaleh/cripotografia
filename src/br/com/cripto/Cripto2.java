package br.com.cripto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.ValidationException;

public class Cripto2 {
	
	/**
	 * @local Faculdade UPIS
	 * @materia Segurança da Informação
	 * @professor Olivier Gbegan
	 * @author Faris Saleh Ahmad
	 * @throws Exception 
	 * @data 25 de setembro de 2019
	 */

//	public static void main(String[] args) throws Exception {
////		List<Integer> lista = Arrays.asList(1  ,3601 ,1352 ,2990 ,184, 1, 184, 1352,  1352  ,2283 ,210 ,1352  ,
////				1352 ,3200 ,1735, 210, 184, 1352, 4119  ,1467 ,210 ,4119, 1735, 3280  ,3281, 1352, 1467 , 360 ,
////				1 ,2990 ,1 , 3200, 1 ,3200);
//		
////		List<Integer> lista = Arrays.asList(1882  ,1325 ,3261 ,2342 , 2304 ,2025, 3261, 1392, 1 , 1168 ,388,2342, 878 ,3349 ,1 , 1168 , 1 ,2304 ,1 ,2304);
//
//		char[] frase = "VOUESTUDARBEMPARASAS".toCharArray();
//		
//		for (char c : frase) {
////			System.out.println((int) c - 64);
//			
//			BigDecimal v = new BigDecimal((int) c - 64) ;
//			
//			BigDecimal E = new BigDecimal(593);
//			BigDecimal D = new BigDecimal(17);
//			BigDecimal N = new BigDecimal(2117);
//			
//			System.out.println( (v.pow(E.intValue()).remainder(N).intValue()));
//		}
//		
////		String text = "";
////		for (Integer integer : lista) {
////			BigDecimal v = new BigDecimal(integer);
////			
//////			E = new BigDecimal(0);
////			BigDecimal D = new BigDecimal(17);
////			BigDecimal N = new BigDecimal(4661);
////			
////			text+=(char) (v.pow(D.intValue()).remainder(N).intValue()+64);
////			
////		}
////		System.out.println(text);
////		System.out.println(new Cripto().hash(text));
////	
//	}
	
	public static void main(String[] args) throws Exception {
//		metod();
//		metod2();
	}
	
	public static void metod() throws Exception {
//		List<Integer> lista = Arrays.asList(1699,5698,1,2760,3125,3048,1,5827,4356,3048,1699,5827,5698,596,1,1024,1);
		List<Integer> lista = Arrays.asList(1568,2048,1568,6686,1,8854,8834,10619,1568,2515,1,6686,1,9341,9632,2798,8563,9632,10396,1);
		
		String text = "";
		for (Integer integer : lista) {
			BigDecimal v = new BigDecimal(integer);
			
			BigDecimal D = new BigDecimal(2291);
			BigDecimal N = new BigDecimal(12827);
			
			text+=(char) (v.pow(D.intValue()).remainder(N).intValue()+64);
			
		}
		System.out.println("a)Texto Claro: "+text);
		System.out.println("b)HASH: "+new Cripto().hash(text));
	
	}
	
	public static void metod2() throws Exception {
		
//		char[] frase = "FRASENAOINFORMADA".toCharArray();
		char[] frase = "AUPISEBOAFACULDADE".toCharArray();
		System.out.print("c) ");
		for (char c : frase) {
			
			BigDecimal v = new BigDecimal((int) c - 64) ;
			
			BigDecimal E = new BigDecimal(11);
			BigDecimal N = new BigDecimal(12827);
			
			System.out.print(+ (v.pow(E.intValue()).remainder(N).intValue())+",");
		}
		
	}
}
