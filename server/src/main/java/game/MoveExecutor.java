package game;

import GameMoves.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 18.12.2017.
 */
public class MoveExecutor {
    private final Lock lock = new ReentrantLock();
    private final Condition notSet = lock.newCondition();
    private Move move;
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    public Move waitForMove(){
      lock.lock();
      try {
        while (move == null)
        notSet.await(30, TimeUnit.SECONDS);
      }catch (InterruptedException e){
        log.error(e.getMessage());
      }
      finally {
        lock.unlock();
      }
      return move;
    }

    public void setMove(Move move){
      lock.lock();
      try{
        this.move = move;
        notSet.signalAll();
      }finally {
        lock.unlock();
      }
    }

}
