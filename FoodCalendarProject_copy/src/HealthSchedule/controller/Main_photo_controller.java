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
	
	@FXML private Button goBack;	//뒤로가기버튼
	@FXML private TextField beforeDate; //이전 날짜
	@FXML private TextField afterDate;	//이후 날짜
	@FXML private Image beforePhoto; 	//이전 사진
	@FXML private Image afterPhoto;		//이후 사진
	String urlToString="";
	String fileName ="";
	
	private Connection conn; //DB 커넥션(연결) 객체
	private static final String USERNAME = "root";   //DB 접속시 ID
	private static final String PASSWORD = "1234";	 //DB 접속시 패스워드
	private static String URL = "jdbc:mysql://localhost:3306/photodb";	//사용자주소/db주소	
	
	public Main_photo_controller() {
		//connection객체를 생성해서 DB에 연결함
		try {
			 //동적 객체를 만들어줌 
	         Class.forName("com.mysql.jdbc.Driver"); 
	         //conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	         System.out.println("드라이버 로딩 성공!!");
		} catch (Exception e) {
			System.out.println("포토 드라이버 로드 실패!!");
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
	}
	
	 //뒤로가기
	 public void btngoBack(ActionEvent event) {   
		 try {
		      Parent foodPicker = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		      Scene scene = new Scene(foodPicker);
		      Stage primaryStage= (Stage)goBack.getScene().getWindow();
		      primaryStage.setScene(scene);
		   } catch (Exception e) {}
	   }
	   
	   //before 사진 가져오기
	   public void btnbeforePhoto(ActionEvent e) {
		   
		   SaveImg saveImg = new SaveImg();

           String file = urlToString;
           String path = "D:\\HealthSchedule\\FoodCalendarProject_copy\\src\\images";
 
    	   final String USERNAME = "root";   //DB 접속시 ID
    	   final String PASSWORD = "1234";	 //DB 접속시 패스워드
    	   final String URL = "jdbc:mysql://localhost:3306/photodb";
    	    
    	    try {
                System.out.println("생성자");
                Class.forName("com.mysql.jdbc.Driver"); 
                //conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("드라이버 로딩 성공!!");
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("드라이버 로드 실패!!");
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
                	System.out.println("사진 추가 성공!");
                	try {
               			int result2 = saveImg.saveImgFromUrl(file, path);
               			if (result2 == 1) {
               				System.out.println("저장된경로 : " + saveImg.getPath());
               				System.out.println("저장된파일이름 : " + saveImg.getSavedFileName());
               			}

               		} catch (IOException e6) {
               			e6.printStackTrace();
               		}
                }           
            } catch (SQLException e3) {            
            	System.out.println("사진 추가 실패!");
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
	   
	   //after 사진 가져오기
	   public void btnafterPhoto(ActionEvent e) {
		   
	   }
	   
	   
    

}
