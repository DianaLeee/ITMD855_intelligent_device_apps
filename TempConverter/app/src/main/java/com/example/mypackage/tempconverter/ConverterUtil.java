package com.example.mypackage.tempconverter;

/**
 * Created by yeony_lee on 2017. 1. 17..
 */

public class ConverterUtil {
    /**
     * @param fahrenheit
     * @return
     */
    // converts to celsius
    public static double convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5.0 / 9.0);
    }

    /**
     * @param celsius
     * @return
     */
    // converts to fahrenheit
    public static double convertCelsiusToFahrenheit(float celsius) {
        return (celsius * (9 / 5.0)) + 32;
    }
}
