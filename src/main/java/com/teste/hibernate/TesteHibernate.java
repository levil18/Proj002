package com.teste.hibernate;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.teste.hibernate.TesteEntity;
  
  public class TesteHibernate {
  

	public void insere() {            

		/*Configuration conf = new Configuration();
		conf.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();        
		SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();*/
		
		Configuration conf = new Configuration();
		conf.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();        
		SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		// Prep Work
		SessionFactory sessionFactory1 = HibernateUtil.getSessionFactory();
		System.out.println("Tentando...");
		try {
			System.out.println("Tentativa 2");
			//session.beginTransaction();

			//save example - with transaction
			Transaction tx1 = session.beginTransaction();
			Session session1 = sessionFactory1.openSession();
			TesteEntity t = new TesteEntity();
			long id1 = (Long) session1.save(t);
			System.out.println("2. Employee save called with transaction, id="+id1);
			System.out.println("3. Before committing save transaction");
			tx1.commit();
			System.out.println("4. After committing save transaction");
			System.out.println("*****");
			
			
			/*t.setSenha("levil");
			t.setUsuario("magnus");*/
			
			/*String hql = "INSERT INTO Employee(firstName, lastName, salary)"  + 
		             "SELECT firstName, lastName, salary FROM old_employee";*/
			String hql = "INSERT INTO TesteEntity(id, nome, senha)"  + 
		             "SELECT t.id, t.usuario, t.senha FROM TesteEntity t";
			//"insert into Venda (id, numeroOcorrencia) select vv.id, vv.numeroOcorrencia from Venda vv";
			//"INSERT INTO `TesteEntity` (`id`, `nome`, `senha`) VALUES ('1', 'Magnus23', '231816');

		//Query query = session.createQuery(hql);
		//int result = query.executeUpdate();
		//System.out.println("Rows affected: " + result);
			
			
			//salva usuario criado acima
			
			 
			//comita a transacao
			//session1.getTransaction().commit();

		} catch ( HibernateException e ) {
			System.out.println("Errou!!!");
			System.out.println(e);
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
		} finally {
			session.close();
		}
					 
	}
	public void lista() {             
		List<TesteEntity> list= null;

		Configuration conf = new Configuration();
		conf.configure();
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();        
		SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);


		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			list = session.createQuery("select t from TesteEntity t").getResultList();
			session.getTransaction().commit();
			 
			for (TesteEntity teste : list) {
				   System.out.println
					("Usuario: " + teste.getNome());
			}

		} catch ( HibernateException e ) {
			if ( session.getTransaction() != null )
				   session.getTransaction().rollback();
		} finally {
			session.close();
		}
						
    }
}