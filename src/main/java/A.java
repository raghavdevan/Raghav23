import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {
    public static void main(String[] args) {

        // PasswordEncoder e = new BCryptPasswordEncoder();
        // System.out.println(e.encode("Raghav@5"));

        String encodedPwd = BCrypt.hashpw("Raghav@5", BCrypt.gensalt(10));
        System.out.println(encodedPwd);

    }
}
