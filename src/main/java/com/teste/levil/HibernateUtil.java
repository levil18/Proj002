package com.teste.levil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	//private static final String HIBERNATE_CFG = "hibernate.cfg.xml";
	//private static final String HIBERNATE_CFG = "TesteEntity.hbm.xml";
	private static final String HIBERNATE_CFG = "PonteEncurta.hbm.xml";
	//classe de onde puxará o mapeamento, tanto na declaração na instância da classe
	//como na classe recebida
	private static final Class<PonteEncurta> CLASSE_MAP = com.teste.levil.PonteEncurta.class;
	
	static SessionFactory buildSessionFactory() {
        try {
        	Configuration configuration = new Configuration();
        	//mudar a classe para a que quer mapear
        	configuration.addAnnotatedClass(CLASSE_MAP);
        	configuration.configure(HIBERNATE_CFG);
        	System.out.println("Configurações do Hibernate Carregado");
        	
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	System.out.println("serviceRegistry Criado!");
        	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Erro na criação do SessionFactory." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}