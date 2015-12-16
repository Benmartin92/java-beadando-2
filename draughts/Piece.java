package draughts;

import position.Position;
import position.Direction;
import grid.Grid;

public abstract class Piece
{
    public enum Color
    {
        WHITE,
        BLACK;
    }
    
    protected final Position position;
    protected final Color color;
    protected final Grid<Piece> grid;
    
    public Piece(Position position, Color color, Grid<Piece> grid)
    {
        this.position = position;
        this.color = color;
        this.grid = grid;
    }
    
    public abstract boolean canStepTo(Position position);
    public abstract String toString();
    
    public void stepTo(Position pos) throws InvalidStepException
    {
        if (canStepTo(pos))
        {
            int[] dist = position.distance(position);
            Direction direction = Direction.fromDistance(dist);
            Position next = position.next(direction);
            if (next.equals(pos))
            {
                grid.set(position, null);
                grid.set(next, this);
            }
            else // ütés van
            {
                grid.set(position, null);
                grid.set(next, null); // ezen volt a bábu, amit leütöttünk
                grid.set(pos, this);
            }
        }
        else
            throw new InvalidStepException(this.position, position);
    }
}
