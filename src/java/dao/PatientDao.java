package dao;

import conn.MyConnectionProvider;
import dto.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDao {

 public int addPatient(Patient patient) {
  int i = 0;
  try {
   MyConnectionProvider myConn = new MyConnectionProvider();
   Connection con = myConn.getCon();
   PreparedStatement s = con.prepareStatement("insert into patientinfo(aadharid,password,fname,lname,gender,mobileno) values(?,?,?,?,?,?)");
   s.setString(1, patient.getAadharId());
   s.setString(2, patient.getPassword());
   s.setString(3, patient.getFName());
   s.setString(3, patient.getLName());
   s.setString(4, patient.getGender());
   s.setString(5, patient.getMobile());
   i = s.executeUpdate();
   s.close();
   con.close();
  } catch (Exception e) {
   System.out.println(e);
  }
  return i;
 }

 public Patient loginpatient(Patient patient) {
  try {
   MyConnectionProvider myConn = new MyConnectionProvider();
   Connection con = myConn.getCon();
   PreparedStatement s = con.prepareStatement("select * from patientinfo where aadharid = ? and password = ?");
   s.setString(1, patient.getAadharId());
   s.setString(2, patient.getPassword());
   ResultSet rs = s.executeQuery();
   if (rs.next()) {
    patient.setAadharId(rs.getString(1));
    patient.setPassword(rs.getString(2));
    patient.setFName(rs.getString(3));
    patient.setLName(rs.getString(4));
    patient.setGender(rs.getString(5));
    patient.setMobile(rs.getString(6));
   } else {
    patient = null;
   }
   s.close();
   con.close();
  } catch (Exception e) {
   System.out.println(e);
  } 
     return patient;
 }

}
