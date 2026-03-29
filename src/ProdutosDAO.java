/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author IgorP
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        
        try {
            //Abre a conexão usando o conectaDAO
            conn = new conectaDAO().connectDB();
            //Define o comando SQL
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            //// Prepara o comando SQL para execução
            prep = conn.prepareStatement(sql);
            
            prep.setString(1,produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, "disponível");
            //Executa o INSERT no banco de dados
            prep.executeUpdate();
            
            //Mensagem de sucesso caso produto seja cadastrado
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + erro.getMessage() );
        }
        
    }
    
    //Método responsável por fazer um SELECT no banco e mostrar na tela os dados cadastrados na tabela do banco
    public ArrayList<ProdutosDTO> listarProdutos() {
        // Limpa a lista antes de buscar, evitando dados duplicados
        listagem.clear();
        
        try {
            conn = new conectaDAO().connectDB(); //Abre a conexão com o banco
            
            String sql = "SELECT id, nome, valor, status FROM produtos"; //Comando sql que busca todos os campos de todos os produtos cadastrados
            
            prep = conn.prepareStatement(sql); //Prepara o comando
            resultset = prep.executeQuery(); //executeQuery() é usado para SELECT (diferente do executeUpdate do INSERT), e o resultado fica guardado no resultset
            
            // Percorre cada linha retornada pelo banco
            // resultset.next() avança para a próxima linha e retorna false quando acabar
            while (resultset.next() ) {
                
                ProdutosDTO produto = new ProdutosDTO(); // Para cada linha, cria um DTO e preenche com os dados
                
                // getInt() e getString() leem o valor da coluna pelo nome
                produto.setId(resultset.getInt("id") );
                produto.setNome(resultset.getString("nome") );
                produto.setValor(resultset.getInt("valor") );
                produto.setStatus(resultset.getString("status") );
                
                // Adiciona o produto preenchido na lista
                listagem.add(produto);
            }
                
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + erro.getMessage() );
        }
        
        // Retorna a lista completa para quem chamou o método
        return listagem;
    }

}
