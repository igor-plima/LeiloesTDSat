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

    // // Variáveis que serão usadas nos métodos para comunicação com o banco
    Connection conn; // Representa a conexão com o banco
    PreparedStatement prep; // Representa o comando SQL que vamos executar
    ResultSet resultset; // Representa o resultado de um SELECT
    ArrayList<ProdutosDTO> listagem = new ArrayList<>(); // Lista de produtos

    /**
     * Recebe um objeto ProdutosDTO já preenchido com nome, valor e status, e
     * salva esses dados no banco de dados com um comando INSERT.
     */
    public void cadastrarProduto(ProdutosDTO produto) { //Método responsável pelo cadastro de produtos no banco (INSERT)

        try {
            //Abre a conexão usando o conectaDAO
            conn = new conectaDAO().connectDB();
            //Define o comando SQL
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            //// Prepara o comando SQL para execução
            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, "disponível");
            //Executa o INSERT no banco de dados
            prep.executeUpdate();

            //Mensagem de sucesso caso produto seja cadastrado
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + erro.getMessage());
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
            while (resultset.next()) {

                ProdutosDTO produto = new ProdutosDTO(); // Para cada linha, cria um DTO e preenche com os dados

                // Lê o valor da coluna "id" da linha atual (retorna número inteiro)
                produto.setId(resultset.getInt("id"));
                // Lê o valor da coluna "nome" da linha atual (retorna texto)
                produto.setNome(resultset.getString("nome"));
                 // Lê o valor da coluna "valor" da linha atual (retorna número inteiro)
                produto.setValor(resultset.getInt("valor"));
                // Lê o valor da coluna "status" da linha atual (retorna texto)
                produto.setStatus(resultset.getString("status"));

                // Adiciona o produto preenchido na lista
                listagem.add(produto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + erro.getMessage());
        }

        // Retorna a lista completa para quem chamou o método
        return listagem;
    }

    public void venderProduto(Integer id) { // Método responsável por receber um ID de um produto e atualizar o status dele para "Vendido" no banco (UPDATE)
        try {
            // Abrindo a conexão com o banco
            conn = new conectaDAO().connectDB();

            // Comando update no banco
            // Atualiza apenas o produto cujo ID for igual ao recebido
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

            //Prepara o comando sqlq
            prep = conn.prepareStatement(sql);

            // Substituindo o "?" pelo Id recebido pelo usuário
            prep.setInt(1, id);

            // Executa o UPDATE no banco
            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender: " + erro.getMessage());
        }
    }

}
