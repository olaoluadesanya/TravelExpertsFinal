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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import model.Product;
import model.ProductsSupplier;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class PackagesController implements Initializable{
	
	// ===================Sunghyun Lee =====================================================

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
    private JFXButton btnRefreshPkg;
    

    //private StringBuffer buffer;
    
    private ObservableList<model.Product> productsInPackage;
    private ObservableList<Packag> packages1;
    private ObservableList<ProductsSupplier> psList;
    private ObservableList<PackagesProductsSupplier> ppsList;
    
    private String status;
    
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
     
  // ===================Sunghyun Lee =====================================================
  
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// initialize ability of controls
    	enableInputs(false);
    	btnAddPackage.setDisable(false);
    	btnEdit1.setDisable(false);
    	btnSave1.setDisable(true);
    	tcPkgId.setSortable(false);
    	tcPkgName.setSortable(false);
    	btnRefreshPkg.setVisible(false);
    	
    	// instantiate lists
    	packages1 =FXCollections.observableArrayList();
    	productsInPackage = FXCollections.observableArrayList();
    	psList = FXCollections.observableArrayList();
    	ppsList = FXCollections.observableArrayList();
    	
    	// instantiate table columns
    	tcPkgId.setCellValueFactory(new PropertyValueFactory<>("PackageId"));
		tcPkgName.setCellValueFactory(new PropertyValueFactory<>("PkgName"));
    	
		// read lists from web server and set them to tables
    	readPackages();
    	tvPackages.setItems(packages1);    	
    	//readPackagesProductsSuppliers();

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
    // read package-product-suppliers list from web server
    /*
    private void readPackagesProductsSuppliers()
	{
    	StringBuffer buffer = new StringBuffer();    	
    	try 
    	{
    		// reading json
            URL url = new URL("http://localhost:8080/TravelExperts2/rs/db/getallpps");
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

                PackagesProductsSupplier pps= new PackagesProductsSupplier(jsonPps.getInt("packageId"), jsonPps.getInt("productSupplierId"));
                ppsList.add(pps); 
            }
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();    	
    	}		
	}
	*/
	// read packages from web server
    private void readPackages()
	{    		
    	packages1.clear();
    	StringBuffer buffer = new StringBuffer();
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

                Packag pkg= new Packag(jsonPkg.getInt("packageId"), new BigDecimal( jsonPkg.getDouble("pkgAgencyCommission") ), new BigDecimal( jsonPkg.getDouble("pkgBasePrice")), jsonPkg.getString("pkgDesc"),ldEndDate, jsonPkg.getString("pkgName"), ldStartDate);
                packages1.add(pkg); 
                System.out.println("pkg "+i+": "+pkg);
            }
            System.out.println("done: "+packages1);
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
	    	status="edit";
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
    	btnEdit1.setDisable(true);
    	btnAddPackage.setDisable(true);
    	btnDelete1.setDisable(true);
    	btnSave1.setDisable(false);
    	enableInputs(true);
    	status="add";
    	tvPackages.setDisable(true);
    	
    	// hide products-related controls
    	
    	lvProductsInPackage.setVisible(false);
    	lblProductsInPkg.setVisible(false);
    	
    	lblProductsSuppliers.setVisible(false);
    	tvProductsSuppliers.setVisible(false);	
    	btnInsertProductIntoPkg.setVisible(false);
    	btnRemoveProductFromPkg.setVisible(false);
    	
    	
    	eraseInputs();
    	
    	
    	

    }

    private void eraseInputs()
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
    void savePackage(ActionEvent event) {
		if (Validated())
		{
			enableInputs(false);
	    	btnDelete1.setDisable(false);
	    	btnAddPackage.setDisable(false);
	    	btnEdit1.setDisable(false);
	    	tvPackages.setDisable(false);
	    	
	    	if (status=="add")
	    	{
	    		// create a new package               
                //newPkg=new Packag(0, new BigDecimal( tfPkgAgencyCommission.getText()), new BigDecimal(tfPkgBasePrice.getText()), taPkgDesc.getText(), Date.from(dpPkgEndDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), tfPkgName.getText(), Date.from(dpPkgStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));                
                newPkg=new Packag(0, new BigDecimal( tfPkgAgencyCommission.getText()), new BigDecimal(tfPkgBasePrice.getText()), taPkgDesc.getText(), dpPkgEndDate.getValue(), tfPkgName.getText(), dpPkgStartDate.getValue());                

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
                
                String       postUrl       = "http://localhost:8080/TravelExperts2/rs/db/postpackage";// put in your url
                HttpClient   httpClient    = HttpClientBuilder.create().build();
                HttpPost     post          = new HttpPost(postUrl);
                StringEntity postingString;
                HttpResponse  response;
                
                int success=0; // store status code from http response to see whether successful
				try
				{
					postingString = new StringEntity(myJson);
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
				

				// 2nd
				/*
		        StringEntity entity = new StringEntity(json,
		                ContentType.APPLICATION_FORM_URLENCODED);

		        HttpClient httpClient = HttpClientBuilder.create().build();
		        HttpPost request = new HttpPost("http://localhost:8080/TravelExperts2/rs/db/postpackage");
		        request.setEntity(entity);

		        HttpResponse response;
				try
				{
					response = httpClient.execute(request);
					System.out.println(response.getStatusLine().getStatusCode());
				} catch (ClientProtocolException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
                // if successful
	        	if (success==200)
	        	{
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Success");
		    		alert.setHeaderText(null);
		    		alert.setContentText("New packages has been successfully created");
		    		alert.showAndWait();
		    		//refreshTables();
		    		
		    	  
		    	   
	        	}
	        	else
	        	{
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Failure");
		    		alert.setHeaderText(null);
		    		alert.setContentText("There was a problem and the package was not created");
		    		alert.showAndWait();
	        	}        	
	        	newPkg=null;
	    	}
	    	// edit
	    	else
	    	{
	    		//readPackagesProductsSuppliers();
	    	}
	    	
	    	// enable back the disabled inputs
        	btnSave1.setDisable(true);
    		lvProductsInPackage.setVisible(true);
        	lblProductsInPkg.setVisible(true);
        	tvPackages.getSelectionModel().select(0);
        	readPackages();
        	displayPackageInfo();
		}
    }
    
    private boolean Validated()
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
    	// empty inputs
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
	    	//System.out.println(selectedPackage.getPkgEndDate());
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

	@FXML
    void refreshPkgtables(ActionEvent event) {
		readPackages();

    }
	// =====================================================================================
	
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
    	status="add";
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
    	status="edit";
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
    	
    	if (status=="add")
    	{
    		// Create a new product          
            Product newProd = new Product(0, tfProdName.getText());                

            // Create the JSON object for the new product
            Gson gson = new Gson();
            Type type = new TypeToken<Product>() {}.getType();
            String json = gson.toJson(newProd, type);
            
            // Create the HTTP post request to send to the web server
            String        postUrl       = "http://localhost:8080/TravelExperts2/rs/db/insertproduct";
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
    	
    	status = "";

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
