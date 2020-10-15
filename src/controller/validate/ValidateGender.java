package controller.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateGender implements IValidate{

    public ValidateGender() {

    }

    @Override
    public boolean validate(String regex) {
        Pattern pattern = Pattern.compile("^[y|Y|n|N]$");
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
