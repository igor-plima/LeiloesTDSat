
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author IgorP
 */
public class conectaDAO {
    
    /*Método responsável pela conexão com o banco de dados
    Ele retorna um objeto do tipo Connection, que representa
      essa conexão aberta. Os outros DAOs vão usar essa conexão
      para executar os comandos SQL (INSERT, SELECT, etc).
    */
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/LeiloesTDSat?user=root&password=root");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }
        return conn;
    }
    
}
