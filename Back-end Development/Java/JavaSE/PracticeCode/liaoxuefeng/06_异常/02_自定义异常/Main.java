public class Main {

    public static void main(String[] args) {
        String token = login("admin", "password");
        System.out.println("Token: " + token);
    }

    static String login(String username, String password) {
        if (username.equals("admin")) {
            if (password.equals("password")) {
                return "xxxxxx";
            } else {
                // TODO: 抛出LoginFailedException:
                throw new LoginFailedException("Bad username or password.");
            }
        } else {
            // TODO: 抛出UserNotFoundException:
            throw new UserNotFoundException("User not found.");
        }
    }
}

/**
 * 自定义根异常
 */
class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }


    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * 自定义登录失败异常
 */
class LoginFailedException extends BaseException {
    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * 自定义未找到用户异常
 */
class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}