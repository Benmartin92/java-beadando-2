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
    
    protected Position position; // ez nem lehet final, megváltozik a bábu áthelyezésekor.
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
    
    public void stepTo(Position position) throws InvalidStepException
    {
        if (canStepTo(position))
        {
            int[] dist = this.position.distance(position);
            Direction direction = Direction.fromDistance(dist);
            Position next = this.position.next(direction);
            if (next.equals(position))
            {
                grid.set(this.position, null);
                grid.set(next, this);
            }
            else // ütés van
            {
                grid.set(this.position, null);
                grid.set(next, null); // ezen volt a bábu, amit leütöttünk
                grid.set(position, this);
            }
            this.position = position;
        }
        else
            throw new InvalidStepException(this.position, position);
    }
}
