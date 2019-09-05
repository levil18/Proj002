package com.teste.leonardo;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="url")
public class App {

	public static void main(String[] args) {
		Encurta u = new Encurta(5, "www.levil.com.br/");
	       //Session session = HibernateUtil.getSessionFactory().openSession();
	        
	        //session.beginTransaction();
	        /*Stock stock = new Stock();
	        
	        stock.setStockCode("4715");
	        stock.setStockName("GENM");
	        */
	        
	        
		String urls[] = { "https://duckduckgo.com/", "https://www.google.com/", "http://www.yahoo.com", "www.yahoo.com/", "www.amazon.com", "www.amazon.com/page1.asp", "www.amazon.com/page2.asp", "www.mercadolivre.com.br", "www.facebook.com", "receita.economia.gov.br", "www.techmundo.com", "www.lifehacker.com", "www.bb.com.br" };

		for (int i = 0; i < urls.length; i++) {
			//session.save(u);
			System.out.println("Original:" + urls[i] + " | Encurtado: " + u.encurtador(urls[i]) + " | Refeita: "+ u.refazUrl(u.encurtador(urls[i])));
		}
		//session.getTransaction().commit();
	}

}
