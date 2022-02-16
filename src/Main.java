import controllers.ProductController;
import controllers.UserController;
import data.DB;
import data.postgres.Postgres;
import repositories.products.IProductRepository;
import repositories.products.ProductRepository;
import repositories.users.IUserRepository;
import repositories.users.UserRepository;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        DB db = Postgres.getInstance();
        IUserRepository userRepo = new UserRepository(db);
        IProductRepository productRepo = new ProductRepository(db);

        UserController userCtrl = new UserController(userRepo);
        ProductController productCtrl = new ProductController(productRepo);

        MyApplication app = new MyApplication(userCtrl, productCtrl);

        app.start();
        db.close();
    }
}
