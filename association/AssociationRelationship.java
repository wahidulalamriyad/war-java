package association;

// -------------------- CITY --------------------
class City {
    private String name; // City HAS a name

    public City(String name) { // Constructor
        this.name = name;
    }

    public String getName() { // Getter
        return name;
    }
}

// -------------------- STATE --------------------
class State {
    private String stateName; // State HAS a name
    private City[] cities; // State HAS many Cities
    private int count = 0; // number of cities in the state

    public State(String stateName, int numberOfCities) { // Constructor
        this.stateName = stateName;
        this.cities = new City[numberOfCities]; // fixed-size array
    }

    public void addCity(City city) { // Method
        if (count < cities.length) {
            cities[count] = city;
            count++;
        } else {
            System.out.println("Cannot add more cities to " + stateName);
        }
    }

    public void showCities() { // Method
        System.out.println("State: " + stateName);
        System.out.println("Cities:");
        for (int i = 0; i < count; i++) {
            System.out.println(" - " + cities[i].getName()); // print city name
        }
    }
}

// -------------------- ADDRESS --------------------
class Address {
    private String road; // Address HAS a road
    private String city; // Address HAS a city

    public Address(String road, String city) { // Constructor
        this.road = road;
        this.city = city;
    }

    public String toString() { // toString() method for readable output
        return road + ", " + city;
    }
}

// -------------------- CUSTOMER --------------------
class Customer {
    private String name; // Customer HAS a name
    private Address address; // Customer HAS an Address

    public Customer(String name, Address address) { // Constructor
        this.name = name;
        this.address = address;
    }

    public void showCustomerInfo() { // Method
        System.out.println("Customer Name: " + name);
        System.out.println("Address: " + address);
    }
}

// -------------------- MAIN --------------------
public class AssociationRelationship {
    public static void main(String[] args) {

        // ----------- Relationship 1: State has many Cities ----------
        State s = new State("California", 3); // we expect 3 cities
        s.addCity(new City("Los Angeles"));
        s.addCity(new City("San Diego"));
        s.addCity(new City("San Francisco"));

        s.showCities();
        System.out.println();

        // ----------- Relationship 2: Customer has an Address ----------
        Address addr = new Address("123 Main St", "Los Angeles");
        Customer c = new Customer("John Doe", addr);

        c.showCustomerInfo();
    }
}
