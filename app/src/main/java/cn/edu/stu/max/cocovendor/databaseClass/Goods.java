package cn.edu.stu.max.cocovendor.databaseClass;

import org.litepal.crud.DataSupport;

public class Goods extends DataSupport{
    //商品id
    private int id;
    //商品名称
    private String name;
    //商品成本价
    private float cost_price;
    //商品销售价
    private float sales_price;
    //商品图片存放路径
    private String image_path;
    //商品库存数量
    private int quanlity;
    //商品条形码
    private String barcode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost_price() {
        return cost_price;
    }

    public void setCost_price(float cost_price) {
        this.cost_price = cost_price;
    }

    public float getSales_price() {
        return sales_price;
    }

    public void setSales_price(float sales_price) {
        this.sales_price = sales_price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
