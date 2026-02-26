package com.gumih.marta_delays_tracker.util;

public final class StationCodeUtil {
    private StationCodeUtil() {}

    private static String normalizeApiStationToCode(String apiStation) {
        if (apiStation == null) return "";

        String s = apiStation.trim().toUpperCase();

        // remove trailing " STATION"
        if (s.endsWith(" STATION")) {
            s = s.substring(0, s.length() - " STATION".length());
        }

        // normalize separators to underscore
        s = s.replace("/", " ");
        s = s.replace("-", " ");
        s = s.replace(".", " ");
        s = s.replaceAll("\\s+", " ").trim(); // collapse spaces
        s = s.replace(" ", "_");

        // special case: "FT" variations sometimes show as "FT" or "FORT"
        // (optional)
        // s = s.replace("FORT_", "FT_");

        return s;
    }




//    public static String toCode(String martaStationName) {
//        if (martaStationName == null) return null;
//
//        String s = martaStationName.trim().toUpperCase();
//
//        if (s.endsWith(" STATION")) {
//            s = s.substring(0, s.length() - " STATION".length());
//        }
//
//        s = s.replaceAll("[^A-Z0-9]+", "_");
//        s = s.replaceAll("^_+|_+$", "");
//        s = s.replaceAll("_+", "_");
//
//        return s;
//    }
}