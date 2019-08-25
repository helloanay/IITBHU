package dao;

import conn.MyConnectionProvider;
import dto.Hospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HospitalDao {

 public int addHospital(Hospital hospital) {
    int i=0;
  try {
   MyConnectionProvider myConn = new MyConnectionProvider();
   Connection con = myConn.getCon();
   PreparedStatement s= con.prepareStatement("insert into hospital(hospitalId,password,name,city) values(?,?,?,?)");
   s.setString(1, hospital.getHospitalId());
   s.setString(2, hospital.getPassword());
   s.setString(3, hospital.getName());
   s.setString(4, hospital.getCity());
   i=s.executeUpdate();
   con.close();
  } catch (Exception e) {
   System.out.println(e);
  }
     return i;
 }

 public Hospital loginhospital(Hospital hospital) {
  try {
   MyConnectionProvider myConn = new MyConnectionProvider();
   Connection con = myConn.getCon();
   PreparedStatement s = con.prepareStatement("select * from hospital where hospitalId = ? and password = ?");
   s.setString(1, hospital.getHospitalId());
   s.setString(2, hospital.getPassword());
   ResultSet rs = s.executeQuery();
   if (rs.next()) {
    hospital.setHospitalId(rs.getString(1));
    hospital.setPassword(rs.getString(2));
    hospital.setName(rs.getString(3));
    hospital.setCity(rs.getString(4));
  } else {
    hospital = null;
   }
   con.close();
  } catch (Exception e) {
   System.out.println(e);
  }
  return hospital;
 }

}