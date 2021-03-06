package li.people.record;

import java.util.List;

import li.annotation.Bean;
import li.annotation.Table;
import li.dao.Page;
import li.dao.Record;
import li.util.Convert;
import li.util.Verify;

@Bean
@Table("t_account")
public class Account extends Record<Account, Integer> {
    private static final long serialVersionUID = 3084398087892682872L;

    public List<Account> list(String key, Page page) {
        String sql = "SELECT a.*,r.name role_name " + "FROM t_account a " + "LEFT JOIN t_role r ON a.role_id=r.id WHERE 1=1";
        if (!Verify.isEmpty(key)) {
            sql += " AND(a.username LIKE '%" + key + "%' OR a.email LIKE '%" + key + "%')";
        }
        return super.list(page, sql);
    }

    public Account findByUsername(String username) {
        return find("WHERE username=?", username);
    }

    public Account findByEmail(String email) {
        return find("WHERE email=?", email);
    }

    public Account login(Account account) {
        String sql = "WHERE (username=? OR email=?) AND password=?";
        return find(sql, account.get("username"), account.get("email"), Convert.toMD5(account.get("password")));
    }

    public Account md5PasswordIfNotNull() {
        String password = this.get(String.class, "password");
        if (!Verify.isEmpty(password)) {
            this.set("password", Convert.toMD5(password));
        }
        return this;
    }
}