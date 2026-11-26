package com.financeiro.casa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiro.casa.model.Comprovante;
import java.util.List;

public interface ComprovanteRepository extends JpaRepository<Comprovante, Long>{
    List<Comprovante>findByPagamentoId(Long id);
}
