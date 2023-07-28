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
public class BirdDTO {
    private int bird_id;
    private String bird_name;
    private int product_id;
    private String product_name;
    private String desc_order;
    private String species;
    private String bird_images;

    public BirdDTO() {
    }

    public BirdDTO(int bird_id, String bird_name, int product_id, String product_name,String desc_order, String species, String bird_images) {
        this.bird_id = bird_id;
        this.bird_name = bird_name;
        this.product_id = product_id;
        this.product_name = product_name;
        this.desc_order = desc_order;
        this.species = species;
        this.bird_images = bird_images;
    }

    public String getDesc_order() {
        return desc_order;
    }

    public void setDesc_order(String desc_order) {
        this.desc_order = desc_order;
    }

    public int getBird_id() {
        return bird_id;
    }

    public void setBird_id(int bird_id) {
        this.bird_id = bird_id;
    }

    public String getBird_name() {
        return bird_name;
    }

    public void setBird_name(String bird_name) {
        this.bird_name = bird_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBird_images() {
        return bird_images;
    }

    public void setBird_images(String bird_images) {
        this.bird_images = bird_images;
    }
    
}
