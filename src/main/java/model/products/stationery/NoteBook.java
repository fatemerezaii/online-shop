package model.products.stationery;

import model.Comment;
import enums.Category;

import java.util.List;

public class NoteBook extends Stationery{
    private int pages;
    private String paperType;

    public NoteBook(double averageRating, Category category, List<Comment> comments, double cost, String name, int productId, String status, String country, int pages, String paperType) {
        super(averageRating, category, comments, cost, name, productId, status, country);
        this.pages = pages;
        this.paperType = paperType;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }
}
