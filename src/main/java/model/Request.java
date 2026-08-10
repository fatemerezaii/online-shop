package model;

import model.accounts.Account;
import enums.RequestStatus;
import enums.RequestType;

public class Request {

    private static int nextId = 1;
    private int id;
    private Account sender;
    private RequestType type;
    private RequestStatus status;
    private Object data;

    public Request(Account sender, RequestType type, Object data) {
        this.id = nextId++;
        this.sender = sender;
        this.type = type;
        this.data = data;
        this.status = RequestStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public Account getSender() {
        return sender;
    }

    public RequestType getType() {
        return type;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "data=" + data +
                ", id=" + id +
                ", sender=" + sender +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}