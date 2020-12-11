package HealthSchedule.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhotoController implements Initializable{
	
	@FXML DatePicker datePicker1;
	@FXML DatePicker datePicker2;
	@FXML ImageView beforePhoto;
	@FXML ImageView afterPhoto;
	
	@Override
	   public void initialize(URL location, ResourceBundle resources) {
	      
	      // datePicker���� ��¥�� �������� ��
			datePicker1.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				 
	            //���� �� ���� ������ �Ʒ� ����
	            if(newValue!=null) {
	               System.out.println(datePicker1.getValue().toString());
	               Connection conn;    //DB Ŀ�ؼ�(����) ��ü
	                String USERNAME = "root";   //DB ���ӽ� ID
	                String PASSWORD = "1234";    //DB ���ӽ� �н�����
	                String URL = "jdbc:mysql://localhost:3306/iddb";
	                
	                try {
	                     System.out.println("������");
	                     Class.forName("com.mysql.jdbc.Driver"); 
	                     conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	                     System.out.println("����̹� �ε� ����!!");
	                     
	                     //���� �� ��¥�� �������� �˻��ؼ� ���������� ��θ� �ҷ���
	                     String sql = "select urlName from test where date=? ;";
	                   PreparedStatement pstmt = null;
	                    try {
	                        pstmt = conn.prepareStatement(sql);
	                        pstmt.setString(1, datePicker1.getValue().toString());
	                        ResultSet rs = pstmt.executeQuery();
	                        if(rs.next()) {
	                           System.out.println(rs.getString("urlName"));
	                           //�̹����信 ���� ����
	                           beforePhoto.setImage(new Image(getClass().getResource(rs.getString("urlName")).toString()));
	                        }
	              
	                    } catch (Exception e) {            
	                       System.out.println("�̹��� �ε� ����!");
	                        e.printStackTrace();
	                    } finally {
	                        try {
	                            if (pstmt != null && !pstmt.isClosed())
	                                pstmt.close();
	                        } catch (Exception e) {                
	                            e.printStackTrace();
	                        }
	                    }
	                 } catch (Exception e) {
	                     e.printStackTrace();
	                     System.out.println("����̹� �ε� ����!!");
	                 }
	            }
				
			}
	      });
			
			
		     // datePicker���� ��¥�� �������� ��
					datePicker2.valueProperty().addListener(new ChangeListener<LocalDate>() {

					@Override
					public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
							LocalDate newValue) {
						 
			            //���� �� ���� ������ �Ʒ� ����
			            if(newValue!=null) {
			               System.out.println(datePicker2.getValue().toString());
			               Connection conn;    //DB Ŀ�ؼ�(����) ��ü
			                String USERNAME = "root";   //DB ���ӽ� ID
			                String PASSWORD = "1234";    //DB ���ӽ� �н�����
			                String URL = "jdbc:mysql://localhost:3306/iddb";
			                
			                try {
			                     System.out.println("������");
			                     Class.forName("com.mysql.jdbc.Driver"); 
			                     conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			                     System.out.println("����̹� �ε� ����!!");
			                     
			                     //���� �� ��¥�� �������� �˻��ؼ� ���������� ��θ� �ҷ���
			                     String sql = "select urlName from test where date=? ;";
			                   PreparedStatement pstmt = null;
			                    try {
			                        pstmt = conn.prepareStatement(sql);
			                        pstmt.setString(1, datePicker2.getValue().toString());
			                        ResultSet rs = pstmt.executeQuery();
			                        if(rs.next()) {
			                           System.out.println(rs.getString("urlName"));
			                           //�̹����信 ���� ����
			                           afterPhoto.setImage(new Image(getClass().getResource(rs.getString("urlName")).toString()));
			                        }
			              
			                    } catch (Exception e) {            
			                       System.out.println("�̹��� �ε� ����!");
			                        e.printStackTrace();
			                    } finally {
			                        try {
			                            if (pstmt != null && !pstmt.isClosed())
			                                pstmt.close();
			                        } catch (Exception e) {                
			                            e.printStackTrace();
			                        }
			                    }
			                 } catch (Exception e) {
			                     e.printStackTrace();
			                     System.out.println("����̹� �ε� ����!!");
			                 }
			            }
						
					}
			      });			
			
			
			
			
	   }
	
	
	

}
