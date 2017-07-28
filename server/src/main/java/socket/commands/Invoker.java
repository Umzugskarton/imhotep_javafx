package socket.commands;


public class Invoker {
    private command command;

    public  Invoker(command c){
        this.command = c;
    }

    public void call(){
        command.exec();
    }
}
