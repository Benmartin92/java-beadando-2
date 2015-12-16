package grid;

import grid.AbstractGrid;
import java.util.ArrayList;
import position.Position;
import java.lang.IndexOutOfBoundsException;

public class DenseGrid<T> extends AbstractGrid<T>
{
    private ArrayList<ArrayList<T>> grid;
    
    public DenseGrid(int rows, int cols)
    {
        super(rows, cols);
        grid = new ArrayList<ArrayList<T>>();
        for (int i = 0; i < rows; i++)
        {
            ArrayList<T> row = new ArrayList<T>();
            for (int j = 0; j < cols; j++)
                row.add(null);
            
            grid.add(row);
        }
    }
    
    public T get(Position pos)
    {
        if (isValid(pos))
            return grid.get(pos.v).get(pos.h);
        
        throw new IndexOutOfBoundsException("Invalid position: " + pos.toString());
    }
    
    public void set(Position pos, T obj)
    {
        if (isValid(pos))
            grid.get(pos.v).add(pos.h, obj);
        else
            throw new IndexOutOfBoundsException("Invalid position: " + pos.toString());
    }
}
