package oop.Iinner.a01Iinnerclassdemo1;

public class Car {
    String carName;
    int carAge;
    String carColor;

    public void show(Car this) {
        IO.println(this.carName);
        Engline e = new Engline();
        IO.println(e.engineName);
    }


    class Engline {
        String engineName;
        int engineAge;

        public void show() {
            System.out.println(engineName);
            System.out.println(carName);
        }
    }
}
