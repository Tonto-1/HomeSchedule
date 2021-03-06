package HealthSchedule.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import net.halowd.saveImg.SaveImg;

public class Main_everydayRecord_controller implements Initializable{

   @FXML private AnchorPane pane;
   @FXML private Label backLabel;
   @FXML private Label breakfast;
   
   @FXML private JFXButton uploadPhoto;//사진업로드
   @FXML private ImageView todayPhoto;//사진업로드
   
   @FXML private JFXButton fullbody;//전신 버튼
   @FXML private JFXButton upperbody;//전신 버튼
   @FXML private JFXButton abs;//복부 버튼
   @FXML private JFXButton lowerbody;//하체 버튼
   @FXML private JFXButton yoga; //요가버튼
   
   
   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      //stage 조정
        stageDragableMoveWindow();
        
        
      
   }

     //////////////////////////
      private double xOffset = 0;
      private double yOffset = 0;
      private Stage stage = null;

      //화면 움직일때 투명으로 변하게 하기
      private void stageDragableMoveWindow() {
         pane.setOnMousePressed((event) -> {
         xOffset = event.getSceneX();
         yOffset = event.getSceneY();
         });
         pane.setOnMouseDragged((event) -> {
         // Launcher.stage.setX(event.getScreenX() - xOffset);
         // Launcher.stage.setY(event.getScreenY() - yOffset);
         // Launcher.stage.setOpacity(0.8f); // 창 투명화
         stage = (Stage) pane.getScene().getWindow();
         stage.setX(event.getScreenX() - xOffset);
         stage.setY(event.getScreenY() - yOffset);
         stage.setOpacity(0.8f); // 창 투명화
         });
         pane.setOnDragDone((event) -> {
         // Launcher.stage.setOpacity(1.0f);
         stage = (Stage) pane.getScene().getWindow();
         stage.setOpacity(1.0f);
         });
         pane.setOnMouseReleased((event) -> {
         // Launcher.stage.setOpacity(1.0f);
         stage = (Stage) pane.getScene().getWindow();
         stage.setOpacity(1.0f);
         });
      }

      //화면 숨기기
      @FXML
      private void actionMinWindow(MouseEvent event) {
      // Launcher.stage.setIconified(true);
      stage = (Stage) pane.getScene().getWindow();
      stage.setIconified(true);
      }
      //전체 화면으로
//      @FXML
//      private void actionMaxWindow(MouseEvent event) {
//      // Launcher.stage.setFullScreen(true);
//      // if (Launcher.stage.isMaximized()) {
//      // Launcher.stage.setMaximized(false);
//      // } else {
//      // Launcher.stage.setMaximized(true);
//      // }
//      stage = (Stage) pane.getScene().getWindow();
//      if (stage.isMaximized()) {
//      stage.setMaximized(false);
//      } else {
//      stage.setMaximized(true);
//      }
//      }
      
      //화면 끄기
      @FXML
      private void actionCloseWindow(MouseEvent event) {
      System.exit(0);
      }

      @FXML
      private void actionBackWindow(MouseEvent event) {
         try {
            //뒤로 가기 버튼을 누르면 뒤로감
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)backLabel.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }

      @FXML
      private void pageMove(MouseEvent event) {
         try {
            //음식 이름을 클릭하면 넘어간다.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_breakfast.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      
      @FXML
      private void currentWeight(MouseEvent event) {
         try {
            //현재 몸무게를 클릭하면 넘어간다.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      
      @FXML
      private void goalWeight(MouseEvent event) {
         try {
            //목표 몸무게를 클릭하면 넘어간다.
            Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
            Scene scene = new Scene(checkOk);
            Stage primaryStage= (Stage)breakfast.getScene().getWindow();
            primaryStage.setScene(scene);
         } catch (Exception e2) {}
      }
      
      
      //사진업로드 버튼
      public void uploadPhoto(ActionEvent event) {
    	  try {
		  //String str = "D:\\HealthSchedule\\FoodCalendarProject_copy\\src\\images\\cake.jpg";
		  //todayPhoto.setImage(new Image(getClass().getResource(str).toString()));
	       FileChooser fc = new FileChooser();
           fc.setTitle("사진올리기");
           fc.setInitialDirectory(new File("C:/"));
           ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
           fc.getExtensionFilters().add(imgType);
           File selectedFile =  fc.showOpenDialog(null);
          // File uploadPhoto = selectedFile;
	           
	           if(selectedFile!=null) {
	              //uploadPhoto.setText("File selected : " + selectedFile.getName());
	              SaveImg saveImg = new SaveImg();
	              String file = selectedFile.toURI().toString();
	              String path = "src/images";
	              
	            int result = saveImg.saveImgFromUrl(file, path);
	            
	            if (result == 1) {
	               String savePath = saveImg.getPath();
	               System.out.println("저장된경로 : " + savePath);
	               String saveFileName = saveImg.getSavedFileName();
	               System.out.println("저장된파일이름 : " + saveImg.getSavedFileName());
	               System.out.println(todayPhoto);
	               System.out.println(getClass());
	               System.out.println(getClass().getResource("/FoodCalendarProject_copy/src/images/"+saveFileName));
	               Image image = new Image(getClass().getResource("/FoodCalendarProject_copy/src/images/"+saveFileName).toString());
	               System.out.println(image);
	               todayPhoto.setImage(image);
	            	}
	           } 
	           
	      } catch (Exception e1) {
	         e1.printStackTrace();
	      }

  }
      
      
      //전신 버튼
      public void fullbodyEx(ActionEvent event) {
         try {
            Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=전신운동"));
            }
         catch (IOException e) {
            e.printStackTrace(); }
            catch (URISyntaxException e) { 
               e.printStackTrace();
               }
      }
      
      //상체 버튼
      public void upperbodyEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=상체운동"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }
      }
      
      //복부 버튼
      public void absEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=복부운동"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }
      }
      
      //하체 버튼
      public void lowerbodyEx(ActionEvent event) {
    	   try {
               Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=하체운동"));
               }
            catch (IOException e) {
               e.printStackTrace(); }
               catch (URISyntaxException e) { 
                  e.printStackTrace();
                  }   
      }
      
      //요가 버튼 
      public void yogaEx(ActionEvent event) {
   	   try {
              Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=스트레칭+및+요가"));
              }
           catch (IOException e) {
              e.printStackTrace(); }
              catch (URISyntaxException e) { 
                 e.printStackTrace();
                 }   
     }
      
      //사진업로드 버튼
      public void uploadBtn(ActionEvent event) {
          try {
            
                FileChooser fc = new FileChooser();
                  fc.setTitle("이미지 선택");
                  fc.setInitialDirectory(new File("C:/"));
                  ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
                  fc.getExtensionFilters().add(imgType);
                  File selectedFile =  fc.showOpenDialog(null);
                  
                  if(selectedFile!=null) {
                     //upload.setText(selectedFile.toURI().toString());
                     SaveImg saveImg = new SaveImg();
                   
                     String file = selectedFile.toURI().toString();

                     String path = "src/images";
                     
                   int result = saveImg.saveImgFromUrl(file, path);
                   if (result == 1) {
                      String savePath = saveImg.getPath();
                      System.out.println("저장된경로 : " + savePath);
                      String saveFileName = saveImg.getSavedFileName();
                      System.out.println("저장된파일이름 : " + saveFileName);
                      System.out.println((savePath+"/"+saveFileName));
                      
                      todayPhoto.setImage(new Image(getClass().getResource("../images/"+saveFileName).toString()));
           
                   }
                  }
                  
                  
             } catch (Exception e) {
                e.printStackTrace();
             }

      }      
      
      
   
}