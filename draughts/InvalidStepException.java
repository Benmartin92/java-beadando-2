package draughts;

import java.lang.Exception;
import position.Position;

public class InvalidStepException extends Exception // ellenõrzött kivétel: Exception-bõl származik
{
    public InvalidStepException(Position from, Position to)
    {
        super("Invalid step from:" + from.toString() + " to: " + to.toString());
    }
}
