package br.com.casanova.apispring.parsers;

public class DoubleConverter {
    public static Double doubleParser(String StringNumber){
        if (StringNumber ==null || StringNumber.length() == 0) return 0D;
        String reformedNumber = StringNumber.replaceAll(",",".");

        if (isNumeric(reformedNumber)) return Double.parseDouble(reformedNumber);
        return 0D;
    }

    public static boolean isNumeric(String number){
        if (number == null || number.length() == 0) return false;
        String reformedNumber = number.replaceAll(",",".");
        return reformedNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
