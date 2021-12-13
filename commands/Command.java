package sketchy.commands;

import sketchy.shapes.SketchyShape;

public interface Command {

    void undo();
    void redo();
}
