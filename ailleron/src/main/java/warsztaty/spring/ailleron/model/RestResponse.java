package warsztaty.spring.ailleron.model;

import java.util.List;

public class RestResponse {
    private List<String> messages;
    private Result result;
    private String sayHello;

    public RestResponse() {
    }

    public RestResponse(List<String> messages, Result result, String sayHello) {
        this.messages = messages;
        this.result = result;
        this.sayHello = sayHello;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getSayHello() {
        return sayHello;
    }

    public void setSayHello(String sayHello) {
        this.sayHello = sayHello;
    }
}
