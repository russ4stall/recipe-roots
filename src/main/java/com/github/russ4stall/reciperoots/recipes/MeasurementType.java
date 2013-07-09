package com.github.russ4stall.reciperoots.recipes;

/**
 * Date: 7/2/13
 * Time: 4:30 PM
 *
 * @author Russ Forstall
 */
public enum
    MeasurementType {
    TEASPOON {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "teaspoon";
            } else {
                return "teaspoons";
            }
        }
    },
    TABLESPOON {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "tablespoon";
            } else {
                return "tablespoons";
            }
        }
    },
    CUP {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "cup";
            } else {
                return "cups";
            }
        }
    },
    PINT {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "pint";
            } else {
                return "pints";
            }
        }
    },
    QUART {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "quart";
            } else {
                return "quarts";
            }        }
    },
    LITER {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "liter";
            } else {
                return "liters";
            }
        }
    },

    GALLON {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "gallon";
            } else {
                return "gallons";
            }        }
    },
    OUNCE {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "ounce";
            } else {
                return "ounces";
            }
        }
    },
    POUND {
        @Override
        public String getLabel(double quantity) {
            if (quantity == 1) {
                return "pound";
            } else {
                return "pounds";
            }
        }
    },
    NONE {
        @Override
        public String getLabel(double quantity) {
            return null;
        }
    };

    public abstract String getLabel(double quantity);
}
