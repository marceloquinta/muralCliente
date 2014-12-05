package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.utils;


import android.widget.EditText;

/**
 * Validador do formulário da ouvidoria
 */
public class FormOuvidoriaValidator {

    /**
     * Mínimo de caracteres aceitos para o título
     */
    public static final int MIN_TITULO = 5;
    /**
     * Mínimo de caracteres aceitos para a descrição
     */
    public static final int MIN_DESCRICAO = 10;

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

            if (!isFormValid()) {
                // Fomulário contem erro, continua checando os outros campos,
                // e o formulário é inválido
                return;
            } else if (formValid) {
                this.formValid = true;
            } else {
                this.formValid = false;
            }
        }

        public boolean isFormValid() {
            return formValid;
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
