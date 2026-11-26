package com.financeiro.casa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.financeiro.casa.service.ComprovanteService;
import com.financeiro.casa.model.Comprovante;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;



@RestController 
@RequestMapping("/api/comprovantes")
public class ComprovanteController {
    private final ComprovanteService service;

    public ComprovanteController(ComprovanteService service){
        this.service = service;
    }

    @GetMapping
    public List<Comprovante>listar(){
        return service.listarTodos();
    }

    @GetMapping("/pagamento/{pagamentoId}")
    public List<Comprovante>listarPorPagamento(@PathVariable Long pagamentoId){
        return service.listarPorPagamento(pagamentoId);
    }

    @GetMapping("/{id}/arquivo")
    public ResponseEntity<byte[]>obterArquivo(@PathVariable  Long id){
        Comprovante comprovante = service.buscarPorId(id)
        .orElseThrow(()-> new RuntimeException("Comprovante nao encontrado"));

        byte[] arquivo =  service.obterArquivo(id);

        return ResponseEntity.ok()
        .header("Content-Type",comprovante.getTipoArquivo())
        .body(arquivo);
    }

    @PostMapping("/upload")
    public Comprovante salvar(
        @RequestParam("arquivo")MultipartFile arquivo,
        @RequestParam("pagamentoId") Long pagamentoId){
        return service.salvarComprovante(arquivo, pagamentoId);
    }

    
}
