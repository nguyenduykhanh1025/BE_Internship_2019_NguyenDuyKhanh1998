package com.kunlez.bookstore.common;

import com.kunlez.bookstore.configurations.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommonMethot {

    public static String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public static String getUserName(String token, TokenProvider tokenProvider) {
        String value = token.split(" ")[token.split(" ").length - 1];

        return tokenProvider.getUsernameFromToken(value);
    }

    public static Set<String> getAllRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
    }

    public static Date formatDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
        return formatter.parse(formatter.format(date));
    }
    public static String getStringStandard(String str) {

        str = str.replace(".", ". ");
        str = str.replace(",", ", ");
        str = str.replace("!", "! ");
        str = str.replace("?", "? ");

        while (str.replace("  ", " ") != str) {
            str = str.replace("  ", " ");
        }

        StringBuffer strResult = new StringBuffer(str);

        if (strResult.charAt(0) > 96) {
            strResult.setCharAt(0, (char) (strResult.charAt(0) - 32));
        }

        StringBuffer strTemp = new StringBuffer(strResult);
        for (int i = 1; i < strResult.length(); ++i) {
            if (strResult.charAt(i) == '.' || strResult.charAt(i) == '?' || strResult.charAt(i) == '!') {

                if (strResult.charAt(i - 1) == ' ') {
                    strResult.delete(i - 1, i);
                    i--;
                }
                if (strResult.charAt(i += 2) > 96) {
                    strResult.setCharAt(i, (char) (strResult.charAt(i) - 32));
                }
            }
        }

        return strResult.toString();
    }

    public static boolean isFormatEmail(String email){
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
}
