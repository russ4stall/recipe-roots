package com.github.russ4stall.reciperoots.recipes;

/**
 * Date: 7/2/13
 * Time: 4:16 PM
 *
 * @author Russ Forstall
 */
public class Ingredient <Ingredient> {
    private String ingredient;
    private double quantity;
    private MeasurementType measurementType;

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasurementLabel() {
        return measurementType.getLabel(quantity);
    }


}
