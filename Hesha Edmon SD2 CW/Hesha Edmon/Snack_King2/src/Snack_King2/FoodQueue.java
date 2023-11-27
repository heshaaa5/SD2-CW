// Importing the packages
package Snack_King2;
import Snack_King2.Exceptions.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class FoodQueue  implements Serializable {
    private SKFoodCenter numOfQueues[] = new SKFoodCenter[3];
    private final int sizeofQueues[] = {2, 3, 5};
    public static final int NO_QUEUES = 3;
    public final int PIZZA_STOCK = 100;
    public static final float PIZZA_PRICE = 1350f;
    public int currNoOfPizza;


    public FoodQueue() {

        numOfQueues[0] = new SKFoodCenter(2, 1);
        numOfQueues[1] = new SKFoodCenter(3, 2);
        numOfQueues[2] = new SKFoodCenter(5, 3);
        this.currNoOfPizza = PIZZA_STOCK;

    }

//Method of adding Customer
    public void addingCustomer(Customer cus) {

        try {

            SKFoodCenter min = numOfQueues[0];
            float maxEmptySizeRatio = (float) (numOfQueues[0].getSize() - numOfQueues[0].getNoOfElements()) / numOfQueues[0].getSize();
            for (int i = 1; i < NO_QUEUES; i++) {

                float emptySizeRatio = (float) (numOfQueues[i].getSize() - numOfQueues[i].getNoOfElements()) / numOfQueues[i].getSize();
                if (emptySizeRatio > maxEmptySizeRatio) {
                    maxEmptySizeRatio = emptySizeRatio;
                    min = numOfQueues[i];
                }
            }

            if (min.isFull()) {
                throw new QueueFullException(min.getId());
            }




            System.out.println(cus.getFullName() + " successfully added.");
            if (currNoOfPizza <= 20) {
                throw new LowPizzaException();
            }
        } catch (QueueFullException |  LowPizzaException ex) {
            System.out.println(ex);
        }
    }

//Method of removing customer in a queue

    public Customer removeCustomer(int queueNo) {

        try {

            Customer name = numOfQueues[queueNo - 1].removeCustomer();

            return name;
        } catch (QueueEmptyException ex) {
            System.out.println(ex);
            return null;
        }

    }

    public Customer removeCustomer(int queueNo, int loc) {

        try {

            Customer cus = numOfQueues[queueNo - 1].removeCustomer(loc);

            return cus;
        } catch (QueueEmptyException | QueueIndexOutOfBoundsException ex) {
            System.out.println(ex);
            return null;
        }

    }


//Method of viewing all queues


    public void viewQueues() {

        System.out.println("**************************");
        System.out.println("*\tCashiers\t*");
        System.out.println("**************************");
        System.out.println("");

        for (int k = 0; k < NO_QUEUES; k++) {
            System.out.println("Queue " + (k + 1));
            numOfQueues[k].viewQueue();
        }
    }

//Method of displaying sorted queues

    public void displaySortedQueue() {

        Customer temp;
        ArrayList<Customer> tempArr = new ArrayList<>();
        for (int k = 0; k < NO_QUEUES; k++) {
            tempArr.addAll(numOfQueues[k].getQueue());
        }
        System.out.println("\tAll customers in dictionary order");
        System.out.println("");
        for (int i = 0; i < tempArr.size(); i++) {
            for (int j = 1; j < (tempArr.size() - i); j++) {
                if (tempArr.get(j - 1).getFullName().compareTo(tempArr.get(j).getFullName()) > 0) {
                    //swap elements

                    temp = tempArr.get(j - 1);
                    tempArr.set(j - 1, tempArr.get(j));
                    tempArr.set(j, temp);
                }

            }
        }
        for (Customer cus : tempArr) {
            System.out.println(cus.getFullName());
        }
    }

//Method of viewing empty queues

    public void viewEmptyQueues() {
        System.out.println("Currently Empty Queues");
        for (int i = 0; i < NO_QUEUES; i++) {
            if (numOfQueues[i].isEmpty()) {
                System.out.println("Queue " + (i + 1));
            }
        }
    }
//Method og getting current number of pizza

    public int getCurrNoOfPizza() {
        return this.currNoOfPizza;
    }

// Method of aading pizza

    public void addPizza(int qty) {
        currNoOfPizza += qty;
        System.out.println("Stock updated successfully!");
    }

//Method of displaying income

    public void displayIncome(int queueNo) {
        System.out.printf("Income of %d: %.2f\n", queueNo, numOfQueues[queueNo - 1].getIncome());
    }


}
