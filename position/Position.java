package position;

public class Position 
{
    public final int h; // vízszintes
    public final int v; // függőleges
    
    public Position(int h, int v)
    {
        this.h = h;
        this.v = v;
    }
    
    public String toString() { return "(" + h + "," + v + ")"; }
    public Position next(Direction direction)
    {
        if (direction == Direction.UP_RIGHT)
            return new Position(h + 1, v - 1);
        else if (direction == Direction.UP_LEFT)
            return new Position(h - 1, v - 1);
        else if (direction == Direction.DOWN_RIGHT)
            return new Position(h + 1, v + 1);
        else
            return new Position(h - 1, v + 1);
    }
    
    @Override
    public boolean equals(Object par)
    {
        if (par instanceof Position)
        {
            Position position = (Position) par;
            return position.h == h && position.v == v;
        }
        return false;
    }
    
    public int[] distance(Position position) { return new int[] {position.h - h, position.v - v}; }
    
    public static boolean isDiagonal(Position pos1, Position pos2)
    {
        int[] dist = pos1.distance(pos2);
        return dist[0] == dist[1] || (dist[0] + dist[1] == 0);
    }
    
    @Override
    public int hashCode() { return 100 * v + h; }
    
}
