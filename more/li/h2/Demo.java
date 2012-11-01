package li.h2;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import li.ioc.Ioc;

public class Demo {

    public static void main(String[] args) {
        Account dao = Ioc.get(Account.class);

        List<Account> list = dao.list(null);
        System.out.println(list);
    }

    public static void insert() {
        final Account dao = Ioc.get(Account.class);

        for (int i = 0; i < 1000; i++) {
            Account account = new Account().set("USERNAME", "li" + System.currentTimeMillis()).set("PASSWORD", "wode").set("EMAIL", "limingwei@mail.com");
            System.out.println(dao.save(account));
            System.out.println(account.get("ID"));
        }
    }

    public static void create_table() {
        String sql = "CREATE TABLE t_account" + //
                "(id int PRIMARY KEY AUTO_INCREMENT," + //
                "username varchar(255) UNIQUE NOT NULL ," + //
                "password varchar(255) NOT NULL," + //
                "email varchar(255) NOT NULL," + //
                "status int NOT NULL DEFAULT 1)";
        try {
            System.out.println(Ioc.get(DataSource.class, "h2").getConnection().prepareStatement(sql).executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}