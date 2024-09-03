import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private Map<String, Map<CartItem, Integer>> userCarts;
    public CartManager(Map<String, Map<CartItem, Integer>> userCarts){
        this.userCarts = userCarts;
    }
    public CartManager(){
        this.userCarts = new HashMap<>();
    }

    public Map<String, Map<CartItem, Integer>> getUserCarts() {
        return userCarts;
    }
    public Map<CartItem, Integer> getUserCart(String email){
        return userCarts.get(email);
    }
    public void addToCart(String email, CartItem item){
        if (userCarts.containsKey(email)) {
            Map<CartItem, Integer> userCart = getUserCart(email);
            userCart.put(item, userCart.getOrDefault(item, 0) + 1);
        }else{
            Map<CartItem, Integer> userCart = new HashMap<>();
            userCart.put(item,1);
            userCarts.put(email, userCart);
        }

    }

    /*
    public static void main(String[] args) {
        // Create instances of UserManager and CartManager
        UserManager userManager = new UserManager();
        CartManager cartManager = new CartManager();

        // Create and register a user
        User user = new User("user1@example.com", "password1", "John", "Doe");
        userManager.registerUser(user);

        // Login the user
        User loggedInUser = userManager.loginUser(new User("user1@example.com", "password1", null, null));
        System.out.println("Logged in user: " + loggedInUser.getEmail());

        // Create cart items
        CartItem item1 = new CartItem("img1.jpg", "Item 1", 10);
        CartItem item2 = new CartItem("img2.jpg", "Item 2", 20);

        // Add items to the user's cart
        cartManager.addToCart("user1@example.com", item1);
        cartManager.addToCart("user1@example.com", item2);

        // Retrieve and print the user's cart
        Map<CartItem, Integer> userCart = cartManager.getUserCart("user1@example.com");
        System.out.println("User's cart:");
        for (Map.Entry<CartItem, Integer> entry : userCart.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }
    */
}
