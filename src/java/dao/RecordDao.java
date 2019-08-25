package dao;


import conn.MyConnectionProvider;
import dto.Patient;
import dto.Record;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RecordDao {
    
  public int addRecord(Record record) {
  int i = 0;
  try {
   MyConnectionProvider myConn = new MyConnectionProvider();
   Connection con = myConn.getCon();
   String query = "insert into record(record) values(?)";
   PreparedStatement s = con.prepareStatement(query);
   i = s.executeUpdate();
   s.close();
   con.close();
  } catch (Exception e) {
   System.out.println(e);
  }
  return i;
 }

  public Record showRecord(Record record) {
  try {
   MyConnectionProvider myConn = new MyConnectionProvider();
   Connection con = myConn.getCon();
   PreparedStatement s = con.prepareStatement("select * from record where aadharId = ?");
   s.setString(1, record.getRecord());
   ResultSet rs = s.executeQuery();
   if (rs.next()) {
    record.setRecord(rs.getString(1));
    } else {
    record = null;
   }
   s.close();
   con.close();
  } catch (Exception e) {
   System.out.println(e);
  }
  return record;
 }  
}
