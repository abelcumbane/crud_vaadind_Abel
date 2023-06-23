
package com.example.application.data.service;

import com.example.application.data.entity.TipoActividade;
import com.example.application.data.repository.TipoActividadeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service 
public class ActividadeService {

    private final TipoActividadeRepository tipoActividadeRepository;
    

    public ActividadeService(TipoActividadeRepository tipoActividadeRepository) { 
        this.tipoActividadeRepository = tipoActividadeRepository;
        
    }

    public List<TipoActividade> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) { 
            return tipoActividadeRepository.findAll();
        } else {
        	//TODO:: Implementacao do metodo search.
           // return tipoActividadeRepository.search(stringFilter);
        }
		return null;
    }
      

    public long countContacts() {
        return tipoActividadeRepository.count();
    }

    public void deleteContact(TipoActividade actividade) {
    	tipoActividadeRepository.delete(actividade);
    }

    public void saveContact(TipoActividade actividade) {
        if (actividade == null) { 
            System.err.println("TipoActividade Incopativel. Falha na conexacao com a entity TipoActividade.");
            return;
        }
        tipoActividadeRepository.save(actividade);
    }

}
