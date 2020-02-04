package java_hotel_system;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ROOMS {

    MY_CONNECTION my_connection = new MY_CONNECTION();

    // kreiranje funkcije za prikaz svih tipova soba tabeli
    public void fillRooms_TYPE_JTable(JTable table) {

        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type` ";

        try {

            ps = my_connection.createConnection().prepareStatement(selectQuery);

            rs = ps.executeQuery();

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);

                tableModel.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // kreiranje funkcije za combobox

    public void fillRooms_TYPE_JCombobox(JComboBox combobox) {

        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type` ";

        try {

            ps = my_connection.createConnection().prepareStatement(selectQuery);

            rs = ps.executeQuery();

            while (rs.next()) {
                combobox.addItem(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void fillRoomsJTable(JTable table) {

        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `rooms` ";

        try {

            ps = my_connection.createConnection().prepareStatement(selectQuery);

            rs = ps.executeQuery();

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);

                tableModel.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // kreiranje funkcije za dodavanje sobe
    public boolean addRoom(int number, int type, String phone) {

        PreparedStatement st;
        ResultSet rs;
        String addQuery = "INSERT INTO `rooms`(`r_number`, `type`, `phone`, `reserved`) VALUES (?,?,?,?)";

        try {
            st = my_connection.createConnection().prepareStatement(addQuery);

            st.setInt(1, number);
            st.setInt(2, type);
            st.setString(3, phone);

            // kada dodamo novu sobu
            st.setString(4, "No");

            return (st.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    // unos podata o sobama
    public boolean editRoom(int number, int type, String phone, String isReserved) {
        PreparedStatement st;
        //ResultSet rs;
        String editQuery = "UPDATE `rooms` SET `type`=?,`phone`=?,`reserved`=? WHERE`r_number`=?";

        try {
            st = my_connection.createConnection().prepareStatement(editQuery);

            st.setInt(1, type);
            st.setString(2, phone);
            st.setString(3, isReserved);
            st.setInt(4, number);

          

            return (st.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    // pravimo funkciju za brisanje izabranje izabrane sobe
    public boolean removeRoom(int roomNumber) {
        PreparedStatement st;
        // ResultSet rs;
        String deleteQuery = "DELETE FROM `rooms` WHERE `r_number`=?";

        try {
            st = my_connection.createConnection().prepareStatement(deleteQuery);

            st.setInt(1, roomNumber);

            return (st.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    public boolean setRoomToReserved(int number, String isReserved) {
        PreparedStatement st;
        //ResultSet rs;
        String editQuery = "UPDATE `rooms` SET `reserved`=? WHERE`r_number`=?";

        try {
            st = my_connection.createConnection().prepareStatement(editQuery);

          
            st.setString(1, isReserved);
            st.setInt(2, number);

          

            return (st.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    // kreiranjefunkcije za proveru dostupnosti rezervacije
    
    public String isRoomReserved(int number) {
        PreparedStatement st;
        ResultSet rs;
        String editQuery = "SELECT `reserved` FROM `rooms` WHERE `r_number`=?";

        try {
            st = my_connection.createConnection().prepareStatement(editQuery);

            st.setInt(1, number);
            rs = st.executeQuery();
          
            if(rs.next()){
               return rs.getString(1);
            }
            else{
                return "";
            
            }

        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

    }
    
    

}
