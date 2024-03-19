package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {
   private static final int DEFAULT_CAPACITY = 10;

   private E[] elements;
   private int front;
   private int rear;
   private int count;

   @SuppressWarnings("unchecked")
   public QueueImplementation(int capacity) {
      elements = (E[]) new Object[capacity];
      front = 0;
      rear = -1;
      count = 0;
   }

   public QueueImplementation() {
      this(DEFAULT_CAPACITY);
   }

   @Override
   public int capacity() {
      return elements.length;
   }

   @Override
   public void enqueue(E element) throws QueueAllocationException, NullPointerException {
      if (element == null) {
         throw new NullPointerException("element cannot be null.");
      }

      if (count == elements.length) {
         reallocate();
      }

      rear = (rear + 1) % elements.length;
      elements[rear] = element;
      count++;
   }

   @Override
   public E dequeue() throws QueueIsEmptyException {
      if (isEmpty()) {
         throw new QueueIsEmptyException("Queue is empty. Cannot dequeue elements.");
      }

      E dequeuedElement = elements[front];
      elements[front] = null;
      front = (front + 1) % elements.length;
      count--;

      return dequeuedElement;
   }

   @Override
   public E element() throws QueueIsEmptyException {
      if (isEmpty()) {
         throw new QueueIsEmptyException("Queue is empty. No element to retrieve.");
      }

      return elements[front];
   }

   @Override
   public int size() {
      return count;
   }

   @Override
   public boolean isEmpty() {
      return count == 0;
   }

   @SuppressWarnings("unchecked")
   @Override
   public void clear() {
      elements = (E[]) new Object[elements.length];
      front = 0;
      rear = -1;
      count = 0;
   }

   private void reallocate() {
      int newCapacity = elements.length * 2;
      @SuppressWarnings("unchecked")
    E[] newElements = (E[]) new Object[newCapacity];

      int index = front;
      for (int i = 0; i < count; i++) {
         newElements[i] = elements[index];
         index = (index + 1) % elements.length;
      }

      elements = newElements;
      front = 0;
      rear = count - 1;
   }
   
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }

      StringBuilder sb = new StringBuilder();
      sb.append("[");
      int index = front;
      for (int i = 0; i < count; i++) {
         sb.append(elements[index]);
         if (i < count - 1) {
            sb.append(", ");
         }
         index = (index + 1) % elements.length;
      }
      sb.append("]");

      return sb.toString();
   }
   
}