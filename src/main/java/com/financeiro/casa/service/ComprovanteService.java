package com.financeiro.casa.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.financeiro.casa.model.Comprovante;
import com.financeiro.casa.repository.ComprovanteRepository;
import com.financeiro.casa.repository.FinanceiroRepository;
import com.financeiro.casa.model.Pagamento;
import java.util.List;
import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


@Service 
public class ComprovanteService {
    private final ComprovanteRepository repo;
    private final FinanceiroRepository pagamentoRepo;
    private final Path diretorio = Paths.get("comprovantes");

    public ComprovanteService(ComprovanteRepository repository,
        FinanceiroRepository financeiroRepository){
        this.repo = repository;
        this.pagamentoRepo = financeiroRepository;

    }
    //SALVAR COMPROVANTE
    public Comprovante salvarComprovante(MultipartFile arquivo, Long pagamentoId){
        Pagamento pagamento =pagamentoRepo.findById(pagamentoId)
        .orElseThrow(()-> new RuntimeException("Pagamento nao encontrado"));
        String nomeArquivo = arquivo.getOriginalFilename();
        String nomeArmazenado = java.util.UUID.randomUUID().toString();
        try{
            Files.createDirectories(diretorio);
        }catch(IOException e){
            throw new RuntimeException("Erro ao criar diretorio",e);
        }
        Path caminho = diretorio.resolve(nomeArmazenado);
        try {
            Files.copy(arquivo.getInputStream(), caminho);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo", e);
        }
        Comprovante comprovante = new Comprovante(
            nomeArquivo,
            nomeArmazenado,
            caminho.toString(),
            arquivo.getContentType(),
            arquivo.getSize(),
            pagamento
        );
        return repo.save(comprovante);
    }

    //LISTAR COMPROVANTES
    public List<Comprovante>listarTodos(){
        return repo.findAll();
    }

    public List<Comprovante>listarPorPagamento(Long pagamentoId){
        return repo.findByPagamentoId(pagamentoId);
    }

    //BUSCAR POR ID
    public Optional<Comprovante> buscarPorId(Long id){
        return repo.findById(id);
    }

    //METODO DELETE
    public void excluirComprovante(Long id){
        Comprovante comprovante = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Comprovante nao encontrado"));

        Path caminho = Paths.get(comprovante.getCaminhoArquivo());
        try{
            Files.deleteIfExists(caminho);
        }catch(IOException e){
            throw new RuntimeException("Erro ao excluir arquivo");
        }

        repo.delete(comprovante);
    }

    public void excluirPorPagamento(Long pagamentoId){
        List<Comprovante> comprovantes = repo.findByPagamentoId(pagamentoId);
        for (Comprovante comprovante : comprovantes){
            Path caminho = Paths.get(comprovante.getCaminhoArquivo());

            try{
                Files.deleteIfExists(caminho);
            }catch(IOException e){
                throw new RuntimeException("Erro ao excluir arquivo");
            }
            repo.delete(comprovante);
        }


    }

    //OBTER ARQUIVO
    public byte[] obterArquivo(Long id){
        Comprovante comprovante = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Comprovante nao encontrado"));

        Path caminho = Paths.get(comprovante.getCaminhoArquivo());
        try{
            return Files.readAllBytes(caminho);
        }catch(IOException e){
            throw new RuntimeException("Erro ao ler arquivo", e);
        }
    }
}
