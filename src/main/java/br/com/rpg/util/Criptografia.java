package br.com.rpg.util;

import br.com.caelum.vraptor.ioc.Component;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe para criptografar.
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Component
public class Criptografia {

	private static final String SALT = "Adeus mundo cruel!";

	/**
	 * Criptografar senha
	 *
	 * @param senha
	 * @return
	 */
	public String criptografarSenha(String senha) {
		String senhaCriptografada = null;
		try {
			senhaCriptografada = hash("SHA-512", senha + SALT);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return senhaCriptografada;
	}

	/**
	 * Gerar Senha Aleatória
	 * 
	 * @return 
	 */
	public String gerarSenhaAleatoria() {
		int selecionado;
		String senha = "";
		for (int i = 0; i < 8; i++) {
			int sort = (int) (Math.random() * 3);
			switch (sort) {
				case 0:
					selecionado = (int) (Math.random() * 9) + 48;
					break;
				case 1:
					selecionado = (int) (Math.random() * 25) + 65;
					break;
				case 2:
					selecionado = (int) (Math.random() * 25) + 97;
					break;
				default:
					selecionado = (int) (Math.random() * 25) + 97;
					break;
			}
			senha += String.format("%c", selecionado);
		}
		return senha;
	}
	
	/**
	 * Metodo para criptografar usando algoritimos de uma via (HASH).
	 *
	 * @param algoritimo algoritimo desejado, exemplo: MD5, SHA-256, etc. (Somente algoritimos de uma via)
	 * @param palavra
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String hash(String algoritimo, String palavra) throws NoSuchAlgorithmException {
		StringBuilder saida = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance(algoritimo);
			md.update(palavra.getBytes("UTF-8"));
			byte b[] = md.digest();
			String parte;
			for (int i = 0; i < b.length; i++) {
				parte = Integer.toHexString(0xFF & b[i]);
				if (parte.length() < 2) {
					parte = "0" + parte;
				}
				saida.append(parte);
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			saida = null;
		}
		return saida.toString();
	}
}