package control;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.fxml.Initializable;
=======
import javafx.scene.control.Alert;
>>>>>>> master
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
<<<<<<< HEAD
=======
import model.Product;
import model.Packag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

>>>>>>> master


public class PackagesController implements Initializable{
	
	// ==Sunghyun Lee ==

	@FXML
    private Label lblPackageId;

    @FXML
    private JFXTextField tfPkgName;

    @FXML
    private JFXDatePicker dpPkgStartDate;

    @FXML
    private JFXDatePicker dpPkgEndDate;

    @FXML
    private JFXTextArea taPkgDesc;

    @FXML
    private JFXTextField tfPkgBasePrice;

    @FXML
    private JFXTextField tfPkgAgencyCommission;

    @FXML
<<<<<<< HEAD
    private TableView<model.Package> tvPackages;
    
    @FXML
    private TableColumn<model.Package, Integer> tcPackageId;

    @FXML
    private TableColumn<model.Package, String> tcPackageName;
=======
    private TableView<Packag> tvPackages;
    
    @FXML
    private TableColumn<Packag, Integer> tcPackageId;

    @FXML
    private TableColumn<Packag, String> tcPackageName;
>>>>>>> master

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXListView<model.Product> lvProductsInPackage;

    @FXML
    private JFXTreeTableView<?> tvProductsSuppliers;

    @FXML
    private Label lblProductsSuppliers;

    
    @FXML
    private JFXButton btnInsertProductIntoPkg;

    @FXML
    private JFXButton btnRemoveProductFromPkg;
    
<<<<<<< HEAD
  
=======
    private StringBuffer buffer = new StringBuffer();
    
    
    ObservableList<Packag> packages;
    @FXML
    void initialize()
    {
    	// manually creating list of products
    	ObservableList<Product> productsInPackage = FXCollections.observableArrayList();
    	productsInPackage.add(new Product(2, "Air Bus"));
    	productsInPackage.add(new Product(3, "Wow"));
    	lvProductsInPackage.setItems(productsInPackage);
    	
    	// manually creating list of packages
    	packages =FXCollections.observableArrayList();
    	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
        
        
    	try
		{
			packages.add(new Packag(1, 231.12, 231.21, "It is a good package!", ft.parse("2018-11-11"), "European Package", ft.parse("2018-11-15")));
			packages.add(new Packag(2, 102.31, 167.38, "Wow! Nice!", ft.parse("2018-10-11"), "american Package", ft.parse("2018-11-130")));
			tcPackageId.setCellValueFactory(new PropertyValueFactory<>("PackageId"));
			tcPackageName.setCellValueFactory(new PropertyValueFactory<>("PkgName"));
			tvPackages.setItems(packages);
		
		
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// reading json
    	try 
    	{
            URL url = new URL("http://10.163.101.59:8080/TravelExperts2/rs/db/getallpackages");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
                System.out.println("succes");
            	buffer.append(line);
            }

            System.out.println(buffer);
        } catch (Exception e) {
            System.out.println(e);
        }
    	
    	try 
    	{
            JSONArray jsonArray = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonPkg = (JSONObject) jsonArray.get(i);
                //create customer object from json object
                Packag pkg = new Packag(jsonPkg.getInt("packageId"), 231.21 ,123.213,"It is a good package!", ft.parse("2018-11-11"), jsonPkg.getString("pkgName"), ft.parse("2018-11-15"));
                
                packages.add(pkg);
            }
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    }
>>>>>>> master
    

    @FXML
    void deletePackage(ActionEvent event) {

    }

    @FXML
    void editPackage(ActionEvent event) {
    	enableInputs(true);

    }

    @FXML
    void savePackage(ActionEvent event) {
    	enableInputs(false);
    }
    
    @FXML
    void displayPackageInfo(MouseEvent event) {
<<<<<<< HEAD
    	model.Package selectedPackage= tvPackages.getSelectionModel().getSelectedItem();
    	lblPackageId.setText(""+selectedPackage.getPackageId());
=======
    	Packag selectedPackage= tvPackages.getSelectionModel().getSelectedItem();
    	//lblPackageId.setText(""+selectedPackage.getPackageId());
>>>>>>> master
    	tfPkgName.setText(""+selectedPackage.getPkgName());
    	dpPkgStartDate.setValue(selectedPackage.getPkgStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    	dpPkgEndDate.setValue(selectedPackage.getPkgEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    	taPkgDesc.setText(selectedPackage.getPkgDesc());
    	tfPkgBasePrice.setText(selectedPackage.getPkgBasePrice()+"");
    }
    
    void enableInputs(boolean myBool)
    {
    	btnSave.setDisable(!myBool);
        
    	tfPkgName.setEditable(myBool);
    	tfPkgBasePrice.setEditable(myBool);
    	tfPkgAgencyCommission.setEditable(myBool);
    	taPkgDesc.setEditable(myBool);
    	dpPkgStartDate.setDisable(!myBool);
    	dpPkgEndDate.setDisable(!myBool);
    	
    	lblProductsSuppliers.setVisible(myBool);
    	tvProductsSuppliers.setVisible(myBool);
    	
    	btnInsertProductIntoPkg.setVisible(myBool);
    	btnRemoveProductFromPkg.setVisible(myBool);
    }


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub
		// hide elements
    	enableInputs(false);
    	
    	// manually creating list of products
    	ObservableList<model.Product> productsInPackage = FXCollections.observableArrayList();
    	productsInPackage.add(new model.Product(2, "Air Bus"));
    	productsInPackage.add(new model.Product(3, "Wow"));
    	lvProductsInPackage.setItems(productsInPackage);
    	
    	// manually creating list of packages
    	ObservableList<model.Package> packages =FXCollections.observableArrayList();
    	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
        
        
    	try
		{
			packages.add(new model.Package(1, 231.12, 231.21, "It is a good package!", ft.parse("2018-11-11"), "European Package", ft.parse("2018-11-15")));
			packages.add(new model.Package(2, 102.31, 167.38, "Wow! Nice!", ft.parse("2018-10-11"), "american Package", ft.parse("2018-11-130")));
			tcPackageId.setCellValueFactory(new PropertyValueFactory<>("PackageId"));
			tcPackageName.setCellValueFactory(new PropertyValueFactory<>("PkgName"));
			tvPackages.setItems(packages);
		
		
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    
    // ==

}
