
package com.example.application.data.service;

import com.example.application.data.entity.TipoOcorrencia;
import com.example.application.data.repository.TipoOcorrenciaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service 
public class OcorrenciaService {

    private final TipoOcorrenciaRepository tipoOcorrenciaRepository;
    

    public OcorrenciaService (TipoOcorrenciaRepository tipoOcorrenciaRepository) { 
        this.tipoOcorrenciaRepository = tipoOcorrenciaRepository;
        
    }

    public List<TipoOcorrencia> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) { 
            return tipoOcorrenciaRepository.findAll();
        } else {
        	//TODO:: Implementacao do metodo search.
           // return tipoActividadeRepository.search(stringFilter);
        }
		return null;
    }
      

    public long countContacts() {
        return tipoOcorrenciaRepository.count();
    }

    public void deleteContact(TipoOcorrencia ocorrencia) {
    	tipoOcorrenciaRepository.delete(ocorrencia);
    }

    public void saveContact(TipoOcorrencia ocorrencia) {
        if (ocorrencia == null) { 
        	System.err.println("TipoOcorrencia Incopativel. Falha na conexacao com a entity TipoOcorrencia.");;
            return;
        }
        tipoOcorrenciaRepository.save(ocorrencia);
    }

}

