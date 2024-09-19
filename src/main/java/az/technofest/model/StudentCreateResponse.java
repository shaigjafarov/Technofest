package az.technofest.model;


public class StudentCreateResponse {

    private String message;


    public StudentCreateResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
