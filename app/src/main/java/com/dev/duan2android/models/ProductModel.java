package com.dev.duan2android.models;

public class ProductModel {
    public static class Product{

        public String nameshop;
        public String nameproduct;
        public String priceproduct;
        public String colorproduct;

        public String describe;
        public String idsp;
        public String uri;
        public String loaisp;
        public String soluong;
        public String thoigian;
        public Product() {
        }

        public Product(String nameshop, String nameproduct, String priceproduct, String colorproduct, String describe, String idsp, String uri, String loaisp, String soluong, String thoigian) {
            this.nameshop = nameshop;
            this.nameproduct = nameproduct;
            this.priceproduct = priceproduct;
            this.colorproduct = colorproduct;
            this.describe = describe;
            this.idsp = idsp;
            this.uri = uri;
            this.loaisp = loaisp;
            this.soluong = soluong;
            this.thoigian = thoigian;
        }

        public String getNameshop() {
            return nameshop;
        }

        public void setNameshop(String nameshop) {
            this.nameshop = nameshop;
        }

        public String getNameproduct() {
            return nameproduct;
        }

        public void setNameproduct(String nameproduct) {
            this.nameproduct = nameproduct;
        }

        public String getPriceproduct() {
            return priceproduct;
        }

        public void setPriceproduct(String priceproduct) {
            this.priceproduct = priceproduct;
        }

        public String getColorproduct() {
            return colorproduct;
        }

        public void setColorproduct(String colorproduct) {
            this.colorproduct = colorproduct;
        }


        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getIdsp() {
            return idsp;
        }

        public void setIdsp(String idsp) {
            this.idsp = idsp;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getLoaisp() {
            return loaisp;
        }

        public void setLoaisp(String loaisp) {
            this.loaisp = loaisp;
        }

        public String getSoluong() {
            return soluong;
        }

        public void setSoluong(String soluong) {
            this.soluong = soluong;
        }

        public String getThoigian() {
            return thoigian;
        }

        public void setThoigian(String thoigian) {
            this.thoigian = thoigian;
        }
    }
    public static class Uriimg{
        private String uri;

        public Uriimg() {
        }

        public Uriimg(String uri) {
            this.uri = uri;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
    public static class Id{
        String id;

        public Id() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Id(String id) {

            this.id = id;
        }
    }

    public static class cartsp{
        public String idsp;
        public String soluong;

        public cartsp() {
        }

        public cartsp(String idsp, String soluong) {
            this.idsp = idsp;
            this.soluong = soluong;
        }

        public String getIdsp() {
            return idsp;
        }

        public void setIdsp(String idsp) {
            this.idsp = idsp;
        }

        public String getSoluong() {
            return soluong;
        }

        public void setSoluong(String soluong) {
            this.soluong = soluong;
        }
    }
}
