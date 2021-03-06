package model;

import external.Log;
import utility.collection.QueueADT;


public class Miner implements Runnable
{
  private ValuableType[] types = {ValuableType.DIAMOND, ValuableType.GOLDNUGGET,
      ValuableType.JEWEL, ValuableType.RUBY, ValuableType.WOODENCOIN};

  private QueueADT<Valuable> queue;

  public Miner(QueueADT<Valuable> queue) {
    this.queue = queue;
  }

  @Override public void run() {
    while (true) {
      Log.getLog().addLog(getName() + " is working");
      int num = (int)(Math.random()*4);
      Valuable valuable = Valuable.getInstance(types[num]);
      Log.getLog().addLog(getName()+ " found " + valuable);
      queue.enqueue(valuable);
      try {
        Log.getLog().addLog(getName() + " is going to rest");
        Thread.sleep((int) ((Math.random() * (7000 - 4000)) + 4000)); //rest between 7 and 4 seconds
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private String getName() {
    return Thread.currentThread().getName();
  }
}
