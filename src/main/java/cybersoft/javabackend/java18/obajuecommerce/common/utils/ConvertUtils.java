package cybersoft.javabackend.java18.obajuecommerce.common.utils;

import lombok.experimental.UtilityClass;

import java.text.Normalizer;
import java.util.regex.Pattern;

@UtilityClass
public class ConvertUtils {

    public static String convertStringToURL(String string) {
        try {
            String temp = Normalizer.normalize(string, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp)
                    .replaceAll("")
                    .toLowerCase()
                    .replace(" ", "-")
                    .replace("đ", "d");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
