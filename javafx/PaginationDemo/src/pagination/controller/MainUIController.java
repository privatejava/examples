/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagination.controller;

import java.net.URL;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import pagination.controller.MainUIController.SortColumn;
import pagination.entity.Transaction;

/**
 * FXML Controller class
 *
 * @author Narayan
 */
public class MainUIController implements Initializable {

    @FXML
    private TableView<Transaction> table;

    @FXML
    private TableColumn<Transaction, String> productCol;

    @FXML
    private TableColumn<Transaction, Double> qtyCol;

    @FXML
    private TableColumn<Transaction, Integer> snCol;

    @FXML
    private TableColumn<Transaction, Date> dateCol;

    @FXML
    private StackPane pagePanel;

    private ObservableList<Transaction> transactions;

    @FXML
    private Pagination pagination;

    private IntegerProperty limit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sortListener();
        limit = new SimpleIntegerProperty(10);
        transactions = FXCollections.observableArrayList();
        for (int i = 0; i < 51; i++) {
            String product = (char) ((int) (Math.random() * 26) + 65) + "-" + (int) (Math.random() * 10000);
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(GregorianCalendar.DAY_OF_YEAR, (int) (Math.random() * 360));
            double qty = Math.ceil(Math.random() * 10);
            transactions.add(new Transaction(i + 1, product, cal.getTime(), qty));
        }
        //System.out.println(transactions.size());

        pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                changeTableView(newValue.intValue(), limit.get());
            }

        });

        limit.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                changeTableView(pagination.getCurrentPageIndex(), newValue.intValue());
            }

        });
