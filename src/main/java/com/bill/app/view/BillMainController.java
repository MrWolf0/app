package com.bill.app.view;

import com.bill.app.model.DataModel;
import com.bill.app.model.HandelDataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class BillMainController {

    HandelDataBaseConnection handelDataBaseConnection = new HandelDataBaseConnection();
    @FXML
    private GridPane summaryGridPain;
    @FXML
    private Label totalityLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label priceTotalLabel;
    @FXML
    private Label totalVatAmountLabel;
    @FXML
    private Label priceWithoutVatLabel;
    @FXML
    private Label onlyVatPriceLabel;
    @FXML
    private Label totalPriceWithVatLabel;
    @FXML
    private Label oneVatPriceLabel;
    @FXML
    private DatePicker datePacker;
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
    private TableColumn<DataModel, String> totalPriceWithoutVat;
    @FXML
    private TableColumn<DataModel, String> VatPrice;
    @FXML
    private TableColumn<DataModel, String> wholeTotalPrice;
    @FXML
    private TableColumn<DataModel, String> oneVatPrice;
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
    private TextField priceTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private VBox vbox;
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
    private final ObservableList<DataModel> model = FXCollections.observableArrayList();
    /* Add Listener on text fields to enforce user add only digits */
    private boolean alertShown = false; // Flag to control alert display

    public void initialize() {
        setDefaultLocaleToArabic();
        setHijriDateToDatePicker();
        setFonts();
        setupTooltips();
        validateFloatInput();
        btnAction();


    }

    private void setFonts() {
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
        productNameLabel.setFont(customFontRDL);
        totalityLabel.setFont(customFontRDL);
        quantityLabel.setFont(customFontRDL);
        priceTotalLabel.setFont(customFontRDL);
        totalVatAmountLabel.setFont(customFontRDL);
        priceWithoutVatLabel.setFont(customFontRDL);
        onlyVatPriceLabel.setFont(customFontRDL);
        totalPriceWithVatLabel.setFont(customFontRDL);

    }

    private void setTableColumns() {
        /*product name property*/
        products.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        /*amount property*/
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asString());
        /*price property*/
        unitPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString());
        /*string property to 15% */
        VATAmount.setCellValueFactory(cellData -> cellData.getValue().VATAmountProperty());
        /*the price without tax it will be the mul of amount and price such as 3 products and the price for each one
         * is 5 so the total will be 5 */
        totalPriceWithoutVat.setCellValueFactory(cellData -> cellData.getValue().totalPriceWithoutVat().asString());
        /* here is the amount of tax should be paid such as if the price 5 the tax will be .75 because 5*.15 = .75*/
        VatPrice.setCellValueFactory(cellData -> cellData.getValue().VATPriceProperty().asString());
        /* the total price after adding tax */
        wholeTotalPrice.setCellValueFactory(cellData -> cellData.getValue().getTotalPriceWithVatProperty().asString());
        /*tax price for only one unit */
        oneVatPrice.setCellValueFactory(cellData -> cellData.getValue().oneVATPriceProperty().asString());
        checkNullValue();
        mainTable.setItems(model);
    }

    @FXML
    protected void btnAction() {
        addbtn.setOnAction(actionEvent -> {
            handelDataBaseConnection.createDatabaseFolder("E:\\App");
            handelDataBaseConnection.creatingTable();
            /*define a new object from dataModel every time we pressed the button and will be assigned to
             * each column in the table */
            DataModel dataModel = new DataModel();
            /*Setting the data to use after that to fill the tableView */
            /*Getting data of textField that has label called (عنوان المشترك) then set to proper property
             * at the model*/
            dataModel.setVendorAddress(vendorAddTextField.getText());
            /*Getting data of textField that has label called (اسم المشترك) then set to proper property
             * at the model*/
            dataModel.setVendorName(vendorNameTextField.getText());
            /*Getting data of textField that has label called (رقم الضريبة) then converting it to float and
             * setting to the proper property at the model */
            dataModel.setVATNumber(StringConvertToFloat(VATNumberTextField.getText()));
            /* This line responsible to assign product name value in the column called (المنتجات) */
            dataModel.setProductName(productNameTxtField.getText());
            /* This line responsible to assign amount value in the column called (الكمية) */
            dataModel.setAmount(StringConvertToFloat(amountTextField.getText()));
            /* This line responsible to assign unit price value in the column called (سعر الوحدة) */
            dataModel.setPrice(StringConvertToFloat(priceTextField.getText()));
            /* This line responsible to assign string value in the column called (ضريبة القيمة المضافة) */
            dataModel.setVATAmount("15%");
            /* This line responsible to assign total value without tax in the column called  (المبلغ غير شامل ضريبة القيمة المضافة) */
            dataModel.setTotalPriceWithoutVat(StringConvertToFloat(priceTextField.getText()) * StringConvertToFloat(amountTextField.getText()));
            /* This line is responsible to set the date value selected from the DatePicker to the proper property in the model */
            dataModel.setDate(Date.valueOf(datePacker.getValue()).toString());
            /* Assigning data to the table by setting the table columns */
            setTableColumns();
            /* Adding the data model to the list */
            model.add(dataModel);
            /* Updating the table in the database with the new data */
            handelDataBaseConnection.updateTable(dataModel);
            /* Clearing the input fields after data is processed */
            clearFields();
            /*Sum values that will update gridPain labels */
            summition();
        });

    }

    /*A helper methode to clear textFields after click the button*/
    public void clearFields() {
        /**/
        List<TextField> fields = Arrays.asList(vendorAddTextField, vendorNameTextField, VATNumberTextField, amountTextField, priceTextField, productNameTxtField);
        fields.forEach(TextField::clear);
        datePacker.getEditor().clear();
    }

    /* This method converts a given String to a float */
    public float StringConvertToFloat(String str) {
        try {
            /* Attempt to parse the string into a float */
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            /* If the string cannot be parsed, show an alert to the user indicating missing or invalid input */
            showAlert("من فضـــلك اضـــف جميـــع القـــيم");
            /* Call method to handle null or invalid values Notify user about the invalid input*/
            checkNullValue();
            return 0;
        }
    }

    public void validateIntegerInput(String newValue, TextField textField, String oldValue) {
        /*Check if the input contains letters or multiple decimal points*/
        boolean isInvalidInput = false;

        /*Check for letters ar and en*/
        if (newValue.matches(".*[a-zA-Zء-ي].*")) {
            if (!alertShown) {
                alertShown = true;
                showAlert("من فضـــلك ادخـــل هنــا ارقـــام فقــط");
                textField.setText(oldValue);
                textField.clear();
                textField.positionCaret(oldValue.length());

            }
            isInvalidInput = true;
        }
        /* Check for multiple dots*/
        else if (newValue.indexOf('.') != newValue.lastIndexOf('.')) {
            if (!alertShown) {
                alertShown = true;
                showAlert("من فضـــلك ادخـــل هنــا عــلامة عــشرية واحــدة فقــط");
                /*Restore the previous valid value*/
                textField.setText(oldValue);
                /* Move caret to the end*/
                textField.positionCaret(oldValue.length());
            }
            isInvalidInput = true;
        }

        /* If the input is invalid, show the alert and restore old value*/

        if (!isInvalidInput) {
            alertShown = false;
        }
    }

    public void validateFloatInput() {
        List<TextField> textFields = Arrays.asList(VATNumberTextField, priceTextField, amountTextField);

        /*Set up listeners for each text field*/
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

        // Tooltip for Product Name
        Tooltip productNameTooltip = new Tooltip("Enter the name of the product");
        productNameTooltip.setShowDelay(Duration.ZERO); // Show immediately
        Tooltip.install(productNameTxtField, productNameTooltip);
    }

    /* Method to check if any required text fields are empty */
    public void checkNullValue() {
        /* Create a list of text fields to validate */
        List<TextField> textFields = Arrays.asList(vendorAddTextField, VATNumberTextField, vendorNameTextField, amountTextField, priceTextField, productNameTxtField);

        /* Iterate through each text field in the list */
        for (TextField textField : textFields) {
            /* Check if the text field is empty after trimming whitespace */
            if (textField.getText().trim().isEmpty()) {
                /* Request focus on the empty text field */
                textField.requestFocus();
                /* Exit the program if any field is found empty */
                System.exit(0);
            }
        }
    }

    /* Method to calculate the sums of various financial fields from the data model */
    public void summition() {
        /* Initialize sums for different financial properties */
        float amountSum = 0; // Sum of amounts
        float priceSum = 0; // Sum of prices
        float priceWithoutVatSum = 0; // Sum of prices without VAT
        float vatSum = 0; // Sum of VAT amounts
        float totalSum = 0; // Total sum including VAT
        float totalVatOneUnitPrice = 0; // Sum of VAT prices per unit

        /* Iterate through each DataModel in the model list */
        for (DataModel dataModel : model) {
            /* Accumulate the values from the DataModel properties */
            amountSum += dataModel.amountProperty().getValue(); // Add amount to the total
            priceSum += dataModel.priceProperty().getValue(); // Add price to the total
            priceWithoutVatSum += dataModel.totalPriceWithoutVat().getValue(); // Add price without VAT to the total
            vatSum += dataModel.VATPriceProperty().getValue(); // Add VAT price to the total
            totalSum += dataModel.getTotalPriceWithVatProperty().getValue(); // Add total price with VAT to the total
            totalVatOneUnitPrice += dataModel.oneVATPriceProperty().getValue(); // Add VAT price per unit to the total

            /* Update the labels with the calculated sums */
            quantityLabel.setText(String.valueOf(amountSum)); // Display the total amount
            priceTotalLabel.setText(String.valueOf(priceSum)); // Display the total price
            priceWithoutVatLabel.setText(String.valueOf(priceWithoutVatSum)); // Display the total price without VAT
            onlyVatPriceLabel.setText(String.valueOf(vatSum)); // Display the total VAT amount
            totalPriceWithVatLabel.setText(String.valueOf(totalSum)); // Display the total price including VAT
            oneVatPriceLabel.setText(String.valueOf(totalVatOneUnitPrice)); // Display the total VAT price per unit
        }
    }

    private void setDefaultLocaleToArabic() {
        Locale.setDefault(new Locale("ar", "SA"));
    }

    private void setHijriDateToDatePicker() {
        // Get today's date in Hijri calendar
        HijrahDate hijrahDate = HijrahDate.now();

        // Set the DatePicker value to the current Hijri date converted to LocalDate
        LocalDate localDate = LocalDate.from(hijrahDate);
        datePacker.setValue(localDate);

        // Create a custom format for the Hijri date
        DateTimeFormatter hijriFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("ar-SA"));

        // Display the formatted Hijri date in a Label or as the prompt text
        String formattedHijriDate = hijriFormatter.format(localDate);
        datePacker.getEditor().setText(formattedHijriDate);  // Optionally, display it directly in the DatePicker's text field
    }


}
