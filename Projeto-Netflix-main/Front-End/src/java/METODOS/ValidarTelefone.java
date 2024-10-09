package METODOS;

import java.util.regex.Pattern;

public class ValidarTelefone {
  public boolean validarTelefone(String telefone) {
    boolean tel=false;
    Pattern p = Pattern.compile("^\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$");
    if (p.matcher(telefone).matches()) {
            tel=true;
    }else{
        tel=false;    
    }
     return tel;
  }
}