package grid;

import position.Position;

public interface Grid<T>
{
    public boolean isValid(Position position);
    public T get(Position position);
    public void set(Position position, T obj);
}
