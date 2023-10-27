package com.nikitasutulov.lab4;

/**
 * The "Furniture" class represents furniture objects and contains methods for sorting by name (ascending) and cost (descending).
 */
class Furniture implements Comparable<Furniture> {
    private final String name;
    private final int cost;
    private final int weight;
    private final String material;
    private final int size;

    /**
     * Constructor for the "Furniture" class.
     *
     * @param name     The name of the furniture
     * @param cost     The cost of the furniture
     * @param weight   The weight of the furniture
     * @param material The material of the furniture
     * @param size     The size of the furniture
     */
    public Furniture(String name, int cost, int weight, String material, int size) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.material = material;
        this.size = size;
    }

    /**
     * Overridden compareTo method for sorting by cost.
     *
     * @param other The other "Furniture" object to compare to
     * @return The result of comparing the costs of the furniture
     */
    @Override
    public int compareTo(Furniture other) {
        return Integer.compare(this.cost, other.cost);
    }

    /**
     * Overridden toString method to represent the object as a string.
     *
     * @return A string representing the "Furniture" object
     */
    @Override
    public String toString() {
        return "Furniture { " +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", weight=" + weight +
                ", material='" + material + '\'' +
                ", size=" + size +
                " }";
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
