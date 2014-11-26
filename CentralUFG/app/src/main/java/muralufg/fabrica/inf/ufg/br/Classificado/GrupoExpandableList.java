package muralufg.fabrica.inf.ufg.br.Classificado;

import java.util.ArrayList;
import java.util.List;

public class GrupoExpandableList {
	
	public String string;
	public final List<String> children = new ArrayList<String>();
	
	public GrupoExpandableList(String string) {
		this.string = string;
	}
}
