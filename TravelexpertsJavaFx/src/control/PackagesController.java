/*
 * This file has many authors
 * Authors: Sunghyun Lee, Graeme, Corinne Mullan
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
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class PackagesController implements Initializable{
	
	//private String URLCONSTANT= "http://10.163.101.59:8080";
	private String URLCONSTANT="http://localhost:8080";
	
	// ===================Sunghyun Lee =====================================================
	// controls and variables for package tab
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
    private TableColumn<ProductsSupplier, String> tcProductsPkgTab;

    @FXML
    private TableColumn<ProductsSupplier, String> tcSuppliersPkgTab;

    @FXML
    private JFXButton btnEdit1;

    @FXML
    private JFXButton btnSave1;

    @FXML
    private JFXButton btnDelete1;

    @FXML
    private TableView<ProductsSupplier> tvProductsSuppliersInPackage;

    @FXML
    private TableColumn<ProductsSupplier, String> tcProductsInPkg;
    
    @FXML
    private TableColumn<ProductsSupplier, String> tcSuppliersInPackage;

    @FXML
    private TableView<ProductsSupplier> tvProductsSuppliers1;

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
    
    private ObservableList<Packag> packages1;
    //private ObservableList<ProductsSupplier> psList;
    private ObservableList<PackagesProductsSupplier> ppsList;
    private ObservableList<ProductsSupplier> productsSuppliersInPkg;
    
    private String pkgStatus="null"; // whether package is being added or edited
    private Packag newPkg; // package that is created or updated
    // productsSupplier that are to be added into or deleted from package
    private List<ProductsSupplier> addedPsList;
    private List<ProductsSupplier> deletedPsList;
    
    
    
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
     private TableColumn<ProductsSupplier, String> tcProducts2;

     @FXML
     private TableColumn<ProductsSupplier, String> tcSuppliers2;

     @FXML
     private JFXButton btnAddProdSupplier;

     @FXML
     private JFXComboBox<Supplier> cboSuppliers;

     @FXML
     private JFXButton btnRefreshProd;

     @FXML
     private JFXButton btnSaveProd;
     
     private ObservableList<model.Product> products;
     private ObservableList<model.Supplier> suppliers;
     private ObservableList<ProductsSupplier> productsSuppliers;
     
     private String statusProd="null";  //add or edit product, or add a new product-supplier relationship
     
  // =====================================================================================
     
  
  
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	// ===================Sunghyun Lee =====================================================
    	// initialize package tab        	
    	
    	// instantiate lists
    	packages1 = FXCollections.observableArrayList();
    	//psList = FXCollections.observableArrayList();
    	ppsList = FXCollections.observableArrayList();
    	addedPsList = new ArrayList<>();
    	deletedPsList=new ArrayList<>();
    	productsSuppliersInPkg = FXCollections.observableArrayList();
    	productsSuppliers = FXCollections.observableArrayList();
    	
    	// instantiate table columns
    	tcPkgId.setCellValueFactory(new PropertyValueFactory<>("PackageId"));
		tcPkgName.setCellValueFactory(new PropertyValueFactory<>("PkgName"));
		tcProductsPkgTab.setCellValueFactory(new PropertyValueFactory<>("prodName"));
		tcSuppliersInPackage.setCellValueFactory(new PropertyValueFactory<>("supName"));
		tcSuppliersPkgTab.setCellValueFactory(new PropertyValueFactory<>("supName"));
		tcProductsInPkg.setCellValueFactory(new PropertyValueFactory<>("prodName"));		
    	
		// read lists from web server and set them to tables
    	readPackages();
    	readProductsSuppliers();
    	tvPackages.setItems(packages1);    	
    	readPackagesProductsSuppliers();
    	tvProductsSuppliers1.setItems(productsSuppliers);
    	tvProductsSuppliersInPackage.setItems(productsSuppliersInPkg);
    	
    	//datepicker styling to enable disabling editing while keeping it opaque
    	dpPkgStartDate.setStyle("-fx-opacity: 1");
    	dpPkgStartDate.getEditor().setStyle("-fx-opacity: 1");
    	dpPkgEndDate.setStyle("-fx-opacity: 1");
    	dpPkgEndDate.getEditor().setStyle("-fx-opacity: 1");

    	// initialize ability of controls
    	enableInputs(false);
    	btnAddPackage.setDisable(false);
    	btnEdit1.setDisable(false);
    	btnSave1.setDisable(true);
    	tcPkgId.setSortable(false);
    	tcPkgName.setSortable(false);
    	btnCancelPkg.setDisable(true);  
    	
    	
    	//======================= Graeme ========================================    	    	
    	
    	//===========================Tab pane
    	//add buttons to tab bar
    	JFXButton btnClose = new JFXButton();
    	Image closeIcon = new Image(getClass().getResourceAsStream("/images/close_icon_white.png"));
    	btnClose.setGraphic(new ImageView(closeIcon));
    	btnClose.getStyleClass().add("button-tab");
    	btnClose.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	Stage stage = (Stage) btnClose.getScene().getWindow();
    	    	stage.close();
    	    }
    	});
    	JFXButton btnMin = new JFXButton();
    	Image minIcon = new Image(getClass().getResourceAsStream("/images/minimize_icon_white.png"));
    	btnMin.setGraphic(new ImageView(minIcon));
    	btnMin.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	Stage stage = (Stage) btnMin.getScene().getWindow();
    	    	stage.setIconified(true);
    	    }
    	});
    	
    	AnchorPane.setTopAnchor(btnMin, 4.0);
        AnchorPane.setRightAnchor(btnMin, 26.0);
    	
    	AnchorPane.setTopAnchor(btnClose, 5.0);
        AnchorPane.setRightAnchor(btnClose, 1.0);
        
        apPackages.getChildren().add(btnClose);
        apPackages.getChildren().add(btnMin);
        
        //========================================Packages
       
        
        //==============================================Bookings
    	//instantiate and fill lists
    	customerIds = FXCollections.observableArrayList();    	
    	fillCustomerIdList(getBuffer(URLCONSTANT + "/TravelExperts2/rs/db/getallcustomers"));
    	classes = FXCollections.observableArrayList();
    	fillClassesList(getBuffer(URLCONSTANT +"/TravelExperts2/rs/db/getallclasses"));
    	feeTypes = FXCollections.observableArrayList();
    	fillFeeTypeList(getBuffer(URLCONSTANT +"/TravelExperts2/rs/db/getallfees"));
    	tripTypes = FXCollections.observableArrayList();
    	fillTripTypeList(getBuffer(URLCONSTANT +"/TravelExperts2/rs/db/getalltriptypes"));
    	
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
    	btnAddProdSupplier.setDisable(true);
    	tcProductId.setSortable(false);
    	tcProdName.setSortable(false);
    	tfProdName.setDisable(true);
    	tcProducts2.setSortable(false);
    	tcSuppliers2.setSortable(false);
    	
    	// Instantiate the lists
    	products = FXCollections.observableArrayList();
    	suppliers = FXCollections.observableArrayList();
    	
    	
    	// Instantiate the table columns
    	tcProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
		tcProdName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
		tcProducts2.setCellValueFactory(new PropertyValueFactory<>("prodName"));
		tcSuppliers2.setCellValueFactory(new PropertyValueFactory<>("supName"));
    	
		// Obtain the Products, Suppliers, and ProductsSuppliers from the web service
    	readProducts();
    	readSuppliers(); 	
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
    	ppsList.clear();
    	StringBuffer buffer = new StringBuffer();    	
    	try 
    	{
    		// reading json
            //URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/getallpackagesproductsuppliers");
    		URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/getallpackagesproductsuppliers");
    		
    		
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
                
                ppsList.add(new PackagesProductsSupplier(jsonPps.getJSONObject("id").getInt("packageId"), jsonPps.getJSONObject("id").getInt("productSupplierId")));
                
                
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
            //URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/getallpackages");
    		URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/getallpackages");
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

                Packag pkg= new Packag(jsonPkg.getInt("packageId"), jsonPkg.getDouble("pkgAgencyCommission"), jsonPkg.getDouble("pkgBasePrice"), jsonPkg.getString("pkgDesc"),ldEndDate, jsonPkg.getString("pkgName"), ldStartDate, jsonPkg.getString("pkgImageFile"));
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
    			// store productsSuppliers that exist in the package before deleting package.
    			// In case of package-deleting failure, we can restore the products.
    			List<ProductsSupplier> tempProductsSuppliersInPkg = new ArrayList<>();
    			for (ProductsSupplier p:tvProductsSuppliersInPackage.getItems())
    			{
    				tempProductsSuppliersInPkg.add(p);
    				System.out.print(p.getProductSupplierId()+" ");
    				System.out.println();
    			}
    			// delete rows from PkgProductsSuppliers table first
    			try
				{
					for (model.ProductsSupplier p : productsSuppliersInPkg )
					{
						model.PackagesProductsSupplier pps = new PackagesProductsSupplier(tvPackages.getSelectionModel().getSelectedItem().getPackageId(), p.getProductSupplierId());
						// send json to web server
		                // manually create json
		                String myJson2= "{" 
		                			+	"\"id\"" + ": {" 
		                			+	"\"packageId\""+": "+ pps.getPackageId() + ", "
		                        	+	"\"productSupplierId\"" +": "+ pps.getProductSupplierId()
		                        	+	"}"
		                        	+	"}";
		                			
		                String       postUrl2       = URLCONSTANT +"/TravelExperts2/rs/db/deletepackagesproductssupplier";
		                HttpClient   httpClient2    = HttpClientBuilder.create().build();
		                HttpPost     post2          = new HttpPost(postUrl2);
		                StringEntity postingString2;
		                HttpResponse  response2;
		                
						
						postingString2 = new StringEntity(myJson2);
						post2.setEntity(postingString2);
						post2.setHeader("Content-type", "application/json");
						response2 = httpClient2.execute(post2);		
					}
				}		
				catch ( IOException e)
				{
					Alert alert2 = new Alert(AlertType.INFORMATION);
	        		alert2.setTitle("Failure");
		    		alert2.setHeaderText(null);
		    		alert2.setContentText("There was a problem, and some products were not inserted");
		    		alert2.showAndWait();
					e.printStackTrace();
				}
    			
    			// then delete the package from packages table
    			int numPackagesBeforeDeleting=packages1.size();
				try
				{
					
					//URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/deletepackage/"+tvPackages.getSelectionModel().getSelectedItem().getPackageId());
					URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/deletepackage/"+tvPackages.getSelectionModel().getSelectedItem().getPackageId());
					HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
	    			//httpCon.setDoOutput(true);
	    			
	    			
					httpCon.setRequestProperty("Content-Type",
	    		                "application/x-www-form-urlencoded");
					httpCon.setRequestMethod("DELETE");
					httpCon.connect();
					System.out.println("line 674: "+httpCon.getResponseCode());

					/*     			
	    			readPackages();
	    			readPackagesProductsSuppliers();
	    			tvPackages.getSelectionModel().select(0);
	    			displayPackageInfo();
	    			*/

				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int numPackagesAfterDeleting = packages1.size();
				
				// deleting package failed
				if (numPackagesBeforeDeleting==numPackagesAfterDeleting)
				{
					// put productssuppliers back into the package
					for (ProductsSupplier p :tempProductsSuppliersInPkg)
					{
						String myJson2= "{" 
	                			+	"\"id\"" + ": {" 
	                			+	"\"packageId\""+": "+ tvPackages.getSelectionModel().getSelectedItem().getPackageId() + ", "
	                        	+	"\"productSupplierId\"" +": "+ p.getProductSupplierId()
	                        	+	"}"
	                        	+	"}";
	                			
		                String       postUrl2       = URLCONSTANT +"/TravelExperts2/rs/db/insertpackagesproductsupplier";
		                HttpClient   httpClient2    = HttpClientBuilder.create().build();
		                HttpPost     post2          = new HttpPost(postUrl2);
		                StringEntity postingString2;
		                HttpResponse  response2;
		                System.out.println(myJson2);
						try
						{
							postingString2 = new StringEntity(myJson2);
							post2.setEntity(postingString2);
							post2.setHeader("Content-type", "application/json");
							response2 = httpClient2.execute(post2);
							System.out.println("line715: " +response2.getStatusLine().toString());
						}
						catch (Exception e) {
							// TODO: handle exception
							System.out.println("dunno what im gonna do");
						}
					}
					
					alert = new Alert(AlertType.INFORMATION);
		    		alert.setTitle("Booking Conflict");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Customers already booked this package");
		    		alert.showAndWait();
				}
				// refresh the page
				readPackages();
				readPackagesProductsSuppliers();
				tvPackages.getSelectionModel().select(0);
				displayPackageInfo();
				
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
    	
    	tvProductsSuppliersInPackage.setVisible(false);    	
    	lblProductsSuppliers.setVisible(false);
    	tvProductsSuppliers1.setVisible(false);	
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
		//System.out.println("emptied stuff");
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
                newPkg=new Packag(0, Double.parseDouble( tfPkgAgencyCommission.getText()), Double.parseDouble(tfPkgBasePrice.getText()), taPkgDesc.getText(), dpPkgEndDate.getValue(), tfPkgName.getText(), dpPkgStartDate.getValue(),"airplane.jpg");                

                // send json to web server
                Gson gson = new Gson();
                Type type = new TypeToken<Packag>() {}.getType();
                String json = gson.toJson(newPkg, type);
                
                int idx=json.indexOf("pkgEndDate"); // index of "pkgEndDate" in json
                
                // manually modify json string to send date variables in a format that web server understands
                String myJson= json.substring(0, idx+12)+"\""+newPkg.getPkgEndDate()+"\""+","
                										+"\"pkgName\":\""+newPkg.getPkgName()+"\","
                										+"\"pkgStartDate\":\""+newPkg.getPkgStartDate()+"\","
                										+"\"pkgImageFile\":\""+newPkg.getPkgImageFile()+"\""
                										+"}";
                
                String       postUrl       = URLCONSTANT +"/TravelExperts2/rs/db/insertpackage";// put in your url
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
	    		
	    		// update package
	    		
                // send json to web server
                Gson gson = new Gson();
                Type type = new TypeToken<Packag>() {}.getType();
                String json = gson.toJson(newPkg, type);
                
                int idx=json.indexOf("pkgEndDate"); // index of "pkgEndDate" in json
                
                // manually modify json string to send date variables in a format that web server understands
                String myJson= json.substring(0, idx+12)+"\""+newPkg.getPkgEndDate()+"\""+","
                										+"\"pkgName\":\""+newPkg.getPkgName()+"\","
                										+"\"pkgStartDate\":\""+newPkg.getPkgStartDate()+"\","
                										+"\"pkgImageFile\":\""+newPkg.getPkgImageFile()+"\""
                										+"}";
                
               
                
                //String       postUrl       = URLCONSTANT +"/TravelExperts2/rs/db/updatepackage";
                String       postUrl       = URLCONSTANT +"/TravelExperts2/rs/db/updatepackage";
                HttpClient   httpClient    = HttpClientBuilder.create().build();
                HttpPost     post          = new HttpPost(postUrl);
                StringEntity postingString;
                HttpResponse  response;
                
				try
				{
					postingString = new StringEntity(myJson);
					post.setEntity(postingString);
					post.setHeader("Content-type", "application/json");
					response = httpClient.execute(post);
					
				} catch ( IOException e)
				{
					e.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Failure");
		    		alert.setHeaderText(null);
		    		alert.setContentText("There was a problem, and the package was not updated");
		    		alert.showAndWait();
					e.printStackTrace();
				}
				
				// insert products into package
				if (addedPsList.size()>0) // if any product has been added
				{	
					try
					{
						for (model.ProductsSupplier p : addedPsList )
						{
							model.PackagesProductsSupplier pps = new PackagesProductsSupplier(newPkg.getPackageId(), p.getProductSupplierId());
							// send json to web server
			                // manually create json
			                String myJson2= "{" 
			                			+	"\"id\"" + ": {" 
			                			+	"\"packageId\""+": "+ pps.getPackageId() + ", "
			                        	+	"\"productSupplierId\"" +": "+ pps.getProductSupplierId()
			                        	+	"}"
			                        	+	"}";
			                			
			                String       postUrl2       = URLCONSTANT +"/TravelExperts2/rs/db/insertpackagesproductsupplier";
			                HttpClient   httpClient2    = HttpClientBuilder.create().build();
			                HttpPost     post2          = new HttpPost(postUrl2);
			                StringEntity postingString2;
			                HttpResponse  response2;
			                
							
							postingString2 = new StringEntity(myJson2);
							post2.setEntity(postingString2);
							post2.setHeader("Content-type", "application/json");
							response2 = httpClient2.execute(post2);							
						}
					}		
					catch ( IOException e)
					{
						Alert alert = new Alert(AlertType.INFORMATION);
		        		alert.setTitle("Failure");
			    		alert.setHeaderText(null);
			    		alert.setContentText("There was a problem, and some products were not inserted");
			    		alert.showAndWait();
						e.printStackTrace();
					}
		        }     
				

				// remove product from package
				if (deletedPsList.size()>0) // if any product has been removed
				{
					try
					{
						for (model.ProductsSupplier p : deletedPsList )
						{
							model.PackagesProductsSupplier pps = new PackagesProductsSupplier(newPkg.getPackageId(), p.getProductSupplierId());
							// send json to web server
			                // manually create json
			                String myJson2= "{" 
			                			+	"\"id\"" + ": {" 
			                			+	"\"packageId\""+": "+ pps.getPackageId() + ", "
			                        	+	"\"productSupplierId\"" +": "+ pps.getProductSupplierId()
			                        	+	"}"
			                        	+	"}";
			                			
			                String       postUrl2       = URLCONSTANT +"/TravelExperts2/rs/db/deletepackagesproductssupplier";
			                HttpClient   httpClient2    = HttpClientBuilder.create().build();
			                HttpPost     post2          = new HttpPost(postUrl2);
			                StringEntity postingString2;
			                HttpResponse  response2;
			                
							
							postingString2 = new StringEntity(myJson2);
							post2.setEntity(postingString2);
							post2.setHeader("Content-type", "application/json");
							response2 = httpClient2.execute(post2);							
						}
					}		
					catch ( IOException e)
					{
						Alert alert = new Alert(AlertType.INFORMATION);
		        		alert.setTitle("Failure");
			    		alert.setHeaderText(null);
			    		alert.setContentText("There was a problem, and some products were not inserted");
			    		alert.showAndWait();
						e.printStackTrace();
					}
				}

        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Success");
	    		alert.setHeaderText(null);
	    		alert.setContentText("The package has been successfully updated");
	    		alert.showAndWait();
	    	}
	    	
	    	newPkg=null;
	    	addedPsList.clear();
	    	deletedPsList.clear();
	    	
        	btnSave1.setDisable(true);
    		tvProductsSuppliersInPackage.setVisible(true);

        	//re-read data from web server
        	readPackagesProductsSuppliers();
        	readPackages();
        	readProductsSuppliers();
        	
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
        	tvProductsSuppliers1.setVisible(true);

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
			// agency commission > base price
			if (Double.parseDouble( tfPkgAgencyCommission.getText())> Double.parseDouble( tfPkgBasePrice.getText()) )
			{
	    		alert.setTitle("Agency Commission and Base Price");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Agency Commission must be smaller than Base Price");
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
    	
    	// check date
    	if (dpPkgStartDate.getValue().isBefore(LocalDate.now()))
    	{
    		alert.setTitle("Date Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Start Date must be after today's date");
    		alert.showAndWait();
    		myBool=false;
    	}
    	
    	if (dpPkgStartDate.getValue().isAfter(dpPkgEndDate.getValue()))
    	{
    		alert.setTitle("Date Error");
    		alert.setHeaderText(null);
    		alert.setContentText("End Date must be after Start Date");
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
	    	
	    	displayProductsInPkg();	    	
	    	
    	}
    }
    // when a package is selected, display the products included in the package on tvProductsInPkg
	private void displayProductsInPkg()
	{
		productsSuppliersInPkg.clear();
		readPackagesProductsSuppliers();
		readProductsSuppliers();
		
		List<Integer> productsSupplierIds = new ArrayList<>(); 
    	
		// first using packagesProductsSupplier list, find productsSupplier ids that are associated with the package.
		// Then, put the ids into the temporary list
    	for (PackagesProductsSupplier p : ppsList)
    	{
    		if (p.getPackageId() == tvPackages.getSelectionModel().getSelectedItem().getPackageId())
    		{
    			productsSupplierIds.add(p.getProductSupplierId());
    		}
    	}
    	
    	// then using ids in the temporary list, find the productSupplier objects that should be displayed on tvProductsInPkg
    	for (ProductsSupplier p : productsSuppliers)
    	{
    		if (productsSupplierIds.contains(p.getProductSupplierId()))
    			productsSuppliersInPkg.add(p);
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
    	
    	lblProductsSuppliers.setDisable(!myBool);
    	tvProductsSuppliers1.setDisable(!myBool);
    	
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
    	tvProductsSuppliers1.setVisible(true);

    	btnDelete1.setDisable(false);
    	btnAddPackage.setDisable(false);
    	btnEdit1.setDisable(false);
    	tvPackages.setDisable(false);
    	btnCancelPkg.setDisable(true);
		
    	newPkg=null;
    	
    	btnSave1.setDisable(true);
		tvProductsSuppliersInPackage.setVisible(true);
		
		displayPackageInfo();
		pkgStatus="null";
		addedPsList.clear();
		deletedPsList.clear();
		
		
    }
	
	@FXML
    void insertProductIntoPkg(ActionEvent event) {
		ProductsSupplier selectedPs = tvProductsSuppliers1.getSelectionModel().getSelectedItem();
		if (selectedPs!= null)
		{
			if (productsSuppliersInPkg.contains(selectedPs))
			{
				Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("The package already contains the product from the selected supplier");
	    		alert.showAndWait();
			}
			else
			{
				if (deletedPsList.contains(selectedPs))
				{
					deletedPsList.remove(selectedPs);
				}
				else 
				{
					addedPsList.add(selectedPs);
				}
				
				productsSuppliersInPkg.add(selectedPs);
			}
			
			
		}

    }

    @FXML
    void removeProductFromPkg(ActionEvent event) {
    	ProductsSupplier selectedPs = tvProductsSuppliersInPackage.getSelectionModel().getSelectedItem();
		if (selectedPs!= null)
		{
			if (addedPsList.contains(selectedPs))
			{
				addedPsList.remove(selectedPs);
			}
			else 
			{
				deletedPsList.add(selectedPs);

			}
			
			productsSuppliersInPkg.remove(selectedPs);
		}

    }
	
	// =====================================================================================

	// ==================================== Graeme =========================================
    //================Packages
    //=============Bookings
    @FXML
    private AnchorPane apPackages;

    @FXML
    private JFXTabPane tpPackages;
    
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
        //System.out.println(json);
        String       postUrl       = URLCONSTANT +"/TravelExperts2/rs/db/postbooking";// put in your url
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

    	    if (responseString.equals("Success")){
    	    	Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Booking Created");
    			alert.setHeaderText(null);
    			alert.setContentText("Booking successfully created");
    			alert.show();  
    	    }  
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
            URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/getallproducts");
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
            URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/getallsuppliers");
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
            URL url = new URL(URLCONSTANT +"/TravelExperts2/rs/db/getallproductssuppliers");
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
                		                                        jsonProdSup.getJSONObject("product").getInt("productId"),
                		                                        jsonProdSup.getJSONObject("product").getString("prodName"),
                		                                        jsonProdSup.getJSONObject("supplier").getInt("supplierId"),
                		                                        jsonProdSup.getJSONObject("supplier").getString("supName"));
                //System.out.println(jsonProdSup.getJSONObject("product").getString("prodName"));
                productsSuppliers.add(prodSup);
                
                
            }
            
            /*// Obtain the list of product-suppliers from the JSON data in "buffer" that was
            // returned by the web service, using the fromJson() method that is part of
            // the Gson package.
            Gson gson = new Gson();
            Type category = new TypeToken<List<ProductsSupplier>>(){}.getType();
            ArrayList<ProductsSupplier> arrayList = gson.fromJson(buffer.toString(), category);
             
            //productsSuppliers*/
            
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
    	
    	// Set the product status to "add" for use by the saveProduct method
    	statusProd = "add";
    }

    @FXML
    void addProductSupplier(ActionEvent event) {
    	
    	// Disable the Edit, Add and AddProdSupplier buttons, disable the product name input, 
    	// disable the products table, and enable the save and reset buttons
    	btnEditProd.setDisable(true);
    	btnAddProd.setDisable(true);
    	btnAddProdSupplier.setDisable(true);
    	tfProdName.setDisable(true);
    	tvProducts.setDisable(true);
    	btnSaveProd.setDisable(false);
    	btnRefreshProd.setDisable(false);
    	
    	// Set the product status to "addProdSup" for use by the saveProduct method
    	statusProd = "addProdSup";
    	
    	// Confirm the product and supplier that will be added to the products_suppliers table
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Click Save to add supplier " +  
		                     cboSuppliers.getSelectionModel().getSelectedItem().getSupName() + 
		                     " to product " + tfProdName.getText());
		alert.showAndWait();  
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
    	
    	// Set the product status to "edit" for use by the saveProduct method
    	statusProd = "edit";
    }

    @FXML
    void refreshProdTables(ActionEvent event) {
    		readProducts();
    		readSuppliers();
    		readProductsSuppliers();
    		
    		// Set all the controls back to their initial state
    		btnAddProd.setDisable(false);
        	btnEditProd.setDisable(false);
        	btnSaveProd.setDisable(true);
        	btnAddProdSupplier.setDisable(true);
        	tvProducts.setDisable(false);
        	tfProdName.setDisable(true);	
        	
        	lblProductId.setText("");
        	tfProdName.setText("");
    }

    @FXML
    void saveProduct(ActionEvent event) {
    	
    	if (statusProd=="add")
    	{
    		// Create a new product          
            Product newProd = new Product(0, tfProdName.getText());                

            // Create the JSON object for the new product
            Gson gson = new Gson();
            Type type = new TypeToken<Product>() {}.getType();
            String json = gson.toJson(newProd, type);
            
            // Create the HTTP post request to send to the web server
            //String        postUrl       = URLCONSTANT +"/TravelExperts2/rs/db/insertproduct";
            String        postUrl       = URLCONSTANT +"/TravelExperts2/rs/db/insertproduct";

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
	    	    //System.out.println("Response: " + responseString);
	    	    
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
    	}
    	else if (statusProd == "edit")
    	{
    		// Create a new product object with the updated description (the product Id will be unchanged)
    		Product updProd = new Product(Integer.parseInt(lblProductId.getText()), tfProdName.getText());
    		
            // Create the JSON string for the updated product to send to the server
            Gson gson = new Gson();
            Type type = new TypeToken<Product>() {}.getType();
            String json = gson.toJson(updProd, type);
            
            String        postUrl       = URLCONSTANT + "/TravelExperts2/rs/db/updateproduct";
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
				success = response.getStatusLine().getStatusCode();
				
			} catch ( IOException e)
			{
				e.printStackTrace();
			}
			if (success == 200)
        	{
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Success");
	    		alert.setHeaderText(null);
	    		alert.setContentText("The product has been successfully updated");
	    		alert.showAndWait();
        	}
        	else
        	{
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Failure");
	    		alert.setHeaderText(null);
	    		alert.setContentText("There was a problem, and the product was not updated");
	    		alert.showAndWait();
        	}        	

    	}
    	else if (statusProd == "addProdSup") {

    		// Obtain the data for the new products_suppliers record.  The product ID can be 
        	// obtained from the label in the detail product view, and the supplier ID is the 
        	// ID of the supplier selected in the combo box.
        	Product newProduct = tvProducts.getSelectionModel().getSelectedItem();
        	Supplier newSupplier = cboSuppliers.getSelectionModel().getSelectedItem();
        	
        	// Create the JSON string for the new product supplier.  The JSON must match the
        	// ProductsSupplier JPA entity class in the web service, which consists of the 
        	// productSupplierId as well as the Product and Supplier objects corresponding to the
        	// product-supplier record.
        	// Use 0 for the productSupplierId since this is auto-incremented by the database
            String json= "{" 
        			+	"\"productSupplierId\":" + "0," 
        			+	"\"product\""+":{\"productId\":"+ newProduct.getProductId() + ","
        			+   "\"prodName\":\"" + newProduct.getProdName() + "\"},"
        			+	"\"supplier\""+":{\"supplierId\":"+ newSupplier.getSupplierId() + ","
        			+   "\"supName\":\"" + newSupplier.getSupName() + "\"}"
                	+	"}"; 
            
            
            
            // Create the HTTP post request to send to the web server
            String        postUrl       = URLCONSTANT + "/TravelExperts2/rs/db/insertproductsupplier";
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
	    	    //System.out.println("Response: " + responseString);
			} 
			catch ( IOException e)
			{
				e.printStackTrace();
			}
			
			// On success
        	if (success==200)
        	{
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Success");
	    		alert.setHeaderText(null);
	    		alert.setContentText("The new product-supplier has been successfully created");
	    		alert.showAndWait();  
        	}
        	// On failure
        	else
        	{
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Failure");
	    		alert.setHeaderText(null);
	    		alert.setContentText("There was a problem and the product-supplier was not created");
	    		alert.showAndWait();
        	}      
        	
    	}
    	
    	// Revert the controls to their initial enabled/disabled state
    	tfProdName.setDisable(true);
    	btnAddProd.setDisable(false);
    	btnEditProd.setDisable(false);
    	tvProducts.setDisable(false);
    	btnSaveProd.setDisable(true);
    	
    	statusProd = "";

    	readProducts();
    	readSuppliers();
    	readProductsSuppliers();
    }
    
    // When a product is selected from the products table, display its information in the
    // detail display.
    @FXML
    void selectProduct(MouseEvent event) {
    	displayProductInfo();
    	
    	// If a supplier is also selected, then enable the Add Product Supplier button
		if (cboSuppliers.getSelectionModel().getSelectedItem() != null) {
			btnAddProdSupplier.setDisable(false);
	    }
    }
    
    
	private void displayProductInfo() {
		
		if (tvProducts.getSelectionModel().getSelectedItem()!= null)
    	{
	    	model.Product selectedProduct= tvProducts.getSelectionModel().getSelectedItem();
	    	lblProductId.setText("" + selectedProduct.getProductId());
	    	tfProdName.setText("" + selectedProduct.getProdName());
    	}
	}
	
	@FXML
    void onSupplierSelected(ActionEvent event) {
		
		// If a product is also selected, then enable the Add Product Supplier button
		if (tvProducts.getSelectionModel().getSelectedItem() != null) {
			btnAddProdSupplier.setDisable(false);
		}
    }
	
	// ====================================================================================
	
		
	}

