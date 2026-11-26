package com.financeiro.casa.service;

import org.springframework.stereotype.Service;
import com.financeiro.casa.repository.FinanceiroRepository;
import com.financeiro.casa.model.Pagamento;
import java.util.List;
import java.util.Optional;

@Service 
public class PagamentoService {
    private final FinanceiroRepository repo;
    private final ComprovanteService comprovanteService;

    public PagamentoService(FinanceiroRepository repository,
        ComprovanteService comprovanteService
    ){
        this.repo = repository;
        this.comprovanteService = comprovanteService;
    }

    //METODO CADASTRAR
    public Pagamento cadastrarPagamento(Pagamento pagamento){
        return repo.save(pagamento);
    }
    
    //LISTAR TODOS PAGAMENTOS
    public List<Pagamento>listarTodos(){
        return repo.findAll();
    }
    //METODO BUSCAR POR ID
    public Optional<Pagamento>buscarPorId(Long id){
        return repo.findById(id);
    }
    //METODO ATUALIZAR
    public void atualizarPagamento(Long id, Pagamento pagamentoAtualizado){
        Pagamento pagamento = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));

        pagamento.setValorPago(pagamentoAtualizado.getValorPago());
        pagamento.setNumeroParcela(pagamentoAtualizado.getNumeroParcela());
        pagamento.setMesParcela(pagamentoAtualizado.getMesParcela());
        pagamento.setDescricao(pagamentoAtualizado.getDescricao());
        pagamento.setDataPagamento(pagamentoAtualizado.getDataPagamento());

        repo.save(pagamento);
    }
    //METODO DELETE
    public void excluirPagamento(Long id){
        Pagamento pagamento = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));

        comprovanteService.excluirPorPagamento(id);

        repo.delete(pagamento);
    }

}
