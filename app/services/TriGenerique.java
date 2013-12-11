

package services;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
/**
 * @author cheick-mahady.sissoko
 *
 */
public class TriGenerique<E extends Sortable> {

    public void split(List<E> l, List<E> l1, List<E> l2) {
        int len = l.size();
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                l1.add(l.get(i));
            } else {
                l2.add(l.get(i));
            }
        }
    }

    public List<E> merge(List<E> l1, List<E> l2, CompareFunction<E> f) {
        LinkedList<E> l = new LinkedList<E>();
        while (!l1.isEmpty() || !l2.isEmpty()) {
            if (!l1.isEmpty() && !l2.isEmpty()) {
                E fl1 = l1.get(0);
                E fl2 = l2.get(0);
                if (f.compare(fl1, fl2) <= 0) {
                    l.add(l1.remove(0));
                } else {
                    l.add(l2.remove(0));
                }
            } else {
                if (!l1.isEmpty()) {
                    l.addAll(l1);
                    return l;
                } else {
                    l.addAll(l2);
                    return l;
                }
            }
        }
        return l;
    }

    public List<E> mergeSort(List<E> l, CompareFunction<E> f) {
        if (l.size() <= 1) {
            return l;
        }
        LinkedList<E> l1 = new LinkedList<E>();
        LinkedList<E> l2 = new LinkedList<E>();
        split(l, l1, l2);
        return merge(mergeSort(l1, f), mergeSort(l2, f), f);
    }

    // Question 1.2. Tri rapide pour un tableau
    void swap(E[] a, int i, int j) {
        E tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    int partition(E[] a, CompareFunction<E> f, int l, int r) {
        if (a.length == 0) {
            return -1;
        }
        int m = l;
        E v = a[l];
        for (int i = l + 1; i <= r; i++) {
            if (f.compare(v, a[i]) > 0) {
                swap(a, ++m, i);
            }
        }
        swap(a, m, l);
        return m;
    }

    void quickrec(E[] a, CompareFunction<E> f, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = partition(a, f, l, r);
        quickrec(a, f, l, m - 1);
        quickrec(a, f, m + 1, r);
    }

    public void quickSort(E[] t, CompareFunction<E> f) {
        quickrec(t, f, 0, t.length - 1);
    }
}
