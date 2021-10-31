package design.command;

import design.inter.Command;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.command
 * @descripation TODO
 * @date 2021-07-21
 */
public class PasteCommand implements Command {
    private TextEditor textEditor;

    public PasteCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    @Override
    public void execute() {
        this.textEditor.paste();
    }

    @Override
    public void undo() {

    }
}
