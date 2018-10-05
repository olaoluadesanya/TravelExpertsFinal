package control;

import java.net.URL;
import java.text.DateFormat;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import model.Product;
import model.ProductsSupplier;
import model.Packag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



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
    private TableView<Packag> tvPackages;

    @FXML
    private TableColumn<Packag, Integer> tcPkgId;

    @FXML
    private TableColumn<Packag, String> tcPkgName;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXListView<model.Product> lvProductsInPackage;

    @FXML
    private TableView<ProductsSupplier> tvProductsSuppliers;

    @FXML
    private Label lblProductsSuppliers;

    
    @FXML
    private JFXButton btnInsertProductIntoPkg;

    @FXML
    private JFXButton btnRemoveProductFromPkg;
    

    private StringBuffer buffer = new StringBuffer();
    
    
    private ObservableList<Packag> packages;
  
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
    	
    	// create a list of packages
    	packages =FXCollections.observableArrayList();
    	tcPkgId.setCellValueFactory(new PropertyValueFactory<>("PackageId"));
		tcPkgName.setCellValueFactory(new PropertyValueFactory<>("PkgName"));
    	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
    	
    	
    	
    	try 
    	{
    		// reading json
            URL url = new URL("http://localhost:8080/TravelExperts2/rs/db/getallpackages");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
            	buffer.append(line);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    	
    	try 
    	{
    		// read and create each package object from json
            JSONArray jsonArray = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonPkg = (JSONObject) jsonArray.get(i);
                //convert date string into date variable
                String startDate = jsonPkg.getString("pkgStartDate");
                String endDate = jsonPkg.getString("pkgEndDate");
                DateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
                
                Packag pkg = new Packag(jsonPkg.getInt("packageId"), jsonPkg.getDouble("pkgAgencyCommission") ,jsonPkg.getDouble("pkgBasePrice"),jsonPkg.getString("pkgDesc"), format.parse(endDate), jsonPkg.getString("pkgName"), format.parse(startDate));
                
                packages.add(pkg);
            }
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
		tvPackages.setItems(packages);
    	
			
			
		
		
	}

    @FXML
    void deletePackage(ActionEvent event) {

    }

    @FXML
    void editPackage(ActionEvent event) {
    	enableInputs(true);
    	btnEdit.setDisable(true);
    	
    	

    }

    @FXML
    void savePackage(ActionEvent event) {
    	enableInputs(false);
    }
    
    @FXML
    void displayPackageInfo(MouseEvent event) {
    	model.Packag selectedPackage= tvPackages.getSelectionModel().getSelectedItem();
    	lblPackageId.setText(""+selectedPackage.getPackageId());
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


	
    
    
    
}
