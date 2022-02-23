package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet {

    float[] rainfall = { 45, 37, 55, 27, 38, 50, 79, 48, 104, 31, 100, 58 };

    String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

    float[] a1 = new float[100];
    float[] a2;

    int minIndex = 0;
    int maxIndex = 0;

    int mode = 0;

    int sizex = 500;

    int sizey = 500;

    float sum(float[] array) {
        float sum = 0;
        for (float r : array) {
            sum += r;
        }
        return sum;
    }

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        println(mode);
    }

    public void settings() {
        size(sizex, sizey);
    }

    public void setup() {
        for (int i = 0; i < rainfall.length; i++) {
            println(rainfall[i] + "\t" + months[i]);
        }
        for (float r : rainfall) {
            println(r);
        }

        int j = 0;
        for (float r : rainfall) {
            println(r + "\t" + months[j]);
            j++;
        }

        for (int i = rainfall.length - 1; i >= 0; i--) {
            println(rainfall[i] + "\t" + months[i]);
        }

        for (int i = 1; i < rainfall.length; i++) {
            if (rainfall[i] < rainfall[minIndex]) {
                minIndex = i;
            } else if (rainfall[i] > rainfall[maxIndex]) {
                maxIndex = i;
            }
        }

        println("Max rainfall: " + rainfall[maxIndex] + " in month " + months[maxIndex]);
        println("Min rainfall: " + rainfall[minIndex] + " in month " + months[minIndex]);

    }

    public void draw() {
        switch (mode) {
            case 0: {
                background(0);
                colorMode(HSB);
                textAlign(CENTER, CENTER);
                float w = width / (float) rainfall.length;
                noStroke();
                for (int i = 0; i < rainfall.length; i++) {
                    float x = map(i, 0, rainfall.length, 0, width);
                    int c = (int) map(i, 0, rainfall.length, 0, 255);
                    fill(c, 255, 255);
                    float h = map(rainfall[i], 0, rainfall[maxIndex], 0, -height);
                    rect(x, height, w, h);
                    fill(255);
                    textAlign(CENTER, CENTER);
                    text(months[i], x + (w / 2), height - 50);
                }
                break;
            }
            case 1: {
                background(0);
                // Bar chart
                // Draw the axis
                float graphBorder = width * 0.1f;
                float range = 120;
                stroke(255);
                line(graphBorder, graphBorder, graphBorder, height - graphBorder);
                line(graphBorder, height - graphBorder, width - graphBorder, height - graphBorder);

                // Y axis labels
                textAlign(CENTER, CENTER);
                colorMode(HSB);
                for (float f = 0; f <= range; f += 10) {
                    float y = map(f, 0, range, height - graphBorder, graphBorder);
                    line(graphBorder - 5, y, graphBorder, y);
                    fill(255);
                    text((int) f, graphBorder * 0.5f, y);
                }

                // X axis labels
                float w = (width - (graphBorder * 2)) / rainfall.length;
                for (int i = 0; i < rainfall.length; i++) {
                    float x = map(i, 0, rainfall.length, graphBorder + 1, width - graphBorder);
                    text(months[i], x + (w * 0.5f), height - (graphBorder * 0.5f));
                }

                // Bars
                for (int i = 0; i < rainfall.length; i++) {
                    float x = map(i, 0, rainfall.length, graphBorder + 1, width - graphBorder);
                    float h = map(rainfall[i], 0, range, 0, height - (graphBorder * 2));
                    float c = map(i, 0, rainfall.length, 0, 255);
                    fill(c, 255, 255);
                    rect(x, height - graphBorder - 1, w, -h);
                    fill(255);
                }
                fill(255);
                text("Rainfall Bar Chart", width / 2, graphBorder / 2);
                break;
            }
            case 2: {
                background(0);
                // Line Graph
                // Draw the axis
                float graphBorder = width * 0.1f;
                float range = 120;
                stroke(255);
                line(graphBorder, graphBorder, graphBorder, height - graphBorder);
                line(graphBorder, height - graphBorder, width - graphBorder, height - graphBorder);

                // Y axis labels
                textAlign(CENTER, CENTER);
                colorMode(HSB);
                for (float f = 0; f <= range; f += 10) {
                    float y = map(f, 0, range, height - graphBorder, graphBorder);
                    line(graphBorder - 5, y, graphBorder, y);
                    fill(255);
                    text((int) f, graphBorder * 0.5f, y);
                }

                // X axis labels
                float w = (width - (graphBorder * 2)) / rainfall.length;
                for (int i = 0; i < rainfall.length; i++) {
                    float x = map(i, 0, rainfall.length, graphBorder + 1, width - graphBorder);
                    float c = map(i, 0, rainfall.length, 0, 255);
                    fill(c, 255, 255);
                    fill(255);
                    text(months[i], x + (w * 0.5f), height - (graphBorder * 0.5f));
                }

                // Lines

                for (int i = 1; i < rainfall.length; i++) {
                    float x1 = map(i - 1, 0, rainfall.length - 1, graphBorder + (w / 2), width - graphBorder - (w / 2));
                    float y1 = map(rainfall[i - 1], 0, range, height - graphBorder, graphBorder);
                    float x2 = map(i, 0, rainfall.length - 1, graphBorder + (w / 2), width - graphBorder - (w / 2));
                    float y2 = map(rainfall[i], 0, range, height - graphBorder, graphBorder);

                    line(x1, y1, x2, y2);
                }
                fill(255);
                text("Rainfall line graph", width / 2, graphBorder / 2);
                break;
            }

            case 3: {
                background(0);
                float graphBorder = width * 0.1f;
                float sum = sum(rainfall);
                float prevTheta = 0;
                float cx = width / 2;
                float cy = height / 2;

                for(int i = 0; i < rainfall.length; i++){
                    //Declare theta
                    float theta = map(rainfall[i], 0, sum, 0, TWO_PI);
                    textAlign(CENTER,CENTER);

                    //Set radius of the pie chart
                    float radius = cx * 0.6f;

                    //Calculate theta of the following segment
                    float nextTheta = prevTheta + theta;
                    float x = cx + sin (prevTheta + (theta * 0.5f) + HALF_PI) * radius;
                    float y = cy - cos (prevTheta + (theta * 0.5f) + HALF_PI) * radius;
                    fill(255);
                    text(months[i], x, y);
                    float colour = map(i, 0, rainfall.length, 0, 255);
                    fill(colour, 255, 255);
                    arc(cx, cy, cx, cy, prevTheta, nextTheta);
                    prevTheta = nextTheta;
                }

                fill(255);
                text("Rainfall Pie Chart", width/2, graphBorder/2);
            }
        }
    }
}
