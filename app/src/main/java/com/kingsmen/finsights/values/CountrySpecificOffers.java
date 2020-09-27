package com.kingsmen.finsights.values;

import com.kingsmen.finsights.dao.Offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountrySpecificOffers {
    Map<String, List<Offer>> offers;

    public CountrySpecificOffers() {
        offers = new HashMap<>();
    }

    public Map<String, List<Offer>> getOffers() {
        return offers;
    }

    public void setOffers(Map<String, List<Offer>> offers) {
        this.offers = offers;
    }

    public void init() {
        // US offers
        Offer usFoodOffer = new Offer();
        usFoodOffer.setHeader("10% off on all food orders.");
        usFoodOffer.setDescription("10% off on all food orders.");
        usFoodOffer.setType("food");

        Offer usShoppingOffer = new Offer();
        usShoppingOffer.setHeader("5% off on all shops in Phoenix mall");
        usShoppingOffer.setDescription("5% off on all shops in Phoenix mall");
        usShoppingOffer.setType("shopping");

        List<Offer> usOffers = new ArrayList<Offer>();
        usOffers.add(usFoodOffer);
        usOffers.add(usShoppingOffer);

        // India offers
        Offer indiaFoodOffer = new Offer();
        indiaFoodOffer.setHeader("10% off on all swiggy orders.");
        indiaFoodOffer.setDescription("10% off on swiggy food orders.");
        indiaFoodOffer.setType("food");

        Offer indiaShoppingOffer = new Offer();
        indiaShoppingOffer.setHeader("15% off on all purchases in Shoppers Stop");
        indiaShoppingOffer.setDescription("5% off on all purchases in Shoppers Stop");
        indiaShoppingOffer.setType("shopping");

        List<Offer> indiaOffers = new ArrayList<Offer>();
        indiaOffers.add(indiaFoodOffer);
        indiaOffers.add(indiaShoppingOffer);

        this.offers.put("United States", usOffers);
        this.offers.put("India", indiaOffers);
    }
}
