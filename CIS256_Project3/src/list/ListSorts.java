/* ListSorts.java */

package list;

import java.lang.Math;


public class ListSorts {

  private final static int SORTSIZE = 1000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    //return null;
    LinkedQueue que = new LinkedQueue();
    int size = q.size();
    for (int i = 0; i < size; ++i) {
      LinkedQueue p = new LinkedQueue();
      try {
        p.enqueue(q.dequeue());
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
      que.enqueue(p);
    }
    return que;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue q = new LinkedQueue();
    while (q1.size() > 0 && q2.size() > 0) {
      try {
        if (((Comparable)q1.front()).compareTo(q2.front()) <= 0) {
          q.enqueue(q1.dequeue());
        } else {
          q.enqueue(q2.dequeue());
        }
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
    }
    while (q1.size() > 0) {
      try {
        q.enqueue(q1.dequeue());
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
    }
    while (q2.size() > 0) {
      try {
        q.enqueue(q2.dequeue());
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
    }
    return q;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
   LinkedQueue qSmall, LinkedQueue qEquals, 
   LinkedQueue qLarge) {
    // Your solution here.
    while (qIn.size() > 0) {
      try {
        if (pivot.compareTo(qIn.front()) < 0) {
          qLarge.enqueue(qIn.dequeue());
        } else if (pivot.compareTo(qIn.front()) > 0) {
          qSmall.enqueue(qIn.dequeue());
        } else {
          qEquals.enqueue(qIn.dequeue());
        }
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    if (q.size() <= 1) {
      return;
    }
    LinkedQueue que = makeQueueOfQueues(q);
    while (que.size() > 1) {
      LinkedQueue q1 = null;
      LinkedQueue q2 = null;
      try {
        q1 = (LinkedQueue)que.dequeue();
        q2 = (LinkedQueue)que.dequeue();

      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
      LinkedQueue p = mergeSortedQueues(q1, q2);
      que.enqueue(p);
    }
    try {
      q.append((LinkedQueue)que.dequeue());
    } catch(QueueEmptyException e) {
      System.err.println(e);
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    int index = ((int)(Math.random() * 100000)) % q.size() + 1;
    Comparable pivot = (Comparable)q.nth(index);
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    partition(q, pivot, qSmall, qEquals, qLarge);
    if (qSmall.size() > 1) {
      quickSort(qSmall);
    } 
    if (qLarge.size() > 1) {
      quickSort(qLarge);
    }
    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }
   
}