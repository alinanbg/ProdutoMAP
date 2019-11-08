package br.com.map.produto.model;

public class Produto {
     private String nome;
     private int codigo;
     private float preco;
     private Especificacao espec;
     
     public Produto(){
         espec = new Especificacao();
     }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Especificacao getEspec() {
        return espec;
    }

    public void setEspec(Especificacao espec) {
        this.espec = espec;
    }
     
     
     @Override
     public String toString(){
         StringBuilder sb = new StringBuilder();
         
         sb.append(codigo).append(" | ")
                 .append(nome).append(" | ")
                 .append(preco).append(" | ")
                 .append(espec.getFabricante()).append(" | ")
                 .append(espec.getCor()).append(" | ")
                 .append(espec.getSistema()).append(" | ")
                 .append(espec.getDetalhes()).append(" | ");
                 
         return sb.toString();
     }
}
