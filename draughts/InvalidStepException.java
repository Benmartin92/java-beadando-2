package draughts;

import java.lang.Exception;
import position.Position;

public class InvalidStepException extends Exception // ellen�rz�tt kiv�tel: Exception-b�l sz�rmazik
{
    public InvalidStepException(Position from, Position to)
    {
        super("Invalid step from:" + from.toString() + " to: " + to.toString());
    }
}
