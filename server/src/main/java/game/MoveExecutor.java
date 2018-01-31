package game;

import GameMoves.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MoveExecutor {

  private final Lock lock = new ReentrantLock();
  private final Condition notSet = lock.newCondition();
  private Move move;
  private final Logger log = LoggerFactory.getLogger(getClass().getName());
  private static final int TURN_TIME = 20;
  private static final int TURN_TIME_BUFFER = 3;

  public void waitForMove() {
    move = null;
    lock.lock();
    try {
      notSet.await(TURN_TIME + TURN_TIME_BUFFER, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    } finally {
      lock.unlock();
    }
  }

  public static int getTurnTime() {
    return TURN_TIME;
  }

  public Move getMove() {
    return move;
  }

  public void setMove(Move move) {
    lock.lock();
    try {
      this.move = move;
      notSet.signalAll();
    } finally {
      lock.unlock();
    }
  }

}
