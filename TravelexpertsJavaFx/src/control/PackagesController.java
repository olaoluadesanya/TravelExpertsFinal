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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


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
    private TableView<model.Package> tvPackages;
    
    @FXML
    private TableColumn<model.Package, Integer> tcPackageId;

    @FXML
    private TableColumn<model.Package, String> tcPackageName;

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
    	model.Package selectedPackage= tvPackages.getSelectionModel().getSelectedItem();
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
