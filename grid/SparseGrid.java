package grid;

import java.util.Map;
import java.util.HashMap;
import position.Position;

public class SparseGrid<T> extends AbstractGrid<T>
{
    private Map<Position, T> grid;
    
    public SparseGrid(int rows, int cols)
    {
        super(rows, cols);
        grid = new HashMap<Position, T>();
    }
    
    public T get(Position pos)
    {
        if (isValid(pos))
            return grid.get(pos);
        throw new IndexOutOfBoundsException("Invalid position: " + pos.toString());
    }
    
        
    public void set(Position pos, T obj)
    {
        if (isValid(pos))
            grid.put(pos, obj);
        else
            throw new IndexOutOfBoundsException("Invalid position: " + pos.toString());
    }
}
