package br.com.map.produto.dao;

import br.com.map.produto.model.Produto;
import br.com.map.produto.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    private Connection con;
    private PreparedStatement st;
    private String sql;
        
        public void inserir(Produto produto) throws Exception{
            
            sql= "insert into especificacoes (fabricante, cor, detalhes, sistema)"
                    + "values (?, ?, ?, ?)";
            
            con = ConnectionFactory.getConnection();
            
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, produto.getEspec().getFabricante());
            st.setString(2, produto.getEspec().getCor());
            st.setString(3, produto.getEspec().getDetalhes());
            st.setString(4, produto.getEspec().getSistema());
            
            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            
            int codigoEspec = 0;
            
            if(rs.next()){
                codigoEspec = rs.getInt(1);
            }
            
            sql = "insert into produtos(nome, preco, codigo_espec) values(?, ?, ?)";
            
            st = con.prepareStatement(sql);
            
            st.setString(1, produto.getNome());
            st.setFloat(2, produto.getPreco());
            st.setInt(3, codigoEspec);
            
            st.executeUpdate();
            
            con.close();
        }
       
        public void editar(Produto p) throws Exception{
            
            sql = "update produtos set nome = ?, preco = ? where codigo = ?";
            
            con = ConnectionFactory.getConnection();
            
            st = con.prepareStatement(sql);
            
            st.setString(1, p.getNome());
            st.setFloat(2, p.getPreco());
            st.setInt(3, p.getCodigo());
            
            st.executeUpdate();
            
            con.close();
        }
        
        public void excluir(Produto p) throws Exception{
            
            sql = "delete from produtos where codigo = ?";
            
            con = ConnectionFactory.getConnection();
            
            st = con.prepareStatement(sql);
            
            st.setInt(1, p.getCodigo());
            
            st.executeUpdate();
        }
        
        public List<Produto> listar() throws Exception{
            
            sql = "select p.*, e.*"
                    + "from produtos p, especificacoes e"
                    + "where p.codigo_espec = e.codigo";
            
            con = ConnectionFactory.getConnection();
            
            st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            
            List<Produto> produtos = new ArrayList<>();
            
            while(rs.next()){
            
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                float preco = rs.getFloat("preco");
                String cor = rs.getString("cor");
                String fabricante = rs.getString("fabricante");
                String sistema = rs.getString("sistema");
                String detalhes = rs.getString("detalhes");
                
                Produto p = new Produto();
                p.setCodigo(codigo);
                p.setNome(nome);
                p.setPreco(preco);
                p.getEspec().setCor(cor);
                p.getEspec().setFabricante(fabricante);
                p.getEspec().setDetalhes(detalhes);
                p.getEspec().setSistema(sistema);
                
                produtos.add(p);
            }
            
            con.close();
            
            return produtos;
        }
        
        public Produto buscarPorCodigo (int codigo) throws Exception{
            
            sql = "select * from produtos where codigo = ?";
            
            con = ConnectionFactory.getConnection();
            
            st = con.prepareStatement(sql);
            st.setInt(1, codigo);
            
            ResultSet rs = st.executeQuery();
            Produto p = null;
            
            if (rs.next()){
                
                String nome = rs.getString("nome");
                Float preco = rs.getFloat("preco");
                
                p = new Produto();
                p.setCodigo(codigo);
                p.setNome(nome);
                p.setPreco(preco);
            }
            
            con.close();
            
            return p;
        }
}
