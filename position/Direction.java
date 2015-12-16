package position;

public enum Direction
{
    UP_LEFT,
    UP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT;
    
    // dist[0] v�zszintes t�vols�g
    // dist[1] f�gg�leges t�vols�g
    public static Direction fromDistance(int[] dist)
    {
        if (dist[0] < 0 && dist[1] < 0)
            return DOWN_LEFT;
        else if (dist[0] < 0 && dist[1] > 0)
            return UP_LEFT;
        else if (dist[0] > 0 && dist[1] < 0)
            return DOWN_RIGHT;
        else
            return UP_RIGHT;
    }
}
