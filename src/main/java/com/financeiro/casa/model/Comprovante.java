package com.financeiro.casa.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity 
@Table (name = "comprovantes")
public class Comprovante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeArquivo;
    private String nomeArmazenado;
    private String caminhoArquivo;
    private String tipoArquivo;
    private Long tamanhoArquivo;

    @CreationTimestamp 
    private LocalDateTime dataEnvio;

    @ManyToOne 
    @JoinColumn(name = "pagamento_id", nullable = false)
    private Pagamento pagamento;
    
    protected Comprovante(){
        //CONTRUTOR VAZIO
    }
    public Comprovante(
    String nomeArquivo,
    String nomeArmazenado,
    String caminhoArquivo,
    String tipoArquivo,
    Long tamanhoArquivo,
    Pagamento pagamento
) {
    this.nomeArquivo = nomeArquivo;
    this.nomeArmazenado = nomeArmazenado;
    this.caminhoArquivo = caminhoArquivo;
    this.tipoArquivo = tipoArquivo;
    this.tamanhoArquivo = tamanhoArquivo;
    this.pagamento = pagamento;
}

    //METODOS GETTERS
    public Long getId(){return id;}
    public String getNomeArquivo(){return nomeArquivo;}
    public String getNomeArmazenado(){return nomeArmazenado;}
    public String getCaminhoArquivo(){return caminhoArquivo;}
    public String getTipoArquivo(){return tipoArquivo;}
    public Long getTamanhoArquivo(){return tamanhoArquivo;}
    public LocalDateTime getDataEnvio(){return dataEnvio;}
}
