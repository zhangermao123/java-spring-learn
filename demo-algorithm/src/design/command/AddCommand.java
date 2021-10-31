package design.command;

import design.inter.Command;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.command
 * @descripation TODO
 * @date 2021-07-21
 */
public class AddCommand implements Command {
    private TextEditor textEditor;

    public AddCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    @Override
    public void execute() {
        textEditor.add("Command pattern in text editor.\n");
    }

    @Override
    public void undo() {

    }
}
