package com.teste.levil;

import java.io.IOException;
import java.util.List;

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

import com.teste.levil.HibernateUtil;



@SuppressWarnings("unused")
@Entity(name = "teste")
@Table(name = "teste")
@WebServlet("/App")
public class App  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String hash;

	@Column(name = "UrlOriginal", nullable = false)
	private String original;

	@Column(name = "IDusr", nullable = false)
	private Integer usuario;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	//---------CRUD------------------------------
	 public void insere(String novaURL, String velhaURL) {            
       	SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
       	Session session = sessionFactory.openSession();
			try{
				//Transaction manipulador = session.beginTransaction();
				session.beginTransaction();
				App  t = new App();
				
				//t.setSenha("orbes");
				//t.setNome("Cooper");
				t.setHash(novaURL);
				t.setOriginal(velhaURL);
				
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
	public List<App> lista() {             
		List<App> list= null;
		
		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
		Session session = sessionFactory.openSession();

		try{
			session.beginTransaction();
			list = session.createQuery("FROM App").list();
			//list = session.createQuery("SELECT t FROM App t").getResultList();
			session.getTransaction().commit();
			 
			/*for (App teste : list) {
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
        App usuario = (App) session.get(App.class, id_usr);

			usuario.setHash(novaURL);
			usuario.setOriginal(velhaURL);

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
    	
    	App usuario = (App) session.get(App.class, usuario_id);
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
	
	
	
	//main abaixo é para fins de teste de encurtador e funciona plenamente
	/*public static void main(String[] args) {
		Encurta u = new Encurta(5, "www.levil.com.br/");
		String urls[] = { "https://duckduckgo.com/", "https://www.google.com/", "http://www.yahoo.com", "www.yahoo.com/", "www.amazon.com", "www.amazon.com/page1.asp", "www.amazon.com/page2.asp", "www.mercadolivre.com.br", "www.facebook.com", "receita.economia.gov.br", "www.techmundo.com", "www.lifehacker.com", "www.bb.com.br" };
		for (int i = 0; i < urls.length; i++) 
			System.out.println("Original:" + urls[i] + " | Encurtado: " + u.encurtador(urls[i]) + " | Refeita: "+ u.refazUrl(u.encurtador(urls[i])));
	}*/

// ver o form no jsp  par ao encurta para setar corretamente aqui
	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String urlOriginal = request.getParameter("url");
	     boolean submitButtonPressed = request.getParameter("submit") != null;
	     //insere(usuario, pass);
	     response.sendRedirect(request.getContextPath()+"/resultado.jsp"); 
	 }
}
