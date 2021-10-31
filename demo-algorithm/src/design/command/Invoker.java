package design.command;

import design.inter.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.command
 * @descripation TODO
 * @date 2021-07-22
 */
public class Invoker {
    private List<Command> list = new ArrayList<>();

    public void addCommands(Command command){
        list.add(command);
    }

    public void execute(){
        for(Command command: list){
            command.execute();
        }
    }

    public void undo(){
        for(Command command: list){
            command.undo();
        }
    }
}
