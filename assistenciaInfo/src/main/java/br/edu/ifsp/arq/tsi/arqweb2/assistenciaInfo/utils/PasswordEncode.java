package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncode {
	public static String criptografarSenha(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			
			for(byte b : bytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		}catch(NoSuchAlgorithmException e) {
			throw new RuntimeException("Erro ao buscar algoritmo", e);
		}
	}
}
