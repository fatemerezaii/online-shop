package controller;

import enums.BicycleType;
import enums.Category;
import enums.PencilType;
import model.accounts.Admin;
import model.products.Edible;
import model.products.Product;
import model.products.electronicProduct.PC;
import model.products.electronicProduct.storageEquipment.Memory;
import model.products.electronicProduct.storageEquipment.SSD;
import model.products.stationery.NoteBook;
import model.products.stationery.Pen;
import model.products.stationery.Pencil;
import model.products.vehicles.Bicycle;
import model.products.vehicles.Car;

import java.util.ArrayList;

public class AdminController {

    public String executeCommand(String command) {
        if (command == null || command.isBlank()) {
            return "Invalid command!";
        }

        String[] parts = command.trim().split("\\s+");

        if (parts[0].equalsIgnoreCase("help")) {
            if (parts.length != 1) {
                return "Invalid help command!";
            }
            return help();
        }

        if (parts[0].equalsIgnoreCase("add")) {
            return addProduct(parts);
        }

        return "Invalid command! Type help to see available commands.";
    }


    private String addProduct(String[] parts) {

        if (parts.length < 2) {
            return "Invalid Add command!";
        }

        String type = parts[1].toLowerCase();

        try {
            switch (type) {
                case "car":
                    return addCar(parts);

                case "bicycle":
                    return addBicycle(parts);

                case "pc":
                    return addPC(parts);

                case "memory":
                    return addMemory(parts);

                case "ssd":
                    return addSSD(parts);

                case "pen":
                    return addPen(parts);

                case "pencil":
                    return addPencil(parts);

                case "notebook":
                    return addNoteBook(parts);

                case "edible":
                    return addEdible(parts);

                default:
                    return "Invalid product type!";
            }

        } catch (NumberFormatException e) {
            return "Invalid number format!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }


    private String addCar(String[] parts) {

        if (parts.length != 11) {
            return "Invalid Car command!\n" + "Format:\n" + "Add Car <category> <cost> <name> <id> <status> <inventory> " + "<companyName> <engineVolume> <isAutomate>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        String companyName = parts[8];
        double engineVolume = Double.parseDouble(parts[9]);
        boolean automate = parseBoolean(parts[10]);

        validateBasicFields(cost, name, id);

        Car car = new Car(0, category, new ArrayList<>(), cost, name, id, status, inventory, companyName, engineVolume, automate);
        addToProducts(car);

        return "Car added successfully.";
    }


    private String addBicycle(String[] parts) {

        if (parts.length != 10) {
            return "Invalid Bicycle command!\n" + "Format:\n" + "Add Bicycle <category> <cost> <name> <id> <status><inventory> " + "<companyName> <bicycleType>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        String companyName = parts[8];
        BicycleType bicycleType = parseBicycleType(parts[9]);
        validateBasicFields(cost, name, id);

        Bicycle bicycle = new Bicycle(0, category, new ArrayList<>(), cost, name, id, status, inventory, companyName, bicycleType);
        addToProducts(bicycle);

        return "Bicycle added successfully.";
    }


    private String addPC(String[] parts) {

        if (parts.length != 12) {
            return "Invalid PC command!\n" + "Format:\n" + "Add PC <category> <cost> <name> <id> <status><inventory> " + "<dimensions> <weight> <CPUType> <RAMCapacity>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        double dimensions = Double.parseDouble(parts[8]);
        double weight = Double.parseDouble(parts[9]);
        String cpuType = parts[10];
        String ramCapacity = parts[11];
        validateBasicFields(cost, name, id);
        PC pc = new PC(0, category, new ArrayList<>(), cost, name, id, status, inventory, dimensions, weight, cpuType, ramCapacity);
        addToProducts(pc);

        return "PC added successfully.";
    }


    private String addMemory(String[] parts) {

        if (parts.length != 12) {
            return "Invalid Memory command!\n" + "Format:\n" + "Add Memory <category> <cost> <name> <id> <status> <inventory>" + "<dimensions> <weight> <capacity> <USBVersion>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        double dimensions = Double.parseDouble(parts[8]);
        double weight = Double.parseDouble(parts[9]);
        double capacity = Double.parseDouble(parts[10]);
        String usbVersion = parts[11];
        validateBasicFields(cost, name, id);

        Memory memory = new Memory(0, category, new ArrayList<>(), cost, name, id, status, inventory,  dimensions, weight, capacity, usbVersion);
        addToProducts(memory);

        return "Memory added successfully.";
    }


    private String addSSD(String[] parts) {

        if (parts.length != 13) {
            return "Invalid SSD command!\n" + "Format:\n" + "Add SSD <category> <cost> <name> <id> <status> <inventory>" + "<dimensions> <weight> <capacity> " + "<readingSpeed> <writingSpeed>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        double dimensions = Double.parseDouble(parts[8]);
        double weight = Double.parseDouble(parts[9]);
        double capacity = Double.parseDouble(parts[10]);
        String readingSpeed = parts[11];
        String writingSpeed = parts[12];
        validateBasicFields(cost, name, id);

        SSD ssd = new SSD(0, category, new ArrayList<>(), cost, name, id, status, inventory,  dimensions, weight, capacity, readingSpeed, writingSpeed);
        addToProducts(ssd);

        return "SSD added successfully.";
    }


    private String addPen(String[] parts) {

        if (parts.length != 10) {
            return "Invalid Pen command!\n" + "Format:\n" + "Add Pen <category> <cost> <name> <id> " + "<status><inventory> <country> <color>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        String country = parts[8];
        String color = parts[9];
        validateBasicFields(cost, name, id);

        Pen pen = new Pen(0, category, new ArrayList<>(), cost, name, id, status, inventory,  country, color);
        addToProducts(pen);

        return "Pen added successfully.";
    }


    private String addPencil(String[] parts) {

        if (parts.length != 10) {
            return "Invalid Pencil command!\n" + "Format:\n" + "Add Pencil <category> <cost> <name> " + "<id> <status> <inventory><country> <pencilType>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        String country = parts[8];
        PencilType pencilType = parsePencilType(parts[9]);
        validateBasicFields(cost, name, id);

        Pencil pencil = new Pencil(0, category, new ArrayList<>(), cost, name, id, status, inventory,  country, pencilType);
        addToProducts(pencil);

        return "Pencil added successfully.";
    }


    private String addNoteBook(String[] parts) {

        if (parts.length != 11) {
            return "Invalid NoteBook command!\n" + "Format:\n" + "Add NoteBook <category> <cost> <name> " + "<id> <status> <inventory><country> <pages> <paperType>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        String country = parts[8];
        int pages = Integer.parseInt(parts[9]);
        String paperType = parts[10];
        validateBasicFields(cost, name, id);

        NoteBook noteBook = new NoteBook(0, category, new ArrayList<>(), cost, name, id, status, inventory,  country, pages, paperType);
        addToProducts(noteBook);

        return "NoteBook added successfully.";
    }


    private String addEdible(String[] parts) {

        if (parts.length != 10) {
            return "Invalid Edible command!\n" + "Format:\n" + "Add Edible <category> <cost> <name> " + "<id> <status> <inventory><expiry> <productionDate>";
        }

        Category category = parseCategory(parts[2]);
        double cost = Double.parseDouble(parts[3]);
        String name = parts[4];
        int id = Integer.parseInt(parts[5]);
        String status = parts[6];
        int inventory = Integer.parseInt(parts[7]);
        String expiry = parts[8];
        String productionDate = parts[9];
        validateBasicFields(cost, name, id);

        Edible edible = new Edible(0, category, new ArrayList<>(), cost, name, id, status, inventory, expiry, productionDate);
        addToProducts(edible);

        return "Edible added successfully.";
    }


    private void addToProducts(Product product) {
        Admin.getInstance().getProducts().add(product);
    }


    private Category parseCategory(String value) {
        try {
            return Category.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category! Valid values: " + "ELECTRONIC_PRODUCT, STATIONERY, " + "EDIBLE, VEHICLES");
        }
    }


    private BicycleType parseBicycleType(String value) {
        try {
            return BicycleType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid bicycle type! Valid values: " + "MOUNTAIN, ROAD, CITY, HYBRID");
        }
    }

    private PencilType parsePencilType(String value) {
        try {
            return PencilType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid pencil type! Valid values: " + "HB, B, F, H, H2");
        }
    }

    private boolean parseBoolean(String value) {
        if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
            throw new IllegalArgumentException("Boolean value must be true or false!");
        }

        return Boolean.parseBoolean(value);
    }


    private void validateBasicFields(double cost, String name, int id) {

        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative!");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }

        if (id < 0) {
            throw new IllegalArgumentException("Product ID cannot be negative!");
        }
    }


    private String help() {

        return """
                ===== ADMIN COMMANDS =====

                Add Car:
                Add Car <category> <cost> <name> <id> <status> <inventory> <companyName> <engineVolume> <isAutomate>

                Add Bicycle:
                Add Bicycle <category> <cost> <name> <id> <status> <inventory> <companyName> <bicycleType>

                Add PC:
                Add PC <category> <cost> <name> <id> <status> <inventory> <dimensions> <weight> <CPUType> <RAMCapacity>

                Add Memory:
                Add Memory <category> <cost> <name> <id> <status> <inventory> <dimensions> <weight> <capacity> <USBVersion>

                Add SSD:
                Add SSD <category> <cost> <name> <id> <status> <inventory> <dimensions> <weight> <capacity> <readingSpeed> <writingSpeed>

                Add Pen:
                Add Pen <category> <cost> <name> <id> <status> <inventory> <country> <color>

                Add Pencil:
                Add Pencil <category> <cost> <name> <id> <status> <inventory> <country> <pencilType>

                Add NoteBook:
                Add NoteBook <category> <cost> <name> <id> <status> <inventory> <country> <pages> <paperType>

                Add Edible:
                Add Edible <category> <cost> <name> <id> <status> <inventory> <expiry> <productionDate>

                =========================
                """;
    }


    public void logout() {
        SessionManager.logout();
    }
}