/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author ADMIN
 */
public class GetProductToFeedbackDTO {
   private int product_name;
   private String images;

    public int getProduct_name() {
        return product_name;
    }

    public void setProduct_name(int product_name) {
        this.product_name = product_name;
    }

    public GetProductToFeedbackDTO(int product_name, String images) {
        this.product_name = product_name;
        this.images = images;
    }

  

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
   
}
