package autocar.microservice.exceptions;

public class TokenIsNotValid extends Exception {
    public TokenIsNotValid() {
        super("token is not valid");
    }
}
