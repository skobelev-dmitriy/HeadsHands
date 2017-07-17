package rf.digworld.headhands.util;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static boolean isValidEmail(String email){
        String expression = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";

        Pattern pattern = Pattern.compile( expression);
        Matcher matcher = pattern.matcher(email);
        boolean match=matcher.matches();
        return match ;
        }
    public static boolean isValidPassword(String password){
        CharSequence inputStr = password;
        String expression = "(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*[\\d])\\S{6,}";//1 Заглавная, 1 строчная, 1 цифра, более 6 знаков

        Pattern pattern = Pattern.compile(expression );
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

}
