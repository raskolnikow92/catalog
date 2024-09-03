import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    @Serial
    public static long serialVersionUID = 2L;
    private String imgAddress, name;
    private int price;
    public CartItem(String imgAddress, String name, int price){
        this.imgAddress = imgAddress;
        this.name = name;
        this.price = price;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return price == cartItem.price && Objects.equals(imgAddress, cartItem.imgAddress) && Objects.equals(name, cartItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgAddress, name, price);
    }
}
