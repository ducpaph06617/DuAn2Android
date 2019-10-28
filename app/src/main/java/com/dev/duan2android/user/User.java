package com.dev.duan2android.user;

public class User {

    public static class Info{
        public String name;
        public String phone;
        public String uri;
        public String address;
        public String email;
        public String gender;

        public Info() {
        }

        public Info(String name, String phone, String uri, String address, String email, String gender) {
            this.name = name;
            this.phone = phone;
            this.uri = uri;
            this.address = address;
            this.email = email;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
    public static class Product{

        public String nameshop;
        public String nameproduct;
        public String priceproduct;
        public String colorproduct;
        public String describe;
        public String idsp;
        public String uri;
        public String loaisp;
        //public String soluong;
        public String thoigian;
        public Product() {
        }

        public Product(String nameshop, String nameproduct, String priceproduct, String colorproduct, String describe, String idsp, String uri, String loaisp, String thoigian) {
            this.nameshop = nameshop;
            this.nameproduct = nameproduct;
            this.priceproduct = priceproduct;
            this.colorproduct = colorproduct;
            this.describe = describe;
            this.idsp = idsp;
            this.uri = uri;
            this.loaisp = loaisp;
            //this.soluong = soluong;
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

//        public String getSoluong() {
//            return soluong;
//        }
//
//        public void setSoluong(String soluong) {
//            this.soluong = soluong;
//        }

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
