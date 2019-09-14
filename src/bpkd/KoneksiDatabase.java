package bpkd;


import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDatabase {
private static java.sql.Connection koneksi;
    public static java.sql.Connection getKoneksi(){
    if (koneksi == null ){
        try {
                String url ="jdbc:mysql://localhost:3306/arsip";
                String user = "root";
                String password="";
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url,user,password);
                System.out.println("Koneksi Berhasil");
        }
        catch (SQLException t){
            System.out.println("Error Membuat Koneksi");
        }
    
    }
    return koneksi;
    }   
}
