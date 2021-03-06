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
import com.teste.levil.Encurta;

@Entity
@Table(name = "urls") 
@WebServlet(
        name = "PonteEncurta",
        description = "Servelet de conex�o",
        urlPatterns = "PonteEncurta"
)
public class PonteEncurta extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String hash;

	@Column(nullable = false)
	private String original;

	@Column(nullable = false)
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
	
	public String toString() {
		return "Detalhes Pessoais?= Id: " + this.usuario + ", Nome: " + this.hash + ", Senha: " + this.original;
	}

   
   public void insere(String hash, String url) {            
       	SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
       	Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			PonteEncurta l = new PonteEncurta();

			l.setHash(hash);
			l.setOriginal(url);
			l.setUsuario(1);
			//long id1 = (Long) session.save(l);
			session.save(l);
			//System.out.println("O Id do usuario �: "+id1);
			session.getTransaction().commit();
			
		}catch ( HibernateException e ){
			System.out.println("Erro de inser��o!!!");
			System.out.println(e);
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
		}finally{
			session.close();
		}		 
	}
   
	@SuppressWarnings("unchecked")
	public List<PonteEncurta> lista() {             
		List<PonteEncurta> list= null;
		
		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
		Session session = sessionFactory.openSession();

		try{
			session.beginTransaction();
			list = session.createQuery("FROM PonteEncurta").list();
			//list = session.createQuery("SELECT l FROM PonteEncurta l").getResultList();
			session.getTransaction().commit();
			 
			/*for (PonteEncurta teste : list) {
				   System.out.println("Usuario: " + teste.getNome());
			}*/
			/*
			for(int i = 0; i < list.size(); i++) {
			   System.out.println("Usu�rio: " + list.get(i).getNome() );
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
	
	//vers�o 1 de atualiza��o que n�o se aplica a este caso
	/*public void atualiza(String hash, String novaURL, String velhaURL) {
		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
	    Session session = sessionFactory.openSession();
	    try {
	        session.beginTransaction();
	        
	        // Criando a Transa��o
	        PonteEncurta l = (PonteEncurta) session.get(PonteEncurta.class, hash);

				l.setHash(novaURL);
				l.setOriginal(velhaURL);
				l.setUsuario(2);
	        session.getTransaction().commit();

	    }catch ( HibernateException e ){
				System.out.println("Erro de atualiza��o!!!");
				System.out.println(e);
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
			}finally{
				session.close();
			}
	}*/
	
	@SuppressWarnings("null")
	public String[] atualiza(String hash, String novaURL) {
		String novaHash, mod[] = null;
		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
	    Session session = sessionFactory.openSession();
		
		Encurta u = new Encurta(5);
		novaHash = u.encurtador(novaURL);
		novaURL = u.refazUrl(novaHash);
		mod[0] = novaHash;
		mod[1] = novaURL;
	    try {
	        PonteEncurta l = new PonteEncurta();
		    l.deleta(hash);
		    l.insere(novaHash, novaURL);
	    }catch ( HibernateException e ){
			System.out.println("Erro de atualiza��o!!!");
			System.out.println(e);
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return mod;
	}

	public void deleta(String hash) {
		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
	    Session session = sessionFactory.openSession();
	    try {
	    	session.beginTransaction();
	    	
	    	PonteEncurta l = (PonteEncurta) session.get(PonteEncurta.class, hash);
	        session.delete(l);

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
	     	String urlOriginal = request.getParameter("url"), hashGerada = null, urlRefeita = null, metodo = request.getParameter("metodo"), hash = request.getParameter("hash"), novos[];
	        boolean PressBotao = request.getParameter("hash") != null;
	     	
	        if(PressBotao) {
	        	switch(metodo) {
	        		case "delete":
	        			deleta(hash);
						request.getRequestDispatcher("/index.jsp");
	        			break;
	        		case "update":
						novos = atualiza(hash, urlOriginal);
						/*request.setAttribute("original", novos[0]);
						request.setAttribute("hash", novos[1]);*/
						
						response.setContentType("text/plain");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(novos[0]);
						//request.getRequestDispatcher("/index.jsp");
	        			break;
	        	}
	        	
	        }else {
		     	List<PonteEncurta> list= null;
		     	
				//Encurta u = new Encurta(5, "www.levil.com.br/");
		     	Encurta u = new Encurta(5);

				
				list = lista();
			    for(int i = 0; i < list.size(); i++) {
				    u.insereKey(list.get(i).getHash(), list.get(i).getOriginal());
				}
			    
			    
			    //System.out.println(hashGerada);
			    //System.out.println(u.verificaHash(urlOriginal)); 
			    
			    if(!(u.verificaHash(urlOriginal))) {
			    	hashGerada = u.encurtador(urlOriginal);
				    urlRefeita = u.refazUrl(hashGerada);
			    	//u.insereKey(hashGerada,urlRefeita);
			        insere(hashGerada, urlRefeita);
			    }
			    //passa atributo para a p�gina
			    request.setAttribute("original", urlRefeita);
			    request.setAttribute("hash", hashGerada);
			    //seta o atributo e joga pra p�gina linkada
			    request.getRequestDispatcher("/index.jsp").forward(request, response);
	        }
	 }
}
