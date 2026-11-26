package com.financeiro.casa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.GenerationType;
import com.financeiro.casa.converter.YearMonthAttributeConverter;


@Entity 
@Table (name = "pagamentos")
public class Pagamento {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroParcela;

    @Convert(converter = YearMonthAttributeConverter.class)
    @Column(name = "mes_referencia") 
    private YearMonth mesParcela;

    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal valorPago;

    private LocalDate dataPagamento;

    private String descricao;

    @CreationTimestamp 
    private LocalDateTime criadoEm;

    protected Pagamento(){
        //vazio
    }
    //METODO GETTERS E SETTERS
    public void setNumeroParcela(Integer numeroParcela){this.numeroParcela = numeroParcela;}
    public void setMesParcela(YearMonth mesParcela){this.mesParcela = mesParcela;}
    public void setValorPago(BigDecimal valorPago){this.valorPago = valorPago;}
    public void setDataPagamento(LocalDate dataPagamento){this.dataPagamento = dataPagamento;}
    public void setDescricao(String descricao){this.descricao = descricao;}

    public Integer getNumeroParcela(){return  numeroParcela;}
    public YearMonth getMesParcela(){return mesParcela;}
    public BigDecimal getValorPago(){return valorPago;}
    public LocalDate getDataPagamento(){return  dataPagamento;}
    public String getDescricao(){return descricao;}

}
