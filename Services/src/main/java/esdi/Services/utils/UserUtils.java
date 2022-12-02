package esdi.Services.utils;

import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;
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

    private static List<Integer> ordenercreadas = new ArrayList<>();

    public static int newOrder(){

            int order = 13000;
            do {
                order++;
            }while (ordenercreadas.contains(order));
            ordenercreadas.add(order);
            return order;

    }




}




//    public static int newOrder(int start){
//
//        if (start > 1){
//            int order = 13000;
//            do {
//                order++;
//            }while (ordenercreadas.contains(order));
//            ordenercreadas.add(order);
//            return order;
//        } else {
//        return 1;
//        }
//    }
