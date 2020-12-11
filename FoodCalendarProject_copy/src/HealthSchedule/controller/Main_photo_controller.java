package HealthSchedule.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.halowd.saveImg.SaveImg;

public class Main_photo_controller implements Initializable {
	
	@FXML private Button goBack;	//�ڷΰ����ư
	@FXML private TextField beforeDate; //���� ��¥
	@FXML private TextField afterDate;	//���� ��¥
	@FXML private Image beforePhoto; 	//���� ����
	@FXML private Image afterPhoto;		//���� ����
	String urlToString="";
	String fileName ="";
	
	private Connection conn; //DB Ŀ�ؼ�(����) ��ü
	private static final String USERNAME = "root";   //DB ���ӽ� ID
	private static final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
	private static String URL = "jdbc:mysql://localhost:3306/photodb";	//������ּ�/db�ּ�	
	
	public Main_photo_controller() {
		//connection��ü�� �����ؼ� DB�� ������
		try {
			 //���� ��ü�� ������� 
	         Class.forName("com.mysql.jdbc.Driver"); 
	         //conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	         System.out.println("����̹� �ε� ����!!");
		} catch (Exception e) {
			System.out.println("���� ����̹� �ε� ����!!");
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
	}
	
	 //�ڷΰ���
	 public void btngoBack(ActionEvent event) {   
		 try {
		      Parent foodPicker = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		      Scene scene = new Scene(foodPicker);
		      Stage primaryStage= (Stage)goBack.getScene().getWindow();
		      primaryStage.setScene(scene);
		   } catch (Exception e) {}
	   }
	   
	   //before ���� ��������
	   public void btnbeforePhoto(ActionEvent e) {
		   
		   SaveImg saveImg = new SaveImg();

           String file = urlToString;
           String path = "D:\\HealthSchedule\\FoodCalendarProject_copy\\src\\images";
 
    	   final String USERNAME = "root";   //DB ���ӽ� ID
    	   final String PASSWORD = "1234";	 //DB ���ӽ� �н�����
    	   final String URL = "jdbc:mysql://localhost:3306/photodb";
    	    
    	    try {
                System.out.println("������");
                Class.forName("com.mysql.jdbc.Driver"); 
                //conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("����̹� �ε� ����!!");
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("����̹� �ε� ����!!");
            }
    	   
    	    String sql = "insert into test values(?,?);";
        	PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(sql);
                String url = "D:\\HealthSchedule\\FoodCalendarProject_copy\\src\\images\\" + fileName;
                pstmt.setString(1, null);
                pstmt.setString(2, url);

                int result = pstmt.executeUpdate();
                if(result == 1) {
                	System.out.println("���� �߰� ����!");
                	try {
               			int result2 = saveImg.saveImgFromUrl(file, path);
               			if (result2 == 1) {
               				System.out.println("����Ȱ�� : " + saveImg.getPath());
               				System.out.println("����������̸� : " + saveImg.getSavedFileName());
               			}

               		} catch (IOException e6) {
               			e6.printStackTrace();
               		}
                }           
            } catch (SQLException e3) {            
            	System.out.println("���� �߰� ����!");
                e3.printStackTrace();
            } finally {
                try {
                    if (pstmt != null && !pstmt.isClosed())
                        pstmt.close();
                } catch (SQLException e4) {                
                    e4.printStackTrace();
                }
            }		   
	   
	   }
	   
	   //after ���� ��������
	   public void btnafterPhoto(ActionEvent e) {
		   
	   }
	   
	   
    

}
