package com.teste.leonardo;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.teste.persistence.HibernateUtil;




@Entity
@Table(name = "url")
@WebServlet("/Encurta")
public class Encurta extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// storage for generated keys
	private HashMap<String, String> mapeiaKey; // mapeamento de chave e url
	private HashMap<String, String> mapeiaVal;// uma pequena busca para saber se está registrado ou não a URL
	private String dominio; // variável para gerar URL, padrão: http://levil.com.br
	private char caracteres[]; // array de caracteres inclui minusculas, mauisculas e numeros 
	private String busca = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	//ex: abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789
	private Random aleatorio; // gerador de números aleatórios
	private int tamKey; // refere-se ao tamanho da chave
	int counter = 0;
	
	
	@Id
	private String hash;

	@Column(name = "url_original", nullable = false)
	private String original;

	@Column(name = "ID_usr", nullable = false)
	private Integer usuario;


	public String getHash() {
		return hash;
	}
	public void setHash(String HASH) {
		this.hash = HASH;
	}

	public String getURL() {
		return original;
	}
	public void setURL(String URL) {
		this.original = URL;
	}

	public Integer getId() {
		return usuario;
	}
	public void setId(int id) {
		this.usuario = id;
	}
	

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
		dominio = "bit.ly/";
	}

	//define o tamamnho do encurtamento da URL
	Encurta(int length, String novoDominio) {
		this();
		this.tamKey = length;
		if (!novoDominio.isEmpty()) {
			novoDominio = padronizaURL(novoDominio);
			dominio = novoDominio;
		}
	}

	// método para encurtar a URL
	public String encurtador(String urlAtual) {
		String novaUrl = "";
		urlAtual = padronizaURL(urlAtual);
		if (mapeiaVal.containsKey(urlAtual)) {
			novaUrl = dominio + "/" + mapeiaVal.get(urlAtual);
		} else {
			novaUrl = dominio + "/" + pegaKey(urlAtual);
		}
		/*setURL(urlAtual);
		setHash(novaUrl);
		setId(1);*/
		return novaUrl;
	}


	// método para retornar a URL original
	public String refazUrl(String novaUrl) {
		String urlAtual = "";
		String key = novaUrl.substring(dominio.length() + 1);
		urlAtual = mapeiaKey.get(key);
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
	
	//---------CRUD------------------------------
	 public void insere(String novaURL, String velhaURL) {            
        	SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        	Session session = sessionFactory.openSession();
			try{
				//Transaction manipulador = session.beginTransaction();
				session.beginTransaction();
				Encurta t = new Encurta();
				
				//t.setSenha("orbes");
				//t.setNome("Cooper");
				t.setHash(novaURL);
				t.setURL(velhaURL);
				
				long id1 = (Long) session.save(t);
				System.out.println("O Id do usuario é: "+id1);
				//manipulador.commit();
				session.getTransaction().commit();
				
			}catch ( HibernateException e ){
				System.out.println("Erro de inserção!!!");
				System.out.println(e);
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
			}finally{
				session.close();
			}		 
	}
 
	@SuppressWarnings("unchecked")
	public List<Encurta> lista() {             
		List<Encurta> list= null;
		
		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
     Session session = sessionFactory.openSession();

		try{
			session.beginTransaction();
			list = session.createQuery("FROM Encurta").list();
			//list = session.createQuery("SELECT t FROM Encurta t").getResultList();
			session.getTransaction().commit();
			 
			/*for (Encurta teste : list) {
				   System.out.println("Usuario: " + teste.getNome());
			}*/
			/*
			for(int i = 0; i < list.size(); i++) {
			   System.out.println("Usuário: " + list.get(i).getNome() );
			}*/

		}catch ( HibernateException e ){
			System.out.println("Erro de leitura!!!");
			System.out.println(e);
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return list;
						
    }
	
 public void atualiza(long id_usr, String novaURL, String velhaURL) {
 	SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
     Session session = sessionFactory.openSession();
     try {
         session.beginTransaction();
         
         // Criando a Transação
         Encurta usuario = (Encurta) session.get(Encurta.class, id_usr);

			usuario.setHash(novaURL);
			usuario.setURL(velhaURL);

         session.getTransaction().commit();

     }catch ( HibernateException e ){
			System.out.println("Erro de atualização!!!");
			System.out.println(e);
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
		}finally{
			session.close();
		}
 }
 
 public void deleta(long usuario_id) {
 	SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
     Session session = sessionFactory.openSession();
     try {
     	session.beginTransaction();
     	
     	Encurta usuario = (Encurta) session.get(Encurta.class, usuario_id);
         session.delete(usuario);

         session.getTransaction().commit();
     }catch ( HibernateException e ){
			System.out.println("Erro em deletar!!!");
			System.out.println(e);
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
		}finally{
			session.close();
		}
 }
 
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String urlOriginal = request.getParameter("url");
     boolean submitButtonPressed = request.getParameter("submit") != null;
     //insere(usuario, pass);
     response.sendRedirect(request.getContextPath()+"/resultado.jsp"); 
 }
}