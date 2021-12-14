package sketchy.commands;

import sketchy.shapes.CurvedLine;

public class DrawCommand implements Command {
    private CurvedLine myLine;
    private int myIndex;

    public DrawCommand(CurvedLine line){
        this.myLine = line;
        this.myIndex = line.getIndex();
    }

    @Override
    public void undo(){
        this.myLine.delete();
    }

    @Override
    public void redo(){
        this.myLine.create(this.myIndex);

    }
}
