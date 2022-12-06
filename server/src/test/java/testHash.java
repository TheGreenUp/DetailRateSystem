import com.bsuir.green.common.utils.Helper;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class testHash {


    public static void main(String[] args) {
        String password = "1111";
        try {
            System.out.println(Helper.getInstance().getPasswordHash(password));
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }
}
