package raphael.recipesapi.exceptions;

public class Exceptions {
    public static class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }
}
