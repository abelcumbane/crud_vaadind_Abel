package com.example.application.data.repository;
import com.example.application.data.entity.TipoActividade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoActividadeRepository extends JpaRepository<TipoActividade, Long> {
	
	//TODO:: Implementacao da query para trazer os dados da tabela TipoActividade e activar o metodo search.
//	@Query("select t from TipoActividade t " +
//		      "where lower(t.descricao) like lower(tipoActividade('%', :searchTerm, '%')) " +
//		      "or lower(t.descricao) like lower(tipoActividadet('%', :searchTerm, '%'))") 
//		    List<TipoActividade> search(@Param("searchTerm") String searchTerm); 

}
