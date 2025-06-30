// ProductObserver.java
package Controller;

import Model.Product;
import java.util.List;

public interface ProductObserver {
    void updateProducts(List<Product> products);
}