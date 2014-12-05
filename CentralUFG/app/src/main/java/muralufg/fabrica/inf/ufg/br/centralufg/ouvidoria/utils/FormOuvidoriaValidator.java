package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.utils;


import android.widget.EditText;

/**
 * Validador do formulário da ouvidoria
 */
public class FormOuvidoriaValidator {

    private boolean formValid;

    private FormOuvidoriaValidator(Builder builder) {
        this.formValid = builder.formValid;
    }

    public boolean isValid() {
        return formValid;
    }

    public static class Builder {
        boolean formValid = true;

        /**
         * Validar formato do campo de data
         *
         * @param editText
         * @return
         */
        public Builder data(EditText editText) {
            setFormValid(dataValidator(editText));
            return this;
        }

        /**
         * Validar o tamanho mínimo que o campo deve ter
         *
         * @param editText
         * @param tamanho
         * @return
         */
        public Builder minimo(EditText editText, int tamanho) {
            setFormValid(mininoValidator(editText, tamanho));
            return this;
        }

        private void setFormValid(boolean formValid) {

            if (this.formValid == false) {
                // Fomulário contem erro, continua checando os outros campos,
                // e o formulário é inválido
                return;
            } else if (formValid) {
                this.formValid = true;
            } else {
                this.formValid = false;
            }
        }

        public FormOuvidoriaValidator build() {
            return new FormOuvidoriaValidator(this);
        }
    }

    /**
     * Validar o tamanho mínimo que o campo deve ter
     *
     * @param editText
     * @param tamanhoMinimo
     * @return
     */
    private static boolean mininoValidator(EditText editText, int tamanhoMinimo) {
        String text = editText.getText().toString().trim();

        if (text.length() >= tamanhoMinimo) {
            editText.setError(null);
            return true;
        } else {
            editText.setError(String.format("O campo deve conter, no mínimo, %d caracteres", tamanhoMinimo));
            return false;
        }
    }

    /**
     * Validar uma data
     *
     * @param editText deve estar no seguinte padrão: dia/mes/ano - 10/10/2010
     * @return true data com formato valido, false data com formato inválido
     */
    private static boolean dataValidator(EditText editText) {
        String text = editText.getText().toString().trim();

        DateValidator dateValidator = new DateValidator();
        if (dateValidator.validate(text)) {
            editText.setError(null);
            return true;
        } else {
            editText.setError("Preencha com: DD/MM/AAAA");
            return false;
        }
    }
}
