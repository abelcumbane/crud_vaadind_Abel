package com.example.application.data.repository;
import com.example.application.data.entity.TipoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Long> {
	

	//TODO:: Implementacao da query para trazer os dados da tabela TipoActividade e activar o metodo search.
//	@Query("select t from TipoOcorrencia t " +
//		      "where lower(t.descricao) like lower(tipoActividade('%', :searchTerm, '%')) " +
//		      "or lower(t.descricao) like lower(tipoActividadet('%', :searchTerm, '%'))") 
//		    List<TipoOcorrencia> search(@Param("searchTerm") String searchTerm); 


}
