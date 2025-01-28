public class Pair<T, U> {
    public T first;
    public U second;

    private Pair(T first, U second) { //закрытый конструктор
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> of(A first, B second) { //создание параметризованной пары
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    @Override
    public boolean equals(Object o) { //равенство объектов
        if (this == o) return true; //проверка на идентичность классов и ссылки
        if (o == null || this.getClass() != o.getClass()) return false;

        Pair<?, ?> p2 = (Pair<?, ?>) o;
        if(this.getFirst() == null && p2.getFirst()!=null) return false;
        if(this.getSecond() == null && p2.getSecond()!=null) return false;
        if(this.getFirst() != null && !(this.getFirst().equals(p2.getFirst()))) return false;
        if(this.getSecond() != null && !(this.getSecond().equals(p2.getSecond()))) return false;
        return true;
    }

    @Override
    public int hashCode() { //хэш-код объекта
        int res = (this.first == null ? 0 : this.first.hashCode());
        res = 29 * res + (this.second == null ? 0 : this.second.hashCode());
        return res;
    }
}
