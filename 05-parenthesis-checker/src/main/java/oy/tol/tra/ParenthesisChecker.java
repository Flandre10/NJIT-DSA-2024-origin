package oy.tol.tra;

/**
 * Uses the StackInterface implementation to check that parentheses in text files
 * match. 
 * <p>
 * TODO: Students, implement the checkParentheses() method using your StackImplementation
 * to check if parentheses in the two test files match or not.
 * <p>
 * NOTE: The Person.json test file has an error, but the tests expect that. So the test will 
 * not fail with that file -- the erroneus json file is _expected_.
 * <p>
 * Note that you do not have to instantiate this class anywhere. The ParenthesisTests:
 * <ul>
 *  <li>Constructs your implementation of the {@code StackImplementation<Character>}, and
 *  <li>Calls ParenthesisChecker.checkParentheses.
 * </ul>
 * So your job is just to have a working StackImplementation and implement the ParenthesisChecker.checkParentheses.
 * Then execute the ParenthesisTests.
 */
public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   /**
    * TODO: Implement this function which checks if the given string has matching opening and closing
    * parentheses. It should check for matching parentheses:<p>
    *  <code>Lorem ipsum ( dolor sit {  amet, [ consectetur adipiscing ] elit, sed } do eiusmod tempor ) incididunt ut...</code>,
    * <p>
    * which can be found for example in Java source code and JSON structures.
    * <p>
    * If the string has issues with parentheses, you should throw a {@code ParenthesisException} with a
    * descriptive message and error code. Error codes are already defined for you in the ParenthesesException
    * class to be used.
    * <p>
    * What is to be tested:
    * <ul>
    *  <li>when an opening parenthesis is found in the string, it is successfully pushed to the stack.
    *  <li>when a closing parenthesis is found in the string, a matching opening parenthesis is popped from the stack.
    *  <li>when popping a parenthesis from the stack, it must not be null. Otherwise string has too many closing parentheses.
    *  <li>when the string has been handled, and if the stack still has parentheses, there are too few closing parentheses.
    * </ul>
    * @param stack The stack object used in checking the parentheses from the string.
    * @param fromString A string containing parentheses to check if it is valid.
    * @return Returns the number of parentheses found from the input in total (both opening and closing).
    * @throws ParenthesesException if the parentheses did not match as intended.
    * @throws StackAllocationException If the stack cannot be allocated or reallocated if necessary.
    */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      for (int i = 0; i < fromString.length(); i++) {
          char ch = fromString.charAt(i);
          if (ch == '(' || ch == '[' || ch == '{') {
              try {
                  stack.push(ch);
              } catch (StackAllocationException | NullPointerException e) {
                  throw new ParenthesesException("Stack allocation failure", ParenthesesException.STACK_FAILURE);
              }
          } else if (ch == ')' || ch == ']' || ch == '}') {
              if (stack.isEmpty()) {
                  throw new ParenthesesException("Too many closing parentheses in the string", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
              }
              char opening = stack.pop();
              if ((ch == ')' && opening != '(') || (ch == ']' && opening != '[') || (ch == '}' && opening != '{')) {
                  throw new ParenthesesException("Parentheses are in the wrong order", ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
              }
          }
      }
  
      if (!stack.isEmpty()) {
          throw new ParenthesesException("Too few closing parentheses in the string", ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
      }
  
      return fromString.length();
  }
}
