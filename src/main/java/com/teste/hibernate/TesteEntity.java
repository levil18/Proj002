package com.teste.hibernate;
  
import java.io.IOException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import org.hibernate.Transaction;
import com.teste.hibernate.HibernateUtil;


//id (tipo: serial), nome (tipo: text), senha (tipo: text), chave primária criada para id


@SuppressWarnings("unused")
@Entity
@Table(name = "teste")
@WebServlet("/TesteEntity")
public class TesteEntity  extends HttpServlet{
  
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy=GenerationType.IDENTITY) 
		private long id;

		@Column(name = "nome", nullable = false)
		private String nome;

		@Column(name = "senha", nullable = false)
		private String senha;

		
		public long getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String toString() {
			return "Detalhes Pessoais?= Id: " + this.id + ", Nome: " + this.nome + ", Senha: " + this.senha;
		}

	   
       public void insere(String user, String pass) {            
           	SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
           	Session session = sessionFactory.openSession();
			try{
				//Transaction manipulador = session.beginTransaction();
				session.beginTransaction();
				TesteEntity t = new TesteEntity();
				
				//t.setSenha("orbes");
				//t.setNome("Cooper");
				t.setNome(user);
				t.setSenha(pass);
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
	public List<TesteEntity> lista() {             
   		List<TesteEntity> list= null;
   		
   		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.openSession();

   		try{
   			session.beginTransaction();
   			list = session.createQuery("FROM TesteEntity").list();
   			//list = session.createQuery("SELECT t FROM TesteEntity t").getResultList();
   			session.getTransaction().commit();
   			 
   			/*for (TesteEntity teste : list) {
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
   	
    public void atualiza(long id_usr) {
    	SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            
            // Criando a Transação
            TesteEntity usuario = (TesteEntity) session.get(TesteEntity.class, id_usr);

			usuario.setNome("Bárbaro");
			usuario.setSenha("love007");
 
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
        	
        	TesteEntity usuario = (TesteEntity) session.get(TesteEntity.class, usuario_id);
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
        String usuario = request.getParameter("nome");
        String pass = request.getParameter("senha");
        boolean submitButtonPressed = request.getParameter("submit") != null;
        insere(usuario, pass);
        response.sendRedirect(request.getContextPath()+"/Teste.jsp"); 
    }
}
