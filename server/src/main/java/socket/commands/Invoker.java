package socket.commands;


public class Invoker {
    private Command command;

    public  Invoker(Command c){
        this.command = c;
    }

    public void call(){
        command.exec();
    }
}
