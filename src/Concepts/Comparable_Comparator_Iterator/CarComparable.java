package Concepts.Comparable_Comparator_Iterator;

public class CarComparable implements Comparable<CarComparable>{
        private String brand;
        private int price;
        private int maxSpeed;

        public CarComparable(String brand, int price, int maxSpeed) {
            super();
            this.brand = brand;
            this.price = price;
            this.maxSpeed = maxSpeed;
        }

        public String getBrand() {return brand;}
        public void setBrand(String brand) {this.brand = brand;}
        public int getPrice() {return price;}
        public void setPrice(int price) {this.price = price;}
        public int getMaxSpeed() {return maxSpeed;}
        public void setMaxSpeed(int maxSpeed) {this.maxSpeed = maxSpeed;}

        @Override
        public String toString() {
            return "car{" + "brand='" + brand + '\'' + ", price=" + price + ", maxSpeed=" + maxSpeed + '}';}

        @Override
        public int compareTo(CarComparable c){   //must override the SAM compareTo with custom implementation
            //this.price > c.price
            return this.getPrice()>=c.getPrice() ? 1 : -1;  //despite passing in only 1 object, it compares the object that calls compareTo against the passed in object
        }

}