//        pagePanel.getChildren().add(pagination);
        StackPane.setAlignment(pagination, Pos.CENTER);

        //pagination.getStyleClass().add(pagination.STYLE_CLASS_BULLET);
        setValueFactory();
        init();

    }

    /**
     * This is the function for setting up the visual representation on the
     * startup of the program
     */
    public void init() {
        resetPage();
        pagination.setCurrentPageIndex(0);
        changeTableView(0, limit.get());
    }

    /**
     * This function resets the pagination pagecount
     */
    public void resetPage() {
        pagination.setPageCount((int) (Math.ceil(transactions.size() * 1.0 / limit.get())));
    }

    /**
     * This function is responsible for changing the TableView according to the
     * index and limit provided
     *
     * @param index
     * @param limit
     */
    public void changeTableView(int index, int limit) {
        int newIndex = index * limit;

        List<Transaction> trans = transactions.subList(Math.min(newIndex, transactions.size()), Math.min(transactions.size(), newIndex + limit));
        table.getItems().clear();
        table.setItems(null);
        ObservableList<Transaction> l = FXCollections.observableArrayList();
        table.setItems(l);
        for (Transaction t : trans) {
            l.add(t);
        }
        System.out.println("Size:" + l.size());

    }

    /**
     * This function is called from the FXML when the user enters new limit
     *
     * @param evt
     */
    @FXML
    public void changeLimit(ActionEvent evt) {

        TextField txt = (TextField) evt.getSource();
        if (txt != null) {
            try {
                int i = Integer.parseInt(txt.getText());

                limit.set(i);

                resetPage();
                System.out.println(table.getItems().size());
                //pagination.setCurrentPageIndex(Math.min(pagination.getCurrentPageIndex(),table.getItems().size()/limit.get()));
            } catch (NumberFormatException nfe) {
                System.err.println("NFE error");
            }
        }

    }

    /**
     * Sorts whole list using preferred column sorting and sort type
     *
     * @param col the column which is to be sorted by
     * @param type ascending or descending sort type
     */
    private void sort(final SortColumn col, final TableColumn.SortType type) {

        transactions.sort(new Comparator<Transaction>() {

            @Override
            public int compare(Transaction o1, Transaction o2) {
                switch (col) {
                    case QTY:
                        return (TableColumn.SortType.ASCENDING == type) ? (o1.quantityProperty().get() > o2.quantityProperty().get() ? 1 : -1) : (o1.quantityProperty().get() < o2.quantityProperty().get() ? 1 : -1);
                    case SN:
                        return (TableColumn.SortType.ASCENDING == type) ? (o1.snProperty().get() > o2.snProperty().get() ? 1 : -1) : (o1.snProperty().get() < o2.snProperty().get() ? 1 : -1);
                    case DATE:
                        return (TableColumn.SortType.ASCENDING == type) ? (o1.dateProperty().get().getTime() > o2.dateProperty().get().getTime() ? 1 : -1) : (o1.dateProperty().get().getTime() < o2.dateProperty().get().getTime() ? 1 : -1);
                    case CODE:
                        return (TableColumn.SortType.ASCENDING == type) ? o1.productCodeProperty().get().compareTo(o2.productCodeProperty().get()) : o2.productCodeProperty().get().compareTo(o1.productCodeProperty().get());
                }
                return o1.quantityProperty().get() > o2.quantityProperty().get() ? -1 : 1;
            }
        });
        changeTableView(pagination.getCurrentPageIndex(), limit.get());
    }

    /**
     * This function helps to set the cellValueFactory of all column
     */
    private void setValueFactory() {
        /*snCol.setCellFactory(new Callback<TableColumn<Transaction,Integer>,TableCell<Transaction,Integer>>(){

         @Override
         public TableCell<Transaction, Integer> call(TableColumn<Transaction, Integer> param) {
         TableCell cell = new TableCell(){
         protected void updateItem(Transaction t, boolean bln) {
         super.updateItem(t, bln);

         setText(""+t.snProperty().get());
         }
         };

         return cell;
         }

         });*/
        snCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        snCol.setCellValueFactory(new Callback<CellDataFeatures<Transaction, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue call(CellDataFeatures<Transaction, Integer> param) {
                if (table.getItems().contains(param.getValue()) && param.getValue() != null) {
                    return param.getValue().snProperty();
                }
                return null;
            }
        });

        productCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        productCol.setCellValueFactory(new Callback<CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Transaction, String> param) {
                return param.getValue().productCodeProperty();
            }
        });

        dateCol.prefWidthProperty().bind(table.widthProperty().divide(5).add(table.widthProperty().divide(5)));
        dateCol.setCellValueFactory(new Callback<CellDataFeatures<Transaction, Date>, ObservableValue<Date>>() {

            @Override
            public ObservableValue<Date> call(CellDataFeatures<Transaction, Date> param) {
                return param.getValue().dateProperty();
            }

        });
        qtyCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        qtyCol.setCellValueFactory(new Callback<CellDataFeatures<Transaction, Double>, ObservableValue<Double>>() {

            @Override
            public ObservableValue call(CellDataFeatures<Transaction, Double> param) {
                return param.getValue().quantityProperty();
            }

        });

    }

    private void sortListener() {
        qtyCol.sortTypeProperty().addListener(new ChangeListener<TableColumn.SortType>() {

            @Override
            public void changed(ObservableValue<? extends TableColumn.SortType> observable, TableColumn.SortType oldValue, TableColumn.SortType newValue) {
                sort(SortColumn.QTY, newValue);
            }
        });
        dateCol.sortTypeProperty().addListener(new ChangeListener<TableColumn.SortType>() {

            @Override
            public void changed(ObservableValue<? extends TableColumn.SortType> observable, TableColumn.SortType oldValue, TableColumn.SortType newValue) {
                sort(SortColumn.DATE, newValue);
            }
        });
        productCol.sortTypeProperty().addListener(new ChangeListener<TableColumn.SortType>() {

            @Override
            public void changed(ObservableValue<? extends TableColumn.SortType> observable, TableColumn.SortType oldValue, TableColumn.SortType newValue) {
                sort(SortColumn.CODE, newValue);
            }
        });
        snCol.sortTypeProperty().addListener(new ChangeListener<TableColumn.SortType>() {

            @Override
            public void changed(ObservableValue<? extends TableColumn.SortType> observable, TableColumn.SortType oldValue, TableColumn.SortType newValue) {
                sort(SortColumn.SN, newValue);
            }
        });
    }

    enum SortColumn {

        SN, CODE, QTY, DATE

    }
}
