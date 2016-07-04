
package DAL;
import java.sql.*;
import javax.swing.JOptionPane;

public class ConectaBd {

    public static Connection conectabd() throws ClassNotFoundException{
        
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurante","postgres","12345");
            return con;
            //JOptionPane.showMessageDialog(null,"Conectado com sucesso!");
        }
        
        
        catch(SQLException error){
            
            JOptionPane.showMessageDialog(null, error);
            return null;  
        }
        
    }   
}
