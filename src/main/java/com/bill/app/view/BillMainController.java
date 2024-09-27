package com.bill.app.view;

import com.bill.app.model.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;


public class BillMainController {

    @FXML
    private DatePicker datePacker;
    private Tooltip theToolTip;
    @FXML
    private Label productNameLabel;
    @FXML
    private TextField productNameTxtField;
    @FXML
    private TableColumn<DataModel, String> products;
    @FXML
    private TableColumn<DataModel, String> amountColumn;
    @FXML
    private TableColumn<DataModel, String> unitPrice;
    @FXML
    private TableColumn<DataModel, String> VATAmount;
    @FXML
    private TableColumn<DataModel, String> totalPrice;
    @FXML
    private TableView<DataModel> mainTable;

    @FXML
    private Button addbtn;
    @FXML
    private TextField VATNumberTextField;
    @FXML
    private TextField vendorNameTextField;
    @FXML
    private TextField vendorAddTextField;
    @FXML
    private TextField totalPriceTxtField;
    @FXML
    private TextField VATTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private VBox vbox;
    @FXML
    private Label VATPrice;
    @FXML
    private Label VAT;
    @FXML
    private Label price;
    @FXML
    private Label amount;
    @FXML
    private Label vendorName;
    @FXML
    private Label vendorAddress;
    @FXML
    private Label recordedNumberVAT;
    @FXML
    private Label continuous_number;
    @FXML
    private Label release_date_label;
    @FXML
    private Label main_label;
    @FXML
    private TextField continuous_number_field;
    private ObservableList<DataModel> model = FXCollections.observableArrayList();

    public void initialize() {

        setFonts();
        setupTooltips();
        validateFloatInput();
        setTableColumns();
        mainTable.setItems(model);
        btnAction();

    }
    private void setFonts()
    {
        /*Setting font family and size*/
        Font customFontRDL = Font.loadFont(getClass().getResourceAsStream("/Fonts/bader-al-yadawi.ttf"), 18);
        Font customFontML = Font.loadFont(getClass().getResourceAsStream("/Fonts/Alarabiya Normal Font.ttf"), 36);
        release_date_label.setFont(customFontRDL);
        main_label.setFont(customFontML);
        continuous_number.setFont(customFontRDL);
        continuous_number_field.setEditable(Boolean.FALSE);
        continuous_number_field.setText("HI");
        recordedNumberVAT.setFont(customFontRDL);
        vendorAddress.setFont(customFontRDL);
        vendorName.setFont(customFontRDL);
        amount.setFont(customFontRDL);
        price.setFont(customFontRDL);
        VAT.setFont(customFontRDL);
        VATPrice.setFont(customFontRDL);
        productNameLabel.setFont(customFontRDL);
    }
    private void setTableColumns()
    {
        products.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asString());
        unitPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString());
        VATAmount.setCellValueFactory(cellData -> cellData.getValue().VATAmountProperty().asString());
        totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asString());
        mainTable.setItems(model);
    }
    @FXML
    protected void btnAction() {

        addbtn.setOnAction(actionEvent -> {
            DataModel dataModel = new DataModel();
            dataModel.setVendorAddress(vendorAddTextField.getText());
            dataModel.setVendorName(vendorNameTextField.getText());
            dataModel.setVATNumber(StringConverterFloat(VATNumberTextField.getText()));
            dataModel.setAmount(StringConverterFloat(amountTextField.getText()));
            dataModel.setPrice(StringConverterFloat(priceTextField.getText()));
            dataModel.setVATAmount(StringConverterFloat(VATTextField.getText()));
            dataModel.setTotalPrice(StringConverterFloat(totalPriceTxtField.getText()));
            dataModel.setProductName(productNameTxtField.getText());
            model.add(dataModel);
            clearFields();
        });

    }
    public void clearFields() {
        List<TextField> fields = Arrays.asList(
                vendorAddTextField, vendorNameTextField, VATNumberTextField, amountTextField,
                priceTextField, VATTextField, totalPriceTxtField, productNameTxtField
        );
        fields.forEach(TextField::clear);
        datePacker.getEditor().clear();
    }

    public float StringConverterFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            showAlert("من فضلك ادخل رقما صحيحا"); // Notify user about the invalid input
            return 0; // Return a default value
        }
    }

/* Add Listener on text fields to enforce user add only digits */
private boolean alertShown = false; // Flag to control alert display
    public void validateIntegerInput(String newValue, TextField textField, String oldValue) {
        // Check if the input contains letters or multiple decimal points
        boolean isInvalidInput = false;

        // Check for letters
        if (newValue.matches(".*[a-zA-Zء-ي].*")) {
            if (!alertShown) {
                alertShown = true; // Set flag to true
                showAlert("من فضـــلك ادخـــل هنــا ارقـــام فقــط");
                    textField.setText(oldValue); // Restore the previous valid value
                    textField.clear();
                    textField.positionCaret(oldValue.length()); // Move caret to the end

            }
            isInvalidInput = true;
        }
        // Check for multiple dots
        else if (newValue.indexOf('.') != newValue.lastIndexOf('.')) {
            if (!alertShown) {
                alertShown = true; // Set flag to true
                showAlert("من فضـــلك ادخـــل هنــا عــلامة عــشرية واحــدة فقــط");
                textField.setText(oldValue); // Restore the previous valid value
                textField.positionCaret(oldValue.length()); // Move caret to the end
            }
            isInvalidInput =true;
        }

        // If the input is invalid, show the alert and restore old value

        if (!isInvalidInput) {
            alertShown = false; // Reset the flag if input is valid
        }
    }

    public void validateFloatInput() {
        // List of text fields to validate
        List<TextField> textFields = Arrays.asList(
                VATNumberTextField,
                totalPriceTxtField,
                VATTextField,
                priceTextField,
                amountTextField
        );

        // Set up listeners for each text field
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                validateIntegerInput(newValue, textField, oldValue);
            });
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.setHeaderText("معلـــومات خاطئـــة");
        alert.setTitle("إدخـــال خـــاطئ");
        alert.showAndWait();
    }



    private void setupTooltips() {
        // Tooltip for Vendor Name
        Tooltip vendorNameTooltip = new Tooltip("Enter the vendor's name");
        vendorNameTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(vendorNameTextField, vendorNameTooltip);

        // Tooltip for Vendor Address
        Tooltip vendorAddressTooltip = new Tooltip("Enter the vendor's address");
        vendorAddressTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(vendorAddTextField, vendorAddressTooltip);

        // Tooltip for VAT Number
        Tooltip vatNumberTooltip = new Tooltip("Enter the vendor's VAT number");
        vatNumberTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(VATNumberTextField, vatNumberTooltip);

        // Tooltip for Amount
        Tooltip amountTooltip = new Tooltip("Enter the amount of products");
        amountTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(amountTextField, amountTooltip);

        // Tooltip for Price
        Tooltip priceTooltip = new Tooltip("Enter the price per unit");
        priceTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(priceTextField, priceTooltip);

        // Tooltip for VAT Amount
        Tooltip vatTooltip = new Tooltip("Enter the VAT amount");
        vatTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(VATTextField, vatTooltip);

        // Tooltip for Total Price
        Tooltip totalPriceTooltip = new Tooltip("Enter the total price");
        totalPriceTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(totalPriceTxtField, totalPriceTooltip);

        // Tooltip for Product Name
        Tooltip productNameTooltip = new Tooltip("Enter the name of the product");
        productNameTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(productNameTxtField, productNameTooltip);
    }





}
