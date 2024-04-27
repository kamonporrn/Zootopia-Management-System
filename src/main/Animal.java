package main;
public class Animal {
    private String name;
    private String zone;
    private String animalType;
    private String description;

    public Animal(String name, String zone, String animalType, String description) {
        this.name = name;
        this.zone = zone;
        this.animalType = animalType;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", zone='" + zone + '\'' +
                ", animalType='" + animalType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

