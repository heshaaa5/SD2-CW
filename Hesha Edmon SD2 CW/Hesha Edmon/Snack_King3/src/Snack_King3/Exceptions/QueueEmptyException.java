
package Snack_King3.Exceptions;


public class QueueEmptyException extends Exception {

    private int queueNo;

    public QueueEmptyException() {

    }

    public QueueEmptyException(int queueNo) {
        this.queueNo = queueNo;
    }

    public String toString() {
        return "This queue " + queueNo + " is currently empty.";
    }
}
