package com.financeiro.casa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiro.casa.model.Pagamento;

public interface FinanceiroRepository extends JpaRepository<Pagamento, Long> {
    
}
