package com.bill.app.view;

import com.bill.app.model.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class billMainController {
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
        /*Setting data*/
        products.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        unitPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        VATAmount.setCellValueFactory(cellData -> cellData.getValue().VATAmountProperty());
        totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
        mainTable.setItems(model);
        btnAction();

    }

    @FXML
    protected void btnAction() {

        addbtn.setOnAction(actionEvent -> {
            DataModel dataModel = new DataModel();
            dataModel.setVendorAddress(vendorAddTextField.getText());
            dataModel.setVendorName(vendorNameTextField.getText());
            dataModel.setVATNumber(VATNumberTextField.getText());
            dataModel.setAmount(amountTextField.getText());
            dataModel.setPrice(priceTextField.getText());
            dataModel.setVATAmount(VATTextField.getText());
            dataModel.setTotalPrice(totalPriceTxtField.getText());
            dataModel.setProductName(productNameTxtField.getText());
            System.out.println(productNameTxtField.getText());
            model.add(dataModel);
            clear();
        });

    }
    private void clear()
    {
        vendorAddTextField.clear();
        vendorNameTextField.clear();
        VATNumberTextField.clear();
        amountTextField.clear();
        priceTextField.clear();
        VATTextField.clear();
        totalPriceTxtField.clear();
        productNameTxtField.clear();
        datePacker.getEditor().clear();

    }
    protected void generatePdf(){

    }
}