package br.com.map.produto.ui;

import br.com.map.produto.dao.ProdutoDAO;
import br.com.map.produto.model.Produto;
import java.util.List;
import java.util.Scanner;

public class ProdutoMainMAP {
    
    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);
        String escolha;
        String aux;
        
        ProdutoDAO p_dao = new ProdutoDAO();
        
        Produto p;
        List<Produto> lista;
        
        int codigo;
        
        do{
            System.out.println("~/~/~/ MENU \\~\\~\\~");
            System.out.println("1 - CADASTRAR");
            System.out.println("2 - EXCLUIR");
            System.out.println("3 - EDITAR");
            System.out.println("4 - CONSULTAR");
            System.out.println("0 - SAIR");
            
            escolha = input. nextLine();
            
            switch(escolha){
                case "1":
                    
                    p = new Produto();
                    
                    System.out.println("\n~~~~ CADASTRAR ~~~~");
                    System.out.println("Nome: " );
                    p.setNome(input.nextLine());
                    System.out.println("Preco: ");
                    aux = input.nextLine();
                    p.setPreco(Float.parseFloat(aux.replaceAll(",", ".")));
                    
                    System.out.println("Fabricante: ");
                    p.getEspec().setFabricante(input.nextLine());
                    System.out.println("Cor: ");
                    p.getEspec().setCor(input.nextLine());
                    System.out.println("Sistema: ");
                    p.getEspec().setSistema(input.nextLine());
                    System.out.println("Detalhes: ");
                    p.getEspec().setDetalhes(input.nextLine());
                    
                    p_dao.inserir(p);
                    
                    System.out.println("Produto cadastrado com sucesso.\n");
                    
                    break;
                
                 case "2":
                    
                    System.out.println("\n~~~~ EXCLUIR ~~~~");
                    System.out.println("Informe o código: ");
                    codigo = Integer.parseInt(input.nextLine());
                    
                    p = p_dao.buscarPorCodigo(codigo);
                    
                    if(p != null){
                        p_dao.excluir(p);
                        System.out.println("Registro removido com sucesso.\n");
                    }else{
                        System.out.println("Registro não existente.\n");
                    }
                    
                    break;
                    
                case "3":
                    
                    System.out.println("\n~~~~ EDITAR ~~~~");
                    
                    System.out.println("Informe o código: ");
                    codigo = Integer.parseInt(input.nextLine());
                    
                    p = p_dao.buscarPorCodigo(codigo);
                    
                    if(p != null){
                        System.out.println("Encontrado: " + p);
                        
                        System.out.println("Novo nome: ");
                        p.setNome(input.nextLine());
                        System.out.println("Novo preço: ");
                        aux = input.nextLine();
                        p.setPreco(Float.parseFloat(aux.replaceAll(",", ".")));
                        
                        p_dao.editar(p);
                        
                        System.out.println("Registro editado com sucesso.\n");
                    }else{
                        System.out.println("Registro não encontrado.\n");
                    }
                    
                    break;
                    
                case "4":
                    
                    System.out.println("\n~~~~ CONSULTA ~~~~\n");
                    
                    lista = p_dao.listar();
                    
                    for(Produto p_aux : lista){
                        System.out.println(p_aux);
                    }
                    
                    System.out.println("\n");
                    
                    break;
                    
                case "0":
                    
                    System.out.println("Saindo...");
                   // Thread.sleep(1000);
                    
                    break;
                    
                default:
                    
                    System.out.println("Entrada inválida.\n");
                        
            }
        }while(!escolha.equals("0"));
    }
    
}
