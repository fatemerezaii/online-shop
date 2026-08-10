package controller;

import enums.CommentStatus;
import model.Comment;
import model.Rate;
import model.accounts.Admin;
import model.accounts.Customer;
import model.products.Product;
import model.products.electronicProduct.PC;
import model.products.electronicProduct.storageEquipment.StorageEquipment;
import model.products.stationery.NoteBook;
import model.products.stationery.Pen;
import model.products.vehicles.Car;
import enums.Category;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private final List<Product> products;

    public ProductController() {
        products = Admin.getInstance().getProducts();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> search(List<Product> products, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return new ArrayList<>(products);
        }

        List<Product> result = new ArrayList<>();
        keyword = keyword.toLowerCase().trim();

        for (Product product : products) {
            if (product.getName() != null && product.getName().toLowerCase().contains(keyword)) {
                result.add(product);
            }
        }

        return result;
    }

    public List<Product> filterByStatus(List<Product> products, String status) {

        if (status == null || status.isBlank()) {
            return new ArrayList<>(products);
        }

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getStatus().equalsIgnoreCase(status)) {
                result.add(product);
            }
        }

        return result;
    }

    public List<Product> filterByPrice(List<Product> products, double minPrice, double maxPrice) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getCost() >= minPrice && product.getCost() <= maxPrice) {

                result.add(product);
            }
        }

        return result;
    }

    public List<Product> filterByRating(List<Product> products, double minRating, double maxRating) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getAverageRating() >= minRating && product.getAverageRating() <= maxRating) {

                result.add(product);
            }
        }

        return result;
    }

    public List<Product> filterByCategory(List<Product> products, Category category) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getCategory() == category) {
                result.add(product);
            }
        }

        return result;
    }

    public List<Product> filterPCByCPU(List<Product> products, String cpuType) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof PC pc) {

                if (pc.getCPUType().equalsIgnoreCase(cpuType)) {
                    result.add(product);
                }
            }
        }

        return result;
    }

    public List<Product> filterPCByRAM(List<Product> products, String ramCapacity) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof PC pc) {

                if (pc.getRAMCapacity().equalsIgnoreCase(ramCapacity)) {
                    result.add(product);
                }
            }
        }

        return result;
    }

    public List<Product> filterByCapacity(List<Product> products, double minCapacity, double maxCapacity) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof StorageEquipment storage) {

                if (storage.getCapacity() >= minCapacity && storage.getCapacity() <= maxCapacity) {

                    result.add(product);
                }
            }
        }

        return result;
    }

    public List<Product> filterNotebookByPages(List<Product> products, int minPages, int maxPages) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof NoteBook notebook) {

                if (notebook.getPages() >= minPages && notebook.getPages() <= maxPages) {

                    result.add(product);
                }
            }
        }

        return result;
    }

    public List<Product> filterPenByColor(List<Product> products, String color) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof Pen pen && pen.getColor().equalsIgnoreCase(color)) {

                result.add(product);
            }
        }

        return result;
    }

    public List<Product> filterCarsByAutomatic(List<Product> products, boolean automatic) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof Car car) {

                if (car.isAutomate() == automatic) {
                    result.add(product);
                }
            }
        }

        return result;
    }

    public List<Product> filterCarsByEngineVolume(List<Product> products, double minVolume, double maxVolume) {

        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof Car car) {

                if (car.getEngineVolume() >= minVolume && car.getEngineVolume() <= maxVolume) {

                    result.add(product);
                }
            }
        }

        return result;
    }

    public List<Product> getProductsPage(List<Product> products, int page) {

        int productsPerPage = 10;

        int start = (page - 1) * productsPerPage;

        if (page < 1 || start >= products.size()) {
            return new ArrayList<>();
        }

        int end = Math.min(start + productsPerPage, products.size());

        return new ArrayList<>(products.subList(start, end));
    }

    public int getPageCount(List<Product> products) {

        int productsPerPage = 10;

        return (int) Math.ceil((double) products.size() / productsPerPage);
    }
}