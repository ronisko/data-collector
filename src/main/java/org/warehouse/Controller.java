package org.warehouse;

import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class Controller {

    @Autowired
    private CategoryRepository categoryRepository;

    public Button query_1;

    public void handleButtonClick() {
        categoryRepository.findAll().forEach(System.out::println);

//        String server = "192.168.22.128";
//        String dbName = "TELECOMMUNICATION";
//        String url = "jdbc:netezza://" + server + "/" + dbName ;
//        String user = "admin";
//        String pwd = "password";
//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//        try {
//            Class.forName("org.netezza.Driver");
//            System.out.println(" Connecting ... ");
//            conn = DriverManager.getConnection(url, user, pwd);
//            System.out.println(" Connected " + conn);
//
//            String sql = "SELECT * FROM category_table;";
//            st = conn.createStatement();
//            rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                System.out.println(rs.getString(1) + " " + rs.getString(2));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if( rs != null)
//                    rs.close();
//                if( st!= null)
//                    st.close();
//                if( conn != null)
//                    conn.close();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        }
    }
}
