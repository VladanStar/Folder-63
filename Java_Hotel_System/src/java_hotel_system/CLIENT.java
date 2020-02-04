package java_hotel_system;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CLIENT { // class client - java klasa za klijenta

    MY_CONNECTION my_connection = new MY_CONNECTION();

    // pravimo funkciju za kreiranje klijenta
    public boolean addClient(String fName, String lName, String phone, String email) {

        PreparedStatement st;
        ResultSet rs;
        String addQuery = "INSERT INTO `clients` (`first_name`, `last_name`, `phone`, `email`) VALUES(?,?,?,?)";

        try {
            st = my_connection.createConnection().prepareStatement(addQuery);

            st.setString(1, fName);
            st.setString(2, lName);
            st.setString(3, phone);
            st.setString(4, email);

            return (st.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    // pravimo funkciju za dodavanje izabranog klijenta
    public boolean editClient(int id, String fName, String lName, String phone, String email) {
        PreparedStatement st;
        //ResultSet rs;
        String editQuery = "UPDATE `clients` SET `first_name`=?,`last_name`=?,`phone`=?,`email`=? WHERE `id`=?";

        try {
            st = my_connection.createConnection().prepareStatement(editQuery);

            st.setString(1, fName);
            st.setString(2, lName);
            st.setString(3, phone);
            st.setString(4, email);
            st.setInt(5, id);

            return (st.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    // pravimo funkciju za brisanje izabranje klijenta
    public boolean removeClient(int id) {
        PreparedStatement st;
       // ResultSet rs;
        String deleteQuery = "DELETE FROM `clients` WHERE `id`=?";

        try {
            st = my_connection.createConnection().prepareStatement(deleteQuery);

            st.setInt(1, id);

            return (st.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    // pravimo funkciju za pregled svih klijenata u bazi
    public void fillClientJTable(JTable table) {

        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `clients` ";

        try {

            ps = my_connection.createConnection().prepareStatement(selectQuery);

            rs = ps.executeQuery();

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);

                tableModel.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
