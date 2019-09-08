package com.teste.levil;
  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
  


//id (tipo: serial), nome (tipo: text), senha (tipo: text), chave primária criada para id

@Entity
@Table(name = "teste")
public class TesteEntity_Old {
  
	   @Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
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
       public void setUsuario(String usuario) {
             this.nome = usuario;
       }
       
       public String getSenha() {
             return senha;
       }
       public void setSenha(String senha) {
             this.senha = senha;
       }
}
