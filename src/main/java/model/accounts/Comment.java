package model.accounts;

import enums.CommentStatus;
import Model.Account.Customer;

public class Comment {
    private Customer user;
    private int productId;
    private String text;
    private CommentStatus status;
    private boolean hasBought;

    public Comment(boolean hasBought, int productId, CommentStatus status, String text, Customer user) {
        this.hasBought = hasBought;
        this.productId = productId;
        this.status = status;
        this.text = text;
        this.user = user;
    }

    public boolean isHasBought() {
        return hasBought;
    }

    public int getProductId() {
        return productId;
    }

    public CommentStatus getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public Customer getUser() {
        return user;
    }

    public void setHasBought(boolean hasBought) {
        this.hasBought = hasBought;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}
