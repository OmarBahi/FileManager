
package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import java.lang.Runtime;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.Files.delete;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.Map;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;




/**
 *
 * @author Omar
 */



public class MainController implements Initializable  {
    @FXML
    Label lbName;
    
    @FXML
    Label lbDir;
    
    @FXML
    Label lbResult;
    
    @FXML
    TextField TextInput;
    
    @FXML
    Label lbResultHide;
    
    @FXML
    Label lbPassFailed;
    
    @FXML
    TextField PasswordL;
    
    @FXML
    public TextField Passwordft;
    
    @FXML
    TextArea kalam;
    
    private Stage stage;
    @Override
    public void initialize(URL location,ResourceBundle resources){
        
        
    }
    public void showgreeting (ActionEvent event){
        System.out.println("HELLO");
    }
    public void init(Stage stage) {
       this.stage= stage;
    }
    String FileDirectory;
    String FileDirectoryCR;
    String FileDirectoryNew;
    String FileDirectoryUnhide;
    String FileName;
    String FilePath;
    String Password;
    
    HashMap<String, String> hm = new HashMap<String, String> (); 
    
    public void login (ActionEvent event) throws Exception {
        Scanner check = new Scanner(FileManager.lol);
        while (check.hasNext()){
            Password=check.next();
        }
        if (PasswordL.getText().equals(Password)){
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Stage primaryStage = new Stage();
        Parent root = loader.load();
        MainController controller = (MainController)loader.getController();
        controller.init(primaryStage);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("File Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
        else {
            lbPassFailed.setText("Password is wrong, please try again");
        }
    } 
    public void loginft (ActionEvent event2) throws Exception {
                if (FileManager.lol.exists() == false){
             PrintWriter lolW = new PrintWriter(FileManager.lol);
             lolW.print(Passwordft.getText());
             lolW.close();
         }
        ((Node)(event2.getSource())).getScene().getWindow().hide();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
          Stage primaryStage = new Stage();
        Parent root = loader.load();
        MainController controller = (MainController)loader.getController();
        controller.init(primaryStage);
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("File Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void about () throws IOException {
        kalam.setText("Welcome to File Manager about page \n"+
                "This current version support these file: \n"+
                "Audio Files : \n" +
                ".mp3 .wav .wma \n" +
                "Video Files : \n" +
                ".3gp .avi .flv .mkv .mov .mp4 .mpg .wmv \n" +
                "Image Files : \n" +
                ".jpeg .jpg .png .psd \n" +
                "Compressed Files : \n" +
                ".rar .zip \n" +
                "Power Point Files :  \n" +
                ".ppt .pptx \n" +
                "Excel Files : \n" +
                ".xls.xlsx \n" +
                "Document Files : \n" +
                ".doc .docx .pdf .txt \n  ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/About.fxml"));
          Stage primaryStage = new Stage();
        Parent root = loader.load();
        MainController controller = (MainController)loader.getController();
        controller.init(primaryStage);
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("About");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void OpenFile(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        
        if ( file != null){
            FileDirectory = file.getAbsolutePath();
            FileDirectoryCR = file.getParent();
            FileName = file.getName();
            FilePath = file.getPath();
            lbName.setText(FileName);
            lbDir.setText(FilePath);  
        }
    }
    public void HideFile () throws java.io.IOException{
        hm.put(FileName, FileDirectory);
        System.out.println ("file name "+ FileName);
        System.out.println ("file d " +FileDirectory);
        Runtime.getRuntime().exec("cmd.exe /C attrib +s +h +r \""+FileDirectory+"\"");
        lbResult.setText("Your file is successfully hidden");
    }
    public void HideFileSB ()throws java.io.IOException{
        HideFile ();
    }
    public void UnhideFile () throws java.io.IOException {
        FileDirectoryUnhide = hm.get(TextInput.getText());
        if(FileDirectoryUnhide == null) {
            lbResultHide.setText("This file wasn't hidden\n if you are sure please check your typo");
            return;   
        }
        System.out.println ("text input" +TextInput.getText());
        System.out.println ("file direct "+FileDirectoryUnhide);
       Runtime.getRuntime().exec("cmd.exe /C attrib -s -h -r \""+FileDirectoryUnhide+"\"");
       lbResultHide.setText("Your file is successfully unhidden");
    }
    public void UnhideFileSB () throws java.io.IOException {
        UnhideFile ();
    }
    public void RenameFile (String FileDirectory) throws java.io.IOException{
        Path source = Paths.get(FileDirectory);
        if (FilePath.endsWith(".mp3")){
            Files.move(source, source.resolveSibling("mp3.mp3"));
        }
        else if (FilePath.endsWith(".wav")){
            Files.move(source, source.resolveSibling("wav.wav"));
        }
        else if (FilePath.endsWith(".wma")){
            Files.move(source, source.resolveSibling("wma.wma"));
        }
        else if (FilePath.endsWith(".rar")){
            Files.move(source, source.resolveSibling("rar.rar"));
        }
        else if (FilePath.endsWith(".zip")){
            Files.move(source, source.resolveSibling("zip.zip"));
        }
        else if (FilePath.endsWith(".jpeg")){
            Files.move(source, source.resolveSibling("jpeg.jpeg"));
        }
        else if (FilePath.endsWith(".jpg")){
            Files.move(source, source.resolveSibling("jpg.jpg"));
        }
        else if (FilePath.endsWith(".png")){
            Files.move(source, source.resolveSibling("png.png"));
        }
        else if (FilePath.endsWith(".psd")){
            Files.move(source, source.resolveSibling("psd.psd"));
        }
        else if (FilePath.endsWith(".ppt")){
            Files.move(source, source.resolveSibling("ppt.ppt"));
        }
        else if (FilePath.endsWith(".pptx")){
            Files.move(source, source.resolveSibling("pptx.pptx"));
        }
        else if (FilePath.endsWith(".xls")){
            Files.move(source, source.resolveSibling("xls.xls"));
        }
        else if (FilePath.endsWith(".xlsx")){
            Files.move(source, source.resolveSibling("xlsx.xlsx"));
        }
        else if (FilePath.endsWith(".3gp")){
            Files.move(source, source.resolveSibling("3gp.3gp"));
        }
        else if (FilePath.endsWith(".avi")){
            Files.move(source, source.resolveSibling("avi.avi"));
        }
        else if (FilePath.endsWith(".flv")){
            Files.move(source, source.resolveSibling("flv.flv"));
        }
        else if (FilePath.endsWith(".mkv")){
            Files.move(source, source.resolveSibling("mkv.mkv"));
        }
        else if (FilePath.endsWith(".mov")){
            Files.move(source, source.resolveSibling("mov.mov"));
        }
        else if (FilePath.endsWith(".mp4")){
            Files.move(source, source.resolveSibling("mp4.mp4"));
        }
        else if (FilePath.endsWith(".mpg")){
            Files.move(source, source.resolveSibling("mpg.mpg"));
        }
        else if (FilePath.endsWith(".wmv")){
            Files.move(source, source.resolveSibling("wmv.wmv"));
        }
        else if (FilePath.endsWith(".doc")){
            Files.move(source, source.resolveSibling("doc.doc"));
        }
        else if (FilePath.endsWith(".docx")){
            Files.move(source, source.resolveSibling("docx.docx"));
        }
        else if (FilePath.endsWith(".pdf")){
            Files.move(source, source.resolveSibling("pdf.pdf"));
        }
        else if (FilePath.endsWith(".txt")){
            Files.move(source, source.resolveSibling("txt.txt"));
        }
        else{
            lbResult.setText("Your file type isn't supported by this version");          
        }
          
    }
    public void CopyAndReplaceFile (String FileDirectory,String FileDirectoryCR) throws java.io.IOException {
        RenameFile(FileDirectory);
        Path source = Paths.get(FileDirectory);
        if (FilePath.endsWith(".mp3")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\mp3.mp3");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\mp3.mp3");
        }
        else if (FilePath.endsWith(".wav")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\wav.wav");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\wav.wav");
        }
        else if (FilePath.endsWith(".wma")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\wma.wma");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\wma.wma");
        }
        else if (FilePath.endsWith(".rar")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\rar.rar");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\rar.rar");
        }
        else if (FilePath.endsWith(".zip")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\zip.zip");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\zip.zip");
        }
        else if (FilePath.endsWith(".jpeg")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\jpeg.jpeg");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\jpeg.jpeg");
        }
        else if (FilePath.endsWith(".jpg")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\jpg.jpg");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\jpg.jpg");
        }
        else if (FilePath.endsWith(".png")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\png.png");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\png.png");
        }
        else if (FilePath.endsWith(".psd")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\psd.psd");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\psd.psd");
        }
        else if (FilePath.endsWith(".ppt")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\ppt.ppt");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\ppt.ppt");
        }
        else if (FilePath.endsWith(".pptx")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\pptx.pptx");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\pptx.pptx");
        }
        else if (FilePath.endsWith(".xls")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\xls.xls");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\xls.xls");
        }
        else if (FilePath.endsWith(".3gp")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\3gp.3gp");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\3gp.3gp");
        }
        else if (FilePath.endsWith(".avi")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\avi.avi");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\avi.avi");
        }
        else if (FilePath.endsWith(".flv")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\flv.flv");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\flv.flv");
        }
        else if (FilePath.endsWith(".mkv")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\mkv.mkv");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\mkv.mkv");
        }
        else if (FilePath.endsWith(".mov")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\mov.mov");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\mov.mov");
        }
        else if (FilePath.endsWith(".mp4")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\mp4.mp4");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\mp4.mp4");
        }
        else if (FilePath.endsWith(".mpg")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\mpg.mpg");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\mpg.mpg");
        }
        else if (FilePath.endsWith(".mpeg")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\mpeg.mpeg");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\mpeg.mpeg");
        }
        else if (FilePath.endsWith(".wmv")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\wmv.wmv");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\wmv.wmv");
        }
        else if (FilePath.endsWith(".doc")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\doc.doc");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\doc.doc");
        }
        else if (FilePath.endsWith(".docx")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\docx.docx");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\docx.docx");
        }
        else if (FilePath.endsWith(".pdf")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\pdf.pdf");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\pdf.pdf");
        }
        else if (FilePath.endsWith(".txt")){
            source = Paths.get("D:\\Java\\FileManager\\Samples\\txt.txt");
            FileDirectoryNew = FileDirectoryCR;
            FileDirectoryNew = FileDirectoryNew.concat("\\txt.txt");
        }
        
        else{
            lbResult.setText("Your file type isn't supported by this version");
                    
        }
    Path newdir = Paths.get(FileDirectoryCR);
    Files.copy(source, newdir.resolve(source.getFileName()),REPLACE_EXISTING);
    Path Todelete = Paths.get(FileDirectoryNew);
    delete(Todelete);
    lbResult.setText("Your file has been successfully deleted");
    }
    public void CopyAndReplaceFileSB () throws java.io.IOException{
        CopyAndReplaceFile(FileDirectory,FileDirectoryCR);
    }
    public void doExit(){
        Platform.exit();
    }

    
    
}
