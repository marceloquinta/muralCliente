package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validar o formato da data com expressão regular DD/MM/YYYY
 */
public class DateValidator {

    private static final int DIA = 1;
    private static final int MES = 2;
    private static final int ANO = 3;

    /**
     * Anos terminados com numero 4. ex: 2004, 2014 ...
     */
    private static final int FINAL_QUATRO = 4;

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN =
            "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    public DateValidator() {
        pattern = Pattern.compile(DATE_PATTERN);
    }

    /**
     * Validar o formato da data com expressão regular
     *
     * @param date data para verificação
     * @return true data com formato valido, false data com formato inválido
     */
    public boolean validate(final String date) {

        matcher = pattern.matcher(date);

        if (matcher.matches()) {

            matcher.reset();

            if (matcher.find()) {

                String dia = matcher.group(DIA);
                String mes = matcher.group(MES);
                int ano = Integer.parseInt(matcher.group(ANO));

                return validarData(dia, mes, ano);

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean validarData(String dia, String mes, int ano) {
        if ("31".equals(dia) && contem30Dias(mes)) {
            // apenas os meses 1,3,5,7,8,10,12 tem 31 dias
            return false;
        } else if (mesFevereiro(mes)) {
            return anoBissexto(ano, dia);
        } else {
            return true;
        }
    }

    private static boolean anoBissexto(int ano, String dia) {
        if ((ano % FINAL_QUATRO) == 0) {
            return !("30".equals(dia) || "31".equals(dia));
        } else {
            return !("29".equals(dia) || "30".equals(dia) || "31".equals(dia));
        }
    }

    private static boolean contem30Dias(String mes) {
        boolean intSemZero = "4".equals(mes) || "6".equals(mes) || "9".equals(mes);
        boolean intComZero = "04".equals(mes) || "06".equals(mes) || "09".equals(mes);

        return intSemZero || intComZero || "11".equals(mes);
    }

    private static boolean mesFevereiro(String mes) {
        return "2".equals(mes) || "02".equals(mes);
    }
}
