package com.dac.marina.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int num_ordem;
    private String email;
    private String primeiro_nome;
    private String nome_meio;
    private String sobrenome;
    private String afiliacao;
    private String afiliacao_ingles;
    private String pais;
    private String OrcID;

    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinTable(name = "autor_artigo",
            joinColumns = {
                    @JoinColumn(name = "autor_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "artigo_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	private List<Artigo> lista_artigos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public int getNum_ordem() {
        return num_ordem;
    }

    public void setNum_ordem(int num_ordem) {
        this.num_ordem = num_ordem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    public void setPrimeiro_nome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    public String getNome_meio() {
        return nome_meio;
    }

    public void setNome_meio(String nome_meio) {
        this.nome_meio = nome_meio;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }

    public String getAfiliacao_ingles() {
        return afiliacao_ingles;
    }

    public void setAfiliacao_ingles(String afiliacao_ingles) {
        this.afiliacao_ingles = afiliacao_ingles;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getOrcID() {
        return OrcID;
    }

    public void setOrcID(String OrcID) {
        this.OrcID = OrcID;
    }
    
    public List<Artigo> getLista_artigos() {
    	return lista_artigos;
   }

   public void setLista_artigos(List<Artigo> lista_artigos) {
	   this.lista_artigos = lista_artigos;
   }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Autor[ id=" + id + " ]";
    }

    
}

