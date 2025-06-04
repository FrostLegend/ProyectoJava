package Com.Projecte.src.dev;

import java.util.Iterator;
import java.util.List;

public class IteradorGenerico<T> implements Iterable<T> {
    private List<T> lista;

    public IteradorGenerico(List<T> lista) {
        this.lista = lista;
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIteradorGenerico();
    }

    private class InnerIteradorGenerico implements Iterator<T> {
        private int indice = 0;
        private boolean sepuedeeliminar = false;

        @Override
        public boolean hasNext() {
            return indice < lista.size();
        }

        @Override
        public T next() {
            sepuedeeliminar = true;
            return lista.get(indice++);
        }

        @Override
        public void remove() {
            if (!sepuedeeliminar) {
                throw new IllegalStateException("No se puede eliminar el dato");
            }
            sepuedeeliminar = false;
            lista.remove(--indice);
        }

    }
}
