package main.java.pattern.command;

import java.util.Stack;

/**
 * 
 * Command Pattern - Invoker
 *
 */
public class ShoppingCart {
    private Stack<Command> history = new Stack<Command>();

    /* Command Pattern Method */
    public void storeAndExecute(Command cmd) throws CloneNotSupportedException {
        history.add(cmd.clone());
        cmd.execute();
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command cmd = history.pop();
            cmd.unExecute();
        } else {
            System.out.println("Undo fail, no command record!");
        }
    }

}
