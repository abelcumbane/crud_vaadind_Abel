package com.example.application.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class TipoActividade extends AbstractEntity {
	
	
	@Id
	private Long id;
		
	@Column
	private String descricao;
		
	@Column
	private Boolean avaliacao;
	  
	  public TipoActividade() {
		  
		  super();
		  this.id = id;
		  this.descricao = descricao;
		  this.avaliacao = avaliacao;
		  
	  }
	  
	  @Override
	  public Long getId() {
	   return id;
	  }

	  public void setId(Long id) {
	   this.id = id;
	  }
	  
	  public String getDescricao() {
		 return descricao;
	  }
	  
	  public void setDescricao(String descricao) {
		  this.descricao = descricao;
	  }

	  @Override
	  public String toString() {
		  return descricao ;
	  }
	  
	  public Boolean getAvaliacao() {
		  return avaliacao;
	  }

	  public void setAvaliacao(Boolean avaliacao) {
		 this.avaliacao = avaliacao;
		 
	  }	

}
