package cat.iespaucasesnoves.spadd.auxiliars;

public abstract class EinesCadenes {
    /**
     * Elimina les comes que es troben dins d'un camp de la base de dades, de manera que mes tard es puguin recuperar però que no molestin a l'hora de separar els diferents camps.
     *
     * @param s La cadena a tractar
     * @return La cadena amb les comes tractades.
     */
    private static String tractaComes(String s) {
        boolean obertes = false;
        char[] caracters = s.toCharArray();
        for (int i = 0; i < caracters.length; i++) {
            if (caracters[i] == '"') {
                obertes = !obertes;
            } else if (caracters[i] == ',' && obertes) {
                caracters[i] = (char) 7;
            }
        }
        return new String(caracters);
    }

    /**
     * Recupera les comes substituides abans per <code>tractaComes</code>
     *
     * @param s La cadena tractada
     * @return La cadena amb les comes recuperades.
     */
    private static String recuperaComes(String s) {
        return s.replaceAll("" + ((char) 7), ",");
    }

    /**
     * Substitueix els camps nulls per la paraula X_VALOR_NULL_X
     * @param value La cadena original
     * @return La cadena tractada
     */
    private static String arreglaNuls(String value){
        value = value.replaceAll(",\\)", ",X_VALOR_NULL_X)");
        value = value.replaceAll("\\(,", "(X_VALOR_NULL_X,");
        value = value.replaceAll(",,", ",X_VALOR_NULL_X,");
        return value;
    }

    /**
     * Modifica el value tornat per postgresql de manera que sigui més fàcil tractar-lo. Modifica els valors nulls, elimina
     * els parèntesis que tanquen el value, i substitueix les comes que estan dins d'un valor de manera que no afectin
     * a l'hora de separar els diferents camps.
     *
     * @param value El valor tornat per postgresql
     * @return Array on cada posició és un camp de la taula.
     */
    public static String[] parseValue(String value) {
        //Arreglam nuls
        value=arreglaNuls(value);

        //Llevam parèntesis
        String valor = value.substring(1, value.length() - 1);

        //Substituïm les comes dins dels atributs
        valor = tractaComes(valor);

        //Separam els atributs segons les comes
        String[] atributs = valor.split(",");

        //Eliminar " inicial i final si n'hi ha. Assignam nuls
        for (int i = 0; i < atributs.length; i++) {
            if ("X_VALOR_NULL_X".equalsIgnoreCase(atributs[i])) {
                atributs[i] = null;
            } else if (atributs[i].charAt(0) == '"' && atributs[i].charAt(atributs[i].length() - 1) == '"') {
                atributs[i] = atributs[i].substring(1, atributs[i].length() - 1);
                //Tornam a posar les comes llevades anteriorment
                atributs[i] = recuperaComes(atributs[i]);
            }
        }
        //Tornam els atributs
        return atributs;
    }

    /**
     * Tanca el valor representat per la cadena entre cometes si fa falta
     *
     * @param valor El valor original
     * @return El valor entre cometes si fa falta.
     */
    public static String tanca(String valor) {
        if (valor != null) {
            if (valor.contains(" ")) {
                return '"' + valor + '"';
            } else return valor;
        } else {
            return "";
        }
    }
}
