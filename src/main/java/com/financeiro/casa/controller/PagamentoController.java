package com.financeiro.casa.controller;

import com.financeiro.casa.service.PagamentoService;
import com.financeiro.casa.model.Pagamento;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/pagamentos")
public class PagamentoController {
    private  final PagamentoService service;
    
    public PagamentoController(PagamentoService service){
        this.service = service;
    }

    @GetMapping 
    public  List<Pagamento>listar(){
        return service.listarTodos();
    }
    @PostMapping
    public Pagamento cadastrar(@RequestBody Pagamento pagamento){
        return  service.cadastrarPagamento(pagamento);
    }
    
    @GetMapping("/{id}")
    public Pagamento buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
        .orElseThrow(()-> new RuntimeException("Livro nao encontrado"));
    }
    @PutMapping("/{id}")
    public void atualizarPagamento(@PathVariable Long id, @RequestBody Pagamento pagamento){
        service.atualizarPagamento(id, pagamento);
    }
    @DeleteMapping ("/{id}")
    public void excluir (@PathVariable Long id){
        service.excluirPagamento(id);
    }
    
}
