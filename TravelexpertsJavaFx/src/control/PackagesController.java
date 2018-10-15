/*
 * This file has many authors
 * Authors: Sunghyun Lee, Graeme
 * created: 2018-10-01
 */

package control;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.ComboBox;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.Product;
import model.ProductsSupplier;

import model.TripType;
import model.Booking;
import model.Clas;
import model.Customer;
import model.FeeType;

import model.Supplier;

import model.Packag;
import model.PackagesProductsSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class PackagesController implements Initializable{
	
	// ===================Sunghyun Lee =====================================================
	// controls and variables for package tab
	@FXML
    private Label lblPackageId;
	
	@FXML
    private Label lblProductsInPkg;

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
    private JFXButton btnEdit1;

    @FXML
    private JFXButton btnSave1;

    @FXML
    private JFXButton btnDelete1;

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
    
    @FXML
    private JFXButton btnAddPackage;
    
    @FXML
    private JFXButton btnCancelPkg;
    

    //private StringBuffer buffer;
    
    private ObservableList<model.Product> productsInPackage;
    private ObservableList<Packag> packages1;
    private ObservableList<ProductsSupplier> psList;
    private ObservableList<PackagesProductsSupplier> ppsList;
    
    private String pkgStatus="null"; // whether package is being added or edited
    
    private Packag newPkg;
    
 // =====================================================================================
	
 // =======================Corinne Mullan================================================
 // Variables used on the Products tab
 	
 	 @FXML
     private TableView<Product> tvProducts;

     @FXML
     private TableColumn<Product, Integer> tcProductId;

     @FXML
     private TableColumn<Product, String> tcProdName;

     @FXML
     private Label lblProductId;

     @FXML
     private JFXTextField tfProdName;

     @FXML
     private JFXButton btnEditProd;

     @FXML
     private JFXButton btnAddProd;

     @FXML
     private TableView<ProductsSupplier> tvProductsSuppliers2;
     
     @FXML
     private TableColumn<Product, String> tcProducts2;

     @FXML
     private TableColumn<Product, String> tcSuppliers2;

     @FXML
     private JFXButton btnAddProdSupplier;

     @FXML
     private ComboBox<Supplier> cboSuppliers;

     @FXML
     private JFXButton btnRefreshProd;

     @FXML
     private JFXButton btnSaveProd;
     
     private ObservableList<model.Product> products;
     private ObservableList<model.Supplier> suppliers;
     private ObservableList<ProductsSupplier> productsSuppliers;

     
  // =====================================================================================
     
  
  
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	// ===================Sunghyun Lee =====================================================
    	// initialize package tab
    	
		// initialize ability of controls
    	enableInputs(false);
    	btnAddPackage.setDisable(false);
    	btnEdit1.setDisable(false);
    	btnSave1.setDisable(true);
    	tcPkgId.setSortable(false);
    	tcPkgName.setSortable(false);
    	btnCancelPkg.setDisable(true);
    	
    	// instantiate lists
    	packages1 = FXCollections.observableArrayList();
    	productsInPackage = FXCollections.observableArrayList();
    	psList = FXCollections.observableArrayList();
    	ppsList = FXCollections.observableArrayList();
    	
    	// instantiate table columns
    	tcPkgId.setCellValueFactory(new PropertyValueFactory<>("PackageId"));
		tcPkgName.setCellValueFactory(new PropertyValueFactory<>("PkgName"));
    	
		// read lists from web server and set them to tables
    	readPackages();
    	tvPackages.setItems(packages1);    	
    	readPackagesProductsSuppliers();
    	
    	
    	//======================= Bookings Tab ========================================
    	//traveler count field input validation: only allows numbers
    	tfBookingTravelerCount.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            tfBookingTravelerCount.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    	//instantiate and fill lists
    	customerIds = FXCollections.observableArrayList();    	
    	fillCustomerIdList(getBuffer("http://localhost:8080/TravelExperts2/rs/db/getallcustomers"));
    	classes = FXCollections.observableArrayList();
    	fillClassesList(getBuffer("http://localhost:8080/TravelExperts2/rs/db/getallclasses"));
    	feeTypes = FXCollections.observableArrayList();
    	fillFeeTypeList(getBuffer("http://localhost:8080/TravelExperts2/rs/db/getallfees"));
    	tripTypes = FXCollections.observableArrayList();
    	fillTripTypeList(getBuffer("http://localhost:8080/TravelExperts2/rs/db/getalltriptypes"));
    	
    	//set combo boxes
    	cbBookingPackage.setItems(packages1);
    	cbBookingPackage.setConverter(
        		new StringConverter<Packag>() {
    				@Override
    				public Packag fromString(String arg0) {
    					// TODO Auto-generated method stub
    					return null;
    				}

    				@Override
    				public String toString(Packag pack) {
    					if (pack == null) {
    						return null;
    					}
    					else {
    						return pack.getPkgName();
    					}
    				}    			
        		}
        );
    	
    	cbBookingCustomerId.setItems(customerIds);
    	cbBookingCustomerId.setConverter(
    		new StringConverter<Customer>() {
				@Override
				public Customer fromString(String arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String toString(Customer cust) {
					if (cust == null) {
						return null;
					}
					else {
						return Integer.toString(cust.getCustomerId());
					}
				}    			
    		}
    	);
    	
    	cbBookingClass.setItems(classes);
    	cbBookingClass.setConverter(
    		new StringConverter<Clas>() {
				@Override
				public Clas fromString(String arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String toString(Clas clas) {
					if (clas == null) {
						return null;
					}
					else {
						return clas.getClassName();
					}
				}    			
    		}
    	);
    	
    	cbBookingFeeType.setItems(feeTypes);
    	cbBookingFeeType.setConverter(
    		new StringConverter<FeeType>() {
				@Override
				public FeeType fromString(String arg0) {
					// TODO Auto-generated method stub
					return null;
				}


				@Override
				public String toString(FeeType type) {
					if (type == null) {
						return null;
					}
					else {
						return type.getFeeName();
					}
				}    			
    		}
    	);
    
      cbBookingTripType.setItems(tripTypes);
    	cbBookingTripType.setConverter(
    		new StringConverter<TripType>() {
				@Override
				public TripType fromString(String arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String toString(TripType trip) {
					if (trip == null) {
						return null;
					}
					else {
						return trip.getTtName();
					}
				}    			
    		}
    	);


    	// =======================Corinne Mullan================================================
    	// Initialize the Products tab
    	
    	// Set the controls to their initial states
    	btnAddProd.setDisable(false);
    	btnEditProd.setDisable(false);
    	btnSaveProd.setDisable(true);
    	tcProductId.setSortable(false);
    	tcProdName.setSortable(false);
    	tfProdName.setDisable(true);
    	tcProducts2.setSortable(false);
    	tcSuppliers2.setSortable(false);
    	
    	// Instantiate the lists
    	products = FXCollections.observableArrayList();
    	suppliers = FXCollections.observableArrayList();
    	productsSuppliers = FXCollections.observableArrayList();
    	
    	// Instantiate the table columns
    	tcProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		tcProdName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
		tcProducts2.setCellValueFactory(new PropertyValueFactory<>("prodName"));
		tcSuppliers2.setCellValueFactory(new PropertyValueFactory<>("supName"));
    	
		// Obtain the Products, Suppliers, and ProductsSuppliers from the web service
    	readProducts();
    	readSuppliers(); 	
    	readProductsSuppliers();
    	tvProducts.setItems(products);   
    	tvProductsSuppliers2.setItems(productsSuppliers);
    	
    	// Initialize the combo box containing supplier names
    	cboSuppliers.setItems(suppliers);
    	
    	// =====================================================================================

    	
    	
	}
    // =====================Sunghyun Lee===================================================
    // methods for package tab
    
    // read package-product-suppliers list from web server
    
    private void readPackagesProductsSuppliers()
	{
    	StringBuffer buffer = new StringBuffer();    	
    	try 
    	{
    		// reading json
            //URL url = new URL("http://localhost:8080/TravelExperts2/rs/db/getallpackagesproductsuppliers");
    		URL url = new URL("http://10.163.101.59:8080/TravelExperts2/rs/db/getallpackagesproductsuppliers");
    		
    		
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
            	buffer.append(line);
            }

        } catch (Exception e) {
    		e.printStackTrace();    	
        }
    	
    	try 
    	{
    		// read packagesProductsSuppliers from json and put them into ppslist
            JSONArray jsonArray = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonPps = (JSONObject) jsonArray.get(i);
                
                System.out.println(jsonPps.getString("id"));
                /*
                PackagesProductsSupplier pps= new PackagesProductsSupplier(jsonPps.getInt("packageId"), jsonPps.getInt("productSupplierId"));
                ppsList.add(pps); 
                */
            }
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();    	
    	}		
	}
	
	// read packages from web server
    private void readPackages()
	{    		
    	packages1.clear();
    	StringBuffer buffer = new StringBuffer();
    	try 
    	{
    		// reading json
            //URL url = new URL("http://localhost:8080/TravelExperts2/rs/db/getallpackages");
    		URL url = new URL("http://10.163.101.59:8080/TravelExperts2/rs/db/getallpackages");
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
            	buffer.append(line);
            }
            
        } catch (Exception e) {
    		e.printStackTrace();    	
        }
    	
    	try 
    	{
    		// read packages from json and put them into packages list
            JSONArray jsonArray = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonPkg = (JSONObject) jsonArray.get(i);
                //convert date string into date variable
                String startDate = jsonPkg.getString("pkgStartDate");
                String endDate = jsonPkg.getString("pkgEndDate");
                DateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
                
                LocalDate ldEndDate=format.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate ldStartDate=format.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                Packag pkg= new Packag(jsonPkg.getInt("packageId"), jsonPkg.getDouble("pkgAgencyCommission"), jsonPkg.getDouble("pkgBasePrice"), jsonPkg.getString("pkgDesc"),ldEndDate, jsonPkg.getString("pkgName"), ldStartDate);
                packages1.add(pkg); 
            }
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();    	
    	}
	}
    
	@FXML
    void deletePackage(ActionEvent event) {
    	if (tvPackages.getSelectionModel().getSelectedItem()!=null)
    	{
    		ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
    		ButtonType cancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
    		Alert alert = new Alert(AlertType.WARNING,
    		        "Are you sure you want to delete this package?",
    		        ok,
    		        cancel);

    		alert.setTitle(null);
    		Optional<ButtonType> result = alert.showAndWait();

    		if (result.orElse(cancel) == ok) 
    		{	
				try
				{
					//URL url = new URL("http://localhost:8080/TravelExperts2/rs/db/deletepackage/"+tvPackages.getSelectionModel().getSelectedItem().getPackageId());
					URL url = new URL("http://10.163.101.59:8080/TravelExperts2/rs/db/deletepackage/"+tvPackages.getSelectionModel().getSelectedItem().getPackageId());
					HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
	    			//httpCon.setDoOutput(true);
	    			
	    			
					httpCon.setRequestProperty("Content-Type",
	    		                "application/x-www-form-urlencoded");
					httpCon.setRequestMethod("DELETE");
	    		    System.out.println(httpCon.getResponseCode());
	    		    //httpCon.disconnect();
		    	    
		    	    	    			
	    			readPackages();
	    			//readpp();
	    			tvPackages.getSelectionModel().select(0);
	    			displayPackageInfo();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select a package to delete");
    		alert.showAndWait();
    	}
    	
    }

    @FXML
    void editPackage(ActionEvent event) {
    	if (tvPackages.getSelectionModel().getSelectedItem()!=null)
    	{
	    	enableInputs(true);
	    	btnEdit1.setDisable(true);
	    	btnSave1.setDisable(false);
	    	btnAddPackage.setDisable(true);
	    	tvPackages.setDisable(true);
	    	pkgStatus="edit";
	    	btnCancelPkg.setDisable(false);
    	}
    	else 
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select a package to edit");
    		alert.showAndWait();
    	}
    }
    
    @FXML
    void AddPackage(ActionEvent event) {
    	// enable or disable elements
    	btnEdit1.setDisable(true);
    	btnAddPackage.setDisable(true);
    	btnDelete1.setDisable(true);
    	btnSave1.setDisable(false);
    	enableInputs(true);
    	pkgStatus="add";
    	tvPackages.setDisable(true);
    	btnCancelPkg.setDisable(false);
    	
    	// hide products-related controls
    	
    	lvProductsInPackage.setVisible(false);
    	lblProductsInPkg.setVisible(false);
    	
    	lblProductsSuppliers.setVisible(false);
    	tvProductsSuppliers.setVisible(false);	
    	btnInsertProductIntoPkg.setVisible(false);
    	btnRemoveProductFromPkg.setVisible(false);
    	
    	
    	emptyTxtFieldsInPkgTab();
    	
    	
    	

    }
    // empty all text fields in the Package tab
    private void emptyTxtFieldsInPkgTab()
	{
		// TODO Auto-generated method stub
		lblPackageId.setText("New");
		tfPkgName.setText("");
		tfPkgAgencyCommission.setText("");
		tfPkgBasePrice.setText("");
		taPkgDesc.setText("");
		dpPkgEndDate.setValue(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		dpPkgStartDate.setValue(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}

	@FXML
    void savePackage(ActionEvent event) 
	{
		if (validatePackage())
		{
			enableInputs(false);
	    	btnDelete1.setDisable(false);
	    	btnAddPackage.setDisable(false);
	    	btnEdit1.setDisable(false);
	    	tvPackages.setDisable(false);
	    	
	    	// add a new package
	    	if (pkgStatus=="add")
	    	{
	    		// create a new package               
                //newPkg=new Packag(0, new BigDecimal( tfPkgAgencyCommission.getText()), new BigDecimal(tfPkgBasePrice.getText()), taPkgDesc.getText(), Date.from(dpPkgEndDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), tfPkgName.getText(), Date.from(dpPkgStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));                
                newPkg=new Packag(0, Double.parseDouble( tfPkgAgencyCommission.getText()), Double.parseDouble(tfPkgBasePrice.getText()), taPkgDesc.getText(), dpPkgEndDate.getValue(), tfPkgName.getText(), dpPkgStartDate.getValue());                

                // send json to web server
                Gson gson = new Gson();
                Type type = new TypeToken<Packag>() {}.getType();
                String json = gson.toJson(newPkg, type);
                
                int idx=json.indexOf("pkgEndDate"); // index of "pkgEndDate" in json
                
                // manually modify json string to send date variables in a format that web server understands
                String myJson= json.substring(0, idx+12)+"\""+newPkg.getPkgEndDate()+"\""+","
                										+"\"pkgName\":\""+newPkg.getPkgName()+"\","
                										+"\"pkgStartDate\":\""+newPkg.getPkgStartDate()+"\""
                										+"}";
                
                //String       postUrl       = "http://localhost:8080/TravelExperts2/rs/db/insertpackage";// put in your url
                String       postUrl       = "http://10.163.101.59:8080/TravelExperts2/rs/db/insertpackage";// put in your url
                HttpClient   httpClient    = HttpClientBuilder.create().build();
                HttpPost     post          = new HttpPost(postUrl);
                StringEntity postingString;
                HttpResponse  response;
                
                int success=0; // status code that tells whether insertpackage request was successful or not
				try
				{
					postingString = new StringEntity(myJson);
					post.setEntity(postingString);
					post.setHeader("Content-type", "application/json");
					response = httpClient.execute(post);
					success=response.getStatusLine().getStatusCode();
					/*
					HttpEntity entity = response.getEntity();
		    	    String responseString = null;
		    	    responseString = EntityUtils.toString(entity, "UTF-8");
		    	    System.out.println("Repoese: " + responseString);
		    	    */
		    	    
		    	    
				} catch ( IOException e)
				{
					e.printStackTrace();
				}

                // if successful
	        	if (success==200)
	        	{
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Success");
		    		alert.setHeaderText(null);
		    		alert.setContentText("New packages has been successfully created");
		    		alert.showAndWait();		    		
		    	  		    	 
	        	}
	        	else
	        	{
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Failure");
		    		alert.setHeaderText(null);
		    		alert.setContentText("There was a problem, and the package was not created");
		    		alert.showAndWait();
	        	}        	
	    	}
	    	// edit the selected package
	    	else if(pkgStatus=="edit")
	    	{
	    		newPkg = tvPackages.getSelectionModel().getSelectedItem();
	    		newPkg.setPkgAgencyCommission(Double.parseDouble( tfPkgAgencyCommission.getText()));
	    		newPkg.setPkgBasePrice(Double.parseDouble(tfPkgBasePrice.getText()));
	    		newPkg.setPkgDesc(taPkgDesc.getText());
	    		newPkg.setPkgName(tfPkgName.getText());
	    		newPkg.setPkgEndDate(dpPkgEndDate.getValue());
	    		newPkg.setPkgStartDate(dpPkgStartDate.getValue());
                // send json to web server
                Gson gson = new Gson();
                Type type = new TypeToken<Packag>() {}.getType();
                String json = gson.toJson(newPkg, type);
                
                int idx=json.indexOf("pkgEndDate"); // index of "pkgEndDate" in json
                
                // manually modify json string to send date variables in a format that web server understands
                String myJson= json.substring(0, idx+12)+"\""+newPkg.getPkgEndDate()+"\""+","
                										+"\"pkgName\":\""+newPkg.getPkgName()+"\","
                										+"\"pkgStartDate\":\""+newPkg.getPkgStartDate()+"\""
                										+"}";
                
                //String       postUrl       = "http://localhost:8080/TravelExperts2/rs/db/updatepackage";
                String       postUrl       = "http://10.163.101.59:8080/TravelExperts2/rs/db/updatepackage";
                HttpClient   httpClient    = HttpClientBuilder.create().build();
                HttpPost     post          = new HttpPost(postUrl);
                StringEntity postingString;
                HttpResponse  response;
                
                int success1=0; // status code that tells whether updatepackage request was successful or not
				try
				{
					postingString = new StringEntity(myJson);
					post.setEntity(postingString);
					post.setHeader("Content-type", "application/json");
					response = httpClient.execute(post);
					success1=response.getStatusLine().getStatusCode();
					
				} catch ( IOException e)
				{
					e.printStackTrace();
				}
				if (success1==200)
	        	{
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Success");
		    		alert.setHeaderText(null);
		    		alert.setContentText("The package has been successfully updated");
		    		alert.showAndWait();
	        	}
	        	else
	        	{
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Failure");
		    		alert.setHeaderText(null);
		    		alert.setContentText("There was a problem, and the package was not updated");
		    		alert.showAndWait();
	        	}        	

	    	}
	    	// edit
	    	
	    	newPkg=null;
	    	
        	btnSave1.setDisable(true);
    		lvProductsInPackage.setVisible(true);
        	lblProductsInPkg.setVisible(true);

        	//re-read data from web server
        	//readPackagesProductsSuppliers();
        	readPackages();
        	
        	// select the package just created or updated
        	if (pkgStatus=="add")
        	{
        		tvPackages.getSelectionModel().select(tvPackages.getItems().size()-1);
        	}
        	else if (pkgStatus=="edit")
        	{
        		for (Packag pkg : tvPackages.getItems())
        		{
        			if (pkg.getPackageId() == Integer.parseInt( lblPackageId.getText()))
        				tvPackages.getSelectionModel().select(pkg);
        		}
        	}
        	
        	displayPackageInfo();
        	btnCancelPkg.setDisable(true);
		}
			
	}
    // validate inputs on package tab before saving
    private boolean validatePackage()
	{
		
    	boolean myBool=true; // return true or false
    	Alert alert = new Alert(AlertType.INFORMATION);
    	// agency commission and base price
    	try 
    	{
    		// agency commission or base price less than 0
			if (Double.parseDouble( tfPkgAgencyCommission.getText())<0 || Double.parseDouble( tfPkgBasePrice.getText()) <0)
			{
	    		alert.setTitle("Negative Value");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Agency Commission and Base Price must be greater than zero");
	    		alert.showAndWait();
	    		myBool=false;
			}
    	} catch (Exception e) 
    	{
    		
    		alert.setTitle("Wrong Format");
    		alert.setHeaderText(null);
    		alert.setContentText("Wrong format for Agency Commission or Base Price");
    		alert.showAndWait();
    		myBool= false;
		}
    	// empty text fields
    	if (tfPkgName.getText().trim().isEmpty())
    	{
    		alert.setTitle("Emtpy Input");
    		alert.setHeaderText(null);
    		alert.setContentText("Please type in a package name");
    		alert.showAndWait();
    		myBool=false;
    	}
    	
    	if (taPkgDesc.getText().trim().isEmpty())
    	{
    		alert.setTitle("Emtpy Input");
    		alert.setHeaderText(null);
    		alert.setContentText("Please type in description");
    		alert.showAndWait();
    		myBool=false;
    	}
    	

    	return myBool;
	}

	@FXML
    void selectPackage(MouseEvent event) {
    	displayPackageInfo();
    	
    }
    // display properties of selected package on text fields
    private void displayPackageInfo()
	{
    	
    	if (tvPackages.getSelectionModel().getSelectedItem()!= null)
    	{
	    	model.Packag selectedPackage= tvPackages.getSelectionModel().getSelectedItem();
	    	lblPackageId.setText(""+selectedPackage.getPackageId());
	    	tfPkgName.setText(""+selectedPackage.getPkgName());
	    	//dpPkgStartDate.setValue(selectedPackage.getPkgStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	    	dpPkgStartDate.setValue(selectedPackage.getPkgStartDate());
	    	
	    	//dpPkgEndDate.setValue(selectedPackage.getPkgEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	    	dpPkgEndDate.setValue(selectedPackage.getPkgEndDate());

	    	taPkgDesc.setText(selectedPackage.getPkgDesc());
	    	tfPkgBasePrice.setText(selectedPackage.getPkgBasePrice()+"");
	    	tfPkgAgencyCommission.setText(selectedPackage.getPkgAgencyCommission()+"");

	    	
    	}
    }

	private void enableInputs(boolean myBool)
    {
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
	// when cancel button is clicked, reset all settings back to default
	@FXML
    void refreshPkgTab(ActionEvent event) {
		// if cancelled while adding, select the first package in tvPackages
		if (pkgStatus=="add")
			tvPackages.getSelectionModel().select(0);
		
		enableInputs(false);
    	btnDelete1.setDisable(false);
    	btnAddPackage.setDisable(false);
    	btnEdit1.setDisable(false);
    	tvPackages.setDisable(false);
    	btnCancelPkg.setDisable(true);
		
    	newPkg=null;
    	
    	btnSave1.setDisable(true);
		lvProductsInPackage.setVisible(true);
    	lblProductsInPkg.setVisible(true);
		
		displayPackageInfo();
		pkgStatus="null";
		
		
    }
	
	// =====================================================================================

	// ==================================== Graeme =========================================
	
	@FXML
    private JFXComboBox<Packag> cbBookingPackage;

    @FXML
    private JFXComboBox<TripType> cbBookingTripType;

    @FXML
    private JFXTextField tfBookingTravelerCount;

    @FXML
    private JFXComboBox<Customer> cbBookingCustomerId;

    @FXML
    private JFXTextArea taBookingDescription;

    @FXML
    private JFXTextField tfBookingDestination;

    @FXML
    private JFXComboBox<Clas> cbBookingClass;

    @FXML
    private JFXComboBox<FeeType> cbBookingFeeType;

    @FXML
    private Label lblBookingPackageId;

    @FXML
    private JFXButton btnCreateBooking;
    
    private ObservableList<Customer> customerIds;
    
    private ObservableList<Clas> classes;
    
    private ObservableList<TripType> tripTypes;
    
    private ObservableList<FeeType> feeTypes;


 // get json from web server
    private StringBuffer getBuffer(String urlString)
	{ 
    	StringBuffer buffer = new StringBuffer();
    	try 
    	{
    		// reading json
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
            	buffer.append(line);
            }

        } catch (Exception e) {
    		e.printStackTrace();    	
        }
    	return buffer;
	}
    
    //fill lists to be used for combo boxes using stringbuffer created getbuffer()
    private void fillClassesList(StringBuffer buffer) {
    	classes.clear();
    	JSONArray jsonArray = new JSONArray(buffer.toString());
    	for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonPkg = (JSONObject) jsonArray.get(i);                
            Clas clas = new Clas(jsonPkg.getString("classId"), jsonPkg.getString("className"));
            classes.add(clas); 
        }
    }
    
    private void fillCustomerIdList(StringBuffer buffer) {
    	customerIds.clear();
    	JSONArray jsonArray = new JSONArray(buffer.toString());
    	for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonPkg = (JSONObject) jsonArray.get(i);                
            Customer cust = new Customer(jsonPkg.getInt("customerId"));
            customerIds.add(cust); 
        }
    }
    
    private void fillTripTypeList(StringBuffer buffer) {
    	tripTypes.clear();
    	JSONArray jsonArray = new JSONArray(buffer.toString());
    	for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonPkg = (JSONObject) jsonArray.get(i);                
            TripType tripType = new TripType(jsonPkg.getString("tripTypeId"), jsonPkg.getString("TTName"));
            tripTypes.add(tripType); 
        }
    }
    
    private void fillFeeTypeList(StringBuffer buffer) {
    	feeTypes.clear();
    	JSONArray jsonArray = new JSONArray(buffer.toString());
    	for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonPkg = (JSONObject) jsonArray.get(i);                
            FeeType feeType = new FeeType(jsonPkg.getString("feeId"), jsonPkg.getString("feeName"));
            feeTypes.add(feeType); 
        }
    }    
    
    public void validateBooking() {   
    	//show alert
    	if(cbBookingCustomerId.getValue() == null || cbBookingClass.getValue() == null || cbBookingPackage.getValue() == null || cbBookingTripType.getValue() == null
    			|| cbBookingFeeType.getValue() == null || tfBookingTravelerCount.getText().trim().isEmpty()) {    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Empty Field");
    		alert.setHeaderText(null);
    		alert.setContentText("Please fill in all required fields, denoted with *");
    		alert.showAndWait();
    		
    		//make empty fields red
    		if(cbBookingCustomerId.getValue() == null) {
    			cbBookingCustomerId.getStylesheets().add(getClass().getResource("/view/error.css").toExternalForm());
    		}
    		if(cbBookingClass.getValue() == null) {
    			cbBookingClass.getStylesheets().add(getClass().getResource("/view/error.css").toExternalForm());
    		}
    		if(cbBookingPackage.getValue() == null) {
    			cbBookingPackage.getStylesheets().add(getClass().getResource("/view/error.css").toExternalForm());
    		}
    		if(cbBookingTripType.getValue() == null) {
    			cbBookingTripType.getStylesheets().add(getClass().getResource("/view/error.css").toExternalForm());
    		}
    		if(cbBookingFeeType.getValue() == null) {
    			cbBookingFeeType.getStylesheets().add(getClass().getResource("/view/error.css").toExternalForm());
    		}
    		if(tfBookingTravelerCount.getText().trim().isEmpty()) {
    			tfBookingTravelerCount.getStylesheets().add(getClass().getResource("/view/error.css").toExternalForm());
    		}
    	}
    	else {
    		insertBooking();
    	}
    }
    
    private void insertBooking() {
    	Booking booking = new Booking(cbBookingCustomerId.getValue().getCustomerId(), cbBookingClass.getValue().getClassId(), cbBookingPackage.getValue().getPackageId(), cbBookingTripType.getValue().getTripTypeId(), 
    			Integer.parseInt(tfBookingTravelerCount.getText().trim()), cbBookingFeeType.getValue().getFeeId(), tfBookingDestination.getText().trim(), taBookingDescription.getText().trim());         
    	
        // send json to web server
        Gson gson = new Gson();
        Type type = new TypeToken<Booking>() {}.getType();
        String json = gson.toJson(booking, type);
        
        //int idx=json.indexOf("pkgEndDate"); // index of "pkgEndDate" in json
        
        // manually modify json string to send date variables in a format that web server understands
        //String myJson= json.substring(0, idx+12)+"\""+newPkg.getPkgEndDate()+"\""+","
        										//+"\"pkgName\":\""+newPkg.getPkgName()+"\","
        										//+"\"pkgStartDate\":\""+newPkg.getPkgStartDate()+"\""
        										//+"}";
        
        String       postUrl       = "http://localhost:8080/TravelExperts2/rs/db/postbooking";// put in your url
        HttpClient   httpClient    = HttpClientBuilder.create().build();
        HttpPost     post          = new HttpPost(postUrl);
        StringEntity postingString;
        HttpResponse  response;
        
        int success=0; // store status code from http response to see whether successful
		try
		{
			postingString = new StringEntity(json);
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
			response = httpClient.execute(post);
			success=response.getStatusLine().getStatusCode();
			
			HttpEntity entity = response.getEntity();
    	    String responseString = null;
    	    responseString = EntityUtils.toString(entity, "UTF-8");
    	    System.out.println("Response: " + responseString);
    	    
			//System.out.println(response);
		} catch ( IOException e)
		{
			e.printStackTrace();
		}
    }


	
	// =======================Corinne Mullan================================================
	// Methods for the Products tab
	
	// Obtain a list of all of the products from the database using the web service
    private void readProducts()
	{    		
    	products.clear();
    	StringBuffer buffer = new StringBuffer();
    	try 
    	{
    		// Read the JSON array from the web service
            URL url = new URL("http://localhost:8080/TravelExperts2/rs/db/getallproducts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
            	buffer.append(line);
            }

        } catch (Exception e) {
    		e.printStackTrace();    	
        }
    	
    	try 
    	{
    		// Obtain the products from the JSON array and put them into the products list
            JSONArray jsonArray = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonProd = (JSONObject) jsonArray.get(i);
                Product prod = new Product(jsonProd.getInt("productId"), 
                		                   jsonProd.getString("prodName"));
                products.add(prod); 
            }
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();    	
    	}
	}
    
 // Obtain a list of all of the suppliers from the database using the web service
    private void readSuppliers()
	{    		
    	suppliers.clear();
    	StringBuffer buffer = new StringBuffer();
    	try 
    	{
    		// Read the JSON array from the web service
            URL url = new URL("http://localhost:8080/TravelExperts2/rs/db/getallsuppliers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
            	buffer.append(line);
            }

        } catch (Exception e) {
    		e.printStackTrace();    	
        }
    	
    	try 
    	{
    		// Obtain the products from the JSON array and put them into the products list
            JSONArray jsonArray = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonSup = (JSONObject) jsonArray.get(i);
                Supplier sup = new Supplier(jsonSup.getInt("supplierId"), 
                		                     jsonSup.getString("supName"));
                suppliers.add(sup); 
            }
            
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();    	
    	}
	}
    
 // Obtain a list of all of the products - suppliers from the database using the web service
    private void readProductsSuppliers()
	{    		
    	productsSuppliers.clear();
    	StringBuffer buffer = new StringBuffer();
    	try 
    	{
    		// Read the JSON array from the web service
            URL url = new URL("http://localhost:8080/TravelExperts2/rs/db/getallproductssuppliers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
            	buffer.append(line);
            }

        } catch (Exception e) {
    		e.printStackTrace();    	
        }
    	
    	try 
    	{
    		// Obtain the products from the JSON array and put them into the products list
            JSONArray jsonArray = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonProdSup = (JSONObject) jsonArray.get(i);
                ProductsSupplier prodSup = new ProductsSupplier(jsonProdSup.getInt("productSupplierId"),
                		                                        jsonProdSup.getInt("productId"), 
                		                                        jsonProdSup.getString("prodName"),
                		                                        jsonProdSup.getInt("supplierId"),
                		                                        jsonProdSup.getString("supName"));
                productsSuppliers.add(prodSup); 
            }
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();    	
    	}
	}

    @FXML
    void addProduct(ActionEvent event) {
    	
    	// Clear the selection on the Products table, and clear the data entry fields
    	tvProducts.getSelectionModel().clearSelection();
    	lblProductId.setText("");
    	tfProdName.setText("");
    	
    	// Disable the Edit and Add buttons, enable the product name input, 
    	// disable the products table, and enable the save and reset buttons
    	btnEditProd.setDisable(true);
    	btnAddProd.setDisable(true);
    	tfProdName.setDisable(false);
    	tfProdName.setEditable(true);
    	tvProducts.setDisable(true);
    	btnSaveProd.setDisable(false);
    	btnRefreshProd.setDisable(false);
    	
    	// Set the status to "add" for use by the saveProduct method
    	pkgStatus="add";
    }

    @FXML
    void addProductSupplier(ActionEvent event) {
    	
    	// ***** TO DO *****

    }

    @FXML
    void editProduct(ActionEvent event) {
    	
    	// Disable the Edit and Add buttons, enable the product name input, 
    	// disable the products table, and enable the save and reset buttons
    	btnEditProd.setDisable(true);
    	btnAddProd.setDisable(true);
    	tfProdName.setDisable(false);
    	tfProdName.setEditable(true);
    	tvProducts.setDisable(true);
    	btnSaveProd.setDisable(false);
    	btnRefreshProd.setDisable(false);
    	
    	// Set the status to "edit" for use by the saveProduct method
    	pkgStatus="edit";
    }

    @FXML
    void refreshProdTables(ActionEvent event) {
    		readProducts();
    		readSuppliers();
    		// readProductsSuppliers();
    		
    		// Set all the controls back to their initial state
    		btnAddProd.setDisable(false);
        	btnEditProd.setDisable(false);
        	btnSaveProd.setDisable(true);
        	tvProducts.setDisable(false);
        	tfProdName.setDisable(true);	
        	
        	lblProductId.setText("");
        	tfProdName.setText("");
    }

    @FXML
    void saveProduct(ActionEvent event) {
    	
    	if (pkgStatus=="add")
    	{
    		// Create a new product          
            Product newProd = new Product(0, tfProdName.getText());                

            // Create the JSON object for the new product
            Gson gson = new Gson();
            Type type = new TypeToken<Product>() {}.getType();
            String json = gson.toJson(newProd, type);
            
            // Create the HTTP post request to send to the web server
            //String        postUrl       = "http://localhost:8080/TravelExperts2/rs/db/insertproduct";
            String        postUrl       = "http://10.163.101.59:8080/TravelExperts2/rs/db/insertproduct";

            HttpClient    httpClient    = HttpClientBuilder.create().build();
            HttpPost      post          = new HttpPost(postUrl);
            StringEntity  postingString;
            HttpResponse  response;
            
            int success=0; // Store the status code from the http response to indicate whether the request was successful
			try
			{
				postingString = new StringEntity(json);
				post.setEntity(postingString);
				post.setHeader("Content-type", "application/json");
				response = httpClient.execute(post);
				success=response.getStatusLine().getStatusCode();
				
				HttpEntity entity = response.getEntity();
	    	    String responseString = null;
	    	    responseString = EntityUtils.toString(entity, "UTF-8");
	    	    System.out.println("Repoese: " + responseString);
	    	    
				//System.out.println(response);
			} catch ( IOException e)
			{
				e.printStackTrace();
			}
			
			// On success
        	if (success==200)
        	{
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Success");
	    		alert.setHeaderText(null);
	    		alert.setContentText("The new product has been successfully created");
	    		alert.showAndWait();  
        	}
        	// On failure
        	else
        	{
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Failure");
	    		alert.setHeaderText(null);
	    		alert.setContentText("There was a problem and the product was not created");
	    		alert.showAndWait();
        	}        	
        	newPkg=null;
    	}
    	// If status = "edit":
    	else
    	{
    		// ***** TO DO ****
    		// Code for updating a product
    	}
    	
    	// Revert the controls to their initial enabled/disabled state
    	
    	tfProdName.setDisable(true);
    	btnAddProd.setDisable(false);
    	btnEditProd.setDisable(false);
    	tvProducts.setDisable(false);
    	btnSaveProd.setDisable(true);
    	
    	pkgStatus = "";

    	readProducts();
    	readSuppliers();
    	// readProductsSuppliers();
    }
    
    @FXML
    void selectProduct(MouseEvent event) {
    	displayProductInfo();
    }
    
    
	private void displayProductInfo() {
		
		if (tvProducts.getSelectionModel().getSelectedItem()!= null)
    	{
	    	model.Product selectedProduct= tvProducts.getSelectionModel().getSelectedItem();
	    	lblProductId.setText("" + selectedProduct.getProductId());
	    	tfProdName.setText("" + selectedProduct.getProdName());
    	}
	}
	
	// ====================================================================================
	
		
	}

