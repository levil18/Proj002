package com.teste.levil;


import java.util.HashMap;
import java.util.Random;




public class Encurta {

	// storage for generated keys
	private HashMap<String, String> mapeiaKey; // mapeamento de chave e url
	private HashMap<String, String> mapeiaVal;// uma pequena busca para saber se está registrado ou não a URL
	private String dominio = "levil.com.br"; // variável para gerar URL, padrão: http://levil.com.br
	private char caracteres[]; // array de caracteres inclui minusculas, mauisculas e numeros 
	private String busca = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private Random aleatorio; // gerador de números aleatórios
	private int tamKey; // refere-se ao tamanho da chave
	int counter = 0;
	

	// versão padrão
	Encurta() {
		mapeiaKey = new HashMap<String, String>();
		mapeiaVal = new HashMap<String, String>();
		aleatorio = new Random();
		tamKey = 8;
		caracteres = new char[62];
		for (int i = 0; i < 62; i++) {
			caracteres[i] = busca.charAt(i);
		}
	}

	//define o tamamnho do encurtamento da URL
	Encurta(int length) {
		this();
		this.tamKey = length;
	}

	// método para encurtar a URL 
	public String encurtador(String urlAtual) {
		String novaUrl = "";
		urlAtual = padronizaURL(urlAtual);
		if (mapeiaVal.containsKey(urlAtual)) {
			novaUrl =  mapeiaVal.get(urlAtual);
		} else {
			novaUrl =  pegaKey(urlAtual);
		}
		return novaUrl;
	}

	public boolean verificaHash (String urlAtual) {
		urlAtual = padronizaURL(urlAtual);
		if (mapeiaVal.containsKey(urlAtual)) {
			return true;
		} else {
			return false;
		}
	}

	// método para retornar a URL original
	public String refazUrl(String novaUrl) {
		String urlAtual = "";
		//String key = novaUrl.substring(dominio.length() + 1);
		//urlAtual = mapeiaKey.get(key);
		urlAtual = mapeiaKey.get(novaUrl);
		return urlAtual;
	}


	// serve para padronizar URLs que vão para o mesmo caminho
	String padronizaURL(String url) {
		if (url.substring(0, 7).equals("http://"))
			url = url.substring(7);

		if (url.substring(0, 8).equals("https://"))
			url = url.substring(8);

		if (url.charAt(url.length() - 1) == '/')
			url = url.substring(0, url.length() - 1);
		return url;
	}

	// serve para pegar a id a relacioná-la à URL atual no hashmap
	private String pegaKey(String urlAtual) {
		String id;
		id = geraKey();
		mapeiaKey.put(id, urlAtual);
		mapeiaVal.put(urlAtual, id);
		return id;
	}
	public void insereKey(String hash, String urlSalva) {
		mapeiaKey.put(hash, urlSalva);
		mapeiaVal.put(urlSalva, hash);
	}
	// gerador de id
	private String geraKey() {
		String id = "";
		boolean flag = true;
		while (flag) {
			id = "";
			for (int i = 0; i <= tamKey; i++) {
				id += caracteres[aleatorio.nextInt(62)];
			}
			//System.out.println("Contador: "+ counter + " Chave: "+ id);
			if (!mapeiaKey.containsKey(id)) {
				flag = false;
			}
			counter++;
		}
		return id;
	}
}