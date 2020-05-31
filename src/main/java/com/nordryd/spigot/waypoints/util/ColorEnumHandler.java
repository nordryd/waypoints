package com.nordryd.spigot.waypoints.util;

import org.bukkit.Color;

public interface ColorEnumHandler
{
    /**
     * <p>
     * Enum for different colors in terms of <b>RGB</b>.
     * </p>
     *
     * @author Nordryd
     */
    enum ParticleColor
    {
        WHITE(255, 255, 255),
        GRAY(100, 100, 100),
        BLACK(1, 1, 1),
        RED(255, 0, 0),
        GREEN(0, 255, 0),
        BLUE(0, 0, 255),
        CYAN(0, 255, 255),
        MAGENTA(255, 0, 255),
        YELLOW(255, 255, 0),
        GOLDENROD(218, 165, 32),
        LIGHT_GOLDENROD(238, 221, 130),
        LIGHT_BLUE(173, 216, 230),
        LIGHT_SKY_BLUE(135, 206, 250),
        ROYAL_BLUE(65, 105, 225),
        IVORY(255, 255, 240),
        LAVENDER(230, 230, 250),
        RED_VIOLET(208, 32, 144),
        VIOLET(160, 32, 240),
        SPRING_GREEN(0, 255, 127),
        SEA_GREEN(46, 139, 87),
        GOLD(255, 215, 0),
        CORNSILK(205, 200, 177),
        ORANGE(255, 165, 0),
        TOMATO(255, 99, 71),
        FIREBRICK(178, 34, 34);

        private final int red, green, blue;

        ParticleColor(int red, int green, int blue) {
            this.red = red;
            this.blue = blue;
            this.green = green;
        }

        /**
         * @return <b>Red</b> value.
         */
        public int getRed() {
            return red;
        }

        /**
         * @return <b>Green</b> value.
         */
        public int getGreen() {
            return green;
        }

        /**
         * @return <b>Blue</b> value.
         */
        public int getBlue() {
            return blue;
        }

        /**
         * @return Normalized <b>red</b> value &isin; [0, 1].
         */
        public double getNormalizedRed() {
            return red / 255.0;
        }

        /**
         * @return Normalized <b>red</b> value &isin; [0, 1].
         */
        public double getNormalizedGreen() {
            return green / 255.0;
        }

        /**
         * @return Normalized <b>red</b> value &isin; [0, 1].
         */
        public double getNormalizedBlue() {
            return blue / 255.0;
        }

        /**
         * @return {@link Color} object based on the {@link ParticleColor}'s RGB values.
         */
        public Color getColorFromRGB() {
            return Color.fromRGB(red, green, blue);
        }
    }

    enum ANSIColor
    {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m");

        private final String seq;

        ANSIColor(String seq) {
            this.seq = seq;
        }

        @Override
        public String toString() {
            return this.seq;
        }
    }
}
