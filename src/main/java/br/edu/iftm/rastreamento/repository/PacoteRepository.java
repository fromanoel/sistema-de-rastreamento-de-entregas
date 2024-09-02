package br.edu.iftm.rastreamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.rastreamento.model.Pacote;

@Repository
public interface PacoteRepository extends CrudRepository<Pacote, Long> {

    // Buscar pacotes por status
    @Query("select p from Pacote p where p.status = :status")
    List<Pacote> findPacotesPorStatus(@Param("status") String status);
    // Buscar pacotes por destinatario
     @Query("select p from Pacote p where p.destinatario = :destinatario")
     List<Pacote> findPacotesPorDestinatario(@Param("destinatario") String destinatario);
}
