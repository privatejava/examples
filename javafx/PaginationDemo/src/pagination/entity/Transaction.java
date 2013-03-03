/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagination.entity;

import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Narayan
 */
public class Transaction {
    
    private IntegerProperty sn;    
    private StringProperty product_code;
    private ObjectProperty<Date> date;
    private DoubleProperty qty;

    public Transaction(int i,String productcode, Date dt, double q) {
        sn = new SimpleIntegerProperty(i);
        product_code = new SimpleStringProperty(productcode);
        date = new SimpleObjectProperty<Date>(dt);
        qty = new SimpleDoubleProperty(q);
        
    }
    public Transaction(int i,String productcode, Date dt) {
        this(i,productcode,dt,0);
    }
    public Transaction(int i,String productcode) {
        this(i,productcode,null,0);
    }

  
    public IntegerProperty snProperty() {
        return sn;
    }
    
    public StringProperty productCodeProperty() {
        return product_code;
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

  
    public DoubleProperty quantityProperty() {
        return qty;
    }

    
    
    
    
    
}
