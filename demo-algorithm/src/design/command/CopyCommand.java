package design.command;

import design.inter.Command;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.command
 * @descripation TODO
 * @date 2021-07-21
 */
public class CopyCommand implements Command {
    private TextEditor textEditor;

    public CopyCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    @Override
    public void execute() {
        this.textEditor.copy();
    }

    @Override
    public void undo() {

    }
}
