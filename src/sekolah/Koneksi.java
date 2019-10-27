/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        
package sekolah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author Idris Mochamad
 */
public class Koneksi {
    private Connection koneksi;
    
    public Connection getKoneksi(){
        if(koneksi == null){
            try{
                String username = "root";
                String password = "";
                String host = "jdbc:mysql://localhost:3306/sekolah";
                //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(host, username, password);
                //JOptionPane.showMessageDialog(null, "Koneksi Berhasil", "Berhasil", JOptionPane.CLOSED_OPTION);
            }catch(SQLException e){
                System.err.println("Gagal melakukan koneksi: "+e);
                JOptionPane.showMessageDialog(null, "Gagal Terhubung ke Database, pastikan database sudah aktif", "Error Database", JOptionPane.ERROR_MESSAGE);
                System.exit(EXIT_ON_CLOSE);
            }                        
        }
        return koneksi;
    }
}
