package com.example.application.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class TipoOcorrencia extends AbstractEntity {
	

	 @Id
	 private Long id;
		
	 @Column
	 private String descricao;
	 
	 public TipoOcorrencia(Long id, String descricao) {
		  super();
		  this.id = id;
		  this.descricao = descricao;
		 }

		 public TipoOcorrencia() {
		  
		 }
		 
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

}
