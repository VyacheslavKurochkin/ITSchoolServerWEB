package ru.kurochkin.country;

import java.util.*;

public class Country {
    public static class Currency {
        public String code;
        public String name;
        public String symbol;

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;

            if (obj == null || this.getClass() != obj.getClass()) return false;

            Currency inputCurrency = (Currency) obj;

            return code.equals(inputCurrency.code);
        }

        @Override
        public int hashCode() {
            final int prime = 37;
            int hash = 1;
            hash = hash * prime + (code != null ? code.hashCode() : 0);
            return hash;
        }
    }

    public static class Language {
        public String iso639_1;
        public String iso639_2;
        public String name;
        public String nativeName;
    }

    public static class RegionalBloc {
        public String acronym;
        public String name;
        public ArrayList<String> otherAcronyms;
        public ArrayList<String> otherNames;
    }

    public static class Translations {
        public String de;
        public String es;
        public String fr;
        public String ja;
        public String it;
        public String br;
        public String pt;
        public String nl;
        public String hr;
        public String fa;
    }

    public String name;
    public ArrayList<String> topLevelDomain;
    public String alpha2Code;
    public String alpha3Code;
    public ArrayList<String> callingCodes;
    public String capital;
    public ArrayList<String> altSpellings;
    public String region;
    public String subregion;
    public int population;
    public ArrayList<Double> latlng;
    public String demonym;
    public double area;
    public double gini;
    public ArrayList<String> timezones;
    public ArrayList<String> borders;
    public String nativeName;
    public String numericCode;
    public ArrayList<Currency> currencies;
    public ArrayList<Language> languages;
    public Translations translations;
    public String flag;
    public ArrayList<RegionalBloc> regionalBlocs;
    public String cioc;
}