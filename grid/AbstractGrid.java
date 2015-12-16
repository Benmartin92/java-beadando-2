package grid;

import position.Position;
import grid.Grid;

//
//  A bal fölső sarok a nulladik sor (függőleges koordináta), nulladik oszlopában (vízszintes koordináta) van.
//  A jobb alsó sarok sorát és oszlopát megkapjuk, ha a tábla sorainak és oszlopainak számából levonunk egyet-egyet.
//
public abstract class AbstractGrid<T> implements Grid<T>
{
    public final int rows;
    public final int cols;
    
    public AbstractGrid(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
    }
    
    //
    // Az isValid metódust, mely igaz értéket ad vissza, ha a paraméterül várt pozíció a játéktáblán van. 
    // Ez akkor teljesül, ha a bal alsó sarokhoz a compareTo-val viszonyítva a paramétert nempozitív számot kapunk, 
    // a jobb fölsőhöz viszonyítva nemnegatívat. compareTo???
    //
    public boolean isValid(Position pos) { return pos.h >= 0 && pos.v >= 0 && pos.v < rows && pos.h < cols; }
}
