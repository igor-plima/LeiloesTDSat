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
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?, ?)";
            //// Prepara o comando SQL para execução
            prep = conn.prepareStatement(sql);
            
            prep.setString(1,produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, "disponível");
            //Executa o INSERT no banco de dados
            prep.executeUpdate();
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + erro.getMessage() );
        }
        
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
