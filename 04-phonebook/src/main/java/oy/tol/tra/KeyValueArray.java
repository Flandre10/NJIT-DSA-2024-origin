package oy.tol.tra;

// This is the BST implementation, KeyValueHashTable has the hash table
  
public class KeyValueArray<K extends Comparable<K>, V> implements Dictionary<K,V> {

   private Pair<K, V>[] elements = null;
   private int count = 0;
   private int reallocationCount = 0;

   public KeyValueArray(int initialCapacity) {
      ensureCapacity(initialCapacity);
   }

   public KeyValueArray() {
      ensureCapacity(20);
   }

   @Override
   public Type getType() {
      return Type.SLOW;
   }

   @SuppressWarnings("unchecked")
   @Override
   public void ensureCapacity(int capacity) throws OutOfMemoryError {
      if (capacity < 20) {
         capacity = 20;
      }
      elements = (Pair<K,V>[])new Pair[capacity];
      reallocationCount = 0;
   }

   @Override
    // TODO: Implement this
   public int size() {
      return count;
   }


   /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something
     * about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter
     * value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function
     * is good or bad (too much collisions against data size).
     */
   @Override
   public String getStatus() {
      String status = "KeyValueArray reallocated " + reallocationCount + " times, each time doubles the size\n";
      status += String.format("KeyValueArray fill rate is %.2f%%%n", (count / (double)elements.length) * 100.0);
      return status;
   }

   @Override
   public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
         // TODO: Implement this
        // Remember null check.
        // If root is null, should go there.

        // update the root node. But it may have children
        // so do not just replace it with this new node but set
        // the keys and values for the already existing root.


      if (null == key || value == null) throw new IllegalArgumentException("Key or value cannot be null");
      for (Pair<K, V> element : elements) {
         if (element != null && element.getKey().equals(key)) {
            element.setValue(value);
            return true;
         }
      }
      if (count >= elements.length) {
         reallocate(elements.length * 2);
      }
      if (count < elements.length) {
         elements[count++] = new Pair<>(key, value);
         return true;
      }
      return false;
   }

   @Override
   public V find(K key) throws IllegalArgumentException {
      if (null == key) throw new IllegalArgumentException("Key cannot be null");
      for (int i = 0; i < count; i++) {
         if (elements[i] != null && key.equals(elements[i].getKey())) {
            return elements[i].getValue();
         }
      }
      return null;
   }

   @Override
   @java.lang.SuppressWarnings({"unchecked"})
   public Pair<K,V>[] toSortedArray() {
      Pair<K, V>[] sortedArray = (Pair<K,V>[])new Pair[count];
      int newIndex = 0;
      for (int i = 0; i < count; i++) {
         if (elements[i] != null) {
            sortedArray[newIndex++] = new Pair<>(elements[i].getKey(), elements[i].getValue());
         }
      }
      Algorithms.fastSort(sortedArray);
      return sortedArray;
   }

   @Override
   public void compress() throws OutOfMemoryError {
      int firstNullIndex = Algorithms.partitionByRule(elements, count, element -> element == null);
      reallocate(firstNullIndex);
   }

   @java.lang.SuppressWarnings("unchecked")
   private void reallocate(int newSize) throws OutOfMemoryError {
      reallocationCount++;
      Pair<K, V>[] newElements = (Pair<K,V>[])new Pair[newSize];
      for (int i = 0; i < count; i++) {
         newElements[i] = elements[i];
      }
      elements = newElements;
   }
}
