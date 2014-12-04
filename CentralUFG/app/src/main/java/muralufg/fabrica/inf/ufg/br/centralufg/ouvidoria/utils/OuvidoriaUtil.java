package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.utils;

/**
 * Utilitários da Ouvidoria
 */
public class OuvidoriaUtil {

    private OuvidoriaUtil() {
    }

    /**
     * Converter o tamanho dos bytes em um formato legível
     *
     * @param bytes tamanho dos bytes
     * @param si    true, prefixo SI caso contrário Prefixo binário
     * @return bytes formato legível
     */
    public static String bytesParaFormatoLegivel(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit){
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}