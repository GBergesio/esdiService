package esdi.Services.utils;

import com.sun.istack.NotNull;

import java.util.Random;

public final class UserUtils {

    @NotNull
    public static String generatePassword(int len){
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                +"jklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }


}
