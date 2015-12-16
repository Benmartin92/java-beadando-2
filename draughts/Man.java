package draughts;

import draughts.Piece;
import position.Position;
import position.Direction;
import grid.Grid;

public class Man extends Piece
{
    public Man(Position position, Color color, Grid<Piece> grid)
    {
        super(position, color, grid);
    }
    
    public boolean canStepTo(Position position)
    {
        if (grid.isValid(position))
        {
            boolean diagonal = Position.isDiagonal(position, this.position);
            boolean differs = !position.equals(this.position);
            if (!diagonal || !differs)
                return false;
            
            int[] distance = this.position.distance(position);
            Direction direction = Direction.fromDistance(distance);
            boolean oneStep = (Math.abs(distance[0]) + Math.abs(distance[1])) == 2;
            boolean twoSteps = (Math.abs(distance[0]) + Math.abs(distance[1])) == 4;
            Piece neighbor = grid.get(this.position.next(direction));
            boolean enemy = neighbor != null && neighbor.color != this.color;
            boolean empty = grid.get(position) == null;
            boolean rightDirection = (color == Color.BLACK && (direction == Direction.DOWN_LEFT || direction == Direction.DOWN_RIGHT)) || 
                                     (color == Color.WHITE && (direction == Direction.UP_LEFT || direction == Direction.UP_RIGHT));
            return rightDirection && empty && (oneStep || (twoSteps && enemy));
        }
        return false;
    }
    
    public String toString() { return color.toString().charAt(0) + "M"; }
    
}
