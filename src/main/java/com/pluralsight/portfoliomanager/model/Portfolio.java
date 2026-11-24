package com.pluralsight.portfoliomanager.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Portfolio {
    private String name;
    private String owner;
    private ArrayList<Valuable> assets;

    public Portfolio(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.assets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<Valuable> getAssets() {
        return assets;
    }

    public void add(Valuable asset) {
        assets.add(asset);
    }

    public BigDecimal getValue() {
        BigDecimal total = BigDecimal.ZERO;

        for (Valuable asset : assets) {
            total = total.add(asset.getValue());
        }

        return total;
    }

    public Valuable getMostValuable() {
        if (assets.size() == 0) {
            return null;
        }

        Valuable mostValuable = assets.get(0);

        for (Valuable asset : assets) {
            if (asset.getValue().compareTo(mostValuable.getValue()) > 0) {
                mostValuable = asset;
            }
        }

        return mostValuable;
    }

    public Valuable getLeastValuable() {
        if (assets.size() == 0) {
            return null;
        }

        Valuable leastValuable = assets.get(0);

        for (Valuable asset : assets) {
            if (asset.getValue().compareTo(leastValuable.getValue()) < 0) {
                leastValuable = asset;
            }
        }

        return leastValuable;
    }
}
