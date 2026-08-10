package controller;

import enums.CommentStatus;
import enums.RequestStatus;
import model.Comment;
import model.Request;
import model.accounts.Admin;
import model.accounts.Customer;
import model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class RequestController {

    public List<Request> getPendingRequests() {

        List<Request> result = new ArrayList<>();
        for (Request request : Admin.getInstance().getRequests()) {
            if (request.getStatus() == RequestStatus.PENDING) {
                result.add(request);
            }
        }

        return result;
    }

    public String approveRequest(Request request) {

        if (request == null) {
            return "Request not found!";
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            return "Request has already been processed!";
        }

        switch (request.getType()) {

            case REGISTER:

                Customer customer = (Customer) request.getData();
                if (!Admin.getInstance().getCustomers().contains(customer)) {
                    Admin.getInstance().getCustomers().add(customer);
                }
                request.setStatus(RequestStatus.ACCEPTED);
                return "Registration request accepted.";

            case COMMENT:

                Comment comment = (Comment) request.getData();
                Product product = findProduct(comment.getProductId());
                if (product == null) {
                    return "Product not found!";
                }
                comment.setStatus(CommentStatus.CONFIRMED);
                if (product.getComments() == null) {
                    product.setComments(new ArrayList<>());
                }
                product.getComments().add(comment);
                request.setStatus(RequestStatus.ACCEPTED);
                return "Comment request accepted.";

            case BALANCE:

                Customer sender = (Customer) request.getSender();
                if (sender == null) {
                    return "Customer not found!";
                }
                double amount = (Double) request.getData();
                sender.setCredit((int) (sender.getCredit() + amount));
                request.setStatus(RequestStatus.ACCEPTED);
                return "Balance request accepted.";

            default:

                return "Unknown request type!";
        }
    }

    public String rejectRequest(Request request) {

        if (request == null) {
            return "Request not found!";
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            return "Request has already been processed!";
        }

        request.setStatus(RequestStatus.REJECTED);
        return "Request rejected.";
    }

    private Product findProduct(int productId) {

        for (Product product : Admin.getInstance().getProducts()) {

            if (product.getProductId() == productId) {
                return product;
            }
        }

        return null;
    }
}