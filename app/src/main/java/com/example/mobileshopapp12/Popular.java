package com.example.mobileshopapp12;

public class Popular {
    private String product_title,product_prize,product_image;

    public Popular() {
    }

    public Popular(String product_title, String product_prize, String product_image) {
        this.product_title = product_title;
        this.product_prize = product_prize;
        this.product_image = product_image;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_prize() {
        return product_prize;
    }

    public void setProduct_prize(String product_prize) {
        this.product_prize = product_prize;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

}
